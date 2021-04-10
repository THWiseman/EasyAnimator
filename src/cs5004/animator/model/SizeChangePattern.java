package cs5004.animator.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores and/or calculates the length and width for a shape object given some time.
 */
public class SizeChangePattern extends AbstractPattern {

    Map<Integer, int[]> pattern;

    /**
     * Constructs a new SizeChangePattern.
     */
    public SizeChangePattern() {
        this.pattern = new HashMap<Integer, int[]>();
        //this.changeLog = new HashMap<Integer, String>();
    }

    //for testing only, delete later.
    public Map<Integer, int[]> getMap() {
        return this.pattern;
    }


    @Override
    public void change(Integer frame1, Integer frame2, Integer[] startValues, Integer[] endValues) {
        if (endValues.length != 2 || frame1 < 0 || frame2 < frame1 || frame2 < 0) {
            throw new IllegalArgumentException("Values must be given as a two Integer array." +
                    "Frame 1 must be less than Frame 2 and both must be greater than 0.");
        }
        for (int i : Arrays.asList(startValues)) {
            if (i < 0) {
                throw new IllegalArgumentException("Length and width values must be zero or greater.");
            }
        }
        for (int i : Arrays.asList(endValues)) {
            if (i < 0) {
                throw new IllegalArgumentException("Length and width values must be zero or greater.");
            }
        }
        for (int i = frame1; i <= frame2; i++) {
            int newLength = tween(frame1, frame2, startValues[0], endValues[0], i);
            int newWidth = tween(frame1, frame2, startValues[1], endValues[1], i);
            this.pattern.put(i, new int[]{newLength, newWidth});
        }

        if (frame1 > this.earliestChangeFrame) {
            this.earliestChangeFrame = frame1;
        }

        if (frame2 > this.latestChangeFrame) {
            this.latestChangeFrame = frame2;
        }

        super.changeTracker(PatternType.SIZECHANGE, frame1, frame2, startValues, endValues);

        /**
         this.changeCount++;
         changeLog.put(changeCount, String.format("changes from length:%d width%d at time %d to length:%d width%d at time %d", startValues[0],
         startValues[1],frame1, endValues[0],endValues[1],frame2)); **/

    }

    @Override
    public int[] get(Integer time) {
        if (time < 0) {
            throw new IllegalArgumentException("Chosen frame must be greater than 0.");
        }
        try {
            return this.pattern.get(time);
        } catch (NullPointerException e) {
            return null;
        }
    }


    /**
     * Override for the toString method. Returns a table of the length and with at every time in the
     * hashmap.
     *
     * @return String table of all the dimensions at every time stored in the hashmap.
     */
    @Override
    public String toString() {
        String str = "";
        for (Map.Entry<Integer, int[]> e : this.pattern.entrySet()) {
            str += "Frame: " + e.getKey() + "  length: " + e.getValue()[0] + "  width: " + e.getValue()[1] + "\n";
        }
        return str.trim();
    }

}