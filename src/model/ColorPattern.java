package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and/or calculates the color for a shape object given some time.
 */
public class ColorPattern {
  private Map<Integer, Integer[]> pattern = new HashMap<>();
  private final Integer originalR;
  private final Integer originalG;
  private final Integer originalB;

  public ColorPattern() {
    this.originalR = 0;
    this.originalG = 0;
    this.originalB = 0;
    for (int i = 1; i < 101; i++) {
      this.pattern.put(i, new Integer[] {0, 0, 0});
    }
  }

  public ColorPattern(Integer R, Integer G, Integer B) {
    this.originalR = R;
    this.originalG = G;
    this.originalB = B;
    for (int i = 1; i < 101; i++) {
      this.pattern.put(i, new Integer[] {R, G, B});
    }
  }

  public void change(Integer frame1, Integer frame2, Integer newR, Integer newG, Integer newB) {
    int time = frame2 - frame1;
    double changeDifferenceR = newR - originalR;
    double changeDifferenceG = newG - originalG;
    double changeDifferenceB = newB - originalB;

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
          (int) (pattern.get(i)[1] + changeFactorB) };
      this.pattern.replace(i, updatedFrame);
    }

    for (int i = frame2; i < 101; i++) {
      this.pattern.replace(i, new Integer[] {newR, newG, newB});
    }
  }

  public int[] getColor(Integer time) {
    return new int[] {pattern.get(time)[0], pattern.get(time)[1], pattern.get(time)[2]};
  }

  public String toString() {
    return "";
  }
}
