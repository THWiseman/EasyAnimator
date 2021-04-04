package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and/or calculates the length and width for a shape object given some time.
 */
public class SizeChangePattern {
  private Map<Integer, Integer[]> pattern = new HashMap<>();
  private int endTime;

  /**
   * Constructs a new SizeChangePattern.
   */
  public SizeChangePattern() {
    this.endTime = 100;
    for (int i = 0; i <= this.endTime; i++) {
      this.pattern.put(i, new Integer[] {10, 10});
    }
  }

  /**
   * Constructs a new SizeChangePattern with all values being set to the given length and width.
   * @param length The length of the shape.
   * @param width The width of the shape.
   */
  public SizeChangePattern(Integer length, Integer width) {
    if (length > 100 || length <= 0 || width > 100 || width <= 0) {
      throw new IllegalArgumentException("Length and width must be between 0 and 100");
    }
    this.endTime = 100;

    for (int i = 0; i <= this.endTime; i++) {
      this.pattern.put(i, new Integer[] {length, width});
    }
  }

  /**
   * Implements a change to the values in the SizeChangePattern. Change is implemented gradually,
   * starting at frame1 and ending at frame2. Changes MUST be implemented in order.
   * @param frame1 the frame at which the change begins.
   * @param frame2 the frame at which the change is over.
   * @param newLength the length of the shape after the change is over.
   * @param newWidth the width of the shape after the change is over.
   */
  public void change(Integer frame1, Integer frame2, Integer newLength, Integer newWidth) {
    if (newLength > this.endTime || newLength <= 0 || newWidth > this.endTime || newWidth <= 0) {
      throw new IllegalArgumentException("Length and width must be between 0 and 100");
    }
    if (frame1 > this.endTime || frame1 < 0 || frame2 > this.endTime || frame2 < 0) {
      throw new IllegalArgumentException("Start and end times must be between 0 and 100");
    }
    if (frame1 > frame2) {
      throw new IllegalArgumentException("End time must be greater than start time");
    }

    int time = frame2 - frame1;
    double changeDifferenceL = newLength - this.pattern.get(frame1)[0];
    double changeDifferenceW = newWidth - this.pattern.get(frame1)[1];

    double incrementL = changeDifferenceL / time;
    double incrementW = changeDifferenceW / time;

    for (int i = frame1; i < frame2; i++) {
      int alteredFrameNumber = i - frame1;
      double changeFactorL = alteredFrameNumber * incrementL;
      double changeFactorW = alteredFrameNumber * incrementW;

      Integer[] updatedFrame = new Integer[] {
          (int) (pattern.get(i)[0] + changeFactorL), (int) (pattern.get(i)[1] + changeFactorW)
      };

      this.pattern.replace(i, updatedFrame);
    }

    for (int i = frame2; i <= this.endTime; i++) {
      this.pattern.replace(i, new Integer[] {newLength, newWidth});
    }
  }

  /**
   * Gets the dimensions stored in the SizeChangePattern at any given frame.
   * @param time the frame that the dimensions are being pulled from.
   * @return the dimensions of the given frame.
   */
  public int[] getSize(Integer time) {
    if (time > this.endTime || time < 0) {
      throw new IllegalArgumentException("Chosen frame must be between 0 and 100");
    }

    return new int[] {pattern.get(time)[0], pattern.get(time)[1]};
  }

  public String toString() {
    String str = "";
    for (int i = 0; i <= this.endTime; i++) {
      str += "\n" + "Frame: " + i + ", Length: " + pattern.get(i)[0] + ", Width: " +
          pattern.get(i)[1];
    }
    return str.substring(1);
  }
}
