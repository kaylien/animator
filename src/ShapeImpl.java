import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
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
  }

  // Copy constructor
  ShapeImpl(ShapeImpl shape) {
    this.color = shape.color;
    this.position = shape.position;
    this.dimension = shape.dimension;
  }

  private void addCommand(Command command) {
    int key = command.getT();
    int endTime = command.getEt();

    if (!this.validCommand(command)) {
      throw new IllegalArgumentException("Invalid Command");
    }

    commands.put(key, command);
    editCommands(commands.get(key), commands.get(endTime));
  }

  private void editCommands(Command startCmd, Command endCmd) {
    List<Variable> vars = whatVarsChanging(startCmd, endCmd);
    int startTime = startCmd.getT();
    int endTime = endCmd.getT();

    if (vars.contains(Variable.COLOR)) {
      Color c1 = startCmd.getColor();
      Color c2 = endCmd.getColor();
      Float[] colorDiff = c1.colorDifference(c2);
      updateColor(colorDiff, startTime, endTime);
    }

    if (vars.contains(Variable.DIMENSION)) {
      Dimension d1 = startCmd.getDimension();
      Dimension d2 = endCmd.getDimension();

      Float[] dimensionDiff = d1.dimensionDifference(d2);
      updateDimension(dimensionDiff, startTime, endTime);
    }

    if (vars.contains(Variable.POSITION)) {
      Position p1 = startCmd.getPosition();
      Position p2 = endCmd.getPosition();

      Float[] positionDiff = p1.positionDifference(p2);
      updatePosition(positionDiff, startTime, endTime);
    }
  }

  // TODO: the next three methods should be abstracted, I just haven't done it yet
  private void updateColor(Float[] array, int startTime, int endTime) {
    int deltaTime = endTime - startTime;
    Color sColor = commands.get(startTime).getColor();
    float r = sColor.getR();
    float g = sColor.getG();
    float b = sColor.getB();

    float changeR = deltaTime / array[0];
    float changeG = deltaTime / array[1];
    float changeB = deltaTime / array[2];


    for (int i = 0; i < deltaTime; i ++) {
      Color c = new Color(r + (changeR * i), g + (changeG * i), b + changeB);
      int key = startTime + i;
      Command currentCmd = commands.get(key);
      currentCmd.setColor(c);
      commands.put(key, currentCmd);
    }
  }

  private void updateDimension(Float[] array, int startTime, int endTime) {
    int deltaTime = endTime - startTime;
    Dimension sDim = commands.get(startTime).getDimension();
    float h = sDim.getH();
    float w = sDim.getW();

    float changeH = deltaTime / array[0];
    float changeW = deltaTime / array[1];

    for (int i = 0; i < deltaTime; i ++) {
      Dimension d = new Dimension(w + (changeW * i), h + (changeH * i));
      int key = startTime + i;
      Command currentCmd = commands.get(key);
      currentCmd.setDimension(d);
      commands.put(key, currentCmd);
    }
  }

  private void updatePosition(Float[] array, int startTime, int endTime) {
    int deltaTime = endTime - startTime;
    Position sPos = commands.get(startTime).getPosition();
    float x = sPos.getX();
    float y = sPos.getY();

    float changeX = deltaTime / array[0];
    float changeY = deltaTime / array[1];

    for (int i = 0; i < deltaTime; i ++) {
      Position p = new Position(x + (changeX * i), y + (changeY * i));
      int key = startTime + i;
      Command currentCmd = commands.get(key);
      currentCmd.setPosition(p);
      commands.put(key, currentCmd);
    }
  }

  /**
   * @param command
   * @return
   */

  private boolean validCommand(Command command) {
    Collection<Command> listOfCommands = commands.values();
//    Collection<Command> locII = splitCommand(Command);
//    for (Command c : locII) {
//
//    }

    int key = command.getT();
    if (commands.get(command.getT()) == null) {
      //get the command right before and right after to the new command
      int lastKey = commands.lowerKey(command.getT());
      int nextKey = commands.higherKey(command.getT());
      Command lastCommand = commands.get(lastKey);
      Command nextCommand = commands.get(nextKey);

    }
    else {
      Collection<Command> loc = commands.values();
      //compare the existing command to the new command
    }

    return true;
  }



  private enum Variable {
    COLOR, DIMENSION, POSITION;
  }

  private boolean isChangingSameVar(List<Variable> v1, List<Variable> v2) {
    for (Variable var : v1) {
      for (Variable var2: v2) {
        if (var == var2) {
          return true;
        }
      }
    }
    return false;
  }

  static private List<Variable> whatVarsChanging(Command c1, Command c2) {
    List<Variable> list = new ArrayList<>();
    if (c1.getColor() != c2.getColor()) {
      list.add(Variable.COLOR);
    } else if (c1.getPosition() != c2.getPosition()) {
      list.add(Variable.POSITION);
    } else if (c1.getDimension() != c2.getDimension()) {
      list.add(Variable.DIMENSION);
    }
    return list;
  }

  @Override
  public void addCommands(Command... commands) {
    for (Command command : commands) {
      addCommand(command);
    }
  }

  public String getCommands() {

//    StringBuilder sb = new StringBuilder();
//
//    for (List<Command> value : values) {
//      sb.append(getTickCommands(value));
//    }

//    return sb.toString();
    return "";
  }

  public String getTickCommands(List<Command> list) {
    StringBuilder sb = new StringBuilder();

    for (Command command : list) {
      sb.append(command.toString() + "/n");
    }

    return sb.toString();
  }

}
