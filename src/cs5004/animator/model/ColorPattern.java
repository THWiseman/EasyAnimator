package cs5004.animator.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores and/or calculates the color for a shape object given some time.
 */
public class ColorPattern extends AbstractPattern {
    private Map<Integer, int[]> pattern;

    /**
     * Constructs a new ColorPattern.
     */
    public ColorPattern() {
        this.pattern = new HashMap<>();
       // this.changeLog = new HashMap<Integer, String>();
    }

    @Override
    public void change(Integer frame1, Integer frame2, Integer[] startValues, Integer[] endValues) {
        if (endValues.length != 3 || frame1 < 0 || frame2 < frame1 || frame2 < 0) {
            throw new IllegalArgumentException("Time must be greater than zero, and arrays must be" +
                    "provided in a 3 value Integer[] array.");
        }
        for(int i : Arrays.asList(startValues)) {
            if (i<0 || i >255) {
                throw new IllegalArgumentException("Color values must be between 0 and 255.");
            }
        }
        for(int i : Arrays.asList(endValues)) {
            if (i<0 || i >255) {
                throw new IllegalArgumentException("Color values must be between 0 and 255.");
            }
        }
        if (frame1 == frame2) {
            this.pattern.put(frame1, new int[]{startValues[0], startValues[1], startValues[2]});
            return;
        }
        for (int i = frame1; i <= frame2; i++) {
            int newR = tween(frame1, frame2, startValues[0], endValues[0], i);
            int newG = tween(frame1, frame2, startValues[1], endValues[1], i);
            int newB = tween(frame1, frame2, startValues[2], endValues[2], i);
            this.pattern.put(i, new int[]{newR, newG, newB});
        }

        super.changeTracker(PatternType.COLOR, frame1, frame2, startValues, endValues);

        /**
        changeCount++;
        this.changeLog.put(changeCount,
                String.format("changes from RGB [%d, %d, %d] at time %d to RGB [%d, %d, %d] at time %d.",
                        startValues[0], startValues[1], startValues[2], frame1, endValues[0], endValues[1], endValues[2], frame2));
         **/
    }

    @Override
    public int[] get(Integer time) {
        if (time < 0) {
            throw new IllegalArgumentException("Chosen frame must be greater than 0.");
        }
        try {
            return this.pattern.get(time);
        } catch(NullPointerException e) {
            return null;
        }
    }

    //for testing purposes, delete later
    public Map<Integer, int[]> getMap() {
        return this.pattern;
    }


    /**
     * toString override method.
     *
     * @return String table of all the Colors at every time stored in the hashmap.
     */
    @Override
    public String toString() {
        String str = "";
        for (Map.Entry<Integer, int[]> e : this.pattern.entrySet()) {
            str += "Frame: " + e.getKey() + ", R: " + e.getValue()[0] + ", G: " + e.getValue()[1] +
                ", B: " + e.getValue()[2] + "\n";
        }
        return str.trim();
    }
}