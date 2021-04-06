package model;

/**
 * All shapes extend Abstract shape. By default, all shapes will simply query their patterns for
 * information about their position/size/color/visibility/timing, so those methods are implemented
 * here.
 */
public abstract class AbstractShape implements Shape {
  ColorPattern color; //decided to store all shape attributes inside of a different class to have
  //both flexibility in how they are calculated and stored, and portability between shapes.
  MovementPattern move;
  SizeChangePattern size;

  int appearTime;
  int disappearTime;


  @Override
  public int[] getPosition(int time) {
    return move.get(time);
  }

  @Override
  public int[] getColor(int time) {
    return color.get(time);
  }

  @Override
  public int[] getSize(int time) {
    return size.get(time);
  }

  @Override
  public boolean getVisibility(int time) {
    if (time >= this.appearTime && time <= this.disappearTime) {
      return true;
    } else {
      return false;
    }
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
  public void setVisibility(int appearTime, int disappearTime) {
    this.appearTime = appearTime;
    this.disappearTime = disappearTime;
  }

  public int getAppearTime() {
    return this.appearTime;
  }

  public int getDisappearTime() {
    return this.disappearTime;
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


}