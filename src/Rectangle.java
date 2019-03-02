
public class Rectangle extends ShapeImpl{

  Rectangle(int x, int y, int w, int h, int r, int g, int b, int appears, int disappears) {
    super(x, y, w, h, r, g, b, appears, disappears);
  }

  @Override public String toString() {
    return "Rectangle";
  }
}
