package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs5004.animator.util.AnimationBuilder;


/**
 * This is the standard implementation of the Canvas interface.
 */
public class CanvasImpl implements Canvas {
  private Map<String, Shape> shapes;
  private int startTime;
  private int endTime;
  private int leftmostX;
  private int topmostY;
  private int width;
  private int height;

  /**
   * Constructor for the Canvas implementation.
   *
   * @param endTime Integer of the time that the animation will end.
   */
  public CanvasImpl(int endTime) {
    this.shapes = new HashMap<String, Shape>();
    this.startTime = 0;
    this.endTime = endTime;
    this.leftmostX = -100;
    this.topmostY = 100;
    this.width = 200;
    this.height = 200;
  }

  /**
   * Constructor for a default canvas. Sets the end time to 100.
   */
  public CanvasImpl() {
    this.shapes = new HashMap<String, Shape>();
    this.startTime = 0;
    this.endTime = 100;
    this.leftmostX = -100;
    this.topmostY = 100;
    this.width = 200;
    this.height = 200;
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
    for (Shape s : this.shapes.values()) {
      if (s.getVisibility(time)) {
        temp.add(s);
      }
    }
    return temp;
  }

  @Override
  public List<Shape> getAllShapes() {
    List<Shape> temp = new ArrayList<>();
    for (Shape s : this.shapes.values()) {
      temp.add(s);
    }
    return temp;
  }

  @Override
  public List<String> getAllShapeIDs() {
    List<String> temp = new ArrayList<>();
    for (String s : this.shapes.keySet()) {
      temp.add(s);
    }
    return temp;
  }

  @Override
  public void addShape(Shape s, String iD) {
    if (s == null || iD == null) {
      throw new IllegalArgumentException("Shape and Shape IDs cannot be null.");
    }
    if (iD.isEmpty()) {
      throw new IllegalArgumentException("Shape ID cannot be an empty string.");
    }
    for (String sample : this.shapes.keySet()) {
      if (sample.equals(iD)) {
        throw new IllegalArgumentException("Must use a unique Shape ID.");
      }
    }
    this.shapes.put(iD, s);
  }

  @Override
  public boolean removeShape(String iD) {
    if (iD.isEmpty() || iD == null) {
      throw new IllegalArgumentException("ID cannot be empty or null.");
    }
    if (this.shapes.get(iD) == null) {
      return false;
    }
    this.shapes.remove(iD);
    return true;
  }

  @Override
  public Shape getShape(String iD) throws IllegalArgumentException {
    if (this.shapes.get(iD) == null) {
      throw new IllegalArgumentException("Shape not found.");
    }
    return this.shapes.get(iD);
  }

