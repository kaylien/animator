public class Dimension {
  int w, h;

  Dimension(int w, int h) {
    illegalDimension(w);
    illegalDimension(h);
    this.w = w;
    this.h = h;
  }

  public void illegalDimension(int d) {
    if (d <= 0) {
      throw new IllegalArgumentException("Must have a positive width and height");
    }
  }

  public int getH() {
    return h;
  }

  public void setH(int h) {
    illegalDimension(h);
    this.h = h;
  }

  public int getW() {
    return w;
  }

  public void setW(int w) {
    illegalDimension(w);
    this.w = w;
  }

}
