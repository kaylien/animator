import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by echung326 on 3/1/19.
 */
public class AnimationModelImplTest {

  AnimationModel base = new AnimationModelImpl();
  AnimationModel addCommandPass = new AnimationModelImpl();
  AnimationModel addCommandFail = new AnimationModelImpl();
  AnimationModel addShapePass = new AnimationModelImpl();
  AnimationModel addShapeFail = new AnimationModelImpl();
  AnimationModel queryCommandPass = new AnimationModelImpl();
  AnimationModel queryCommandFail = new AnimationModelImpl();
  AnimationModel queryShapePass = new AnimationModelImpl();
  AnimationModel queryShapeFail = new AnimationModelImpl();

  ShapeImpl r_1 = new Rectangle(10,20,40,10,100,200,50);
  ShapeImpl r_2 = new Rectangle(20,30,40,10,100,200,55);
  ShapeImpl r_3 = new Rectangle(30,40,40,10,100,200,60);
  ShapeImpl r_4 = new Rectangle(40,50,40,10,100,200,65);

  ShapeImpl e_1 = new Ellipse(0,0,40,10,100,195,50);
  ShapeImpl e_2 = new Ellipse(10,10,40,10,100,205,55);
  ShapeImpl e_3 = new Ellipse(20,30,40,10,100,200,60);
  ShapeImpl e_4 = new Ellipse(40,50,40,10,100,200,65);

  Command c1 = new Command(10,20,30,40,100,200,50,0,10);
  Command c2 = new Command(10,20,35,40,100,200,50,10,15);
  Command c3 = new Command(10,20,30,40,100,200,50,5,20);
  Command c4 = new Command(10,20,30,40,100,200,50,20,25);

  @Test
  public void testAddCommands() {
    base.addShape(r_1);
    base.addShape(r_2);
    base.addShape(r_3);
    base.addShape(r_4);
    base.addCommands(r_1,c1);
    assertEquals("",base.getCommands());
    base.addCommands(r_2,c2);
    assertEquals("",base.getCommands());
    base.addCommands(r_3,c3);
    assertEquals("",base.getCommands());
  }

  //addCommand

//  @Test
//  void addCommandsI() {
//    assertEquals();
//  }
  //addShape

  //queryCommand

  //queryShape


}
