package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and/or calculates the length and width for a shape object given some time.
 */
public class SizeChangePattern {
  private Map<Integer, Integer[]> pattern = new HashMap<>();
  private final Integer originalLength;
  private final Integer originalWidth;

  public SizeChangePattern() {
    this.originalLength = 10;
    this.originalWidth = 10;
    for (int i = 1; i < 101; i++) {
      this.pattern.put(i, new Integer[] {10, 10});
    }
  }

  public SizeChangePattern(Integer length, Integer width) {
    this.originalLength = length;
    this.originalWidth = width;
    for (int i = 1; i < 101; i++) {
      this.pattern.put(i, new Integer[] {length, width});
    }
  }

  public void change(Integer frame1, Integer frame2, Integer newLength, Integer newWidth) {
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

    for (int i = frame2; i < 101; i++) {
      this.pattern.replace(i, new Integer[] {newLength, newWidth});
    }
  }

  public int[] getSize(Integer time) {
    return new int[] {pattern.get(time)[0], pattern.get(time)[1]};
  }

  public String toString() {
    return "";
  }
}
