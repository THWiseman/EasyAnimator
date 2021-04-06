package cs5004.animator.model;

/**
 * Stores and/or calculates the length and width for a shape object given some time.
 */
public class SizeChangePattern extends AbstractPattern {

  /**
   * Constructs a new SizeChangePattern.
   */
  public SizeChangePattern() {
    this.endTime = 100;
    for (int i = 0; i <= this.endTime; i++) {
      this.pattern.put(i, new Integer[]{10, 10});
    }
  }

  /**
   * Constructs a new SizeChangePattern with all values being set to the given length and width.
   *
   * @param length The length of the shape.
   * @param width  The width of the shape.
   */
  public SizeChangePattern(Integer length, Integer width) {
    if (length > 100 || length <= 0 || width > 100 || width <= 0) {
      throw new IllegalArgumentException("Length and width must be between 0 and 100");
    }
    this.endTime = 100;

    for (int i = 0; i <= this.endTime; i++) {
      this.pattern.put(i, new Integer[]{length, width});
    }
  }

  /**
   * Implements a change to the values in the SizeChangePattern. Change is implemented gradually,
   * starting at frame1 and ending at frame2. Changes MUST be implemented in order.
   * @param frame1 the frame at which the change begins.
   * @param frame2 the frame at which the change is over.
   * @param values the length by width dimensions that the shape will change to.
   */
  public void change(Integer frame1, Integer frame2, Integer[] values) {
    if (values.length != 2) {
      throw new IllegalArgumentException("Values must be given as a two Integer array.");
    }

    Integer newLength = values[0];
    Integer newWidth = values[1];

    if (newLength > this.endTime || newLength <= 0 || newWidth > this.endTime || newWidth <= 0) {
      throw new IllegalArgumentException("Length and width must be between 0 and 100");
    }
    super.change(frame1, frame2, values);
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
    for (int i = 0; i <= this.endTime; i++) {
      str += "\n" + "Frame: " + i + ", Length: " + pattern.get(i)[0] + ", Width: " +
          pattern.get(i)[1];
    }
    return str.substring(1);
  }
}