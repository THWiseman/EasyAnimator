package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and/or calculates the visibility for a shape object given some time.
 */
public class VisibilityPattern {
  private Map<Integer, Boolean> pattern = new HashMap<>();
  private final boolean originallyVisible;

  public VisibilityPattern() {
    originallyVisible = false;
    for (int i = 1; i < 101; i++) {
      this.pattern.put(i, false);
    }
  }

  public VisibilityPattern(boolean visible) {
    originallyVisible = visible;
    for (int i = 1; i < 101; i++) {
      this.pattern.put(i, visible);
    }
  }

  public void change(Integer frame, boolean isVisible) {
    for (int i = frame; i < 101; i++) {
      this.pattern.replace(i, isVisible);
    }
  }


  public boolean getVisibility(Integer time) {
    return pattern.get(time);
  }

  public int getAppearTime() {
    return 0;
  }

  public int getDisappearTime() {
    return 0;
  }

  public String toString() {
    return "";
  }
}
