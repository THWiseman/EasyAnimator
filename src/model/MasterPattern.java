package model;

public interface MasterPattern {

  int[] getPosition(int time);

  int[] getColor(int time);

  int[] getSize(int time);

  boolean getVisiblity(int time);

  int getAppearTime();

  int getDisappearTime();

  MasterPattern copy();
}
