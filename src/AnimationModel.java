public interface AnimationModel {
  List<Shape> shapeList;

  void addCommand(Shape shape, Command command);
  void ();
  String getCommands();

  public enum Command {
    String value;

    Move("Move"),
    Appear("Appear"),
    Change("Change");

    }

}
