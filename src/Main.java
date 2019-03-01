/**
 * Created by echung326 on 3/1/19.
 */
public class Main {
  public static void main(String[] args) {
    ShapeImpl s= new Rectangle(20,30,40,10,100,200,50);
    Command c= new Command(20,30,40,10,100,200,50,1,1);
    s.addCommands(c);
  }
}
