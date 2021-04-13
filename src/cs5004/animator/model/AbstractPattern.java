package cs5004.animator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * This abstract class represents the qualities of a Shape as it changes over the course of
 * an animation, such as its color, size, and position.
 */
public abstract class AbstractPattern implements Pattern {
    List<LogNode> changeLog = new ArrayList<>();
    int earliestChangeFrame = 0; //will be updated whenever change() is called to keep track of the appear and
    //and disappear time.
    int latestChangeFrame = 0;
    Map<Integer, int[]> pattern;

    /**
     * Given start/end times and start/end values, figures out what the value of something would be between those times.
     * @param t1 Initial Time
     * @param t2 Ending Time
     * @param startValue Known starting value.
     * @param endValue Known ending value.
     * @param desiredTime Time for which you wish to calculate the unknown value.
     * @return The value of the desiredTime.
     */
    int tween(int t1, int t2, int startValue, int endValue, int desiredTime) {

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

    //used to update ChangeLog as changes are made.
    void changeTracker(PatternType type, Integer frame1, Integer frame2,
        Integer[] startValues, Integer[] endValues) {
        if (!Arrays.equals(startValues, endValues)) {
            changeLog.add(new LogNode(type, frame1, frame2, startValues, endValues));
        }
    }

}