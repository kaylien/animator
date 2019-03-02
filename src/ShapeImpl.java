import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public abstract class ShapeImpl implements ShapeInt{
  private int t;
  private Color color;
  private Position position;
  private Dimension dimension;
  private TreeMap<Integer, Command> commands;
  private int appears, disappears;

  /**
   *
   * @param x
   * @param y
   * @param w
   * @param h
   * @param r
   * @param g
   * @param b
   */
  ShapeImpl(int x, int y, int w, int h, int r, int g, int b, int appears, int disappears) {

    if (x < 0 || y < 0 || w < 0 || h < 0 || r < 0 || g < 0 || b < 0 || appears < 0 ||
    disappears < 0) {
      throw new IllegalArgumentException("Cannot Use Negative Numbers");
    }

    this.color = new Color(r, g, b);
    this.position = new Position(x, y);
    this.dimension = new Dimension(w, h);
    commands = new TreeMap<>();
    // Going to add a new shape to the model, and it automatically creates an command for
    // initialization then you can pick a shape to continue to add commands to
    Command cmd = new Command(x, y, w, h, r, g, b, appears, disappears);
    commands.put(appears, cmd);
    commands.put(disappears, cmd);
  }

  // Copy constructor
  ShapeImpl(ShapeImpl shape) {
    this.color = shape.color;
    this.position = shape.position;
    this.dimension = shape.dimension;
  }

  public static void main(String[] args) {
    ShapeImpl s = new Rectangle(0, 200, 50, 100, 255, 0,  0, 50,
    70);
    Command c = new Command(255, 200, 50, 100, 255, 0, 0, 51, 80);
    Command c2 = new Command(200, 200, 50, 100, 255, 0, 0, 81, 90);
    Command c3 = new Command(255, 200, 50, 100, 0, 0, 0, 82, 95);
//    Command c
//    s.addCommands(c);
    System.out.println(s.validCommand(c));
    s.addCommand(c);
    System.out.println("First command list: \n" + s.getCommands());
    s.addCommand(c2);
    System.out.println("Second command list: \n" + s.getCommands());
    s.addCommand(c3);
    System.out.println("Third command list: \n" + s.getCommands());


    //TODO: If a command is added that exceeds the given "appear" and "disappear" ticks, then you
    //TODO: can delete the command at the prior appear or disappear ticks
  }

  /**
   * Puts in command with a key at the start and end time.
   * @param c
   */
   void addCommand (Command c) {
    if (c == null) {
      throw new IllegalArgumentException("Command is null");
    }

    int key = c.getT();
    int endKey = c.getEt();
    Command startCommand;

    if (!(validCommand(c))) {
      throw new IllegalArgumentException("Invalid command");
    }

//    if (key <= commands.firstKey()) {
//      commands.remove(commands.firstKey());
//    }
//
//    if (key >= commands.lastKey()) {
//      commands.remove(commands.lastKey());
//    }

    isCommandExisting(key, endKey);

    //TODO: Abstract this as a command?
    try {
      startCommand = new Command(commands.floorEntry(key).getValue());
    } catch (NullPointerException e) {
      startCommand = new Command(commands.firstEntry().getValue());
    }
    startCommand.setT(key);
    startCommand.setEt(endKey);

    commands.put(endKey, c);
    commands.put(key, startCommand);
    fixCommands(key, endKey);
  }

  /**
   * Changing the variables in between the new command and its end time to reflect the mid-stages
   * of the mutating variable values
   *
   * Additionally, after the end time, the start point of the changed variable for the following
   * commands will have to be altered to avoid things like 'suddenly' transporting across the grid
   * or a random change of color. Once another command begins changing that variable, stop changing
   * the start point
   * @param startTime
   * @param endTime
   */
  private void fixCommands(int startTime, int endTime) {
    NavigableMap<Integer, Command> commandList = commands.subMap(startTime,true,
      endTime, true);

    Command startCmd = commands.get(startTime);
    Command endCmd = commands.get(endTime);
    List<Variable> varsToChange = whatVarsChanging(startCmd, endCmd);

    System.out.println(startCmd.toStringV2() + "\n" + endCmd.toStringV2());
    System.out.println(startTime + " " + endTime);
    System.out.println(varsToChange);

    int deltaTime = endTime - startTime;
    Integer firstKey = commandList.firstKey();
    Integer priorKey = firstKey;
    Integer key = commandList.higherKey(firstKey);
    Command add = commandList.get(firstKey);
    System.out.println("firstkey: " + key);
    Color newColor;
    Position newPosition;
    Dimension newDimension;

    while (!(commandList.higherKey(key) == null)) {
      Command cmd = new Command(commandList.get(key));
      int nextKey = commandList.higherKey(key);
      System.out.println("Next key:" + nextKey + " key: " + key + " deltatime: " + deltaTime);
      float deltaKey = key - priorKey;
//      System.out.println(deltaKey);
      float multiplier = deltaKey / deltaTime;
      System.out.println(multiplier);

      if (varsToChange.contains(Variable.COLOR)) {
        Float[] colorDiff = endCmd.getColor().colorDifference(startCmd.getColor());
        newColor = updateToColor(colorDiff, multiplier, add.getColor());
        cmd.setColor(newColor);
      }

      if (varsToChange.contains(Variable.POSITION)) {
        Float[] positionDiff = endCmd.getPosition().positionDifference(startCmd.getPosition());
//        System.out.println(positionDiff[0] + " " + positionDiff[1] + " Multiplier: " + multiplier);
        newPosition = updateToPosition(positionDiff, multiplier,
          add.getPosition());
        cmd.setPosition(newPosition);
      }

      if (varsToChange.contains(Variable.DIMENSION)) {
        Float[] dimensionDiff = endCmd.getPosition().positionDifference(startCmd.getPosition());
        newDimension = updateToDimension(dimensionDiff, multiplier, add.getDimension());
        cmd.setDimension(newDimension);
      }

      commands.put(key, cmd);
      priorKey = key;
      add = cmd;
      key = nextKey;
    }
  }

  public Color updateToColor(Float[] array, float multiplier, Color nextKey) {

    float changeR = multiplier * array[0] + nextKey.getR();
    float changeG = multiplier * array[1] + nextKey.getG();
    float changeB = multiplier * array[2] + nextKey.getB();

    return new Color(changeR, changeG, changeB);
  }


  public Position updateToPosition(Float[] array, float multiplier, Position nextKey) {

    System.out.println("Multiplier: " + multiplier + " X: " + array[0]);
    float changeX = (multiplier * array[0]) + nextKey.getX();
    float changeY = (multiplier * array[1]) + nextKey.getY();

    System.out.println(changeX + " " + changeY);

    return new Position(changeX, changeY);
  }


  public Dimension updateToDimension(Float[] array, float multiplier, Dimension nextKey) {

    float changeW = multiplier * array[0] + nextKey.getW();
    float changeH = multiplier * array[1] + nextKey.getH();
    System.out.println(changeW + " " + changeH);

    return new Dimension(changeW, changeH);
  }

//  public void addCommands(Command... commands) {
//    for(Command command: commands) {
//      addCommand(command);
//    }
//  }
  

  public String getCommands() {
    StringBuilder sb = new StringBuilder();
    Collection<Command> values = commands.values();
    Set<Integer> keys = commands.keySet();
    for (Integer key : keys) {
      sb.append(key);
      sb.append(" ");
      sb.append(commands.get(key).toStringV2());
      sb.append("\n");
    }

    //TODO: Edit this, just testing rn
//    for (Command c : values) {
//      sb.append(c.toStringV2());
//      sb.append("\n");
//    }

    return sb.toString();
  }

  private enum Variable {
    COLOR, DIMENSION, POSITION,INVALID; //Added Invalid for varsChanging function
  }

  /**
   * A command is only invalid if there is an existing command that is changing the same variable
   * in the same time frame.
   *
   * It's iterating through the existing list of commands, to check if the ones with an overlapping
   * time frame are changing the same variables. Each command stores an end time, so what's
   * determining the changing variables are comparing that start command to its end command.
   *
   * OK for the context of this assignment, the user will add all of their commands at once
   * @param c
   * @return
   */
   boolean validCommand(Command c) {
    int cKey = c.getT();

    Command startCommand1;
    Collection<Command> commandList = commands.values();

    // Trying to find the variables changing between the existing state of the shape at the start
    // time, and comparing it to the given command
     // If the floor entry doesn't exist, it's bc this new command is outside of the given range
     //upon creation of the shape. However, we'll just use the first entry as the comparator
    try {
      startCommand1 = commands.floorEntry(cKey).getValue();
    } catch (NullPointerException e) {
      startCommand1 = commands.firstEntry().getValue();
    }

    List<Variable> v2 = whatVarsChanging(startCommand1, c);

    for (Command command : commandList) {
      List<Variable> v1 = whatVarsChanging(command, commands.get(command.getEt()));
//      System.out.println(isSameTimeFrame(command, c));
//      System.out.println("isChangingSameVar: " + isChangingSameVar(v1, v2));

      if (isSameTimeFrame(command, c) && isChangingSameVar(v1, v2)) {
        return false;
      }
    }

    return true;
  }

  private void isCommandExisting(int startTime, int endTime) {
     if (!(commands.get(startTime) == null) || !(commands.get(endTime) == null)) {
       throw new IllegalArgumentException("There is a command that exists at that tick already.");
     }
  }

  private boolean isChangingSameVar(List<Variable> v1, List<Variable> v2) {
    return v1.contains(Variable.COLOR) && v2.contains(Variable.COLOR) ||
      v1.contains(Variable.POSITION) && v2.contains(Variable.POSITION) ||
      v1.contains(Variable.DIMENSION) && v2.contains(Variable.DIMENSION);
  }
/**
  static private List<Variable> whatVarsChanging(Command c1, Command c2) {
    List<Variable> list = new ArrayList<>();
    if(c1.getColor() != c2.getColor()) {
      list.add(Variable.COLOR);
    }
    if(c1.getPosition() != c2.getPosition()) {
      list.add(Variable.POSITION);
    }
    if(c1.getDimension() != c2.getDimension()) {
      list.add(Variable.DIMENSION);
    }
    return list;
  }
*/
  /**
  public void deleteCommands(Command... command) {
    int n = command.length;
    for (int i = 0; i< 0; i++) {

    }
  }
   */

  private boolean isSameTimeFrame(Command c1, Command c2) {
    int c1st = c1.getT();
    int c1et = c1.getEt();
    int c2st = c2.getT();
    int c2et = c2.getEt();


    return (c1et >= c2st && c1st <= c2st) || (c1st >= c2st && c1st <= c2et) ||
      (c1st >= c2st && c1et < c2et) || (c1st <= c2st && c1et >= c2et);
  }

  static private List<Variable> whatVarsChanging(Command c1, Command c2) {
    List<Variable> list = new ArrayList<>();
    if(!c1.getColor().equals(c2.getColor())) {
      list.add(Variable.COLOR);
    }
    if(!c1.getPosition().equals(c2.getPosition())) {
      list.add(Variable.POSITION);
    }
    if(!c1.getDimension().equals(c2.getDimension())) {
      list.add(Variable.DIMENSION);
    }

    return list;
  }

}
