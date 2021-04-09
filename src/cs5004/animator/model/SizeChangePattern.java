package cs5004.animator.model;

/**
 * Stores and/or calculates the length and width for a shape object given some time.
 */
public class SizeChangePattern extends AbstractPattern {

  /**
   * Constructs a new SizeChangePattern.
   */
  public SizeChangePattern() {
  }


  public void change(Integer frame1, Integer frame2, Integer[] startValues, Integer[] endValues) {
    if (endValues.length != 2) {
      throw new IllegalArgumentException("Values must be given as a two Integer array.");
    }
    for(int i = frame1; i<=frame2; i++) {
      if (this.pattern.keySet().contains(i)) {
        continue;
      }
      int newLength = tween(frame1,frame2,startValues[0],endValues[0],i);
      int newWidth = tween(frame1,frame2,startValues[1],endValues[1],i);
      this.pattern.put(i, new Integer[] {newLength, newWidth});
    }

  }

  @Override
  public Integer[] get(Integer time) {
    if (time < 0) {
      throw new IllegalArgumentException("Chosen frame must be greater than 0.");
    }
    System.out.print(String.format("Integer[]get at time %d", (int) time));
    return this.pattern.get(time);
  }

  //////////////////////////////////////////////////////////////////////////

  /**
   * Override for the toString method. Returns a table of the length and with at every time in the
   * hashmap.
   *
   * @return String table of all the dimensions at every time stored in the hashmap.
   */
  @Override
  public String toString() {
    return String.format("SizeChange object with HashMap size %d", this.pattern.size());
  }
}