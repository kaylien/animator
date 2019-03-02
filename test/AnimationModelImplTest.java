import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by echung326 on 3/1/19.
 */
public class AnimationModelImplTest {
  AnimationModel base = new AnimationModelImpl();

  ShapeImpl r_1;
  ShapeImpl r_4;
  ShapeImpl e_1;
  ShapeImpl e_2;
  Command c1;
  Command c2;
  Command c3;
  Command c4;
  Command c5;
  Command c6;
  Command c7;
  Command cr1;
  Command cr2;
  Command cr3;
  Command cr4;
  Command cr5;
  Command cr6;
  Command cr7;

  /**
   * Initializes the Test Environment for each test.
   */
  public void initializeTestEnvironment() {

    this.base = new AnimationModelImpl();

    this.r_1 = new Rectangle(0, 0, 10, 10, 0, 0, 0, 0, 20);
    this.r_4 = new Rectangle(40, 50, 40, 10, 100, 200, 65, 0, 20);

    this.e_1 = new Ellipse(0, 0, 40, 10, 100, 195, 50, 0, 20);
    this.e_2 = new Ellipse(10, 10, 40, 10, 100, 205, 55, 0, 20);

    this.c1 = new Command(0, 10, 10, 10, 0, 0, 0, 0, 10);
    this.c2 = new Command(0, 5, 10, 10, 0, 0, 0, 5, 15);
    this.c3 = new Command(0, 5, 10, 10, 0, 0, 0, 0, 5);
    this.c4 = new Command(0, 10, 10, 10, 0, 0, 0, 5, 10);
    this.c5 = new Command(0, 10, 10, 10, 0, 0, 0, 6, 10);
    this.c6 = new Command(0, 15, 10, 10, 0, 0, 0, 1, 9);
    this.c7 = new Command(0, 10, 10, 10, 0, 10, 10, 0, 10);

    this.cr1 = new Command(0, 10, 10, 10, 0, 15, 0, 0, 10);
    this.cr2 = new Command(0, 5, 10, 10, 0, 10, 0, 5, 15);
    this.cr3 = new Command(0, 20, 10, 10, 0, 10, 0, 0, 5);
    this.cr4 = new Command(0, 10, 10, 10, 0, 0, 0, 5, 10);
    this.cr5 = new Command(0, 25, 10, 10, 0, 20, 0, 5, 10);
    this.cr6 = new Command(0, 15, 10, 10, 0, 0, 0, 1, 9);
    this.cr7 = new Command(0, 10, 10, 10, 0, 10, 10, 0, 10);

  }

