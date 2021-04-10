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
    return str.substring(1);
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


}