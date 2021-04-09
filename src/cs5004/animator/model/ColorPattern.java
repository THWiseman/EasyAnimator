package cs5004.animator.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and/or calculates the color for a shape object given some time.
 */
public class ColorPattern extends AbstractPattern {

  Map<Integer, int[]> pattern = new HashMap<>();
  /**
   * Constructs a new ColorPattern.
   */
  public ColorPattern() {
  }

  /**
   * Constructs a new ColorPattern with all RGB values being set to the given R, G, and B.
   * @param r the red value of the shape's color.
   * @param g the green value of the shape's color.
   * @param b the blue value of the shape's color.
   */
  public ColorPattern(Integer r, Integer g, Integer b) {
    if (r > 255 || r < 0 || g > 255 || g < 0 || b < 0 || b > 255 ) {
      throw new IllegalArgumentException("Length and width must be between 0 and 100");
    }
  }


  public void change(Integer frame1, Integer frame2, Integer[] startValues, Integer[] endValues) {
    if (endValues.length != 3) {
      throw new IllegalArgumentException("Values must be given as a three Integer array.");
    }
    if(frame1==frame2) {
      this.pattern.put(frame1,new int[] {startValues[0], startValues[1], startValues[2]});
      return;
    }
    for(int i = frame1; i<=frame2; i++) {
      if (this.pattern.keySet().contains(i)) {
        continue;
      }
      int newR = tween(frame1,frame2,startValues[0],endValues[0],i);
      int newG = tween(frame1,frame2,startValues[1],endValues[1],i);
      int newB = tween(frame1,frame2,startValues[2],endValues[2],i);
      this.pattern.put(i, new int[] {newR, newG, newB});
    }

  }

  @Override
  public int[] get(Integer time) {
      if (time < 0) {
        throw new IllegalArgumentException("Chosen frame must be greater than 0.");
      }
      System.out.print(String.format("Integer[]get at time %d", (int) time));
      return this.pattern.get(time);
    }

    public Map<Integer, int[]> getMap() {
    return this.pattern;
    }


  /**
   * toString override method.
   * @return String table of all the Colors at every time stored in the hashmap.
   */
  @Override
  public String toString() {
    return String.format("ColorPattern object with HashMap size %d", this.pattern.size());
  }
}