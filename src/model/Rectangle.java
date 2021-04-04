package model;

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
    this.visibility = new VisibilityPattern();
    this.color = new ColorPattern();
  }

  /**
   * Constructor for a rectangle where each pattern is provided as an argument, rather than using
   * default patterns.
   *
   * @param move       MovementPattern that will replace the default one.
   * @param size       SizeChangePattern that will replace the default one.
   * @param visibility VisiblityPattern that will replace the default one.
   * @param color      ColorPattern that will replace the default one.
   */
  public Rectangle(ColorPattern color, MovementPattern move, SizeChangePattern size,
                   VisibilityPattern visibility) {
    this.color = color;
    this.move = move;
    this.size = size;
    this.visibility = visibility;
  }


  @Override
  public Rectangle copy() {
    return new Rectangle(this.color, this.move, this.size, this.visibility);
  }

  @Override
  public String toString() {
    return "Rectangle Object";
  }
}
