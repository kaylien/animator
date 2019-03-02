/**
 * Created by echung326 on 3/1/19.
 */
public class Main {
  public static void main(String[] args) {
    ShapeImpl s= new Rectangle(20,30,40,10,100,200,50,10,
      50);
    Command c= new Command(20,30,40,10,100,200,50,1,1);
    Command c1= new Command(20,30,40,10,100,200,12,1,1);
    Command c2= new Command(20,30,40,10,100,200,50,1,1);
    s.addCommands(c);
    System.out.println(s.getCommands());
    s.addCommands(c1);
  }
}
