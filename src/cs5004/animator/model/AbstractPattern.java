package cs5004.animator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This abstract class represents the qualities of a Shape as it changes over the course of
 * an animation, such as its color, size, and position.
 */
public abstract class AbstractPattern implements Pattern {
  Map<Integer, Integer[]> pattern = new HashMap<>();
  List<LogNode> changeLog = new ArrayList<>();

  /**
   * Implements a change to the values in the Pattern. Change is implemented gradually,
   * starting at frame1 and ending at frame2. Changes MUST be implemented in order.
   * @param frame1 the frame at which the change begins.
   * @param frame2 the frame at which the change is over.
   * @param values the new values to which the Shape will change.
   */
  @Override
  public void change(Integer frame1, Integer frame2, Integer[] values) {
    //should change to something like:
    //change(Integer frame1, Integer frame2, Integer values1, Integer values2)
    if (frame1 < 0 || frame2 < 0) {
      throw new IllegalArgumentException("Start and end times must be between 0 and 100");
    }
    if (frame1 > frame2) {
      throw new IllegalArgumentException("End time must be greater than start time");
    }

    int time = frame2 - frame1;

    for (int i = frame1; i < frame2; i++) {
      int alteredFrameNumber = i - frame1;
      Integer[] updatedFrame = new Integer[values.length];

      for (int j = 0; j < values.length; j++) {

        double difference = values[j] - this.pattern.get(frame1)[j];

        double increment = difference / time;

        double changeFactor = alteredFrameNumber * increment;

        updatedFrame[j] = (int) (pattern.get(i)[j] + changeFactor);
      }
      this.pattern.replace(i, updatedFrame);
    }

   // for (int i = frame2; i <= this.endTime; i++) {
  //    this.pattern.replace(i, values);
  //  }
  }


  @Override
  public void change(Integer frame1, Integer frame2, Integer[] startValues, Integer[] endValues) {
    //should change to something like:
    //change(Integer frame1, Integer frame2, Integer values1, Integer values2)
    if (frame1 < 0 || frame2 < 0) {
      throw new IllegalArgumentException("Start and end times must be between 0 and 100");
    }
    if (frame1 > frame2) {
      throw new IllegalArgumentException("End time must be greater than start time");
    }

    int time = frame2 - frame1;

    for (int i = frame1; i < frame2; i++) {
      int alteredFrameNumber = i - frame1;
      Integer[] updatedFrame = new Integer[endValues.length];

      for (int j = 0; j < endValues.length; j++) {

        double difference = endValues[j] - startValues[j];

        double increment = difference / time;

        double changeFactor = alteredFrameNumber * increment;

        updatedFrame[j] = (int) (pattern.get(i)[j] + changeFactor);
      }
      this.pattern.replace(i, updatedFrame);
    }
  }

  /**
   * Gets the values stored in the Pattern at any given frame.
   *
   * @param time the frame that the values are being pulled from.
   * @return the values of the given frame.
   */
  @Override
  public int[] get(Integer time) {
    if (time < 0) {
      throw new IllegalArgumentException("Chosen frame must be greater than 0.");
    }
    try {
      int[] updatedFrame = new int[pattern.get(time).length];
      for (int i = 0; i < pattern.get(time).length; i++) {
        updatedFrame[i] = pattern.get(time)[i];
      }
      return updatedFrame;
    } catch (NullPointerException e) {
      throw new IndexOutOfBoundsException("No position found for given time.");
    }
  }

  public String getChangeLog() {
      String str = "";
      for (int i = 0; i < changeLog.size(); i++) {

        str += "\n" + changeLog.get(i).getChangeNotes();
      }
      return str.substring(1);
    }

  public List<LogNode> pullChangeLog() {
    return this.changeLog;
  }


  public void changeTracker(PatternType type, Integer frame1, Integer frame2,
      Integer[] startValues, Integer[] endValues) {

    if (type == PatternType.SIZECHANGE) {
      changeLog.add(new LogNode(frame1, "changes dimensions from length " + startValues[0] + " by width "
          + startValues[1] + " to length " + endValues[0] + " by width " + endValues[1]
          + ", from time t=" + frame1 + " to t=" + frame2));
    } else if (type == PatternType.MOVEMENT) {
      changeLog.add(new LogNode(frame1, "moves position from (" + startValues[0] + ", " + startValues[1]
          + ") to (" + endValues[0] + ", " + endValues[1] + "), from time t=" + frame1 + " to t="
          + frame2));
    } else if (type == PatternType.COLOR) {
      changeLog.add(new LogNode(frame1, "changes color from RGB" + Arrays.toString(startValues) + " to RGB"
          + Arrays.toString(endValues) + ", from time t=" + frame1 + " to t=" + frame2));
    }
  }
}