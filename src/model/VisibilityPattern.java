package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and/or calculates the visibility for a shape object given some time.
 */
public class VisibilityPattern {
  private Map<Integer, Boolean> pattern = new HashMap<>();

  /**
   * Constructs a new visibility pattern.
   */
  public VisibilityPattern() {
    for (int i = 1; i < 101; i++) {
      this.pattern.put(i, false);
    }
  }

  /**
   * Constructs a new VisibilityPattern with all values being set to the given boolean.
   * @param visible whether the shape is visible or not in this VisibilityPattern.
   */
  public VisibilityPattern(boolean visible) {
    for (int i = 1; i < 101; i++) {
      this.pattern.put(i, visible);
    }
  }

  /**
   * Implements a change to the values in the Visibility Pattern. Changes MUST be implemented
   * in order.
   * @param frame the frame at which the change takes place.
   * @param isVisible the value to be stored in the frame, and all subsequent frames.
   */
  public void change(Integer frame, boolean isVisible) {
    if (frame > 100 || frame < 0) {
      throw new IllegalArgumentException("Chosen frame must be between 0 and 100");
    }
    for (int i = frame; i < 101; i++) {
      this.pattern.replace(i, isVisible);
    }
  }

  /**
   * Gets the value stored in the Visibility pattern at any given frame.
   * @param time the frame that the visibility is being pulled from.
   * @return the visibility of the shape at the given frame.
   */
  public boolean getVisibility(Integer time) {
    if (time > 100 || time < 0) {
      throw new IllegalArgumentException("Chosen frame must be between 0 and 100");
    }
    return pattern.get(time);
  }


  public String toString() {
    String str = "";
    for (int i = 1; i < 101; i++) {
      str += "\n" + "Frame: " + i + ", is visible: " + pattern.get(i);
    }
    return str.substring(1);
  }
}
