/**
 * Shape Interface for shapes to be animated in the animation model.
 * The model is able to support various kinds of 2D shapes, although currently we have
 * described only rectangles and ovals. The model is able to support at least
 * describing the size, position, and color of shapes.
 */

public interface ShapeInt {

  /**
   * Provides the list of commands currently nested in a Shape.
   * @return a list of existing Commands that a Shape has
   */
  public String getCommands();

  /**
   * Adds a list of commands to a Shape.
   * @param commands the list of commands to be executed
   */
  public void addCommands(Command... commands);



  /**
   * Deletes a list of commands from a Shape.
   * @param commands the list of commands to be deleted
   */
  //  public void deleteCommands(Command... commands);

}
