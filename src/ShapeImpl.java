import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
  }

  private void editCommands(Command startCmd, Command endCmd) {

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
