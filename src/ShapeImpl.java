import java.util.*;

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
    commands = new TreeMap<Integer, Command>();
  }

  // Copy constructor
  ShapeImpl(ShapeImpl shape) {
    this.color = shape.color;
    this.position = shape.position;
    this.dimension = shape.dimension;
  }

  private void addCommand(Command command) {
    int key = command.getT();
    List<Command> loc = splitCommand(command);
    if (!this.validCommand(loc)) {
      throw new IllegalArgumentException("Invalid Command");
    }
    else {
      this.mergeCommands(loc);
    }
  }

  public void mergeCommands(List<Command> loc) {
    for (Command c : loc) {
      int key = c.getT();
      if (this.commands.containsKey(key)) {
        mergeCommand(key,c);
      } else {
        this.commands.put(key,c);
      }
    }
  }


  public void mergeCommand(int key, Command command) {
    command.g
  }

  private static List<Command> splitCommand(Command command) {
    int n = command.getEt() - command.getT();
    ArrayList<Command> result = new ArrayList<>();
    for (int i = 0; i < n ; i++) {
      Command current_state = tickState(command,n,i);
      result.add(current_state);
    }
    return result;
  }

  private static Command tickState(Command c, int n, int i) {
    int multiplier = (1+i/n);
    int r = c.getColor().getR() * multiplier;
    int g = c.getColor().getG() * multiplier;
    int b = c.getColor().getB() * multiplier;
    int w = c.getDimension().getW() * multiplier;
    int h = c.getDimension().getH() * multiplier;
    int x = c.getPosition().getX() * multiplier;
    int y = c.getPosition().getY() * multiplier;
    int t = c.getT() * multiplier;
    return new Command(x,y,w,h,r,g,b,t,c.getEt());
  }
  /**
   * Given a command, what is it changing?
   * Get tick last prior to the start time of the command, pull out the command
   * Compare that command to the given command
   * What's changing?
   * @param command
   * @return
   */

  private boolean validCommand(List<Command> loc) {
    Collection<Command> loc0 = commands.values();
    for (Command c : loc) {

    }

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
//    Collection<List<Command>> values = commands.values();
//    StringBuilder sb = new StringBuilder();
//
//    for (List<Command> value : values) {
//      sb.append(getTickCommands(value));
//    }
//
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
