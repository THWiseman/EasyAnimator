package cs5004.animator.model;

import java.util.Comparator;

public class LogNode implements Comparable<LogNode> {
  private final int frame;
  private String changeNotes;

  public LogNode(int frame, String changeNotes) {
    this.frame = frame;
    this.changeNotes = changeNotes;
  }

  public int getFrame() {
    return this.frame;
  }

  public String getChangeNotes() {
    return this.changeNotes;
  }


  @Override
  public int compareTo(LogNode o) {
    return this.frame - o.getFrame();
  }
}
