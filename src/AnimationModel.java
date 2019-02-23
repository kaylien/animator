public interface AnimationModel {

  /**
   * Adds command to the commandList
   * @param t startTime
   * @param x X coordinate
   * @param y Y coordinate
   * @param w Width
   * @param h Height
   * @param r Red
   * @param g Green
   * @param b Blue
   * @return nothing
   */
  void addCommands(int t, int x, int y, int w, int h,int r, int g, int b);

  /**
   * Returns a list of shapes.
   * @param t
   * @param x
   * @param y
   * @param w
   * @param h
   * @param r
   * @param g
   * @param b
   */
  void addShape(int t, int x, int y, int w, int h, int r, int g, int b);

  /**
   * Returns a list of commands for each shape.
   * @return A string representation of a list of commands
   */
  String getCommands();

}
