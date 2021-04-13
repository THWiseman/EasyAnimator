package cs5004.animator.model;

import java.util.*;

/**
 * Stores and/or calculates the color for a shape object given some time.
 */
public class ColorPattern extends AbstractPattern {

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

        //these if statements will be used to determine the shape appear/disappear time.
        if (this.earliestChangeFrame == 0) {
            this.earliestChangeFrame = frame1;
        }
        if (frame1 < this.earliestChangeFrame) {
            this.earliestChangeFrame = frame1;
        }
        if (frame2 > this.latestChangeFrame) {
            this.latestChangeFrame = frame2;
        }

        super.changeTracker(PatternType.COLOR, frame1, frame2, startValues, endValues);

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

    public List<String> getTextDescription() {
        List<String> str = new ArrayList<>();
        //get a list of all the times in the hashmap and sort it.
        List<Integer> times = new ArrayList<>();
        for (Integer i : this.pattern.keySet()) {
            times.add(i);
        }
        Collections.sort(times);

        //get a list of all the int[] in the hashmap. It should be in sorted order by time since we got it by using
        //the sorted times list.
        List<int[]> positions = new ArrayList<>();
        for (Integer i : times) {
            positions.add(this.pattern.get(i));
        }


        str.add(String.format("starts at color RGB[%d,%d,%d] at time %d",positions.get(0)[0],positions.get(0)[1],positions.get(0)[2],times.get(0)));

        int i = 1;
        int j = 1;
        //for i less than the size of the list
        while (i < times.size()-1) {
            int[] firstValue = positions.get(i);
            int[] secondValue = positions.get(j);
            System.out.print(String.format("%d, %d",i,j));

            //if element i is equal to element i+1, figure out how long they stay the same for.
            if(Arrays.equals(positions.get(i),positions.get(i+1))){
                //increment j until secondValue no longer equals first value.
                while(Arrays.equals(firstValue,secondValue) &&j<times.size()-1) {
                    j++;
                    secondValue = positions.get(j);
                }
                //The map has not changed from time i to time j.
                str.add(String.format("stays color  {%d, %d, %d} from time %d to %d.",
                        firstValue[0],firstValue[1],firstValue[2],i,j));
                j++;
                i=j;
                continue;
            }
            //if element i is NOT equal to element i+1, the color is changing. Figure out how long the change lasts,
            //and record that change.
            if(!Arrays.equals(positions.get(i),positions.get(i+1))) {
                while (!Arrays.equals(firstValue, secondValue) && j < times.size() - 1) {
                    j++;
                    secondValue = positions.get(j);
                }

                str.add(String.format("changes color from {%d, %d, %d} to {%d, %d, %d} from time %d to %d", firstValue[0],
                        firstValue[1], firstValue[2], secondValue[0], secondValue[1], secondValue[2], i, j));

                j++;
                i=j;
            }

        }
        return str;
    }
}