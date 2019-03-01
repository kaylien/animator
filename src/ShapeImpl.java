import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.SortedMap;
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
    System.out.println(s.getCommands());
  }

  private void addCommands (Command c) {
    int key = c.getT();

    if (!(validCommand(c))) {
      throw new IllegalArgumentException("Invalid command");
    }

    commands.put(key, c);
    fixCommands(c.getT(), c.getEt());
  }

  private void fixCommands(int startTime, int endTime) {
    NavigableMap<Integer, Command> commandList = commands.subMap(startTime,true,
      endTime, true);
    Set<Integer> keys = commandList.keySet();
    Command startCmd = commands.get(startTime);
    Command endCmd = commands.get(endTime);
    List<Variable> varsToChange = whatVarsChanging(startCmd, endCmd);
    int deltaTime = endTime - startTime;
    int deltaKeys = commandList.lastKey() - commandList.firstKey();


    for (Variable var : varsToChange) {
      for (Integer key : keys) {
        int keyDiff = key - commandList.higherKey(key);
        float multiplier = keyDiff / deltaKeys;

        
      }

      for (int i = 0; i < commandList.size(); i++) {
        if (var == Variable.COLOR) {
          int cKey = commandList.get
          Color c1 = startCmd.getColor();
          Color c2 = endCmd.getColor();
          Float[] array = c1.colorDifference(c2);

          Color sColor = commands.get(startTime).getColor();


          float r = sColor.getR();
          float g = sColor.getG();
          float b = sColor.getB();

          float changeR = deltaTime / array[0];
          float changeG = deltaTime / array[1];
          float changeB = deltaTime / array[2];

          Color c = new Color(r + (changeR * ), g + (changeG * i), b + changeB);
        }
      }
    }

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
    }
    return sb.toString();
  }

  public void mergeCommands(List<Command> loc) {
    for (Command c : loc) {
      int key = c.getT();
      if (this.commands.containsKey(key)) {
        this.commands.replace(key,c);
        System.out.println(commands.get(key).toStringV2());
      } else {
        this.commands.put(key,c);
        System.out.println(commands.get(key).toStringV2());
      }
    }
  }

  /**
  private static List<Command> splitCommand(Command command) {
    int n = command.getEt() - command.getT();
    List<Command> result = new ArrayList<Command>();
    for (int i = 0; i < n ; i++) {
      Command current_state = tickState(command,n,i);
      result.add(current_state);
      System.out.println(current_state.toString());
    }
    //needs to be reversed before returned
    return result;
  }
   */

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

  }


  private enum Variable {
    COLOR, DIMENSION, POSITION;
  }

  /**
   * @param c
   * @return
   */
  private boolean validCommand(Command c) {
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

    Variable c = c1.getColor() != c2.getColor() ? Variable.COLOR : null;
    Variable p = c1.getPosition() != c2.getPosition() ? Variable.POSITION : null;
    Variable d = c1.getDimension() != c2.getDimension() ? Variable.DIMENSION : null;

    list.add(c);
    list.add(p);
    list.add(d);

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
