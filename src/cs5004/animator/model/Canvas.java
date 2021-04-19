package cs5004.animator.model;

import java.util.List;
import java.util.Map;

/**
 * Canvas is an object that keeps track of the locations of shapes in 2d space, and it keeps track
 * of time. It contains all the shapes that are present in the animation.
 */
public interface Canvas {

  /**
   * Returns the start time of the animation. By default, this value will be zero. Looks through all
   * shapes for the earliest frame that they had a change applied.
   *
   * @return the start time of the animation.
   */
  int getStartTime();


  /**
   * Returns the end time of the animation. By default, this value will be zero. Looks through all
   * shapes for the latest frame that they had a change applied.
   *
   * @return Integer of the time that the animation will end.
   */
  int getEndTime();


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
   * @param iD A unique string that can be mapped to the shape added in this method.
   */
  void addShape(Shape s, String iD);

  /**
   * Removes a shape from the list of shapes that the canvas has.
   *
   * @param iD Unique string ID of the shape you wish to remove.
   * @return True if a shape was successfully removed, false otherwise.
   */
  boolean removeShape(String iD);

  /**
   * Returns the shape that matches the string provided. NOT a copy.
   *
   * @param iD String of the stringID that was used in the addShape method.
   * @return Shape object that matches the ID.
   */
  Shape getShape(String iD);

  /**
   * Sets the dimensions for the Canvas.
   *
   * @param leftmostX The leftmost edge of the canvas. Default is -100.
   * @param width     The width of the canvas. Default is 200.
   * @param topmostY  The topmost Y coordinate of the canvas. Default is 100.
   * @param height    The total height of the canvas. Default is 200.
   */
  void setDimensions(int leftmostX, int width, int topmostY, int height);

  /**
   * Gets the dimensions for the canvas. Returns them as an int array in the form: {leftmostX,
   * width, topmostY, height}
   *
   * @return int array with all the dimension values.
   */
  int[] getDimensions();


  /**
   * Returns the map of all shapes that are in the canvas model.
   *
   * @return Map of all the shapes in the current canvas.
   */
  Map<String, Shape> getShapeMap();

  /**
   * Returns a list of the shape names in the order that they were created.
   *
   * @return List of string of all the shape names.
   */
  List<String> getOrderedShapeNames();

  void remove(String iD);
}
