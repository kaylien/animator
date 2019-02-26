public interface AnimationModel {


  /**
   * Adds commands to a shape in the shape universe.
   * @param shape
   * @param command
   */
  void addCommands(ShapeModel shape,Command command);


  /**
   * Adds a shape to the animation model.
   * @param shape the shape to be added to the AnimationModel.
   */
  void addShape(ShapeModel shape);

  /**
   * Returns a list of commands for each shape.
   * @return A string representation of a list of commands
   */
  String getCommands();

}
