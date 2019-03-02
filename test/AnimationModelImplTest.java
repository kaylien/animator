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

  ShapeImpl r_1 = new Rectangle(0,0,10,10,0,0,0);
  ShapeImpl r_2 = new Rectangle(20,30,40,10,100,200,55);
  ShapeImpl r_3 = new Rectangle(30,40,40,10,100,200,60);
  ShapeImpl r_4 = new Rectangle(40,50,40,10,100,200,65);

  ShapeImpl e_1 = new Ellipse(0,0,40,10,100,195,50);
  ShapeImpl e_2 = new Ellipse(10,10,40,10,100,205,55);
  ShapeImpl e_3 = new Ellipse(20,30,40,10,100,200,60);
  ShapeImpl e_4 = new Ellipse(40,50,40,10,100,200,65);

  Command c1 = new Command(0,10,10,10,0,0,0,0,10);
  Command c2 = new Command(0,5,10,10,0,0,0,5,15);
  Command c3 = new Command(0,20,10,10,0,0,0,0,5);
  Command c4 = new Command(0,10,10,10,0,0,0,5,10);
  Command c5 = new Command(0,25,10,10,0,0,0,5,10);
  Command c6 = new Command(0,15,10,10,0,0,0,1,9);

  public void initializeTestEnvironment() {

    AnimationModel base = new AnimationModelImpl();
    AnimationModel addCommandPass = new AnimationModelImpl();
    AnimationModel addCommandFail = new AnimationModelImpl();
    AnimationModel addShapePass = new AnimationModelImpl();
    AnimationModel addShapeFail = new AnimationModelImpl();
    AnimationModel queryCommandPass = new AnimationModelImpl();
    AnimationModel queryCommandFail = new AnimationModelImpl();
    AnimationModel queryShapePass = new AnimationModelImpl();
    AnimationModel queryShapeFail = new AnimationModelImpl();

    ShapeImpl r_1 = new Rectangle(0,0,10,10,0,0,0);
    ShapeImpl r_2 = new Rectangle(20,30,40,10,100,200,55);
    ShapeImpl r_3 = new Rectangle(30,40,40,10,100,200,60);
    ShapeImpl r_4 = new Rectangle(40,50,40,10,100,200,65);

    ShapeImpl e_1 = new Ellipse(0,0,40,10,100,195,50);
    ShapeImpl e_2 = new Ellipse(10,10,40,10,100,205,55);
    ShapeImpl e_3 = new Ellipse(20,30,40,10,100,200,60);
    ShapeImpl e_4 = new Ellipse(40,50,40,10,100,200,65);

    Command c1 = new Command(0,10,10,10,0,0,0,0,10);
    Command c2 = new Command(0,5,10,10,0,0,0,5,15);
    Command c3 = new Command(0,20,10,10,0,0,0,0,5);
    Command c4 = new Command(0,10,10,10,0,0,0,5,10);
    Command c5 = new Command(0,25,10,10,0,0,0,6,10);
    Command c6 = new Command(0,15,10,10,0,0,0,1,9);

  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddCommandsEdgeCaseI() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addCommands(r_1,c1);
    base.addCommands(r_1,c2);
    assertEquals("",base.getCommands());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddCommandsEdgeCaseII() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addCommands(r_1,c2);
    base.addCommands(r_1,c1);
    assertEquals("",base.getCommands());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddCommandsEdgeCaseIII() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addCommands(r_1,c1);
    base.addCommands(r_1,c3);
    base.addCommands(r_1,c5);
    assertEquals("",base.getCommands());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddCommandsEdgeCaseIV() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addCommands(r_1,c4);
    base.addCommands(r_1,c5);
    assertEquals("",base.getCommands());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddCommandsEdgeCaseV() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addCommands(r_1,c1);
    base.addCommands(r_1,c6);
    assertEquals("",base.getCommands());
  }

  @Test
  public void testAddCommandsPass() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addCommands(r_1,c3);
    base.addCommands(r_1,c4);
    assertEquals("",base.getCommands());
  }

  //addCommand

  //addShape
  @Test (expected = NullPointerException.class)
  public void testAddShapeI() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addShape(null);
    assertEquals("",base.getCommands());
  }

  @Test (expected = NullPointerException.class)
  public void testAddShapeII() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addShape(null);
    assertEquals("",base.getCommands());
  }

  @Test (expected = NullPointerException.class)
  public void testAddShapeIII() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addShape(r_4);
    assertEquals("",base.getCommands());
  }

  //getCommand

  @Test (expected = NullPointerException.class)
  public void testAddShapeIV() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addShape(r_3);
    assertEquals("",base.getCommands());
  }

  @Test
  public void testAddShapeV() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addShape(r_2);
    assertEquals("",base.getShapes());
  }

}
