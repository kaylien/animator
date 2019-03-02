/**
 * Class representing a position in a x,y coordinate plane.
 */
public class Position {
  private float x;
  private float y;

  Position(float x, float y) {
    this.x = x;
    this.y = y;
  }

  protected Float[] positionDifference(Position p) {
    Float[] list = new Float[2];

    list[0] = this.x - p.getX();
    list[1] = this.y - p.getY();

    return list;
  }

  protected float getX() {
    return x;
  }

  protected void setX(float x) {
    this.x = x;
  }

  protected float getY() {
    return y;
  }

  protected void setY(float y) {
    this.y = y;
  }

  @Override public int hashCode() {
    return this.hashCode();
  }

  @Override public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Position p = (Position) obj;
    return p.getX() == this.getX() && p.getY() == this.getY();
  }

  public String toStringV2() {
    return String.format("%-4s %-4s", String.valueOf(Math.round(x)), String.valueOf(Math.round(y)));
  }

}
