package cs5004.animator.model;

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
    }

    @Override
    public void change(Integer frame1, Integer frame2, Integer[] startValues, Integer[] endValues) {
        if (endValues.length != 3) {
            throw new IllegalArgumentException("Values must be given as a three Integer array.");
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
    }

    @Override
    public int[] get(Integer time) {
        if (time < 0) {
            throw new IllegalArgumentException("Chosen frame must be greater than 0.");
        }
        return this.pattern.get(time);
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
        return "ColorPattern object:\n" + this.pattern.toString();
    }
}