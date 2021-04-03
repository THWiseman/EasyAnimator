package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and/or calculates the position for a shape object given some time.
 */
public class MovementPattern {
  private Map<Integer, Integer[]> pattern = new HashMap<>();

  /**
   * Constructs a new MovementPattern
   */
  public MovementPattern() {
    for (int i = 1; i < 101; i++) {
      this.pattern.put(i, new Integer[] {50, 50});
    }
  }

  /**
   * Constructs a new MovementPattern with all values being set to the given X and Y;
   * @param X the x coordinate location of the shape.
   * @param Y the y coordinate location of the shape.
   */
  public MovementPattern(Integer X, Integer Y) {
    if (X > 1000 || X <= 0 || Y > 1000 || Y <= 0) {
      throw new IllegalArgumentException("Length and width must be between 0 and 100");
    }
    for (int i = 1; i < 101; i++) {
      this.pattern.put(i, new Integer[] {X, Y});
    }
  }

  /**
   * Implements a change to the values in the MovementPattern. Change is implemented gradually,
   * starting at frame1 and ending at frame2. Changes MUST be implemented in order.
   * @param frame1 the frame at which the change begins.
   * @param frame2 the frame at which the change is over.
   * @param newY the x coordinate of the shape after the change is over.
   * @param newY the y coordinate of the shape after the change is over.
   */
  public void change(Integer frame1, Integer frame2, Integer newX, Integer newY) {
    if (newX > 1000 || newX <= 0 || newY > 1000 || newY <= 0) {
      throw new IllegalArgumentException("Length and width must be between 0 and 100");
    }
    if (frame1 > 100 || frame1 < 0 || frame2 > 100 || frame2 < 0) {
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

      Integer[] updatedFrame = new Integer[] {
          (int) (pattern.get(i)[0] + changeFactorX), (int) (pattern.get(i)[1] + changeFactorY)
      };

      this.pattern.replace(i, updatedFrame);
    }

    for (int i = frame2; i < 101; i++) {
      this.pattern.replace(i, new Integer[] {newX, newY});
    }
  }

  /**
   * Gets the coordinate stored in the MovementPattern at any given frame.
   * @param time the frame that the coordinate are being pulled from.
   * @return the coordinates of the given frame.
   */
  public int[] getPosition(Integer time) {
    if (time > 100 || time < 0) {
      throw new IllegalArgumentException("Chosen frame must be between 0 and 100");
    }
    return new int[] {pattern.get(time)[0], pattern.get(time)[1]};
  }

  public String toString() {
    String str = "";
    for (int i = 1; i < 101; i++) {
      str += "\n" + "Frame: " + i + ", X: " + pattern.get(i)[0] + ", Y: " +
          pattern.get(i)[1];
    }
    return str.substring(1);
  }
}
