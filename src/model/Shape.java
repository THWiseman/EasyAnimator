package model;

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
   * @return Int[2] length and width values of the shape.
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
   * Returns the time that the shape will become visible.
   *
   * @return Integer of the time the shape will become visible.
   */
  int getAppearTime();

  /**
   * Returns the time that the shape will become invisible.
   *
   * @return Integer of the time the shape will become invisible.
   */
  int getDisappearTime();

  /**
   * Changes the color pattern of the shape to the one provided.
   *
   * @param color ColorPattern object that you wish to replace the existing one with.
   */
  void setColorPattern(ColorPattern color);

  /**
   * Returns the ColorPattern object that the current shape is using.
   *
   * @return ColorPattern object that the current shape is using.
   */
  ColorPattern getColorPattern();

  /**
   * Changes the visibility pattern of the shape to the one provided.
   *
   * @param visibility VisibilityPattern Object that you wish to replace the existing one with.
   */
  void setVisibilityPattern(VisibilityPattern visibility);

  /**
   * Getter for the shape's current visibility pattern.
   *
   * @return VisibilityPattern object that the current shape is using.
   */
  VisibilityPattern getVisibilityPattern();

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
}
  