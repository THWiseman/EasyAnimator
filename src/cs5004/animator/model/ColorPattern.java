package cs5004.animator.model;

import java.util.HashMap;

/**
 * Stores and/or calculates the color for a shape object given some time.
 */
public class ColorPattern extends AbstractPattern {

  /**
   * Constructs a new ColorPattern.
   */
  public ColorPattern() {
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

    if (newR > 255 || newR < 0 || newG > 255 || newG < 0 || newB < 0 || newB > 255) {
      throw new IllegalArgumentException("Color values must be between 0 and 255.");
    }
    super.change(frame1, frame2, values);

    super.changeTracker(PatternType.COLOR, frame1, frame2, pattern.get(frame1), values);
  }


  /////////////////////////////////////////////////////////

  public void change(Integer frame1, Integer frame2, Integer[] startValues, Integer[] endValues) {
    if (endValues.length != 3) {
      throw new IllegalArgumentException("Values must be given as a two Integer array.");
    }

    Integer newR = endValues[0];
    Integer newG = endValues[1];
    Integer newB = endValues[2];

    if (newR > 255 || newR < 0 || newG > 255 || newG < 0 || newB < 0 || newB > 255) {
      throw new IllegalArgumentException("Length and width must be between 0 and 100");
    }
    super.change(frame1, frame2, startValues, endValues);

    super.changeTracker(PatternType.COLOR, frame1, frame2, startValues, endValues);
  }

  //////////////////////////////////////////////////////////////////////////

  /**
   * toString override method.
   * @return String table of all the Colors at every time stored in the hashmap.
   */
  @Override
  public String toString() {
    String str = "";
    for (int i = 0; i <= pattern.size(); i++) {
      str += "\n" + "Frame: " + i + ", R: " + pattern.get(i)[0] + ", G: " + pattern.get(i)[1] +
          ", B: " + pattern.get(i)[2];
    }
    return str.substring(1);
  }
}