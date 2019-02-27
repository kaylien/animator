import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public abstract class ShapeImpl implements ShapeInt{
  private int x, y, w, h, r, g, b, t;

  // Integer will be tick
  HashMap<Integer, List<Command>> commands;

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
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.r = r;
    this.g = g;
    this.b = b;

    commands = new HashMap<>();
  }

  // Copy constructor
  ShapeImpl(ShapeImpl shape) {
    this.x = shape.x;
    this.y = shape.y;
    this.w = shape.w;
    this.h = shape.h;
    this.r = shape.r;
    this.g = shape.g;
    this.b = shape.b;
  }

  private void addCommand(Command command) {
    int key = command.getT();

    if (!this.validCommand(command)) {
      throw new IllegalArgumentException("Invalid Command");
    }
    if (commands.containsKey(key)) {
      commands.get(key).add(command);
    }
    else {
      commands.put(command.getT(), new ArrayList<>());
    }
  }

  private boolean validCommand(Command command) {
    return true;
  }
/*
  private boolean validCommand(Command command) {
    for (c : this.commands) {
      int startC0 = c.getT();
      int endC0 = c.getET();
      int startC1 = command.getT();
      int endC1 = command.getET();
      if(startC1 >= startC0 && startC1 <= endC0 || endC1 >= startC0 && endC1 <= endC0 ) {
        if(conflictingVariable(c,command)) {
          return false;
        } else {
          continue;
        }
      }
    }
    return true;
  }

  private static boolean conflictingVariable(Command c1, Command c2) {
    return c1.getB() != c2.getB() &&
      c1.getG() != c2.getG() &&
    c1.getR() != c2.getR() &&
    c1.getX() != c2.getX() &&
    c1.getY() != c2.getY() &&
    c1.getW() != c2.getW() &&
    c1.getH() != c2.getH();
  }
*/

  @Override
  public void addCommands(Command... commands) {
    for (Command command : commands) {
      addCommand(command);
    }
  }

  public String getCommands() {
    Collection<List<Command>> values = commands.values();
    StringBuilder sb = new StringBuilder();

    for (List<Command> value : values) {
      sb.append(getTickCommands(value));
    }

    return sb.toString();
  }

  public String getTickCommands(List<Command> list) {
    StringBuilder sb = new StringBuilder();

    for (Command command : list) {
      sb.append(command.toString() + "/n");
    }

    return sb.toString();
  }

}
