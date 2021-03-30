package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is the standard implementation of the Canvas interface.
 */
public class CanvasImpl implements Canvas {
  private int length;
  private int width;
  private int[] backgroundColor;
  private List<Shape> shapes;
  private List<String> shapeIDs;
  private int startTime;
  private int endTime;
  int currentTime;

  /**
   * Constructor for the Canvas implementation.
   *
   * @param length  Integer of how long (x dimension) the canvas will be.
   * @param width   Integer of how wide (y dimension) the canvas will be.
   * @param endTime Integer of the time that the animation will end.
   */
  public CanvasImpl(int length, int width, int endTime) {
    this.length = length;
    this.width = width;
    this.backgroundColor = new int[]{255, 255, 255};
    this.shapes = new ArrayList<>();
    this.shapeIDs = new ArrayList<>();
    this.startTime = 0;
    this.endTime = endTime;
    this.currentTime = 0;
  }

  /**
   * Constructor for a default canvas. Sets length and width to 100, and sets the end time to 100.
   */
  public CanvasImpl() {
    this.length = 100;
    this.width = 100;
    this.backgroundColor = new int[]{255, 255, 255};
    this.shapes = new ArrayList<>();
    this.shapeIDs = new ArrayList<>();
    this.startTime = 0;
    this.endTime = 100;
    this.currentTime = 0;
  }

  @Override
  public void setBackgroundColor(int[] RGB) {
    if (RGB.length != 3) {
      throw new IllegalArgumentException("RGB values must be a 3 element array");
    }
    if (RGB.equals(null)) {
      throw new IllegalArgumentException("RGB value cannot be null.");
    }
    for (int i : RGB) {
      if (i < 0 || i > 255) {
        throw new IllegalArgumentException("RGB values must be between 0 and 255");
      }
      this.backgroundColor = RGB;
    }
  }

  @Override
  public int[] getBackgroundColor() {
    return this.backgroundColor;
  }

  @Override
  public void setTime(int time) {
    if (time < this.startTime || time > this.endTime) {
      throw new IllegalArgumentException("Time must be >= start time (default 0)" +
              " and <= or equal to the end time.");
    }
    this.currentTime = time;
  }

  @Override
  public int incrementTime() {
    if (this.currentTime == this.endTime) {
      throw new IllegalStateException("The animation has already reached its end time.");
    }
    this.currentTime++;
    return this.currentTime;
  }

  @Override
  public int getTime() {
    return this.currentTime;
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public void setStartTime(int time) {
    if (time < 0 || time >= this.endTime) {
      throw new IllegalArgumentException("Time must be greater than zero" +
              " and less than the end time.");
    }
    this.startTime = time;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }

  @Override
  public void setEndTime(int time) {
    if (time <= this.startTime || time <= 0) {
      throw new IllegalArgumentException("End time must be greater than the start time.");
    }
    this.endTime = time;
  }

  @Override
  public List<Shape> getShapesAtTime(int time) {
    List<Shape> temp = new ArrayList<>();
    for (Shape s : this.shapes) {
      if (s.getVisibility(time)) {
        temp.add(s);
      }
    }
    return temp;
  }

  @Override
  public List<Shape> getAllShapes() {
    List<Shape> temp = new ArrayList<>();
    for (Shape s : this.shapes) {
      temp.add(s);
    }
    return temp;
  }

  @Override
  public List<String> getAllShapeIDs() {
    List<String> temp = new ArrayList<>();
    for (String s : this.shapeIDs) {
      temp.add(s);
    }
    return temp;
  }

  @Override
  public void updateShapes(int time) {
    for (Shape s : this.shapes) {
      s.update(this.currentTime);
    }
  }

  @Override
  public void addShape(Shape s, String ID) {
    if (s == null || ID == null) {
      throw new IllegalArgumentException("Shape and Shape IDs cannot be null");
    }
    if (ID.isEmpty()) {
      throw new IllegalArgumentException("Shape ID cannot be an empty string.");
    }
    for (String sample : shapeIDs) {
      if (sample.equals(ID)) {
        throw new IllegalArgumentException("Must use a unique Shape ID.");
      }
    }
    this.shapes.add(s);
    this.shapeIDs.add(ID);
  }

  @Override
  public boolean removeShape(String ID) {
    if (ID.isEmpty() || ID.equals(null)) {
      throw new IllegalArgumentException("ID cannot be empty or null");
    }
    for (String sample : this.shapeIDs) {
      if (sample.equals(ID)) {
        int index = this.shapeIDs.indexOf(sample);
        this.shapeIDs.remove(index);
        this.shapes.remove(index);
        return true;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    return (this.length * 3 + this.width * 5 + this.startTime * 7
            + this.endTime * 13 + this.currentTime * 17);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || other.getClass() != this.getClass()) {
      return false;
    }
    CanvasImpl canvas = (CanvasImpl) other;
    if (!canvas.getAllShapes().equals(this.getAllShapes())) {
      return false;
    }
    if (!canvas.shapeIDs.equals(this.shapeIDs)) {
      return false;
    }
    return (this.length == canvas.length && this.width == canvas.width && this.currentTime ==
            canvas.currentTime && this.startTime
            == canvas.startTime && this.endTime == canvas.endTime
            && Arrays.equals(this.backgroundColor,canvas.backgroundColor));
  }

  @Override
  public String toString() {
    String returnString = ""
            + String.format("Canvas with Length: %d and Width: %d \n", this.length, this.width);
    returnString = returnString
            + String.format("Start: %d, end:%d, current %d\n",
            this.startTime, this.endTime, this.currentTime);
    returnString = returnString
            + String.format("There are %d shapes currently in the shape list", this.shapes.size());
    return returnString;
  }
}
