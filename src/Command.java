/**
 * Represents a Command to be conducted during an animation.
 */

public class Command {
  private int t;
  private int et;
  private Color color;
  private Position position;
  private Dimension dimension;

  /**
   * Describes the motions of shape R, between two moments of animation: t == tick,
   * (x,y) == position, (w,h) == dimensions, (r,g,b) == color (with values between 0 and 255).
   * @param x  position x
   * @param y  position y
   * @param w  width
   * @param h  height
   * @param r  red
   * @param g  green
   * @param b  blue
   * @param t  tick
   * @param et end tick
   */
  protected Command(int x, int y, int w, int h, int r, int g, int b, int t, int et) {
    if (et < 0 || t < 0 || x < 0 || y < 0) {
      throw new IllegalArgumentException(
        "Cannot create a command with start or end time" + " less than 0.");
    }

    this.color = new Color(r, g, b);
    this.position = new Position(x, y);
    this.dimension = new Dimension(w, h);
    this.t = t;
    this.et = et;
  }

  /**
   * Copy Constructor for commmands.
   * @param command the command to be copied.
   */
  protected Command(Command command) {
    this.color = command.getColor();
    this.position = command.getPosition();
    this.dimension = command.getDimension();
    this.t = command.getT();
    this.et = command.getEt();
  }

  // Hashing by making the start time the key
  @Override public int hashCode() {
    return getT();
  }

  @Override public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Command cmd = (Command) obj;
    return color == cmd.getColor() && dimension == cmd.getDimension() && position == cmd
      .getPosition() && t == cmd.getT();
  }

  protected Color getColor() {
    return color;
  }

  protected void setColor(Color color) {
    this.color = color;
  }

  protected void setColor(int r, int g, int b) {
    this.color = new Color(r, g, b);
  }

  protected Dimension getDimension() {
    return dimension;
  }

  protected void setDimension(Dimension dimension) {
    this.dimension = dimension;
  }

  protected Position getPosition() {
    return position;
  }

  protected void setPosition(Position position) {
    this.position = position;
  }

  protected void setPosition(int x, int y) {
    this.position = new Position(x, y);
  }

  protected int getT() {
    return t;
  }

  protected void setT(int t) {
    this.t = t;
  }

  protected int getEt() {
    return et;
  }

  protected void setEt(int et) {
    this.et = et;
  }


  protected String toStringV2() {
    return ""
      .join(" ", getPosition().toStringV2(), getColor().toStringV2(), getDimension().toStringV2());
  }

}
