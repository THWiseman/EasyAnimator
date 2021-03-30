package model;

/**
 * All shapes extend Abstract shape. By default, all shapes will simply query their MasterPattern
 * for information about their position/size/color/visibility/timing, so those methods are
 * implemented here.
 */
public abstract class AbstractShape implements Shape {

MasterPattern master;

  @Override
  public int[] getPosition(int time) {
    return master.getPosition(time);
  }

  @Override
  public int[] getColor(int time) {
    return master.getColor(time);
  }

  @Override
  public int[] getSize(int time) {
    return master.getSize(time);
  }

  @Override
  public boolean getVisibility(int time) {
    return master.getVisiblity(time);
  }

  @Override
  public abstract void update(int time);

  @Override
  public int getAppearTime() {
    return master.getAppearTime();
  }

  @Override
  public int getDisappearTime() {
    return master.getDisappearTime();
  }

  @Override
  public void setMasterPattern(MasterPattern master) {
    this.master = master;
  }

  @Override
  public MasterPattern getMasterPatternCopy() {
    return master.copy();
  }

  @Override
  public abstract Shape copy();
}
