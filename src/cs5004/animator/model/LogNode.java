package cs5004.animator.model;

import java.util.Arrays;

public class LogNode implements Comparable<LogNode> {
  private final int frame1;
  private final int frame2;
  private String changeNotes;
  private PatternType type;
  private Integer[] startValues;
  private Integer[] endValues;

  public LogNode(PatternType type, Integer frame1, Integer frame2,
      Integer[] startValues, Integer[] endValues) {
    this.frame1 = frame1;
    this.frame2 = frame2;
    this.type = type;
    this.startValues = startValues;
    this.endValues = endValues;

    if (type == PatternType.SIZECHANGE) {
      this.changeNotes = "changes dimensions from length " + startValues[0] + " by width "
          + startValues[1] + " to length " + endValues[0] + " by width " + endValues[1]
          + ", from time t=" + frame1 + " to t=" + frame2;
    } else if (type == PatternType.MOVEMENT) {
      this.changeNotes = "moves position from (" + startValues[0] + ", " + startValues[1]
          + ") to (" + endValues[0] + ", " + endValues[1] + "), from time t=" + frame1 + " to t="
          + frame2;
    } else if (type == PatternType.COLOR) {
      this.changeNotes = "changes color from RGB" + Arrays.toString(startValues) + " to RGB"
          + Arrays.toString(endValues) + ", from time t=" + frame1 + " to t=" + frame2;
    }

  }

  public int getFrame1() {
    return this.frame1;
  }

  public int getFrame2() {
    return this.frame2;
  }

  public String getChangeNotes() {
    return this.changeNotes;
  }

  public PatternType getType() {
    return this.type;
  }

  public int[] getStartValues() {
    int[] updatedFrame = new int[startValues.length];
        for (int i = 0; i < startValues.length; i++) {
          updatedFrame[i] = startValues[i];
        }
        return updatedFrame;
    }


  public int[] getEndValues() {
    int[] updatedFrame = new int[endValues.length];
    for (int i = 0; i < endValues.length; i++) {
      updatedFrame[i] = endValues[i];
    }
    return updatedFrame;
  }


  @Override
  public int compareTo(LogNode o) {
    return this.frame1 - o.getFrame1();
  }
}
