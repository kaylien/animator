import java.awt.Shape;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by echung326 on 2/23/19.
 * TODO: DOCUMENT EVERYTHING
 */
public class AnimationModelImpl implements AnimationModel {
  List<ShapeImpl> shapeList;

  AnimationModelImpl(){
    shapeList = new ArrayList<>();
  }

  @Override
  public void addCommands(ShapeImpl shape, Command... commands) {
    shape.addCommands(commands);
  }

  @Override
  public void addShape(ShapeImpl shape) {
    shapeList.add(shape);
  }

  @Override
  public String getCommands() {
    StringBuilder sb = new StringBuilder();
    String heading = "t x y w h r g b\n";
    String lines = "----------------\n";
    sb.append(heading);
    sb.append(lines);
    for (ShapeImpl shape : shapeList) {
      sb.append(shape.toString());
      sb.append(shape.getCommands());
    }
    return sb.toString();
  }

  /**
   * Copies the current list of shapes.
   * @return A copy of the list of shapes.
   */
  private List<ShapeInt> copyShapeList() {
    List<ShapeImpl> result = new ArrayList<>();
    for(ShapeInt s: shapeList) {
//      ShapeImpl copy = new ShapeImpl(s);
//      result.add(copy);
    }
    return copyShapeList();
  }

  /**
   * Returns the current list of Shapes.
   * @return Currently outputs the current list of Shapes.
   */
  @Override
  public String getShapes() {
    StringBuilder result = new StringBuilder();
    for (ShapeInt s: shapeList) {
      result.append(s.toString());
      result.append("\n");
    }
    return result.toString();
  }

}
