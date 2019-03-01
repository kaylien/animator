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

  // Integer will be tick
//  HashMap<Integer, List<Command>> commands;
//  HashMap<Integer, Command> commands;
  TreeMap<Integer, Command> commands;
  //NavigableMap?????? It finds those closeby keys

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
//    commands = new HashMap<>();
  }

  // Copy constructor
  ShapeImpl(ShapeImpl shape) {
    this.color = shape.color;
    this.position = shape.position;
    this.dimension = shape.dimension;
  }

  private void addCommand(Command command) {
    int key = command.getT();

    if (!this.validCommand(command)) {
      throw new IllegalArgumentException("Invalid Command");
    }

    commands.put(key, command);
  }

  /**
   * Given a command, what is it changing?
   * Get tick last prior to the start time of the command, pull out the command
   * Compare that command to the given command
   * What's changing?
   * @param command
   * @return
   */

  private boolean validCommand(Command command) {
//    Collection<List<Command>> listOfCommands = commands.values();
//    for (List<Command> list : listOfCommands) {
//      for (Command c : list) {
//
//      }
//    }
//
//    int key = command.getT();
//    if (commands.get(command.getT()) == null) {
//      //get the command right before and right after to the new command
//      int lastKey = commands.lowerKey(command.getT());
//      int nextKey = commands.higherKey(command.getT());
//      Command lastCommand = commands.get(lastKey);
//      Command nextCommand = commands.get(nextKey);
//
//      List<Variable>
//    }
//    else {
//      Collection<Command> listOfCommands = commands.values();
//      //compare the existing command to the new command
//    }
//
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
  
//  private boolean validCommand(Command command) {
//    return true;

  static private List<Variable> whatVarsChanging(Command c, Command otherC) {
    List<Variable> list = new ArrayList<>();
    if (c.getColor() != otherC.getColor()) {
      list.add(Variable.COLOR);
    } else if (c.getPosition() != otherC.getPosition()) {
      list.add(Variable.POSITION);
    } else if (c.getDimension() != otherC.getDimension()) {
      list.add(Variable.DIMENSION);
    }
    return list;
  }

//  private boolean isSameTimeRange(Command c1, Command c2) {
//    int c1T = c1.getT();
//    int c2T = c2.getT();
//    int c1eT = c1.getEt();
//    int c2eT = c2.getEt();
//
//    return c1T > c2T && c1eT < c2eT || c1T > c2T && c1T < c2eT || c2T > c1T && c2T < c1eT;
//  }

/*
  private boolean validCommand(Command command) {
    for (c : this.commands) {
      int startC0 = c.getT();
      int endC0 = c.getET();
      int startC1 = command.getT();
      int endC1 = command.getET();
      if(startC1 >= startC0 && startC1 <= endC0 || endC1 >= startC0 && endC1 <= endC0 ) {
        if(!conflictingVariable(c,command)) {
          return false;
        } else {
          continue;
        }
      }
    }
    return true;
  }

  private static boolean conflictingVariable(Command c1, Command c2) {
    return c1.equals(c2)
*/

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
