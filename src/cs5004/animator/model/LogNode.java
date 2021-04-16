package cs5004.animator.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents and stores the data of a single animation, and is stored within the
 * ChangeLog.
 */
public class LogNode implements Comparable<LogNode> {

  private final int frame1;
  private final int frame2;
  private String changeNotes;
  private final PatternType type;
  private final Integer[] startValues;
  private final Integer[] endValues;

  /**
   * Constructs a new LogNode object.
   *
   * @param type        the type of Pattern that is changing.
   * @param frame1      the starting frame of the animation.
   * @param frame2      the ending frame of the animation
   * @param startValues the values of the Pattern at the beginning of the animation.
   * @param endValues   the values of the Pattern at the end of the animation.
   */
  public LogNode(PatternType type, Integer frame1, Integer frame2,
      Integer[] startValues, Integer[] endValues) {
    if (frame1 < -1) {
      throw new IllegalArgumentException("Starting frame must be an integer 0 or greater.");
    }
    if (frame2 < frame1) {
      throw new IllegalArgumentException(
          "Ending frame must be greater than or equal to starting frame.");
    }

    this.frame1 = frame1;
    this.frame2 = frame2;
    this.type = type;
    this.startValues = startValues;
    this.endValues = endValues;

    if (type == PatternType.SIZECHANGE) {
      this.changeNotes = "changes dimensions from width " + startValues[0] + " by height "
          + startValues[1] + " to width " + endValues[0] + " by height " + endValues[1]
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LogNode logNode = (LogNode) o;
    return getFrame1() == logNode.getFrame1() && getFrame2() == logNode.getFrame2()
        && getChangeNotes().equals(logNode.getChangeNotes()) && getType() == logNode.getType()
        && Arrays.equals(getStartValues(), logNode.getStartValues()) && Arrays
        .equals(getEndValues(), logNode.getEndValues());
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(getFrame1(), getFrame2(), getChangeNotes(), getType());
    result = 31 * result + Arrays.hashCode(getStartValues());
    result = 31 * result + Arrays.hashCode(getEndValues());
    return result;
  }

  @Override
  public int compareTo(LogNode o) {
    return this.frame1 - o.getFrame1();
  }

  /**
   * Returns the starting frame of the animation.
   *
   * @return the starting frame of the animation.
   */
  public int getFrame1() {
    return this.frame1;
  }

  /**
   * Returns the ending frame of the animation.
   *
   * @return the ending frame of the animation.
   */
  public int getFrame2() {
    return this.frame2;
  }

  /**
   * Returns a String that describes the animation taking place.
   *
   * @return the String descriptor of the animation.
   */
  public String getChangeNotes() {
    return this.changeNotes;
  }

  /**
   * Returns the type of Pattern that this animation refers to.
   *
   * @return the type of Pattern that this animation refers to.
   */
  public PatternType getType() {
    return this.type;
  }

  /**
   * Returns the values of the Pattern at the beginning of the animation.
   *
   * @return the values of the Pattern at the beginning of the animation.
   */
  public int[] getStartValues() {
    int[] updatedFrame = new int[startValues.length];
    for (int i = 0; i < startValues.length; i++) {
      updatedFrame[i] = startValues[i];
    }
    return updatedFrame;
  }

  /**
   * Returns the values of the Pattern at the end of the animation.
   *
   * @return the values of the Pattern at the end of the animation.
   */
  public int[] getEndValues() {
    int[] updatedFrame = new int[endValues.length];
    for (int i = 0; i < endValues.length; i++) {
      updatedFrame[i] = endValues[i];
    }
    return updatedFrame;
  }

}
