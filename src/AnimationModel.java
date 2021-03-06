/**
 * The Animation Model represents all the different states
 * of an animation in across time periods.
 */
public interface AnimationModel {

  /**
   * Adds commands to a shape in the shape universe.
   *
   * @param shape the shape to add commands to.
   * @param command the command to add to shapes.
   */
  void addCommands(ShapeImpl shape, Command... command);


  /**
   * Adds a shape to the animation model.
   *
   * @param shape the shape to be added to the AnimationModel.
   */
  void addShape(ShapeImpl shape);

  /**
   * Returns a list of commands for each shape.
   *
   * @return A string representation of a list of commands
   */
  String getCommands();

  /**
   * Returns the list of Shapes in the animation.
   *
   * @return A string representation of a list of shapes
   */
  String getShapes();
}
