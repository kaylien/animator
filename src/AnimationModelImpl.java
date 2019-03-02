import java.awt.Shape;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * An Implementation of an Animation that handles Shapes.
 */
public class AnimationModelImpl implements AnimationModel {

  List<ShapeImpl> shapeList;
  AnimationModelImpl(){
    shapeList = new ArrayList<>();
  }

  @Override
  public void addCommands(ShapeImpl shape, Command... commands) {
//    shape.addCommands(commands);
  }

  @Override
  public void addShape(ShapeImpl shape) {
    if(shape == null) {
      throw new IllegalArgumentException("Shape is null");
    }
    shapeList.add(shape);
  }

  @Override
  public String getCommands() {
    StringBuilder sb = new StringBuilder();
    String heading = "st et x y w h r g b\n";
    String lines = "----------------------\n";
    for (ShapeImpl shape : shapeList) {
      sb.append(shape.toString());
      sb.append("\n");
      sb.append(heading);
      sb.append(lines);
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
