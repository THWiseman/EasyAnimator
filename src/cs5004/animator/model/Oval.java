package cs5004.animator.model;

/**
 * An oval is a 2d shape with rounded edges defined by a length and width.
 */
public class Oval extends AbstractShape {

  /**
   * No argument constructor for a Oval object. Creates an oval with default patterns. Refer to
   * pattern documentation to see how each default pattern is created.
   */
  public Oval() {
    this.color = new ColorPattern();
    this.move = new MovementPattern();
    this.size = new SizeChangePattern();
    this.visibility = new VisibilityPattern();
  }

  /**
   * Constructor for a oval where each pattern is provided as an argument. Overwrites the default
   * patterns.
   *
   * @param move       MovementPattern that will replace the default one.
   * @param size       SizeChangePattern that will replace the default one.
   * @param visibility VisiblityPattern that will replace the default one.
   * @param color      ColorPattern that will replace the default one.
   */
  public Oval(ColorPattern color, MovementPattern move, SizeChangePattern size,
              VisibilityPattern visibility) {
    this.color = color;
    this.move = move;
    this.size = size;
    this.visibility = visibility;
  }

  @Override
  public Oval copy() {
    return new Oval(this.color, this.move, this.size, this.visibility);
  }

  @Override
  public String toString() {
    return "oval";
  }
}
