package model;

/**
 * A rectangle is a 2D shape that has a length and width.
 */
public class Rectangle extends AbstractShape {
  private int length;
  private int width;
  private int[] bottomLeft;
  private int[] color;
  private MasterPattern master;
  private boolean isVisible;

  /**
   * Constructor for a Rectangle object. Sets the rectangle to default values which wont be changed
   * until the update() method is called on this object. By default, the bottom left corner is at
   * {0,0}, it is not visible, and the color is black {0,0,0}.
   *
   * @param length integer of the length of the rectangle.
   * @param width  integer of the width of the rectangle.
   * @param master MasterPattern that contains the various attribute modifiers for the rectangle.
   */
  public Rectangle(int length, int width, MasterPattern master) {
    if (length < 1 || width < 1) {
      throw new IllegalArgumentException("Length and Width must be one or greater.");
    }
    if (master == null) {
      throw new IllegalArgumentException("MasterPattern cannot be null");
    }
    this.master = master;
    this.length = length;
    this.width = width;
    this.bottomLeft = new int[]{0, 0};
    this.isVisible = false;
    this.color = new int[]{0, 0, 0};
  }

  @Override
  public void update(int time) {
    this.length = master.getSize(time)[0];
    this.width = master.getSize(time)[1];
    this.bottomLeft = master.getPosition(time);
    this.color = master.getColor(time);
    this.isVisible = master.getVisibility(time);
  }

  @Override
  public Rectangle copy() {
    Rectangle copy = new Rectangle(this.length, this.width, this.master);
    copy.bottomLeft = this.bottomLeft;
    copy.color = this.color;
    copy.isVisible = this.isVisible;
    return copy;
  }

}
