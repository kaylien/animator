import java.util.Set;

/**
 * TODO: Class comment
 * TODO: Create all class invariants & enforce them
 * Invariants:
 * - No negative arguments
 * - Color values between 0 and 255
 */
public class Command {
  private int x, y, w, h, r, g, b, t;

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
   */
  public Command(int x, int y, int w, int h, int r, int g, int b, int t) {
    if (x < 0 || y < 0 || w < 0 || h < 0 ||r < 0 || g < 0 || b < 0 || t < 0) {
      throw new IllegalArgumentException("Cannot create a command with an argument less than 0.");
    }

    if (r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("Color inputs must be between 0 and 255.");
    }

    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.r = r;
    this.g = g;
    this.b = b;
    this.t = t;
  }

  //TODO: Make error commands


  /**
   * TODO: Copy constructor comment
   * @param command
   */
  public Command(Command command) {
    this.x = command.getX();
    this.y = command.getY();
    this.w = command.getW();
    this.h = command.getH();
    this.r = command.getR();
    this.g = command.getG();
    this.b = command.getB();
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
    return x == cmd.getX() && y == cmd.getY() && w == cmd.getW() && h == cmd.getH() &&
      r == cmd.getR() && g == cmd.getG() && b == cmd.getB() && t == cmd.getT();
  }

  public int getX() { return x; }

  public void setX(int x) {
    this.x = x;
  }

  public int getB() {
    return b;
  }

  public void setB(int b) {
    this.b = b;
  }

  public int getG() {
    return g;
  }

  public void setG(int g) {
    this.g = g;
  }

  public int getR() {
    return r;
  }

  public void setR(int r) {
    this.r = r;
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

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

}
