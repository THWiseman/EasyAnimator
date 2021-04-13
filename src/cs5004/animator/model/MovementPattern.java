package cs5004.animator.model;

import java.util.*;

/**
 * Stores and/or calculates the position for a shape object given some time.
 */
public class MovementPattern extends AbstractPattern {

  /**
   * Constructs a new MovementPattern.
   */
  public MovementPattern() {
    this.pattern = new HashMap<Integer,int[]>();
  }

  //for testing purposes only, delete later.
  public Map<Integer, int[]> getMap() {
    return this.pattern;
  }

  @Override
  public void change(Integer frame1, Integer frame2, Integer[] startValues, Integer[] endValues) {
    if (endValues.length != 2) {
      throw new IllegalArgumentException("Values must be given as a two Integer array.");
    }
    if(frame1 < 0 || frame2 < frame1 || frame2 < 0) {
      throw new IllegalArgumentException("Frame 2 must be greater than frame 1, and both must be greater than zero.");
    }

    for (int i = frame1; i <= frame2; i++) {
      int newX = tween(frame1, frame2, startValues[0], endValues[0], i);
      int newY = tween(frame1, frame2, startValues[1], endValues[1], i);
      this.pattern.put(i, new int[]{newX, newY});
    }
    if (this.earliestChangeFrame == 0) {
      this.earliestChangeFrame = frame1;
    }
    if (frame1 < this.earliestChangeFrame) {
      this.earliestChangeFrame = frame1;
    }
    if (frame2 > this.latestChangeFrame) {
      this.latestChangeFrame = frame2;
    }

    super.changeTracker(PatternType.MOVEMENT, frame1, frame2, startValues, endValues);

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


  /**
   * toString override method. Returns a table of the x and y cooridnates for every time stored.
   *
   * @return String table of all the coordinates at every time stored in the hashmap.
   */
  @Override
  public String toString() {
    String str = "";
    for (Map.Entry<Integer, int[]> e : this.pattern.entrySet()) {
      str += "Frame: " + e.getKey() + "  x: " + e.getValue()[0] + "  y: " + e.getValue()[1] + "\n";
    }
    return str.trim();
  }

  private List<String>  getTextDescription() {
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
    int i = 0;
    int j = 0;
    for (i = 0; i < times.size(); i++) {
      int[] firstValue = positions.get(i);
      int[] secondValue = positions.get(i);

      while(Arrays.equals(firstValue,secondValue)) {
        j++;
        secondValue = positions.get(j);
      }

      str.add(String.format("moves from %d,%d to %d,%d from time %d to %d",firstValue[0],firstValue[1],secondValue[0],
              secondValue[1],i,j));
      j++;
      i = j;

    }
    return str;
  }
}