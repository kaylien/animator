public class Dimension {
  float w, h;

  Dimension(float w, float h) {
    illegalDimension(w);
    illegalDimension(h);
    this.w = w;
    this.h = h;
  }

  public Float[] dimensionDifference(Dimension d) {
    Float[] list = new Float[2];

    list[0] = this.h - d.getH();
    list[1] = this.w - d.getW();

    return list;
  }

  public void illegalDimension(float d) {
    if (d <= 0) {
      throw new IllegalArgumentException("Must have a positive width and height");
    }
  }

  public float getH() {
    return h;
  }

  public void setH(float h) {
    illegalDimension(h);
    this.h = h;
  }

  public float getW() {
    return w;
  }

  public void setW(float w) {
    illegalDimension(w);
    this.w = w;
  }

  public String toStringV2() {
    return "".join(" ",String.valueOf(w),String.valueOf(h));
  }

}
