package cs5004.animator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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


  private void updateChangeLog() {
    changeLog.addAll(color.pullChangeLog());
    changeLog.addAll(move.pullChangeLog());
    changeLog.addAll(size.pullChangeLog());
    changeLog = changeLog.stream().distinct().collect(Collectors.toList());
    Collections.sort(changeLog);
  }

  @Override
  public String getChangeLog() {
    this.updateChangeLog();
    String str = "";
    for (int i = 0; i < changeLog.size(); i++) {

      str += "\n" + changeLog.get(i).getChangeNotes();
    }
    return str.substring(1);
  }

  @Override
  public List<LogNode> pullChangeLog() {
    this.updateChangeLog();
    return changeLog;
  }

  @Override
  public int getAppearTime() {
    int appearTime = 0;
    if (color.earliestChangeFrame > appearTime) {
      appearTime = color.earliestChangeFrame;
    }
    if (move.earliestChangeFrame > appearTime) {
      appearTime = move.earliestChangeFrame;
    }
    if (size.earliestChangeFrame > appearTime) {
      appearTime = size.earliestChangeFrame;
    }
    if (color.earliestChangeFrame < appearTime) {
      appearTime = color.earliestChangeFrame;
    }
    if (move.earliestChangeFrame < appearTime) {
      appearTime = move.earliestChangeFrame;
    }
    if (size.earliestChangeFrame < appearTime) {
      appearTime = size.earliestChangeFrame;
    }
    return appearTime;
  }

  @Override
  public int getDisappearTime() {
    int disappearTime = 0;
    if (color.latestChangeFrame > disappearTime) {
      disappearTime = color.latestChangeFrame;
    }
    if (move.latestChangeFrame > disappearTime) {
      disappearTime = move.latestChangeFrame;
    }
    if (size.latestChangeFrame > disappearTime) {
      disappearTime = size.latestChangeFrame;
    }
    return disappearTime;
  }

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
    int appearTime = this.getAppearTime();
    int disappearTime = this.getDisappearTime();
    return time >= appearTime && time <= disappearTime;
  }


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