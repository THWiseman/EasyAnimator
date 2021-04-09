package cs5004.animator.model;

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
    }

    //for testing only, delete later.
    public Map<Integer, int[]> getMap() {
        return this.pattern;
    }


    @Override
    public void change(Integer frame1, Integer frame2, Integer[] startValues, Integer[] endValues) {
        if (endValues.length != 2) {
            throw new IllegalArgumentException("Values must be given as a two Integer array.");
        }
        for (int i = frame1; i <= frame2; i++) {
            int newLength = tween(frame1, frame2, startValues[0], endValues[0], i);
            int newWidth = tween(frame1, frame2, startValues[1], endValues[1], i);
            this.pattern.put(i, new int[]{newLength, newWidth});
        }
        this.changeCount++;
        changeLog.put(changeCount, String.format("changes from length:%d width%d at time %d to length:%d width%d at time %d", startValues[0],
                startValues[1],frame1, endValues[0],endValues[1],frame2));

    }

    @Override
    public int[] get(Integer time) {
        if (time < 0) {
            throw new IllegalArgumentException("Chosen frame must be greater than 0.");
        }
        return this.pattern.get(time);
    }


    /**
     * Override for the toString method. Returns a table of the length and with at every time in the
     * hashmap.
     *
     * @return String table of all the dimensions at every time stored in the hashmap.
     */
    @Override
    public String toString() {
        return "SizeChange object:\n" + this.pattern.toString();
    }
}