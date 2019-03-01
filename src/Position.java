public class Position {
  private float x, y;

  Position(int x, int y) {
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
}
