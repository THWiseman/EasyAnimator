package cs5004.animator.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and/or calculates the position for a shape object given some time.
 */
public class MovementPattern extends AbstractPattern {

  Map<Integer, int[]> pattern = new HashMap<>();

  /**
   * Constructs a new MovementPattern.
   */
  public MovementPattern() {
  }

  /**
   * Constructs a new MovementPattern with all values being set to the given X and Y.
   *
   * @param x the x coordinate location of the shape.
   * @param y the y coordinate location of the shape.
   */
  public MovementPattern(Integer x, Integer y) {
    if (x > 1000 || x <= 0 || y > 1000 || y <= 0) {
      throw new IllegalArgumentException("Length and width must be between 0 and 100");
    }
  }
  public Map<Integer, int[]> getMap() {
    return this.pattern;
  }

  public void change(Integer frame1, Integer frame2, Integer[] startValues, Integer[] endValues) {
    if (endValues.length != 2) {
      throw new IllegalArgumentException("Values must be given as a two Integer array.");
    }
    for (int i = frame1; i <= frame2; i++) {
      if (this.pattern.keySet().contains(i)) {
        continue;
      }
      int newX = tween(frame1, frame2, startValues[0], endValues[0], i);
      int newY = tween(frame1, frame2, startValues[1], endValues[1], i);
      this.pattern.put(i, new int[]{newX, newY});
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


  /**
   * toString override method. Returns a table of the x and y cooridnates for every time stored.
   *
   * @return String table of all the coordinates at every time stored in the hashmap.
   */
  @Override
  public String toString() {
    return String.format("MovementPattern object with HashMap size %d", this.pattern.size());
  }
}