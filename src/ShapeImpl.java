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

  public static void main(String[] args) {
    ShapeImpl s = new Rectangle(20, 30, 40, 10, 100, 200, 50);
    Command c = new Command(20, 30, 40, 10, 100, 200, 100, 1, 10);
    s.addCommands(c);
  }

  private void addCommand(Command command) {
    int key = command.getT();
    List<Command> loc = splitCommand(command);
    if (!this.validCommands(loc)) {
      throw new IllegalArgumentException("Invalid Command");
    } else {
      this.mergeCommands(loc);
    }
  }

  public void addCommands(Command... commands) {
    for (Command c : commands) {
      addCommand(c);
    }
  }

  public String getCommands() {
    StringBuilder sb = new StringBuilder();
    Collection<Command> values = commands.values();
    for (Command c : values) {
      sb.append(c.toString());
    }
    return sb.toString();
  }

  public void mergeCommands(List<Command> loc) {
    for (Command c : loc) {
      int key = c.getT();
      if (this.commands.containsKey(key)) {
        this.commands.replace(key,c);
      } else {
        this.commands.put(key,c);
      }
    }
  }

  private static List<Command> splitCommand(Command command) {
    int n = command.getEt() - command.getT();
    List<Command> result = new ArrayList<Command>();
    for (int i = 0; i < n ; i++) {
      Command current_state = tickState(command,n,i);
      result.add(current_state);
    }
    //needs to be reversed before returned
    return result;
  }

  private static Command tickState(Command c, int n, int i) {
    int multiplier = (1-i/n);
    int r = Math.round(c.getColor().getR() * multiplier);
    int g = Math.round(c.getColor().getG() * multiplier);
    int b = Math.round(c.getColor().getB() * multiplier);
    int w = Math.round(c.getDimension().getW() * multiplier);
    int h = Math.round(c.getDimension().getH() * multiplier);
    //making a linear movement for x and y???
    int x = Math.round(c.getPosition().getX() * multiplier);
    int y = Math.round(c.getPosition().getY() * multiplier);
    //index???
    int t = c.getEt() - i;
    return new Command(x,y,w,h,r,g,b,t,c.getEt());

    //commands.put(key, command);
    //editCommands(commands.get(key), commands.get(endTime));
  }


  /**
   * @param loc
   * @return
   */
  private boolean validCommands(List<Command> loc) {
    //data structs man
    List<Command> loc0 = new ArrayList<>();
    for (Command c : loc) {
      loc0.add(c);
    }
    List<List<Integer>> v_c_0 = allVarsChanging(loc0);
    List<List<Integer>> v_c_1 = allVarsChanging(loc);
    return isChangingSameVar(v_c_0,v_c_1);

  }

  private enum Variable {
    COLOR, DIMENSION, POSITION;
  }

  private boolean isChangingSameVar(List<List<Integer>> v1, List<List<Integer>> v2) {
    int n = v1.size();
    for (int i = 0; i< n; i++) {
      List<Integer> current_row = v1.get(i);
      int n_j = current_row.size();
      for (int j = 0 ; j < n_j ; j++) {
        if (v1.get(i).get(j) != v2.get(i).get(j)) {
          return true;
        }
      }
    }
    return false;
  }

  static private List<List<Integer>> allVarsChanging(List<Command> loc) {
    int n = loc.size();
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    for (int i = 1; i < 0; i++) {
      Command c1 = loc.get(i-1);
      Command c2 = loc.get(i);
      List<Integer> row = whatVarsChanging(c1,c2);
      result.add(row);
    }
    return result;
  }

  static private List<Integer> whatVarsChanging(Command c1, Command c2) {
    List<Integer> list = new ArrayList<>();

    int c = c1.getColor() != c2.getColor() ? 1 : 0;
    int p = c1.getPosition() != c2.getPosition() ? 1 : 0;
    int d = c1.getDimension() != c2.getDimension() ? 1 : 0;

    list.add(c);
    list.add(p);
    list.add(d);

    return list;

  }

//  public void deleteCommands(Command... command) {
//    int n = command.length;
//    for (int i; i< 0; i++) {
//
//    }
//  }

  public String queryCommands() {
    int n = commands.size();
    String result = "";
    for (int i = 0; i < n; i++) {
      Command current = commands.get(i);
      result.join("\n",current.toStringV2());
    }
    return result;
  }

}
