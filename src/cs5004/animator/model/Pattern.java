package cs5004.animator.model;

/**
 * Pattern is an object which represents the qualities of a Shape as it changes over the course of
 * an animation, such as its color, size, and position.
 */
public interface Pattern {

  /**
   * Implements a change to the values in the Pattern. Change is implemented gradually,
   * starting at frame1 and ending at frame2. Changes MUST be implemented in order.
   * @param frame1 the frame at which the change begins.
   * @param frame2 the frame at which the change is over.
   * @param values the new values to which the Shape will change.
   */
  void change(Integer frame1, Integer frame2, Integer[] values);

  void change(Integer frame1, Integer frame2, Integer[] startValues, Integer[] endValues);

  /**
   * Gets the values stored in the Pattern at any given frame.
   *
   * @param time the frame that the values are being pulled from.
   * @return the values of the given frame.
   */
  int[] get(Integer time);
}