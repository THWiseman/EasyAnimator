package cs5004.animator.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and/or calculates the visibility for a shape object given some time.
 */
public class VisibilityPattern {
  private Map<Integer, Boolean> pattern = new HashMap<>();
  //Visibility is most simply stored as a Boolean. A shape is either visible, or not. If necessary,
  //opacity could be handled by the cs5004.animator.view or by the Color pattern.
  private int endTime;

  /**
   * Constructs a new visibility pattern.
   */
  public VisibilityPattern() {
    this.endTime = 100;
    for (int i = 0; i <= this.endTime; i++) {
      this.pattern.put(i, false);
    }
  }

  /**
   * Constructs a new VisibilityPattern with all values being set to the given boolean.
   *
   * @param visible whether the shape is visible or not in this VisibilityPattern.
   */
  public VisibilityPattern(boolean visible) {
    this.endTime = 100;
    for (int i = 0; i <= this.endTime; i++) {
      this.pattern.put(i, visible);
    }
  }

  /**
   * Implements a change to the values in the Visibility Pattern. Changes MUST be implemented in
   * order.
   *
   * @param frame     the frame at which the change takes place.
   * @param isVisible the value to be stored in the frame, and all subsequent frames.
   */
  public void change(Integer frame, boolean isVisible) {
    if (frame > this.endTime || frame < 0) {
      throw new IllegalArgumentException("Chosen frame must be between 0 and 100");
    }
    for (int i = frame; i <= this.endTime; i++) {
      this.pattern.put(i, isVisible);
    }
  }

  /**
   * Gets the value stored in the Visibility pattern at any given frame.
   *
   * @param time the frame that the visibility is being pulled from.
   * @return the visibility of the shape at the given frame.
   */
  public boolean getVisibility(int time) {
    if (time > this.endTime || time < 0) {
      throw new IllegalArgumentException("Chosen frame must be between 0 and 100");
    }
    //if a time is not found in the map, assume that the shape is not visible.
    try {
      return pattern.get(time);
    } catch (NullPointerException e) {
      return false;
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
