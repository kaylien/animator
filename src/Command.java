import java.util.Set;

/**
 * TODO: Class comment
 * TODO: Create all class invariants & enforce them
 * Invariants:
 * - No negative arguments
 * - Color values between 0 and 255 (ENFORCED by using the Color class)
 * - Dimension allows negative values, have to check for that
 */
public class Command {
  private int t, et;
  private Color color;
  private Position position;
  private Dimension dimension;

//  TODO: Errors handled in Object classes now
//  enum Error {
//    LESS_THAN_0(0, "Cannot create a command with an argument less than 0."),
//    ILLEGAL_COLOR(1, "Color inputs must be between 0 and 255.");
//
//    private final int code;
//    private final String description;
//
//    private Error(int code, String description) {
//      this.code = code;
//      this.description = description;
//    }
//
//    public String getDescription() {
//      return description;
//    }
//
//    public int getCode() {
//      return code;
//    }
//
//    @Override
//    public String toString() {
//      return code + ": " + description;
//    }
//
//  }

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
    if (et < 0 || t < 0) {
      throw new IllegalArgumentException("Cannot create a command with start or end time" +
        " less than 0.");
    }

    this.color = new Color(r, g, b);
    this.position = new Position(x, y);
    this.dimension = new Dimension(w, h);
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
    this.dimension = command.getDimension();
    this.t = command.getT();
    this.et = command.getEt();
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
    return color == cmd.getColor() && dimension == cmd.getDimension()
      && position == cmd.getPosition() && t == cmd.getT();
  }

  public Color getColor() {
    return color;
  }

  public void setColor(int r, int g, int b) {
    this.color = new Color(r, g, b);
  }

  public void setColor(Color color) { this.color = color; }

  public Dimension getDimension() {
    return dimension;
  }

  public void setDimension(Dimension dimension) {
    this.dimension = dimension;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(int x, int y) {
    this.position = new Position(x, y);
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public int getT() {
    return t;
  }

  public void setT(int t) {
    this.t = t;
  }

  public int getEt() {
    return et;
  }

  public void setEt(int et) {
    this.et = et;
  }


  public String toStringV2() {
    return "".join(" ",
      String.valueOf(getT()),
      String.valueOf(getEt()),
      getDimension().toStringV2(),
      getPosition().toStringV2(),
      getColor().toStringV2());
  }

}
