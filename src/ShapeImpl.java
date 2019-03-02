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
    // Going to add a new shape to the model, and it automatically creates an initialize command
    // then you can pick a shape to continue to add commands to
    Command cmd = new Command(x, y, w, h, r, g, b, appears, 0);
    commands.put(appears, cmd);
  }

  // Copy constructor
  ShapeImpl(ShapeImpl shape) {
    this.color = shape.color;
    this.position = shape.position;
    this.dimension = shape.dimension;
  }

  public static void main(String[] args) {
    ShapeImpl s = new Rectangle(200, 200, 50, 100, 255, 0,  0, 5,
    80);
//    Command c = new Command(20, 30, 40, 10, 100, 200, 100, 1, 10);
//    s.addCommands(c);
    System.out.println(s.getCommands());
  }

  /**
   * Puts in command with a key at the start and end time.
   * @param c
   */
  private void addCommand (Command c) {
    if (c == null) {
      throw new IllegalArgumentException("Command is null");
    }
    int key = c.getT();
    int endKey = c.getEt();

    if (!(validCommand(c))) {
      throw new IllegalArgumentException("Invalid command");
    }

//    if (commands.isEmpty()) {
//      commands.put(key, new Command(position.getX(), position.getY(), dimension.getW(),
//        dimension.getH(), color.getR(), color.getG(), color.getB(), key, endKey));
//    }

    commands.put(endKey, c);
//    fixCommands(key, endKey);
  }

  private void fixCommands(int startTime, int endTime) {
    NavigableMap<Integer, Command> commandList = commands.subMap(startTime,true,
      endTime, true);
    Set<Integer> keys = commandList.keySet();
    Command startCmd = commands.get(startTime);
    Command endCmd = commands.get(endTime);
    List<Variable> varsToChange = whatVarsChanging(startCmd, endCmd);
    int deltaTime = endTime - startTime;

    for (Integer key : keys) {
      Command cmd = commandList.get(key);
      if (commandList.higherKey(key) == null) {
        return;
      }
      int keyDiff = key - commandList.higherKey(key);
      float multiplier = keyDiff / deltaTime;

      if (varsToChange.contains(Variable.COLOR)) {
        Float[] colorDiff = startCmd.getColor().colorDifference(endCmd.getColor());
        Color newColor = updateToColor(colorDiff, multiplier);
        cmd.setColor(newColor);
        commands.put(key, cmd);
      }

      if (varsToChange.contains(Variable.POSITION)) {
        Float[] positionDiff = startCmd.getPosition().positionDifference(endCmd.getPosition());
        Position newPosition = updateToPosition(positionDiff, multiplier);
        cmd.setPosition(newPosition);
        commands.put(key, cmd);
      }

      if (varsToChange.contains(Variable.DIMENSION)) {
        Float[] dimensionDiff = startCmd.getPosition().positionDifference(endCmd.getPosition());
        Dimension newDimension = updateToDimension(dimensionDiff, multiplier);
        cmd.setDimension(newDimension);
        commands.put(key, cmd);
      }
    }
  }

  public Color updateToColor(Float[] array, float multiplier) {

    float changeR = multiplier * array[0];
    float changeG = multiplier * array[1];
    float changeB = multiplier * array[2];

    return new Color(changeR, changeG, changeB);
  }


  public Position updateToPosition(Float[] array, float multiplier) {

    float changeW = multiplier * array[0];
    float changeH = multiplier * array[1];

    return new Position(changeW, changeH);
  }


  public Dimension updateToDimension(Float[] array, float multiplier) {

    float changeX = multiplier * array[0];
    float changeY = multiplier * array[1];

    return new Dimension(changeX, changeY);
  }

  public void addCommands(Command... commands) {
    for(Command command: commands) {
      addCommand(command);
    }
  }
  

  public String getCommands() {
    StringBuilder sb = new StringBuilder();
    Collection<Command> values = commands.values();
    for (Command c : values) {
      sb.append(c.toStringV2());
      sb.append("\n");
    }
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
   * @param c
   * @return
   */
  private boolean validCommand(Command c) {
    //getting the endstate
    Command endCmd = commands.get(c.getEt());
    if (endCmd == null) {
      return true;
    }

    List<Variable> v2 = whatVarsChanging(c, commands.get(c.getEt()));

    for (int key = 0; key < commands.size() - 1; key++) {
      Command command = commands.get(key);
      Command nextCmd = commands.get(key + 1);
      List<Variable> v1 = whatVarsChanging(command, nextCmd);
      
      if (isSameTimeFrame(command, c) && isChangingSameVar(v1, v2)) {
        return false;
      }
    }
    return true;
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

    return
      (c1st >= c1st && c1et <= c2et)  //
        || (c1st >= c2st && c1et >= c2et)
        || (c1st <= c2st && c1et >= c2et)
        || (c1st <= c2st && c1et <= c2et);
  }

  static private List<Variable> whatVarsChanging(Command c1, Command c2) {
    List<Variable> list = new ArrayList<>();
    if(!c1.getColor().equals(c2.getColor())) {
      list.add(Variable.COLOR);
    }

    if(c1.getColor().equals(c2.getColor())) {
      list.add(Variable.INVALID);
    }

    if(!c1.getPosition().equals(c2.getPosition())) {
      list.add(Variable.POSITION);
    }

    if(c1.getPosition().equals(c2.getPosition())) {
      list.add(Variable.INVALID);
    }

    if(!c1.getDimension().equals(c2.getDimension())) {
      list.add(Variable.DIMENSION);
    }

    if(c1.getDimension().equals(c2.getDimension())) {
      list.add(Variable.INVALID);
    }
    return list;
  }

}
