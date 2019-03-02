/**
 * Created by echung326 on 3/1/19.
 */
public class Ellipse extends ShapeImpl {

  Ellipse(int x, int y, int w, int h, int r, int g, int b, int appears, int disappears) {
    super(x, y, w, h, r, g, b, appears, disappears);
  }

 @Override public String toString() {
   return "Ellipse";
 }
}
