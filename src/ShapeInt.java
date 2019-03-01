import java.util.ArrayList;
import java.util.List;

public interface ShapeInt {

  /**
   * What does the model represent?
   *
   * The model should be able to support various kinds of 2D shapes, although currently we have
   * described only rectangles and ovals.
   *
   * The model should support at least describing the size, position, and color of shapes, though
   * you might support additional features.
   *
   * Remember to think from not only the implementor’s perspective (the person that is implementing
   * the model) but also the client perspective (the people or classes that are using the model).
   * What operations might they reasonably want to perform? What observations might they reasonably
   * want to make?
   *
   * Need to throw IllegalArgumentExceptions if we are trying to add in time-conflicting commands
   * with existing commands -> will have to say delete commands first
   *
   * Maybe sort the shape commands by sorting key values in a hashMap (Collections.sort())
   * List<Shape<Map<Int,Command>>> where the Int is the start time
   *
   * Shapes should be able to do multiple things simultaneously without having the same start times
   *
   * The same variable can't be changing at the same time
   * Command constraints for variables (ex. rgb < 255)
   *
   */

  /**
   *
   * @return a list of
   */
//  public List<String> possibleCommands();

  /**
   * TODO: Remember to return with copies of commands
   * @return a list of existing Commands that a Shape has
   */
//  public String getCommands();

  /**
   * Adds a list of commands to a Shape
   * @param commands the list of commands to be executed
   */
  public void addCommands(Command... commands);

  /**
   * Deletes a list of commands from a Shape
   * @param commands the list of commands to be deleted
   */
//  public void deleteCommands(Command... commands);

}
