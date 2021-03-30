package model;
import java.util.List;

/**
 * Canvas is an object that keeps track of the locations of shapes in 2d space, and it keeps track
 * of time. It contains all the shapes that are present in the animation.
 */

public interface Canvas {

  /**
   * Sets the color of the background. RGB is an array with 3 elements where each element
   * is a value from 0 to 255 representing red, green, or blue values.
   * @param RGB Int array with red, green, and blue values.
   */
  void setBackgroundColor(int[] RGB);

  /**
   * Returns a 3 element array that corresponds to the RGB value of the background color.
   * @return Background RGB values in a 3 element array.
   */
  int[] getBackgroundColor();

  /**
   * Sets the current time in the canvas.
   * @param time Integer value that represents the current time. Must be greater than zero
   *             and less than the end time of the canvas.
   */
  void setTime(int time);

  /**
   * Increments the time by one.
   * @return Integer of the current time (after it was incremented).
   */
  int incrementTime();

  /**
   * Returns the current time of the canvas.
   * @return Integer of the current time.
   */
  int getTime();

  /**
   * Returns the start time of the animation. By default, this value will be zero.
   * @return the start time of the animation.
   */
  int getStartTime();

  /**
   * Sets the start time of the animation.
   * @param time Integer of the time that you wish the animation to begin.
   */
  void setStartTime(int time);

  /**
   * Returns the end time of the animation.
   * @return Integer of the time that the animation will end.
   */
  int getEndTime();

  /**
   * Changes the end time of the animation.
   * @param time Integer of the time that you wish the animation to end.
   */
  void setEndTime(int time);

  /**
   * Returns a copy of the list of the shapes that are visible on the screen at time T.
   * @param time Time of the animation that you wish to query.
   * @return A list of every shape that will be visible at time.
   */
  List<Shape> getShapesAtTime(int time);

  /**
   * Returns a copy of the list of every shape present in the current canvas.
   * @return A list of every shape in the current animation.
   */
  List<Shape> getAllShapes();

  /**
   * Gets all the shapeID strings.
   * @return List of strings that contains all shape IDs.
   */
  List<String> getAllShapeIDs();

  /**
   * Will call the update(time) method on every shape in the list of shapes that the canvas has.
   * @param time Time that you wish to update the shapes to. In the standard program loop, this
   *             will usually equal incrementTime().
   */
  void updateShapes(int time);

  /**
   * Adds a shape to the list of shapes that the canvas has.
   * @param s Shape object that you wish to add to the canvas.
   * @param ID A unique string that can be mapped to the shape added in this method.
   */
  void addShape(Shape s, String ID);

  /**
   * Removes a shape from the list of shapes that the canvas has.
   * @param ID Unique string ID of the shape you wish to remove.
   * @return Tue if a shape was successfully removed, false otherwise.
   */
  boolean removeShape(String ID);
}
