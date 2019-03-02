public class Position {
  private float x, y;

  Position(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public Float[] positionDifference(Position p) {
    Float[] list = new Float[2];

    list[0] = this.x - p.getX();
    list[1] = this.y - p.getY();

    return list;
  }

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public boolean equals(Position p) {
    return p.getX() == getX() && p.getY() == getY();
  }

  public String toStringV2() {
    return String.format("%-4s %-4s", String.valueOf(Math.round(x)),String.valueOf(Math.round(y)));
//    return "".join("   ",);
  }

}
