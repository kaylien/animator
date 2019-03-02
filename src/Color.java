

/**
 * Represents a color in rgb format.  /**
 * Invariants: No negative arguments. Color values between 0 and 255
 * (ENFORCED by throwing IllegalArgument Exceptions).
 */

public class Color {

  private float r;
  private float g;
  private float b;

  Color(float r, float g, float b) {
    illegalColor(r);
    illegalColor(g);
    illegalColor(b);

    this.r = r;
    this.g = g;
    this.b = b;
  }

  protected void illegalColor(float c) {
    if (c < 0 || c > 255) {
      throw new IllegalArgumentException("Cannot have a color argument below 0 or above 255");
    }
  }

  /**
   * Calculates the difference in color of a Color.
   * @param c color to grab.
   * @return list of value differences or deltas.
   */
  protected Float[] colorDifference(Color c) {
    Float[] list = new Float[3];

    list[0] = this.r - c.getR();
    list[1] = this.g - c.getG();
    list[2] = this.b - c.getB();

    return list;
  }

  protected float getR() {
    return r;
  }

  protected void setR(float r) {
    illegalColor(r);
    this.r = r;
  }

  protected float getG() {
    return g;
  }

  protected void setG(float g) {
    illegalColor(g);

    this.g = g;
  }

  protected float getB() {
    return b;
  }

  protected void setB(float b) {
    illegalColor(b);

    this.b = b;
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Color c = (Color) obj;
    return c.getR() == this.getR()
      && c.getG() == this.getG()
      && c.getB() == this.getB();
  }

  @Override public int hashCode() {
    return this.hashCode();
  }


  protected String toStringV2() {
    return String
      .format("%-4s %-4s %-4s", String.valueOf(Math.round(r)), String.valueOf(Math.round(g)),
        String.valueOf(Math.round(b)));
  }
}
