package model;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the standard implementation of the Canvas interface.
 */
public class CanvasImpl implements Canvas {
  private List<Shape> shapes; //List of Shape and Strings provide fast access
  // to the shapes as needed.
  private List<String> shapeIDs;
  private int startTime; //time is best stored as an integer for simplicity.
  private int endTime;

  /**
   * Constructor for the Canvas implementation.
   *
   * @param endTime Integer of the time that the animation will end.
   */
  public CanvasImpl(int endTime) {
    this.shapes = new ArrayList<>();
    this.shapeIDs = new ArrayList<>();
    this.startTime = 0;
    this.endTime = endTime;
  }

  /**
   * Constructor for a default canvas. Sets the end time to 100.
   */
  public CanvasImpl() {
    this.shapes = new ArrayList<>();
    this.shapeIDs = new ArrayList<>();
    this.startTime = 0;
    this.endTime = 100;
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
  public void addShape(Shape s, String iD) {
    if (s == null || iD == null) {
      throw new IllegalArgumentException("Shape and Shape IDs cannot be null");
    }
    if (iD.isEmpty()) {
      throw new IllegalArgumentException("Shape ID cannot be an empty string.");
    }
    for (String sample : shapeIDs) {
      if (sample.equals(iD)) {
        throw new IllegalArgumentException("Must use a unique Shape ID.");
      }
    }
    this.shapes.add(s);
    this.shapeIDs.add(iD);
  }

  @Override
  public boolean removeShape(String iD) {
    if (iD.isEmpty() || iD == null) {
      throw new IllegalArgumentException("ID cannot be empty or null");
    }
    for (String sample : this.shapeIDs) {
      if (sample.equals(iD)) {
        int index = this.shapeIDs.indexOf(sample);
        this.shapeIDs.remove(index);
        this.shapes.remove(index);
        return true;
      }
    }
    return false;
  }

  @Override
  public Shape getShape(String iD) throws IllegalArgumentException {
    int index = this.shapeIDs.indexOf(iD);
    if (index == -1) {
      throw new IllegalArgumentException("Shape not found.");
    }
    return this.shapes.get(index);
  }

  @Override
  public int hashCode() {
    String hashString = "";
    for (String s : this.shapeIDs) {
      hashString = hashString + s;
    }
    for (Shape s : this.shapes) {
      hashString = hashString + s.toString();
    }
    return hashString.hashCode();
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
    return (this.startTime == canvas.startTime && this.endTime == canvas.endTime);
  }


  @Override
  public String toString() {
    String returnString = "Canvas object:\n";
    returnString = returnString
            + String.format("Start time: %d, end time: %d\n",
            this.startTime, this.endTime);
    returnString = returnString
            + String.format("There are %d shapes currently in the shape list:\n",
            this.shapes.size());
    for (Shape s : this.shapes) {
      returnString = returnString + s.toString() + "\n";
    }
    return returnString;
  }
}
