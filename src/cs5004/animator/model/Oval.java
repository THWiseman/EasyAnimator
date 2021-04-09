package cs5004.animator.model;

import java.util.List;

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
    this.appearTime = 0;
    this.disappearTime = 100;
  }

  /**
   * Constructor for a oval where each pattern is provided as an argument. Overwrites the default
   * patterns.
   *
   * @param move       MovementPattern that will replace the default one.
   * @param size       SizeChangePattern that will replace the default one.
   * @param color      ColorPattern that will replace the default one.
   * @param appearTime The frame at which the shape will appear.
   * @param disappearTime The frame at which the shape will disappear.
   */
  public Oval(ColorPattern color, MovementPattern move, SizeChangePattern size,
      int appearTime, int disappearTime) {
    this.color = color;
    this.move = move;
    this.size = size;
    this.appearTime = appearTime;
    this.disappearTime = disappearTime;
  }

  @Override
  public Oval copy() {
    return new Oval(this.color, this.move, this.size, this.appearTime, this.disappearTime);
  }

  @Override
  public List<LogNode> pullChangeLog() {
    return null;
  }

  @Override
  public String getChangeLog() {
    return null;
  }

  @Override
  public String toString() {
    return "oval";
  }
}