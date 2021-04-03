package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and/or calculates the position for a shape object given some time.
 */
public class MovementPattern {
  private Map<Integer, Integer[]> pattern = new HashMap<>();
  private final Integer originalX;
  private final Integer originalY;


  public MovementPattern() {
    this.originalX = 50;
    this.originalY = 50;
    for (int i = 1; i < 101; i++) {
      this.pattern.put(i, new Integer[] {50, 50});
    }
  }

  public MovementPattern(Integer X, Integer Y) {
    this.originalX = X;
    this.originalY = Y;
    for (int i = 1; i < 101; i++) {
      this.pattern.put(i, new Integer[] {X, Y});
    }
  }

  public void change(Integer frame1, Integer frame2, Integer newX, Integer newY) {
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

  public int[] getPosition(Integer time) {
    return new int[] {pattern.get(time)[0], pattern.get(time)[1]};
  }

  public String toString() {
    return "";
  }
}
