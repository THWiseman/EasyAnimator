package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and/or calculates the color for a shape object given some time.
 */
public class ColorPattern implements Pattern {
  private Map<Integer, Integer[]> pattern = new HashMap<>();
  //color is best stored in an array that can hold values for R,G, and B.
  //It is mapped to an Integer for time to be consistent with the other patterns, although
  //we could have easily used a 4th field in the array instead.
  private int endTime;

  /**
   * Constructs a new ColorPattern.
   */
  public ColorPattern() {
    this.endTime = 100;
    for (int i = 0; i <= endTime; i++) {
      this.pattern.put(i, new Integer[] {0, 0, 0});
    }
  }

  /**
   * Constructs a new ColorPattern with all RGB values being set to the given R, G, and B.
   * @param r the red value of the shape's color.
   * @param g the green value of the shape's color.
   * @param b the blue value of the shape's color.
   */
  public ColorPattern(Integer r, Integer g, Integer b) {
    if (r > 255 || r < 0 || g > 255 || g < 0 || b < 0 || b > 255 ) {
      throw new IllegalArgumentException("Length and width must be between 0 and 100");
    }
    this.endTime = 100;
    for (int i = 0; i <= endTime; i++) {
      this.pattern.put(i, new Integer[] {r, g, b});
    }
  }

  /**
   * Implements a change to the color values in the ColorPattern. Change is implemented gradually,
   * starting at frame1 and ending at frame2. Changes MUST be implemented in order.
   * @param frame1 the frame at which the change begins.
   * @param frame2 the frame at which the change is over.
   * @param values the color which the shape will change to.
   */
  public void change(Integer frame1, Integer frame2, Integer[] values) {
    if (values.length != 3) {
      throw new IllegalArgumentException("Values must be given as a three Integer array.");
    }

    Integer newR = values[0];
    Integer newG = values[1];
    Integer newB = values[2];

    if (newR > 255 || newR < 0 || newG > 255 || newG < 0 || newB < 0 || newB > 255 ) {
      throw new IllegalArgumentException("Length and width must be between 0 and 100");
    }
    if (frame1 > this.endTime || frame1 < 0 || frame2 > this.endTime || frame2 < 0) {
      throw new IllegalArgumentException("Start and end times must be between 0 and 100");
    }
    if (frame1 > frame2) {
      throw new IllegalArgumentException("End time must be greater than start time");
    }

    int time = frame2 - frame1;
    double changeDifferenceR = newR - this.pattern.get(frame1)[0];
    double changeDifferenceG = newG - this.pattern.get(frame1)[1];
    double changeDifferenceB = newB - this.pattern.get(frame1)[2];

    double incrementR = changeDifferenceR / time;
    double incrementG = changeDifferenceG / time;
    double incrementB = changeDifferenceB / time;

    for (int i = frame1; i < frame2; i++) {
      int alteredFrameNumber = i - frame1;
      double changeFactorR = alteredFrameNumber * incrementR;
      double changeFactorG = alteredFrameNumber * incrementG;
      double changeFactorB = alteredFrameNumber * incrementB;

      Integer[] updatedFrame = new Integer[] {
          (int) (pattern.get(i)[0] + changeFactorR), (int) (pattern.get(i)[1] + changeFactorG),
          (int) (pattern.get(i)[2] + changeFactorB) };
      this.pattern.replace(i, updatedFrame);
    }

    for (int i = frame2; i <= this.endTime; i++) {
      this.pattern.replace(i, new Integer[] {newR, newG, newB});
    }
  }

  /**
   * Gets the RGB color values stored in the ColorPattern at any given frame.
   * @param time the frame that the color values are being pulled from.
   * @return the RGB color values of the given frame.
   */
  public int[] get(Integer time) {
    if (time > this.endTime || time < 0) {
      throw new IllegalArgumentException("Chosen frame must be between 0 and 100");
    }
    try {
      return new int[]{pattern.get(time)[0], pattern.get(time)[1], pattern.get(time)[2]};
    } catch (NullPointerException e) {
      throw new IndexOutOfBoundsException("Color not found at specified time");
    }
  }

  /**
   * toString override method.
   * @return String table of all the Colors at every time stored in the hashmap.
   */
  @Override
  public String toString() {
    String str = "";
    for (int i = 0; i <= this.endTime; i++) {
      str += "\n" + "Frame: " + i + ", R: " + pattern.get(i)[0] + ", G: " + pattern.get(i)[1] +
          ", B: " + pattern.get(i)[2];
    }
    return str.substring(1);
  }
}
