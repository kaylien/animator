/**
 * Class representing the dimensions of a shape.
 */
public class Dimension {
  float w;
  float h;

  Dimension(float w, float h) {
    illegalDimension(w);
    illegalDimension(h);
    this.w =  w;
    this.h = h;
  }

  protected Float[] dimensionDifference(Dimension d) {
    Float[] list = new Float[2];

    list[0] = this.h - d.getH();
    list[1] = this.w - d.getW();

    return list;
  }

  protected void illegalDimension(float d) {
    if (d <= 0) {
      throw new IllegalArgumentException("Must have a positive width and height");
    }
  }

  protected float getH() {
    return h;
  }

  protected void setH(float h) {
    illegalDimension(h);
    this.h = h;
  }

  protected float getW() {
    return w;
  }

  protected void setW(float w) {
    illegalDimension(w);
    this.w = w;
  }


  @Override public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Dimension d = (Dimension) obj;
    return d.getW() == this.getW() && d.getW() == this.getH();
  }

  @Override public int hashCode() {
    return this.hashCode();
  }

  protected String toStringV2() {
    return String.format("%-4s %-4s", String.valueOf(Math.round(w)),String.valueOf(Math.round(h)));
  }

}
