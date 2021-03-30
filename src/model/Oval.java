package model;

/**
 * An oval is a 2d shape with rounded edges defined by a length and width.
 */
public class Oval extends AbstractShape {
  int length;
  int width;
  int[] center;
  int[] color;
  MasterPattern master;
  boolean isVisible;

  /**
   * Constructor for an Oval object. Sets the oval to default values which wont be changed until the
   * update() method is called on this object. By default, the center is at {0,0}, it is not
   * visible, and the color is black {0,0,0}.
   *
   * @param length integer of the length of the oval.
   * @param width  integer of the width of the oval.
   * @param master MasterPattern that contains the various attribute modifiers for the oval.
   */
  public Oval(int length, int width, MasterPattern master) {
    if (length < 1 || width < 1) {
      throw new IllegalArgumentException("Length and Width must be one or greater.");
    }
    if (master == null) {
      throw new IllegalArgumentException("MasterPattern cannot be null");
    }
    this.master = master;
    this.length = length;
    this.width = width;
    this.center = new int[]{0, 0};
    this.isVisible = false;
    this.color = new int[]{0, 0, 0};
  }

  @Override
  public void update(int time) {
    this.length = master.getSize(time)[0];
    this.width = master.getSize(time)[1];
    this.center = master.getPosition(time);
    this.color = master.getColor(time);
    this.isVisible = master.getVisibility(time);
  }

  @Override
  public Oval copy() {
    Oval copy = new Oval(this.length, this.width, this.master);
    copy.center = this.center;
    copy.color = this.color;
    copy.isVisible = this.isVisible;
    return copy;
  }

}
