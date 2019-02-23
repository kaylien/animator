import java.util.List;

/**
 * Created by echung326 on 2/23/19.
 */
public class AnimationModelImpl implements AnimationModel {
  List<Command> commandList;

  AnimationModelImpl(List<Command> commandList){
    this.commandList = commandList;
  }

  @Override public void addCommands(ShapeModel shape, Command command) {

  }

  @Override public void addShape(ShapeModel shape) {

  }

  @Override public String getCommands() {
    return null;
  }

}
