import java.util.ArrayList;
import java.util.List;

/**
 * An Implementation of an Animation that handles Shapes.
 */
public class AnimationModelImpl implements AnimationModel {

  List<ShapeImpl> shapeList;

  AnimationModelImpl() {
    shapeList = new ArrayList<>();
  }

  @Override public void addCommands(ShapeImpl shape, Command... commands) {
    shape.addCommands(commands);
  }

  @Override public void addShape(ShapeImpl shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape is null");
    }
    shapeList.add(shape);
  }

  @Override public String getCommands() {
    StringBuilder sb = new StringBuilder();
    String heading = String
        .format("%-4s %-4s %-4s %-4s %-4s %-4s %-4s %-4s", "t", "x", "y", "w", "h", "r", "g", "b");
    String lines =
        "-------------------------------------      " + " -------------------------------------\n";

    for (ShapeImpl shape : shapeList) {
      sb.append("shape ");
      sb.append(shape.toString());
      sb.append("\n");
      sb.append(heading);
      sb.append("      ");
      sb.append(heading);
      sb.append("\n");
      sb.append(lines);
      sb.append(String.format("%s", shape.getCommands()));
      sb.append("\n");
    }
    return sb.toString();
  }

  /**
   * Returns the current list of Shapes.
   *
   * @return Currently outputs the current list of Shapes.
   */
  @Override public String getShapes() {
    StringBuilder result = new StringBuilder();
    for (ShapeInt s : shapeList) {
      result.append(s.toString());
      result.append("\n");
    }
    return result.toString();
  }

}
