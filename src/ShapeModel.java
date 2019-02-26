public interface ShapeModel {

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
   * Maybe sort the shape commands by sorting key values in a hashMap
   *
   * Shapes should be able to do multiple things simultaneously without having the same start times
   */


}
