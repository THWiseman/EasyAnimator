package cs5004.animator.model;

/**
 * Pattern is an object which represents the qualities of a Shape as it changes over the course of
 * an animation, such as its color, size, and position.
 */
public interface Pattern {


  void change(Integer frame1, Integer frame2, Integer[] startValues, Integer[] endValues);

  /**
   * Gets the values stored in the Pattern at any given frame.
   *
   * @param time the frame that the values are being pulled from.
   * @return the values of the given frame.
   */
  int[] get(Integer time);
}