  @Override
  public void setDimensions(int leftmostX, int width, int topmostY, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive.");
    }
    this.leftmostX = leftmostX;
    this.width = width;
    this.topmostY = topmostY;
    this.height = height;
  }

  @Override
  public int[] getDimensions() {
    return new int[] {this.leftmostX,this.width,this.topmostY,this.height};
  }


  @Override
  public String getStringDescription() {
    String allCreates = "";

    //this for loop will create a string like "Create rectangle R with corner at (200,200),
    //width 50 and height 100\n" for each shape and append it to createString.
    for (Map.Entry<String, Shape> e : shapes.entrySet()) {
      String createString = "Create "; //"Create "
      createString += e.getValue().toString() + " "; //"rectangle "

      if (e.getValue().toString() == "rectangle") {
        //"R with corner at (200,200), "
        createString += String.format("%s with corner at (%d,%d), ", e.getKey(), e.getValue().getPosition(0)[0], e.getValue().getPosition(0)[1]);
        //width 50, height 100, and color.\n"
        createString += String.format("width %d, height %d, and color %s.\n", e.getValue().getSize(0)[0], e.getValue().getSize(0)[1],e.getValue().getSize(0).toString());
      }
      if (e.getValue().toString() == "oval") {
        //"C with center at (500,100), "
        createString += String.format("%s with center at (%d,%d), ", e.getKey(), e.getValue().getPosition(0)[0], e.getValue().getPosition(0)[1]);
        //radius 60 and height 30\n"
        createString += String.format("radius %d and %d, and color %s.\n", e.getValue().getSize(0)[0], e.getValue().getSize(0)[1],e.getValue().getSize(0).toString());
      }
      allCreates += createString;
    }
    allCreates += "\n"; //add extra newline to separate from next section.

    //this for loop will create a string like "R appears at time t=1 and disappears at time t=100\n
    //and append it to allAppears.
    String allAppears = "";
    for (Map.Entry<String, Shape> e : shapes.entrySet()) {
      String appearString = "";
      appearString += e.getKey() + String.format("appears at time t=%d and disappears at time t=%d\n", e.getValue().getAppearTime(), e.getValue().getDisappearTime());
      allAppears += appearString;
    }
    allAppears += "\n"; //add extra newline to separate from next section.?.OMRT


    //this for loop will create strings like:
    //C changes from blue to green from time t=50 to t=80\n
    //R Moves from (300,300) to (200,200) from time t=70 to t=100\n
    //R changes width from 50 to 25 from time t=51 to t=70\n
    //and append them to allChanges.
    String allChanges = "";
    for (Map.Entry<String, Shape> e : shapes.entrySet()) {
      allChanges += "send help im being held hostage by my OOD teacher";
    }
    return allCreates + allAppears + allChanges;
  }

  //Shallow copy of the shape hash map. Changing these will not affect the shapes in the model.
  @Override
  public Map<String, Shape> getShapeMap() {
    return new HashMap<String,Shape>(this.shapes);
  }

  @Override
  public int hashCode() {
    String hashString = "";
    for (String s : this.shapes.keySet()) {
      hashString = hashString + s;
    }
    for (Shape s : this.shapes.values()) {
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
    if (!canvas.shapes.keySet().equals(this.shapes.entrySet())) {
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
    for (Shape s : this.shapes.values()) {
      returnString = returnString + s.toString() + "\n";
    }
    return returnString;
  }

  public static final class Builder implements AnimationBuilder<Canvas> {
    //default values for a Canvas are a coordinate plane from -100 to 100 for both x and y.
    private int leftmostX = -100;
    private int topmostY = 100;
    private int width = 200;
    private int height = 200;
    private Map<String, Shape> shapes;

    @Override
    public Canvas build() {
      //create a new canvas
      Canvas returnCanvas = new CanvasImpl();
      //sets the dimensions of the new canvas. If the setBounds method was never called, uses
      //the default values.
      returnCanvas.setDimensions(leftmostX, width, topmostY, height);
      //adds every shape in the map to the CanvasImpl
      for (Map.Entry<String, Shape> entry : shapes.entrySet()) {
        returnCanvas.addShape(entry.getValue(), entry.getKey());
      }
      //returns the constructed CanvasImpl object.
      return returnCanvas;
    }

    @Override
    public AnimationBuilder<Canvas> setBounds(int x, int y, int width, int height) {
      this.leftmostX = x;
      this.topmostY = y;
      this.width = width;
      this.height = height;
      return this;
    }

    @Override
    public AnimationBuilder<Canvas> declareShape(String name, String type) {
      Shape newShape;
      if (!type.toUpperCase().equals("RECTANGLE") || !type.toUpperCase().equals("ELLIPSE") || !type.toUpperCase().equals("OVAL")) {
        throw new IllegalArgumentException("Shape type must be RECTANGLE or ELLIPSE.");
      }
      if (type.toUpperCase().equals("RECTANGLE")) {
        newShape = new Rectangle();
        this.shapes.put(name, newShape);
        return this;
      }
      if (type.toUpperCase().equals("ELLIPSE") || type.toUpperCase().equals("OVAL")) {
        newShape = new Oval();
        this.shapes.put(name, newShape);
        return this;
      } else {
        throw new IllegalStateException("Error adding shape.");
      }
    }

    @Override
    public AnimationBuilder<Canvas> addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
      ColorPattern color = new ColorPattern();
      color.change(t1, t2, new Integer[] {r2, g2, b2}); //NEED TO CHANGE THIS CODE TO INCORPORATE r1,g1,b1.
      //should eventually be color.change(t1,r1,g1,b1,t2,r2,g2,b2);
      MovementPattern move = new MovementPattern();
      move.change(t1, t2, new Integer[] {x2, y2}); //NEED TO CHANGE THIS CODE TO INCORPORATE x1, y1.
      //should eventually be move.change(t1,x1,y1,t2,x2,y2);
      SizeChangePattern size = new SizeChangePattern();
      size.change(t1, t2, new Integer[] {h2, w2});//NEED TO CHANGE PATTERN TO USE HEIGHT INSTEAD OF LENGTH
      //NEED TO CHANGE THIS CODE TO INCORPORATE h1,w1
      //should eventually be size.change(t1,w1,h1,t2,w2,h2);
      this.shapes.get(name).setColorPattern(color);
      this.shapes.get(name).setMovementPattern(move);
      this.shapes.get(name).setSizeChangePattern(size);
      return this;
    }
  }
}
