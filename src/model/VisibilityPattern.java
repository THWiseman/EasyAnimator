package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and/or calculates the visibility for a shape object given some time.
 */
public class VisibilityPattern implements Pattern{
  private Map<Integer, Integer> pattern = new HashMap<>();
  //Visibility is most simply stored as a Boolean. A shape is either visible, or not. If necessary,
  //opacity could be handled by the view or by the Color pattern.
  private int endTime;

  /**
   * Constructs a new visibility pattern.
   */
  public VisibilityPattern() {
    this.endTime = 100;
    for (int i = 0; i <= this.endTime; i++) {
      this.pattern.put(i, 0);
    }
  }

  /**
   * Constructs a new VisibilityPattern with all values being set to the given boolean.
   *
   * @param visible whether the shape is visible or not in this VisibilityPattern.
   */
  public VisibilityPattern(Integer visible) {
    this.endTime = 100;
    for (int i = 0; i <= this.endTime; i++) {
      this.pattern.put(i, visible);
    }
  }

  /**
   * Implements a change to the values in the Visibility Pattern. Changes MUST be implemented in
   * order.
   *
//   * @param frame     the frame at which the change takes place.
 //  * @param isVisible the value to be stored in the frame, and all subsequent frames.
   */
  public void change(Integer frame1, Integer frame2, Integer[] values) {
    Integer isVisible = values[0];

    if (frame1 > this.endTime || frame1 < 0) {
      throw new IllegalArgumentException("Chosen frame must be between 0 and 100");
    }
    for (int i = frame1; i <= this.endTime; i++) {
      this.pattern.put(i, isVisible);
    }
  }

  /**
   * Gets the value stored in the Visibility pattern at any given frame.
   *
   * @param time the frame that the visibility is being pulled from.
   * @return the visibility of the shape at the given frame.
   */
  public int[] get(Integer time) {
    if (time > this.endTime || time < 0) {
      throw new IllegalArgumentException("Chosen frame must be between 0 and 100");
    }
    try {
      return new int[pattern.get(time)];
    } catch (NullPointerException e) {
      throw new IndexOutOfBoundsException("No Size stored for provided time");
    }
  }

  /**
   * Override for the toString method. Returns table of whether or not a shape is visibile for every
   * time in the hashmap.
   *
   * @return String table.
   */
  @Override
  public String toString() {
    String str = "";
    for (int i = 0; i <= this.endTime; i++) {
      str += "\n" + "Frame: " + i + ", is visible: " + pattern.get(i);
    }
    return str.substring(1);
  }
}
