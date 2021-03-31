package model;

import java.util.List;

/**
 * Canvas is an object that keeps track of the locations of shapes in 2d space, and it keeps track
 * of time. It contains all the shapes that are present in the animation.
 */
public interface Canvas {

  /**
   * Returns the start time of the animation. By default, this value will be zero.
   *
   * @return the start time of the animation.
   */
  int getStartTime();

  /**
   * Sets the start time of the animation. When a Canvas is first created, it will have a default
   * start time of zero.
   *
   * @param time Integer of the time that you wish the animation to begin.
   */
  void setStartTime(int time);

  /**
   * Returns the end time of the animation.
   *
   * @return Integer of the time that the animation will end.
   */
  int getEndTime();

  /**
   * Changes the end time of the animation.
   *
   * @param time Integer of the time that you wish the animation to end.
   */
  void setEndTime(int time);

  /**
   * Returns a copy of the list of the shapes that are visible on the screen at time T.
   *
   * @param time Time of the animation that you wish to query.
   * @return A list of every shape that will be visible at time.
   */
  List<Shape> getShapesAtTime(int time);

  /**
   * Returns a copy of the list of every shape present in the entire, independent of time.
   *
   * @return A list of every shape in the current animation.
   */
  List<Shape> getAllShapes();

  /**
   * Gets all the shapeID strings.
   *
   * @return List of strings that contains all shape IDs.
   */
  List<String> getAllShapeIDs();

  /**
   * Adds a shape to the list of shapes that the canvas has.
   *
   * @param s  Shape object that you wish to add to the canvas.
   * @param ID A unique string that can be mapped to the shape added in this method.
   */
  void addShape(Shape s, String ID);

  /**
   * Removes a shape from the list of shapes that the canvas has.
   *
   * @param ID Unique string ID of the shape you wish to remove.
   * @return True if a shape was successfully removed, false otherwise.
   */
  boolean removeShape(String ID);

  /**
   * Returns the shape that matches the string provided. NOT a copy.
   *
   * @param ID String of the stringID that was used in the addShape method.
   * @return Shape object that matches the ID.
   */
  Shape getShape(String ID);
}
