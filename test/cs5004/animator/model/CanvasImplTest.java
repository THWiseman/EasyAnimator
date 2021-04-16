package cs5004.animator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Canvas interface.
 */

public class CanvasImplTest {

  Canvas defaultCanvas;
  Canvas small;
  Canvas big;

  Shape rec1;
  Shape rec2;
  Shape rec3;
  Shape ov1;
  Shape ov2;
  Shape ov3;
  ColorPattern c1;
  MovementPattern m1;
  SizeChangePattern s1;

  ColorPattern c2;
  MovementPattern m2;
  SizeChangePattern s2;

  @Before
  public void setUp() {
    c1 = new ColorPattern();
    m1 = new MovementPattern();
    s1 = new SizeChangePattern();

    c2 = new ColorPattern();
    c2.change(1, 10, new Integer[]{1, 2, 3}, new Integer[]{100, 150, 200});
    m2 = new MovementPattern();
    m2.change(20, 50, new Integer[]{100, 200}, new Integer[]{300, 400});
    s2 = new SizeChangePattern();
    s2.change(100, 200, new Integer[]{1, 4}, new Integer[]{50, 50});

    rec1 = new Rectangle();
    rec2 = new Rectangle(c1, m1, s1);
    rec3 = new Rectangle(c2, m2, s2);
    ov1 = new Oval();
    ov2 = new Oval(c1, m1, s1);
    ov3 = new Oval(c2, m1, s2);

    defaultCanvas = new CanvasImpl();
    small = new CanvasImpl(5);
    small.addShape(rec1, "1");
    big = new CanvasImpl(200);
    big.addShape(rec1, "1");
    big.addShape(rec2, "2");
    big.addShape(ov1, "3");
    big.addShape(rec3, "4");
    big.addShape(ov3, "5");
  }


  @Test
  public void testGetStartAndEndTime() {
    assertEquals(0, defaultCanvas.getStartTime());
    assertEquals(100, defaultCanvas.getEndTime());
    assertEquals(0, big.getStartTime());
    assertEquals(200, big.getEndTime());
    assertEquals(0, small.getStartTime());
    assertEquals(5, small.getEndTime());


  }

  @Test
  public void testGetAllShapes() {
    //test on empty list
    List<Shape> emptyShapes = new ArrayList<>();
    List<String> emptyStrings = new ArrayList<>();
    assertEquals(true, emptyShapes.equals(defaultCanvas.getAllShapes()));
    assertEquals(true, emptyStrings.equals(defaultCanvas.getAllShapeIDs()));

    //add one shape and test
    defaultCanvas.addShape(rec1, "1");
    emptyShapes.add(rec1);
    emptyStrings.add("1");
    assertEquals(true, emptyShapes.equals(defaultCanvas.getAllShapes()));
    assertEquals(true, emptyStrings.equals(defaultCanvas.getAllShapeIDs()));

    //add three more shapes and test
    defaultCanvas.addShape(rec2, "2");
    emptyShapes.add(rec2);
    emptyStrings.add("2");
    defaultCanvas.addShape(ov1, "3");
    emptyShapes.add(ov1);
    emptyStrings.add("3");
    defaultCanvas.addShape(ov2, "4");
    emptyShapes.add(ov2);
    emptyStrings.add("4");
    assertEquals(true, emptyShapes.equals(defaultCanvas.getAllShapes()));
    assertEquals(true, emptyStrings.equals(defaultCanvas.getAllShapeIDs()));
  }


  @Test
  public void testAddAndRemoveShape() {
    List<Shape> emptyShapes = new ArrayList<>();
    List<String> emptyStrings = new ArrayList<>();

    //add one shape and test
    defaultCanvas.addShape(rec1, "1");
    emptyShapes.add(rec1);
    emptyStrings.add("1");
    assertEquals(true, emptyShapes.equals(defaultCanvas.getAllShapes()));
    assertEquals(true, emptyStrings.equals(defaultCanvas.getAllShapeIDs()));

    //remove one shape and test
    defaultCanvas.removeShape("1");
    emptyShapes.remove(rec1);
    emptyStrings.remove(0);
    assertEquals(true, emptyShapes.equals(defaultCanvas.getAllShapes()));
    assertEquals(true, emptyStrings.equals(defaultCanvas.getAllShapeIDs()));

    //add four shapes and test
    defaultCanvas.addShape(rec1, "1");
    emptyShapes.add(rec1);
    emptyStrings.add("1");
    defaultCanvas.addShape(rec2, "2");
    emptyShapes.add(rec2);
    emptyStrings.add("2");
    defaultCanvas.addShape(ov1, "3");
    emptyShapes.add(ov1);
    emptyStrings.add("3");
    defaultCanvas.addShape(ov2, "4");
    emptyShapes.add(ov2);
    emptyStrings.add("4");
    assertEquals(true, emptyShapes.equals(defaultCanvas.getAllShapes()));
    assertEquals(true, emptyStrings.equals(defaultCanvas.getAllShapeIDs()));

    //remove two shapes and test
    defaultCanvas.removeShape("2");
    defaultCanvas.removeShape("4");
    emptyShapes.remove(1);
    emptyShapes.remove(2);
    emptyStrings.remove(1);
    emptyStrings.remove(2);
    assertEquals(true, emptyShapes.equals(defaultCanvas.getAllShapes()));
    assertEquals(true, emptyStrings.equals(defaultCanvas.getAllShapeIDs()));

    //verify that there is no mutation
    defaultCanvas.getAllShapes().add(ov2);
    assertEquals(true, emptyShapes.equals(defaultCanvas.getAllShapes()));

    //remove shape with invalid string ID
    assertEquals(false, defaultCanvas.removeShape("invalid"));
    //empty the list
    defaultCanvas.removeShape("1");
    defaultCanvas.removeShape("3");
    emptyShapes.remove(0);
    emptyShapes.remove(0);
    emptyStrings.remove(0);
    emptyStrings.remove(0);
    assertEquals(true, emptyShapes.equals(defaultCanvas.getAllShapes()));
    assertEquals(true, emptyStrings.equals(defaultCanvas.getAllShapeIDs()));

    //remove shape from empty list
    assertEquals(false, defaultCanvas.removeShape("1"));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testRedundantID() {
    //add shape with  stringID that's already taken
    small.addShape(rec1, "1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullID() {
    //add shape with null stringID
    small.addShape(rec2, null);
  }

  @Test(expected = NullPointerException.class)
  public void removeNullID() {
    //remove shape with null string ID
    small.removeShape(null);
  }

  @Test
  public void testEquality() {
    assertFalse(small.equals(big));
    assertTrue(small.equals(small));
    Canvas test1 = new CanvasImpl(100);
    Canvas test2 = new CanvasImpl(100);
    Canvas test3 = new CanvasImpl(100);
    assertTrue(test1.equals(test2));
    assertTrue(test1.equals(test3));
  }

  @Test
  public void testGetShape() {
    assertEquals(rec1, small.getShape("1"));
    assertEquals(rec1, big.getShape("1"));
    assertEquals(rec2, big.getShape("2"));
    assertEquals(ov1, big.getShape("3"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetShapeThrow() {
    small.getShape("30");
  }


}
