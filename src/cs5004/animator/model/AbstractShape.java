package cs5004.animator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * All shapes extend Abstract shape. By default, all shapes will simply query their patterns for
 * information about their position/size/color/visibility/timing, so those methods are implemented
 * here.
 */
public abstract class AbstractShape implements Shape {
  ColorPattern color;
  MovementPattern move;
  SizeChangePattern size;
  private List<LogNode> changeLog = new ArrayList<>();

  int appearTime;
  int disappearTime;

  ////////////////////////////////////////////////////////
  private void updateChangeLog() {
    changeLog.addAll(color.pullChangeLog());
    changeLog.addAll(move.pullChangeLog());
    changeLog.addAll(size.pullChangeLog());
    Collections.sort(changeLog);
  }

  public String getChangeLog() {
    this.updateChangeLog();
    String str = "";
    for (int i = 0; i < changeLog.size(); i++) {

      str += "\n" + changeLog.get(i).getChangeNotes();
    }
    try { // FIX THIS
      return str.substring(1);
    } catch (StringIndexOutOfBoundsException e) {
      return "";
    }
  }

  public List<LogNode> pullChangeLog() {
    this.updateChangeLog();
    return changeLog;
  }
  ///////////////////////////////////////////////////////////

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
    return time >= this.appearTime && time <= this.disappearTime;
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

  @Override
  public void setAppearTime(int time) {
    if(time < 0 || time >= this.disappearTime) {
      throw new IllegalArgumentException("Appear time must be greater than zero and before the disappear time.");
    }
    this.appearTime = time;
  }

  @Override
  public void setDisappearTime(int time) {
    if(time < this.appearTime || time <= 0) {
      throw new IllegalArgumentException("Disappear time must be greater than the appear time and greater than zero.");
    }
    this.disappearTime = time;
  }
}