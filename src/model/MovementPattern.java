package model;

/**
 * Stores and/or calculates the position for a shape object given some time.
 */
public class MovementPattern extends AbstractPattern {

  /**
   * Constructs a new MovementPattern.
   */
  public MovementPattern() {
    this.endTime = 100;
    for (int i = 0; i <= this.endTime; i++) {
      this.pattern.put(i, new Integer[]{50, 50});
    }
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
    this.endTime = 100;
    for (int i = 0; i <= this.endTime; i++) {
      this.pattern.put(i, new Integer[]{x, y});
    }
  }

  /**
   * Implements a change to the values in the MovementPattern. Change is implemented gradually,
   * starting at frame1 and ending at frame2. Changes MUST be implemented in order.
   *
   * @param frame1 the frame at which the change begins.
   * @param frame2 the frame at which the change is over.
   * @param values the (X,Y) coordinates to which the shape will move.
   */
  @Override
  public void change(Integer frame1, Integer frame2, Integer[] values) {
    if (values.length != 2) {
      throw new IllegalArgumentException("Values must be given as a two Integer array.");
    }

    Integer newX = values[0];
    Integer newY = values[1];

    if (newX > 1000 || newX <= 0 || newY > 1000 || newY <= 0) {
      throw new IllegalArgumentException("Coordinates must be between 0 and 100");
    }
    super.change(frame1, frame2, values);
  }


  /**
   * toString override method. Returns a table of the x and y cooridnates for every time stored.
   *
   * @return String table of all the coordinates at every time stored in the hashmap.
   */
  @Override
  public String toString() {
    String str = "";
    for (int i = 0; i <= this.endTime; i++) {
      str += "\n" + "Frame: " + i + ", X: " + pattern.get(i)[0] + ", Y: " +
          pattern.get(i)[1];
    }
    return str.substring(1);
  }
}
