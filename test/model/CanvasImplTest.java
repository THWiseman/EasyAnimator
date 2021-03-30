package model;
import org.junit.Test;
import static org.junit.Assert.*;


public class CanvasImplTest {

  @org.junit.Before
  public void setUp(){
  }

  @Test
  public void testSetAndGetBackgroundColor() {
    //negative values
    //values over 255
    //not enough arguments
    //too many arguments
    //null
    //two valid cases
    //black
    //white
  }
  @Test
  public void testSetAndGetTime() {
    //negative number
    //number greater than end time
    //number less than non-zero start time
  }

  @Test
  public void testGetStartAndEndTime(){
    //test getStartTime if start is default value
    //test getStartTime if start is set value
    //test getEndTime
  }

  @Test
  public void testGetShapesAtTime() {
    //test before and after change in listOfShapes
    //test before and after shape disappears
    //test before and after shape added
    //test before and after shape removed
    //test at time0
    //test at endTIme
    //test at modified start time
  }
  @Test
  public void testUpdateShapes() {
    //make sure every shape in the list has update() called on it
    //test at time 0
    //test at endTime
  }

  @Test
  public void testAddAndRemoveShape() {
  //testAddShape and testRemoveShape() {
    //remove shape from empty list
//remove shape from 1 element list
    //remove shape with invalid string ID
    //remove shape with null string ID
    //make sure it actually removes shapes
    //make sure it actually adds shape
    //add shape with  stringID that's already taken
    //add shape with null stringID
    //add shape with argument other than String and shape
  }

}