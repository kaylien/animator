import java.util.TreeMap;
import java.util.List;
import java.util.NavigableMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Class Representation of an animatable shape.
 * Invariants:
 * - No negative arguments (ENFORCED by constructor)
 * - Color values between 0 and 255 (ENFORCED by using the Color class)
 * - Commands cannot have conflicting variable changes (ENFORCED BY fixed command function)
 */
public abstract class ShapeImpl implements ShapeInt {

  private int t;
  private Color color;
  private Position position;
  private Dimension dimension;
  private TreeMap<Integer, Command> commands;
  private int appears;
  private int disappears;

  /**
   * Constructor for ShapeImpl.
   * @param x x coordinate.
   * @param y y coordinate.
   * @param w width of the shape.
   * @param h height of the shape.
   * @param r red value of the shape.
   * @param g green value of the shape.
   * @param b blue value of the shape.
   */

  ShapeImpl(int x, int y, int w, int h, int r, int g, int b, int appears, int disappears) {

    if (x < 0 || y < 0 || w < 0 || h < 0 || r < 0 || g < 0 || b < 0 || appears < 0
        || disappears < 0) {
      throw new IllegalArgumentException("Cannot Use Negative Numbers");
    }

    this.color = new Color(r, g, b);
    this.position = new Position(x, y);
    this.dimension = new Dimension(w, h);
    commands = new TreeMap<>();
    Command cmd = new Command(x, y, w, h, r, g, b, appears, disappears);
    commands.put(appears, cmd);
    commands.put(disappears, cmd);
  }

  /**
   * Copy Constructor for Shape.
   * @param shape to be copied.
   */
  ShapeImpl(ShapeImpl shape) {
    this.color = shape.color;
    this.position = shape.position;
    this.dimension = shape.dimension;
  }

  /**
   * Checks a command and the previous command to see what variables are changing.
   * @param c1 previous command
   * @param c2 current command
   * @return List of Variables that are changing in the given time period.
   */
  private static List<Variable> whatVarsChanging(Command c1, Command c2) {
    List<Variable> list = new ArrayList<>();
    if (!c1.getColor().equals(c2.getColor())) {
      list.add(Variable.COLOR);
    }
    if (!c1.getPosition().equals(c2.getPosition())) {
      list.add(Variable.POSITION);
    }
    if (!c1.getDimension().equals(c2.getDimension())) {
      list.add(Variable.DIMENSION);
    }

    return list;
  }

