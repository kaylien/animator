public abstract class ShapeModelImpl {
  /**
   * Describes the motions of shape R, between two moments of animation: t == tick,
   * (x,y) == position, (w,h) == dimensions, (r,g,b) == color (with values between 0 and 255).
   */
  private int x, y, w, h, r, g, b, t;

  ShapeModelImpl(int x, int y, int w, int h, int r, int g, int b, int t) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.r = r;
    this.g = g;
    this.b = b;
    this.t = t;
  }


  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getB() {
    return b;
  }

  public void setB(int b) {
    this.b = b;
  }

  public int getG() {
    return g;
  }

  public void setG(int g) {
    this.g = g;
  }

  public int getR() {
    return r;
  }

  public void setR(int r) {
    this.r = r;
  }

  public int getT() {
    return t;
  }

  public void setT(int t) {
    this.t = t;
  }

  public int getW() {
    return w;
  }

  public void setW(int w) {
    this.w = w;
  }

  public int getH() {
    return h;
  }

  public void setH(int h) {
    this.h = h;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }
}
