package cs5004.animator.model;

import java.util.List;

/**
 * Pattern is an object which represents the qualities of a Shape as it changes over the course of
 * an animation, such as its color, size, and position.
 */
public interface Pattern {


  /**
   * All patterns store information about a particular shape property in a private Hashmap. This
   * change method updates their internal hashmap by interpolating values between the start/end
   * value over the times between the start/end time.
   *
   * @param frame1      Initial Time
   * @param frame2      End Time
   * @param startValues Array of starting values.
   * @param endValues   Array of ending values.
   */
  void change(Integer frame1, Integer frame2, Integer[] startValues, Integer[] endValues);

  /**
   * Gets the values stored in the Pattern at any given frame.
   *
   * @param time the frame that the values are being pulled from.
   * @return the values of the given frame.
   */
  int[] get(Integer time);

  /**
   * Returns a list of LogNodes which represent a history of all changes made to this pattern.
   *
   * @return List of LogNode.
   */
  List<LogNode> pullChangeLog();

  /**
   * Returns a string that is a history of all changes made to this pattern.
   *
   * @return String history of this pattern.
   */
  String getChangeLog();


}