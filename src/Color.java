import java.util.List;

/**
 * Cannot have a Color with params outside of 0 and 255
 */
public class Color {
  private float r, g, b;

  Color(float r, float g, float b) {
    illegalColor(r);
    illegalColor(g);
    illegalColor(b);

    this.r = r;
    this.g = g;
    this.b = b;
  }

  // TODO: Fix? Not sure if best way to handle errors. But could do try catches later
  public void illegalColor(float c) {
    if (c < 0 || c > 255) {
      throw new IllegalArgumentException("Cannot have a color argument below 0 or above 255");
    }
  }

  public Float[] colorDifference(Color c) {
    Float[] list = new Float[3];

    list[0] = this.r - c.getR();
    list[1] = this.g - c.getG();
    list[2] = this.b - c.getB();

    return list;
  }

  public float getR() {
    return r;
  }

  public void setR(float r) {
    illegalColor(r);
    this.r = r;
  }

  public float getG() {
    return g;
  }

  public void setG(float g) {
    illegalColor(g);

    this.g = g;
  }

  public float getB() {
    return b;
  }

  public void setB(float b) {
    illegalColor(b);

    this.b = b;
  }
}
