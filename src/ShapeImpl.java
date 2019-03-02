import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import javafx.geometry.Pos;

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
  ShapeImpl(int x, int y, int w, int h, int r, int g, int b) {

    this.color = new Color(r, g, b);
    this.position = new Position(x, y);
    this.dimension = new Dimension(w, h);
    commands = new TreeMap<>();
    Command cmd = new Command(x, y, w, h, r, g, b, 0, 0);
    commands.put(0, cmd);
  }

  // Copy constructor
  ShapeImpl(ShapeImpl shape) {
    this.color = shape.color;
    this.position = shape.position;
    this.dimension = shape.dimension;
  }

  public static void main(String[] args) {
    ShapeImpl s = new Rectangle(20, 30, 40, 10, 100, 200, 50);
    Command c = new Command(20, 30, 40, 10, 100, 200, 100, 1, 10);
    s.addCommands(c);
    System.out.println(s.getCommands());
  }


  private void addCommand (Command c) {
    int key = c.getT();
    int endKey = c.getEt();

//    if (!(validCommand(c))) {
//      throw new IllegalArgumentException("Invalid command");
//    }

    commands.put(endKey, c);
    fixCommands(key, endKey);
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
        Color newColor = updateToColor(colorDiff, multiplier, startTime, endTime);
        cmd.setColor(newColor);
        commands.put(key, cmd);
      }

      if (varsToChange.contains(Variable.POSITION)) {
        Float[] positionDiff = startCmd.getPosition().positionDifference(endCmd.getPosition());
        Position newPosition = updateToPosition(positionDiff, multiplier, startTime, endTime);
        cmd.setPosition(newPosition);
        commands.put(key, cmd);
      }

      if (varsToChange.contains(Variable.DIMENSION)) {
        Float[] dimensionDiff = startCmd.getPosition().positionDifference(endCmd.getPosition());
        Dimension newDimension = updateToDimension(dimensionDiff, multiplier, startTime, endTime);
        cmd.setDimension(newDimension);
        commands.put(key, cmd);
      }
    }
  }

  public Color updateToColor(Float[] array, float multiplier, int startTime, int endTime) {

    float changeR = multiplier * array[0];
    float changeG = multiplier * array[1];
    float changeB = multiplier * array[2];

    return new Color(changeR, changeG, changeB);
  }


  public Position updateToPosition(Float[] array, float multiplier, int startTime, int endTime) {

    float changeW = multiplier * array[0];
    float changeH = multiplier * array[1];

    return new Position(changeW, changeH);
  }


  public Dimension updateToDimension(Float[] array, float multiplier, int startTime, int endTime) {

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
    COLOR, DIMENSION, POSITION;
  }

  /**
   * @param c
   * @return
   */
  private boolean validCommand(Command c) {
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
        return true;
      }
    }
    return false;
  }


  private boolean isChangingSameVar(List<Variable> v1, List<Variable> v2) {
    return v1.contains(Variable.COLOR) && v2.contains(Variable.COLOR) ||
      v1.contains(Variable.POSITION) && v2.contains(Variable.POSITION) ||
      v1.contains(Variable.DIMENSION) && v2.contains(Variable.DIMENSION);
  }

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

    return (c1et > c2st && c1st < c2st) || (c1st > c2st && c1st < c2et) ||
      (c1st > c2st && c1et < c2et) || (c1st < c2st && c1et > c2et);
  }

}
