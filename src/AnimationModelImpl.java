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
  public String queryCommands() {
    String result = "";
    String heading = "t x y w h r g b";
    String lines = "----------------";
    result.join("\n",heading,lines);
    for (ShapeImpl shape : shapeList) {
      result.join("\n",shape.getCommands());
    }

    return result;
  }

  @Override
  public String queryShapes() {

    return "";
  }
}
