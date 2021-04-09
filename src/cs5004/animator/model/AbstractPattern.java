package cs5004.animator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This abstract class represents the qualities of a Shape as it changes over the course of
 * an animation, such as its color, size, and position.
 */
public abstract class AbstractPattern implements Pattern {
    List<LogNode> changeLog = new ArrayList<>();


    /**
     * Given start/end times and start/end values, figures out what the value of something would be between those times.
     * @param t1 Initial Time
     * @param t2 Ending Time
     * @param startValue Known starting value.
     * @param endValue Known ending value.
     * @param desiredTime Time for which you wish to calculate the unknown value.
     * @return The value of the desiredTime.
     */
    public int tween(int t1, int t2, int startValue, int endValue, int desiredTime) {

        if (desiredTime < t1 || desiredTime > t2) {
            throw new IllegalArgumentException("Desired time must be between start and end times.");
        }
        if (desiredTime == t1) {
            return startValue;
        }
        if (desiredTime == t2) {
            return endValue;
        }
        int totalTime = t2-t1;
        double step = (endValue-startValue)/totalTime;
        double answer = startValue + (step*(desiredTime-t1));
        return (int) Math.round(answer);
    }

    /**
     * Fills in times from frame1 to frame2 with their interpolated values. Uses the tween method to calculate the
     * values between frame1 and frame2.
     * @param frame1 Initial Time
     * @param frame2 End Time
     * @param startValues Array of starting values.
     * @param endValues Array of ending values.
     */
    public abstract void change(Integer frame1, Integer frame2, Integer[] startValues, Integer[] endValues);

    /**
     * Gets the values stored in the Pattern at any given frame.
     *
     * @param time the frame that the values are being pulled from.
     * @return the values of the given frame.
     */
    public abstract int[] get(Integer time);


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