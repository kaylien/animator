import java.awt.Shape;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by echung326 on 2/23/19.
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

    for (ShapeImpl shape : shapeList) {
      sb.append(shape.getCommands());
    }

    return sb.toString();
  }

  @Override
  public String queryCommands() {
    return "";
  }

  @Override
  public String queryShapes() {
    return "";
  }

}
