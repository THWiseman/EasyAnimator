package model;

public class AbstractShape implements Shape {
  @Override
  public int[] getPosition(int time) {
    return new int[0];
  }

  @Override
  public int[] getColor(int time) {
    return new int[0];
  }

  @Override
  public int[] getSize(int time) {
    return new int[0];
  }

  @Override
  public boolean getVisibility(int time) {
    return false;
  }

  @Override
  public void update(int time) {

  }

  @Override
  public int getAppearTime() {
    return 0;
  }

  @Override
  public int getDisappearTime() {
    return 0;
  }

  @Override
  public void setMasterPattern(MasterPattern master) {

  }
}
