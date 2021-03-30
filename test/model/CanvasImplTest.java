package model;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class CanvasImplTest {
  Canvas defaultCanvas;
  Canvas small;
  Canvas big;

  @org.junit.Before
  public void setUp(){
    defaultCanvas = new CanvasImpl();
    small = new CanvasImpl(1,1,5);
    small.addShape(new Rectangle(5,5,new MasterPatternImpl()), "1");
    big = new CanvasImpl(100,150,200);
    big.addShape(new Rectangle(5,5,new MasterPatternImpl()), "1");
    big.addShape(new Rectangle(5,5,new MasterPatternImpl()), "2");
    big.addShape(new Rectangle(5,5,new MasterPatternImpl()), "3");
  }

  @Test
  public void testSetAndGetBackgroundColor() {
    int[] white = new int[]{255,255,255};
    int[] black = new int[]{0,0,0};
    int[] grey = new int[]{150,150,150};
    //test background color initialized correctly in default canvas
    assertEquals(true, Arrays.equals(white, defaultCanvas.getBackgroundColor()));
    //test background color initialized correctly in custom canvas
    assertEquals(true, Arrays.equals(white, small.getBackgroundColor()));
    assertEquals(true, Arrays.equals(white, big.getBackgroundColor()));

    //test set/get background color to black.
    defaultCanvas.setBackgroundColor(black);
    small.setBackgroundColor(black);
    assertEquals(true, Arrays.equals(black, defaultCanvas.getBackgroundColor()));
    assertEquals(true, Arrays.equals(black, small.getBackgroundColor()));

    //test set/get background color to grey.
    defaultCanvas.setBackgroundColor(grey);
    big.setBackgroundColor(grey);
    assertEquals(true, Arrays.equals(grey, defaultCanvas.getBackgroundColor()));
    assertEquals(true, Arrays.equals(grey, big.getBackgroundColor()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeColor(){
    int[] invalid = new int[]{-1,100,100};
    defaultCanvas.setBackgroundColor(invalid);
  }

  @Test(expected = IllegalArgumentException.class)
  public void tooLargeColor(){
    int[] invalid = new int[]{256,100,100};
    defaultCanvas.setBackgroundColor(invalid);
  }

  @Test(expected = NullPointerException.class)
  public void nullColor(){
    int[] invalid = null;
    defaultCanvas.setBackgroundColor(invalid);
  }

  @Test(expected = IllegalArgumentException.class)
  public void arrayTooSmall(){
    int[] invalid = new int[]{100,0};
    defaultCanvas.setBackgroundColor(invalid);
  }

  @Test(expected = IllegalArgumentException.class)
  public void arrayTooBig(){
    int[] invalid = new int[]{100,100,100,100};
    defaultCanvas.setBackgroundColor(invalid);
  }
  @Test
  public void testSetAndGetTime() {
    assertEquals(0,defaultCanvas.getTime());
    assertEquals(0,small.getTime());
    assertEquals(0,big.getTime());
    defaultCanvas.setTime(5);
    big.setTime(10);
    assertEquals(5,defaultCanvas.getTime());
    assertEquals(10,big.getTime());
    //set time back to zero
    defaultCanvas.setTime(0);
    big.setTime(0);
    assertEquals(0,small.getTime());
    assertEquals(0,big.getTime());
    //set time to current time
    defaultCanvas.setTime(0);
    big.setTime(0);
    assertEquals(0,small.getTime());
    assertEquals(0,big.getTime());
    //number greater than end time
    //number less than non-zero start time
  }

  @Test(expected = IllegalArgumentException.class)
  public void setNegativeTime() {
    defaultCanvas.setTime(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setTimeAfterEnd() {
    small.setTime(6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setTimeBeforeStart() {
    small.setStartTime(6);
    small.setTime(5);
  }

  @Test
  public void testGetStartAndEndTime(){
    assertEquals(0,defaultCanvas.getStartTime());
    assertEquals(100,defaultCanvas.getEndTime());
    assertEquals(0,big.getStartTime());
    assertEquals(200,big.getEndTime());
    assertEquals(0,small.getStartTime());
    assertEquals(5,small.getEndTime());

    small.setStartTime(4);
    small.setEndTime(6);
    assertEquals(4,small.getStartTime());
    assertEquals(6, small.getEndTime());
  }

  @Test
  public void testGetAllShapes() {
    //test on empty list
    List<Shape> emptyShapes = new ArrayList<>();
    List<String> emptyStrings = new ArrayList<>();
    assertEquals(true, emptyShapes.equals(defaultCanvas.getAllShapes()));
    assertEquals(true,emptyStrings.equals(defaultCanvas.getAllShapeIDs()));

    //make shapes
    Shape rec1 = new Rectangle(5,6,new MasterPatternImpl());
    Shape rec2 = new Rectangle(5,6,new MasterPatternImpl());
    Shape ov1 = new Oval(5,6,new MasterPatternImpl());
    Shape ov2 = new Oval(5,6,new MasterPatternImpl());

    //add one shape and test
    defaultCanvas.addShape(rec1,"1");
    emptyShapes.add(rec1);
    emptyStrings.add("1");
    assertEquals(true, emptyShapes.equals(defaultCanvas.getAllShapes()));
    assertEquals(true, emptyStrings.equals(defaultCanvas.getAllShapeIDs()));

    //add three more shapes and test
    defaultCanvas.addShape(rec2,"2");
    emptyShapes.add(rec2);
    emptyStrings.add("2");
    defaultCanvas.addShape(ov1,"3");
    emptyShapes.add(ov1);
    emptyStrings.add("3");
    defaultCanvas.addShape(ov2,"4");
    emptyShapes.add(ov2);
    emptyStrings.add("4");
    assertEquals(true, emptyShapes.equals(defaultCanvas.getAllShapes()));
    assertEquals(true, emptyStrings.equals(defaultCanvas.getAllShapeIDs()));
  }

  @Test
  public void testGetShapesAtTime() { //waiting on Pattern Implementation.
    //test before and after shape disappears
    //test before and after shape added
    //test before and after shape removed
    //test at time0
    //test at endTIme
    //test at modified start time
  }
  @Test
  public void testUpdateShapes() { //waiting on Pattern Implementation
    //make sure every shape in the list has update() called on it
    //test at time 0
    defaultCanvas.updateShapes(0);
    big.updateShapes(0);
    small.updateShapes(0);
    //test at endTime
    defaultCanvas.updateShapes(defaultCanvas.getEndTime());
    big.updateShapes(big.getEndTime());
    small.updateShapes(small.getEndTime());
  }

  @Test
  public void testAddAndRemoveShape() {
    List<Shape> emptyShapes = new ArrayList<>();
    List<String> emptyStrings = new ArrayList<>();
    Shape rec1 = new Rectangle(5,6,new MasterPatternImpl());
    Shape rec2 = new Rectangle(5,6,new MasterPatternImpl());
    Shape ov1 = new Oval(5,6,new MasterPatternImpl());
    Shape ov2 = new Oval(5,6,new MasterPatternImpl());

    //add one shape and test
    defaultCanvas.addShape(rec1,"1");
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
    defaultCanvas.addShape(rec1,"1");
    emptyShapes.add(rec1);
    emptyStrings.add("1");
    defaultCanvas.addShape(rec2,"2");
    emptyShapes.add(rec2);
    emptyStrings.add("2");
    defaultCanvas.addShape(ov1,"3");
    emptyShapes.add(ov1);
    emptyStrings.add("3");
    defaultCanvas.addShape(ov2,"4");
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
    assertEquals(false,defaultCanvas.removeShape("invalid"));
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
    assertEquals(false,defaultCanvas.removeShape("1"));

    //add shape with argument other than String and shape
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRedundantID() {
    //add shape with  stringID that's already taken
    small.addShape(new Rectangle(4,4, new MasterPatternImpl()),"1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullID() {
    //add shape with null stringID
    small.addShape(new Rectangle(4,4,new MasterPatternImpl()),null);
  }
  @Test(expected = NullPointerException.class)
  public void removeNullID() {
    //remove shape with null string ID
    small.removeShape(null);
  }

  @Test


}