  /**
   * Puts in command with a key at the start and end time.
   * @param c command to be added.
   */
  public void addCommand(Command c) {
    if (c == null) {
      throw new IllegalArgumentException("Command is null");
    }
    int key = c.getT();
    int endKey = c.getEt();
    Command startCommand;
    if (!(validCommand(c))) {
      throw new IllegalArgumentException("Invalid command");
    }
    isCommandExisting(key, endKey);
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
   * of the mutating variable values.
   * <p>Additionally, after the end time, the start point of the changed variable for the following
   * commands will have to be altered to avoid things like 'suddenly' transporting across the grid
   * or a random change of color. Once another command begins changing that variable, stop changing
   * the start point.</p>
   *
   * @param startTime start time of the command to be fixed.
   * @param endTime endtime of the command to be fixed.
   */
  private void fixCommands(int startTime, int endTime) {
    NavigableMap<Integer, Command> commandList = commands.subMap(startTime, true, endTime, true);

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

    while (commandList.higherKey(key) != null) {
      Command cmd = new Command(commandList.get(key));
      int nextKey = commandList.higherKey(key);
      System.out.println("Next key:" + nextKey + " key: " + key + " deltatime: " + deltaTime);
      float deltaKey = key - priorKey;
      float multiplier = deltaKey / deltaTime;
      System.out.println(multiplier);

      if (varsToChange.contains(Variable.COLOR)) {
        Float[] colorDiff = endCmd.getColor().colorDifference(startCmd.getColor());
        newColor = updateToColor(colorDiff, multiplier, add.getColor());
        cmd.setColor(newColor);
      }

      if (varsToChange.contains(Variable.POSITION)) {
        Float[] positionDiff = endCmd.getPosition().positionDifference(startCmd.getPosition());
        newPosition = updateToPosition(positionDiff, multiplier, add.getPosition());
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

  /**
   * Updates a color according to an array of differences by tick.
   * @param array list of value differences
   * @param multiplier the value to muliply the value differences
   * @param nextKey retrieves the previous keys color
   * @return Color
   */
  private Color updateToColor(Float[] array, float multiplier, Color nextKey) {

    float changeR = multiplier * array[0] + nextKey.getR();
    float changeG = multiplier * array[1] + nextKey.getG();
    float changeB = multiplier * array[2] + nextKey.getB();

    return new Color(changeR, changeG, changeB);
  }

  /**
   * Updates a position according to an array of differences by tick.
   * @param array list of value differences
   * @param multiplier the value to muliply the value differences
   * @param nextKey retrieves the previous keys position
   * @return Position
   */
  private Position updateToPosition(Float[] array, float multiplier, Position nextKey) {

    System.out.println("Multiplier: " + multiplier + " X: " + array[0]);
    float changeX = (multiplier * array[0]) + nextKey.getX();
    float changeY = (multiplier * array[1]) + nextKey.getY();

    System.out.println(changeX + " " + changeY);

    return new Position(changeX, changeY);
  }

  /**
   * Updates a dimension according to an array of differences by tick.
   * @param array list of value differences
   * @param multiplier the value to muliply the value differences
   * @param nextKey retrieves the previous keys dimension
   * @return dimension
   */
  private Dimension updateToDimension(Float[] array, float multiplier, Dimension nextKey) {

    float changeW = multiplier * array[0] + nextKey.getW();
    float changeH = multiplier * array[1] + nextKey.getH();
    System.out.println(changeW + " " + changeH);

    return new Dimension(changeW, changeH);
  }

  /**
   * Adds commands to the current shape.
   * @param commands the list of commands to be executed.
   */
  public void addCommands(Command... commands) {
    for (Command command : commands) {
      addCommand(command);
    }
  }

  /**
   * Returns all the commands of a shape.
   * @return String of all commands of a shape.
   */
  public String getCommands() {
    StringBuilder sb = new StringBuilder();
    Collection<Command> values = commands.values();
    Set<Integer> keys = commands.keySet();
    Integer key = commands.firstKey();
    while (commands.higherKey(key) != null) {
      int nextKey = commands.higherKey(key);
      sb.append(String.format("%-4s ", key.toString()));
      sb.append(commands.get(key).toStringV2());
      sb.append("      ");
      sb.append(String.format("%-4s", key.toString()));
      sb.append(commands.get(nextKey).toStringV2());
      sb.append("\n");
      key = nextKey;
    }

    return sb.toString();
  }

  /**
   * A command is only invalid if there is an existing command that is changing the same variable
   * in the same time frame.
   * <p>It's iterating through the existing list of commands,
   * to check if the ones with an overlapping
   * time frame are changing the same variables. Each command stores an end time, so what's
   * determining the changing variables are comparing that start command to its end command.</p>
   *
   * @param c A command.
   * @return boolean
   */
  private boolean validCommand(Command c) {
    int cKey = c.getT();

    Command startCommand1;
    Collection<Command> commandList = commands.values();
    try {
      startCommand1 = commands.floorEntry(cKey).getValue();
    } catch (NullPointerException e) {
      startCommand1 = commands.firstEntry().getValue();
    }

    List<Variable> v2 = whatVarsChanging(startCommand1, c);

    for (Command command : commandList) {
      List<Variable> v1 = whatVarsChanging(command, commands.get(command.getEt()));

      if (isSameTimeFrame(command, c) && isChangingSameVar(v1, v2)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Returns whether a command exists in a time period.
   * @param startTime start time of period.
   * @param endTime end time of period.
   */
  private void isCommandExisting(int startTime, int endTime) {
    if (commands.get(startTime) != null || commands.get(endTime) != null) {
      throw new IllegalArgumentException("There is a command that exists at that tick already.");
    }
  }

  /**
   * Checks if two commands have conflicting variable changes.
   * @param v1 list of changing variables from a command.
   * @param v2 list of changing variables from a command.
   * @return boolean.
   */
  private boolean isChangingSameVar(List<Variable> v1, List<Variable> v2) {
    return v1.contains(Variable.COLOR) && v2.contains(Variable.COLOR)
      || v1.contains(Variable.POSITION) && v2.contains(Variable.POSITION)
      || v1.contains(Variable.DIMENSION) && v2.contains(Variable.DIMENSION);
  }

  /**
   * Checks a command and the previous command to see if they have overlapping time periods.
   * @param c1 previous command.
   * @param c2 current command.
   * @return boolean
   */
  private boolean isSameTimeFrame(Command c1, Command c2) {
    int c1st = c1.getT();
    int c1et = c1.getEt();
    int c2st = c2.getT();
    int c2et = c2.getEt();


    return (c1et >= c2st && c1st <= c2st) || (c1st >= c2st && c1st <= c2et)
      || (c1st >= c2st && c1et < c2et) || (c1st <= c2st && c1et >= c2et);
  }

  /**
   * This enum class is to identify changing variables in helper methods.
   */
  private enum Variable {
    COLOR, DIMENSION, POSITION
  }

}