  //Constructor Tests.
  @Test(expected = IllegalArgumentException.class) public void negativeRectangleX() {
    ShapeImpl r_1 = new Rectangle(-10, 0, 10, 10, 0, 0, 0, 0, 20);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeRectangleY() {
    ShapeImpl r_1 = new Rectangle(10, -10, 10, 10, 0, 0, 0, 0, 20);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeRectangleWidth() {
    ShapeImpl r_1 = new Rectangle(10, 0, -10, 10, 0, 0, 0, 0, 20);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeRectangleHeight() {
    ShapeImpl r_1 = new Rectangle(10, 0, 10, -10, 0, 0, 0, 0, 20);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeRectangleR() {
    ShapeImpl r_1 = new Rectangle(10, 0, 10, 10, -10, 10, 10, 0, 20);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeRectangleG() {
    ShapeImpl r_1 = new Rectangle(10, 0, 10, 10, 10, -10, 10, 0, 20);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeRectangleB() {
    ShapeImpl r_1 = new Rectangle(10, 0, 10, 10, 10, 10, -10, 0, 20);
  }

  //Ellipse Constructor Tests.
  @Test(expected = IllegalArgumentException.class) public void negativeEllipseX() {
    ShapeImpl e_1 = new Ellipse(-10, 0, 10, 10, 0, 0, 0, 0, 20);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeEllipseY() {
    ShapeImpl e_1 = new Ellipse(10, -10, 10, 10, 0, 0, 0, 0, 20);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeEllipseWidth() {
    ShapeImpl r_1 = new Ellipse(10, 0, -10, 10, 0, 0, 0, 0, 20);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeEllipseHeight() {
    ShapeImpl r_1 = new Ellipse(10, 0, 10, -10, 0, 0, 0, 0, 20);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeEllipseR() {
    ShapeImpl r_1 = new Ellipse(10, 0, 10, 10, -10, 10, 10, 0, 20);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeEllipseG() {
    ShapeImpl r_1 = new Ellipse(10, 0, 10, 10, 10, -10, 10, 0, 20);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeEllipseB() {
    ShapeImpl r_1 = new Ellipse(10, 0, 10, 10, 10, 10, -10, 0, 20);
  }

  //Command Constructor Tests.
  @Test(expected = IllegalArgumentException.class) public void negativeCommandX() {
    Command c_1 = new Command(-10, 0, 10, 10, 0, 0, 0, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeCommandY() {
    Command c_1 = new Command(10, -10, 10, 10, 0, 0, 0, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeCommandWidth() {
    Command c_1 = new Command(10, 0, -10, 10, 0, 0, 0, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeCommandHeight() {
    Command c_1 = new Command(10, 0, 10, -10, 0, 0, 0, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeCommandR() {
    Command c_1 = new Command(10, 0, 10, 10, -10, 10, 10, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeCommandG() {
    Command c_1 = new Command(10, 0, 10, 10, 10, -10, 10, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeCommandB() {
    Command c_1 = new Command(10, 0, 10, 10, 10, 10, -10, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeCommandStart() {
    Command c_1 = new Command(10, 0, 10, 10, 10, 10, 10, -5, 10);
  }

  @Test(expected = IllegalArgumentException.class) public void negativeCommandEnd() {
    Command c_1 = new Command(10, 0, 10, 10, 10, 10, 10, 5, -10);
  }

  //addCommand tests.
  @Test public void testAddCommandsOverlappingPeriodConflictI() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addCommands(r_1, c1);
    base.addCommands(r_1, c2);
    assertEquals("shape Rectangle\n"
        + "t    x    y    w    h    r    g    b         t    x    y    w    h    r    g    b   \n"
        + "-------------------------------------       -------------------------------------\n"
        + "0    0    0    0    0    0    10   10        0   0    0    0    0    0    10   10  \n"
        + "\n", base.getCommands());
  }

  @Test public void testAddCommandsOverlappingPeriodConflictII() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addCommands(r_1, c2);
    base.addCommands(r_1, c1);
    assertEquals("shape Rectangle\n"
        + "t    x    y    w    h    r    g    b         t    x    y    w    h    r    g    b   \n"
        + "-------------------------------------       -------------------------------------\n"
        + "0    0    0    0    0    0    10   10        0   0    0    0    0    0    10   10  \n"
        + "\n", base.getCommands());
  }

  @Test public void testAddCommandsInternalPeriodConflict() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addCommands(r_1, c1);
    base.addCommands(r_1, c3);
    base.addCommands(r_1, c5);
    assertEquals("shape Rectangle\n"
        + "t    x    y    w    h    r    g    b         t    x    y    w    h    r    g    b   \n"
        + "-------------------------------------       -------------------------------------\n"
        + "0    0    0    0    0    0    10   10        0   0    0    0    0    0    10   10  \n"
        + "\n", base.getCommands());
  }

  @Test public void testAddCommandsIdenticalPeriodConflict() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addCommands(r_1, c4);
    base.addCommands(r_1, c5);
    assertEquals("shape Rectangle\n"
        + "t    x    y    w    h    r    g    b         t    x    y    w    h    r    g    b   \n"
        + "-------------------------------------       -------------------------------------\n"
        + "0    0    0    0    0    0    10   10        0   0    0    0    0    0    10   10  \n"
        + "\n", base.getCommands());
  }

  @Test public void testAddCommandsVariableConflictI() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addCommands(r_1, c1);
    base.addCommands(r_1, c6);
    assertEquals("shape Rectangle\n"
        + "t    x    y    w    h    r    g    b         t    x    y    w    h    r    g    b   \n"
        + "-------------------------------------       -------------------------------------\n"
        + "0    0    0    0    0    0    10   10        0   0    0    0    0    0    10   10  \n"
        + "\n", base.getCommands());
  }

  @Test public void testAddCommandsVariableConflictII() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addCommands(r_1, c6);
    base.addCommands(r_1, c1);
    assertEquals("shape Rectangle\n"
        + "t    x    y    w    h    r    g    b         t    x    y    w    h    r    g    b   \n"
        + "-------------------------------------       -------------------------------------\n"
        + "0    0    0    0    0    0    10   10        0   0    0    0    0    0    10   10  \n"
        + "\n", base.getCommands());
  }

  @Test public void testNullCommand() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addCommands(r_1, null);
    assertEquals("shape Rectangle\n"
        + "t    x    y    w    h    r    g    b         t    x    y    w    h    r    g    b   \n"
        + "-------------------------------------       -------------------------------------\n"
        + "0    0    0    0    0    0    10   10        0   0    0    0    0    0    10   10  \n"
        + "\n", base.getCommands());
  }

  @Test public void testAddCommandsPassNonConflictingPeriods() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addCommands(r_1, c3);
    base.addCommands(r_1, c4);
    assertEquals("shape Rectangle\n"
        + "t    x    y    w    h    r    g    b         t    x    y    w    h    r    g    b   \n"
        + "-------------------------------------       -------------------------------------\n"
        + "0    0    0    0    0    0    10   10        0   0    0    0    0    0    10   10  \n"
        + "\n", base.getCommands());
  }

  @Test public void testAddCommandsPassVariedVariableChanges() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addCommands(r_1, c1);
    base.addCommands(r_1, c7);
    assertEquals("shape Rectangle\n"
        + "t    x    y    w    h    r    g    b         t    x    y    w    h    r    g    b   \n"
        + "-------------------------------------       -------------------------------------\n"
        + "0    0    0    0    0    0    10   10        0   0    0    0    0    0    10   10  \n"
        + "\n", base.getCommands());
  }

  //add shape tests.
  @Test public void testEmptyShapeList() {
    initializeTestEnvironment();
    assertEquals("", base.getShapes());
  }

  @Test(expected = IllegalArgumentException.class) public void testNullShape() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addShape(null);
    assertEquals("", base.getShapes());
  }

  @Test public void testAddShapesTwoRectangles() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addShape(r_4);
    assertEquals("Rectangle\nRectangle\n", base.getShapes());
  }

  @Test public void testAddShapesRectangleEllipse() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addShape(e_2);
    assertEquals("Rectangle\nEllipse\n", base.getShapes());
  }

  @Test public void testAddShapesAndCommands() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addShape(e_2);
    base.addCommands(r_1, c1);
    base.addCommands(r_1, c7);
    base.addCommands(e_2, c1);
    base.addCommands(e_2, c7);
    assertEquals("shape Rectangle\n"
        + "t    x    y    w    h    r    g    b         t    x    y    w    h    r    g    b   \n"
        + "-------------------------------------       -------------------------------------\n"
        + "0    0    0    0    0    0    10   10        0   0    0    0    0    0    10   10  \n"
        + "\n" + "shape Ellipse\n"
        + "t    x    y    w    h    r    g    b         t    x    y    w    h    r    g    b   \n"
        + "-------------------------------------       -------------------------------------\n"
        + "0    10   10   100  205  55   40   10        0   10   10   100  205  55   40   10  \n"
        + "\n", base.getCommands());
  }

  @Test public void testAddCommandsNonConflictingVariable() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addCommands(r_1, c1);
    base.addCommands(r_1, cr1);
    assertEquals("shape Rectangle\n"
        + "t    x    y    w    h    r    g    b         t    x    y    w    h    r    g    b   \n"
        + "-------------------------------------       -------------------------------------\n"
        + "0    0    0    0    0    0    10   10        0   0    0    0    0    0    10   10  \n"
        + "\n", base.getCommands());
  }

  @Test public void testAddCommandsNonConflictingVariableII() {
    initializeTestEnvironment();
    base.addShape(r_1);
    base.addCommands(r_1, c1, cr2);
    assertEquals("shape Rectangle\n"
        + "t    x    y    w    h    r    g    b         t    x    y    w    h    r    g    b   \n"
        + "-------------------------------------       -------------------------------------\n"
        + "0    0    0    0    0    0    10   10        0   0    0    0    0    0    10   10  \n"
        + "\n", base.getCommands());
  }

}
