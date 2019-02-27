import java.awt.Color;
import java.awt.Point;
import java.util.Set;
import javax.swing.text.Position;

/**
 * TODO: Class comment
 * TODO: Create all class invariants & enforce them
 * Invariants:
 * - No negative arguments
 * - Color values between 0 and 255 (ENFORCED by using the Color class)
 */
public class Command {
  private int w, h, t, et;
  private Color color;
  private Point position;

  //TODO: Fix errors
  enum Error {
    LESS_THAN_0(0, "Cannot create a command with an argument less than 0."),
    ILLEGAL_COLOR(1, "Color inputs must be between 0 and 255.");

    private final int code;
    private final String description;

    private Error(int code, String description) {
      this.code = code;
      this.description = description;
    }

    public String getDescription() {
      return description;
    }

    public int getCode() {
      return code;
    }

    @Override
    public String toString() {
      return code + ": " + description;
    }

  }
  /**
   * TODO: Constructor comments
   *
   * Describes the motions of shape R, between two moments of animation: t == tick,
   * (x,y) == position, (w,h) == dimensions, (r,g,b) == color (with values between 0 and 255).
   *
   * @param x position x
   * @param y position y
   * @param w width
   * @param h height
   * @param r red
   * @param g green
   * @param b blue
   * @param t tick
   * @param et end tick
   */
  public Command(int x, int y, int w, int h, int r, int g, int b, int t, int et) {
    if (x < 0 || y < 0 || w < 0 || h < 0 ||r < 0 || g < 0 || b < 0 || t < 0) {
      throw new IllegalArgumentException("Cannot create a command with an argument less than 0.");
    }

    this.color = new Color(r, g, b);
    this.position = new Point(x, y);
    this.w = w;
    this.h = h;
    this.t = t;
    this.et = et;
  }

  //TODO: Make error commands


  /**
   * TODO: Copy constructor comment
   * @param command
   */
  public Command(Command command) {
    this.color = command.getColor();
    this.position = command.getPosition();
    this.w = command.getW();
    this.h = command.getH();
    this.t = command.getT();
  }

  // Hashing by making the start time the key
  @Override
  public int hashCode() {
    return getT();
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Command cmd = (Command) obj;
    return color == cmd.getColor() && w == cmd.getW() && h == cmd.getH() &&
      position == cmd.getPosition() && t == cmd.getT();
  }

  public Color getColor() {
    return color;
  }

  public void setColor(int r, int g, int b) {
    this.color = new Color(r, g, b);
  }

  public Point getPosition() {
    return position;
  }

  public void setPosition(int x, int y) {
    this.position = new Point(x, y);
  }

  public int getT() {
    return t;
  }

  public void setT(int t) {
    this.t = t;
  }

  public int getW() {
    return w;
  }

  public void setW(int w) {
    this.w = w;
  }

  public int getH() {
    return h;
  }

  public void setH(int h) {
    this.h = h;
  }

  public int getEt() {
    return et;
  }

  public void setEt(int et) {
    this.et = et;
  }
}
