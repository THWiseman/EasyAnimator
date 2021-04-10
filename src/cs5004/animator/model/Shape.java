package cs5004.animator.model;

import java.util.List;

/**
 * Shape is an object that can be displayed on a canvas.
 */
public interface Shape {

  /**
   * Returns the x and y coordinates of the shape at the specified time.
   *
   * @param time Integer of the time you wish to know the position of the shape.
   * @return Int[2] x and y coordinates of the shape.
   */
  int[] getPosition(int time);

  /**
   * Returns the RGB value  of the shape at the specified time.
   *
   * @param time Integer of the time you wish to know the color of the shape.
   * @return Int[3] RGB values  of the shape.
   */
  int[] getColor(int time);

  /**
   * Returns the length and with of the shape at the specified time.
   *
   * @param time Integer of the time you wish to know the length and with of the shape.
   * @return Int[2] width and height values of the shape.
   */
  int[] getSize(int time);

  /**
   * Returns the visibility of the shape at the current time.
   *
   * @param time Integer of the time you wish to know the visibility of the shape.
   * @return True if the shape is visible at the provided time, false otherwise.
   */
  boolean getVisibility(int time);

  /**
   * Returns the earliest frame (time) that the shape has ever had a change applied to it.
   * @return Integer of the earliest frame the shape has ever changed. Returns 0 if the shape has never changed.
   */
  int getAppearTime();

  /**
   * Returns the latest frame (time) that the shape has ever had a change applied to it.
   * @return Integer of the latest frame that the shape has ever changed. Returns 0 if the shape has never changed.
   */
  int getDisappearTime();


  /**
   * Changes the color change pattern of the current shape to the one provided.
   *
   * @param color ColorPattern object to replace the existing one with.
   */
  void setColorPattern(ColorPattern color);

  /**
   * Returns the ColorPattern object that the current shape is using.
   *
   * @return ColorPattern object that the current shape is using.
   */
  ColorPattern getColorPattern();

  /**
   * Sets the SizeChange pattern of the shape to the one provided.
   *
   * @param size SizePattern object that you wish to replace the existing one with.
   */
  void setSizeChangePattern(SizeChangePattern size);

  /**
   * Getter for the shape's current sizeChange pattern.
   *
   * @return SizeChangePattern object that the current shape is using.
   */
  SizeChangePattern getSizeChangePattern();

  /**
   * Sets the movement pattern of the shape to the one provided.
   *
   * @param move MovementPattern object that you wish to replace the existing one with.
   */
  void setMovementPattern(MovementPattern move);

  /**
   * Getter for the shape's current movement pattern.
   *
   * @return MovementPatternObject that the current shape is using.
   */
  MovementPattern getMovementPattern();

  /**
   * Returns a copy of the current Shape object.
   *
   * @return Shape that is a copy of the current shape object.
   */
  Shape copy();


  /**
   * Returns a list of LogNode which represents a history of all changes made to the shape.
   * @return List of LogNode
   */
  List<LogNode> pullChangeLog();

  /**
   * Returns a string history of all changes made to the shape.
   * @return String history of shape.
   */
  String getChangeLog();

}