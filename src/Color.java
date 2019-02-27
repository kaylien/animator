/**
 * Cannot have a Color with params outside of 0 and 255
 */
public class Color {
  private int r, g, b;

  Color(int r, int g, int b) {
    illegalColor(r);
    illegalColor(g);
    illegalColor(b);

    this.r = r;
    this.g = g;
    this.b = b;
  }

  // TODO: Fix? Not sure if best way to handle errors. But could do try catches later
  public void illegalColor(int c) {
    if (c < 0 || c > 255) {
      throw new IllegalArgumentException("Cannot have a color argument below 0 or above 255");
    }
  }

  public int getR() {
    return r;
  }

  public void setR(int r) {
    illegalColor(r);
    this.r = r;
  }

  public int getG() {
    return g;
  }

  public void setG(int g) {
    illegalColor(g);

    this.g = g;
  }

  public int getB() {
    return b;
  }

  public void setB(int b) {
    illegalColor(b);

    this.b = b;
  }
}
