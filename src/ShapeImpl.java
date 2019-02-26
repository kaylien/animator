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

    if (commands.containsKey(key)) {
      commands.get(key).add(command);
    }
    else {
      commands.put(command.getT(), new ArrayList<>());
    }
  }

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
