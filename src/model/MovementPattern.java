package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and/or calculates the position for a shape object given some time.
 */
public class MovementPattern implements Pattern {

  private Map<Integer, Integer[]> pattern = new HashMap<>();
  //position is most simply stored as an array with X and Y coordinates. It is mapped to integer
  //for time with a hashmap.
  private int endTime;

  /**
   * Constructs a new MovementPattern.
   */
  public MovementPattern() {
    this.endTime = 100;
    for (int i = 0; i <= this.endTime; i++) {
      this.pattern.put(i, new Integer[]{50, 50});
    }
  }

  /**
   * Constructs a new MovementPattern with all values being set to the given X and Y.
   *
   * @param x the x coordinate location of the shape.
   * @param y the y coordinate location of the shape.
   */
  public MovementPattern(Integer x, Integer y) {
    if (x > 1000 || x <= 0 || y > 1000 || y <= 0) {
      throw new IllegalArgumentException("Length and width must be between 0 and 100");
    }
    this.endTime = 100;
    for (int i = 0; i <= this.endTime; i++) {
      this.pattern.put(i, new Integer[]{x, y});
    }
  }

  /**
   * Implements a change to the values in the MovementPattern. Change is implemented gradually,
   * starting at frame1 and ending at frame2. Changes MUST be implemented in order.
   *
   * @param frame1 the frame at which the change begins.
   * @param frame2 the frame at which the change is over.
   * @param values the (X,Y) coordinates to which the shape will move.
   */
  public void change(Integer frame1, Integer frame2, Integer[] values) {
    if (values.length != 2) {
      throw new IllegalArgumentException("Values must be given as a two Integer array.");
    }

    Integer newX = values[0];
    Integer newY = values[1];

    if (newX > 1000 || newX <= 0 || newY > 1000 || newY <= 0) {
      throw new IllegalArgumentException("Length and width must be between 0 and 100");
    }
    if (frame1 > this.endTime || frame1 < 0 || frame2 > this.endTime || frame2 < 0) {
      throw new IllegalArgumentException("Start and end times must be between 0 and 100");
    }
    if (frame1 > frame2) {
      throw new IllegalArgumentException("End time must be greater than start time");
    }


    int time = frame2 - frame1;

    double changeDifferenceX = newX - this.pattern.get(frame1)[0];
    double changeDifferenceY = newY - this.pattern.get(frame1)[1];

    double incrementX = changeDifferenceX / time;
    double incrementY = changeDifferenceY / time;

    for (int i = frame1; i < frame2; i++) {
      int alteredFrameNumber = i - frame1;
      double changeFactorX = alteredFrameNumber * incrementX;
      double changeFactorY = alteredFrameNumber * incrementY;

      Integer[] updatedFrame = new Integer[]{(int) (pattern.get(i)[0] + changeFactorX),
          (int) (pattern.get(i)[1] + changeFactorY)
      };

      this.pattern.replace(i, updatedFrame);
    }

    for (int i = frame2; i <= this.endTime; i++) {
      this.pattern.replace(i, new Integer[]{newX, newY});
    }
  }

  /**
   * Gets the coordinate stored in the MovementPattern at any given frame.
   *
   * @param time the frame that the coordinate are being pulled from.
   * @return the coordinates of the given frame.
   */
  public int[] get(Integer time) {
    if (time > this.endTime || time < 0) {
      throw new IllegalArgumentException("Chosen frame must be between 0 and 100");
    }
    try {
      return new int[]{pattern.get(time)[0], pattern.get(time)[1]};
    } catch (NullPointerException e) {
      throw new IndexOutOfBoundsException("No position found for given time.");
    }
  }

  /**
   * toString override method. Returns a table of the x and y cooridnates for every time stored.
   *
   * @return
   */
  @Override
  public String toString() {
    String str = "";
    for (int i = 0; i <= this.endTime; i++) {
      str += "\n" + "Frame: " + i + ", X: " + pattern.get(i)[0] + ", Y: " +
          pattern.get(i)[1];
    }
    return str.substring(1);
  }
}
