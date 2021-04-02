package model;

/**
 * All shapes extend Abstract shape. By default, all shapes will simply query their patterns for
 * information about their position/size/color/visibility/timing, so those methods are implemented
 * here.
 */
public abstract class AbstractShape implements Shape {

  ColorPattern color;
  MovementPattern move;
  SizeChangePattern size;
  VisibilityPattern visibility;

  @Override
  public int[] getPosition(int time) {
    return move.getPosition(time);
  }

  @Override
  public int[] getColor(int time) {
    return color.getColor(time);
  }

  @Override
  public int[] getSize(int time) {
    return size.getSize(time);
  }

  @Override
  public boolean getVisibility(int time) {
    return visibility.getVisibility(time);
  }

  @Override
  public int getAppearTime() {
    return visibility.getAppearTime();
  }

  @Override
  public int getDisappearTime() {
    return visibility.getDisappearTime();
  }

  @Override
  public abstract Shape copy();

  @Override
  public void setColorPattern(ColorPattern color) {
    if (color == null) {
      throw new IllegalArgumentException("Pattern cannot be null.");
    }
    this.color = color;
  }

  @Override
  public ColorPattern getColorPattern() {
    return this.color;
  }

  @Override
  public void setVisibilityPattern(VisibilityPattern visibility) {
    if (visibility == null) {
      throw new IllegalArgumentException("Pattern cannot be null.");
    }
    this.visibility = visibility;
  }

  @Override
  public VisibilityPattern getVisibilityPattern() {
    return this.visibility;
  }

  @Override
  public void setSizeChangePattern(SizeChangePattern size) {
    if (size == null) {
      throw new IllegalArgumentException("Pattern cannot be null.");
    }
    this.size = size;
  }

  @Override
  public SizeChangePattern getSizeChangePattern() {
    return this.size;
  }

  @Override
  public void setMovementPattern(MovementPattern move) {
    if (move == null) {
      throw new IllegalArgumentException("Pattern cannot be null.");
    }
    this.move = move;
  }

  @Override
  public MovementPattern getMovementPattern() {
    return this.move;
  }

  @Override
  public String toString() {
    String returnString = "Shape Object:\n";
    returnString += this.getColorPattern().toString() + "\n";
    returnString += this.getMovementPattern().toString() + "\n";
    returnString += this.getSizeChangePattern().toString() + "\n";
    returnString += this.getVisibilityPattern().toString() + "\n";
    return returnString;
  }
}