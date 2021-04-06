package cs5004.animator.model;

/**
 * A rectangle is a 2D shape that has a length and width.
 */
public class Rectangle extends AbstractShape {


  /**
   * No argument constructor for a Rectangle object. Creates a rectangle with default patterns.
   * Refer to pattern documentation to see how each default pattern is created.
   */
  public Rectangle() {
    this.move = new MovementPattern();
    this.size = new SizeChangePattern();
    this.color = new ColorPattern();
    this.appearTime = 0;
    this.disappearTime = 100;
  }

  /**
   * Constructor for a rectangle where each pattern is provided as an argument, rather than using
   * default patterns.
   *
   * @param move       MovementPattern that will replace the default one.
   * @param size       SizeChangePattern that will replace the default one.
   * @param color      ColorPattern that will replace the default one.
   * @param appearTime The frame at which the shape will appear.
   * @param disappearTime The frame at which the shape will disappear.
   */
  public Rectangle(ColorPattern color, MovementPattern move, SizeChangePattern size,
      int appearTime, int disappearTime) {
    this.color = color;
    this.move = move;
    this.size = size;
    this.appearTime = appearTime;
    this.disappearTime = disappearTime;
  }


  @Override
  public Rectangle copy() {
    return new Rectangle(this.color, this.move, this.size, this.appearTime, this.disappearTime);
  }

  @Override
  public String toString() {
    return "Rectangle Object";
  }
}