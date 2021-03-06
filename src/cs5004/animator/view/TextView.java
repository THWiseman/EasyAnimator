package cs5004.animator.view;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.LogNode;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Text view of the Canvas model. Prints a string description of the canvas provided in the
 * constructor to the appendable provided in the constructor.
 */
public class TextView implements View {

  Appendable output;
  Canvas canvas;
  Map<String, Shape> shapes;

  /**
   * Constructor for the TextView. Must provided an appendable to output the text to, and a Canvas
   * from which to draw information.
   *
   * @param output Appendable object to print text to when the go() method is called.
   * @param canvas Canvas object to get the information to display from.
   */
  public TextView(Appendable output, Canvas canvas) {
    this.output = output;
    this.canvas = canvas;
    this.shapes = canvas.getShapeMap();
  }

  /**
   * Constructor for the TextView. System.out will be used as the default output.
   *
   * @param canvas The Canvas from which the animation will be built.
   */
  public TextView(Canvas canvas) {
    this.output = System.out;
    this.canvas = canvas;
    this.shapes = canvas.getShapeMap();
  }


  /**
   * Returns a string that looks something like this: Create rectangle R with corner at (200,200),
   * width 50 and height 100, and color {255,255,255}. Create oval C with center at (500,100),
   * radius 60 and 30, and color {128,0,240}.
   *
   * @return
   */
  String getStringDescription() {
    String allCreates = "";

    //this for loop will create a string like "Create rectangle R with corner at (200,200),
    //width 50, height 100, and color {255,255,255}.\n" for each shape and append it to createString
    for (Map.Entry<String, Shape> e : shapes.entrySet()) {
      String createString = "Create "; //"Create "
      Shape shape = e.getValue();
      try {
        if (e.getValue() instanceof Rectangle) {
          //rectangle
          createString += "rectangle ";
          //"R with corner at (200,200), "
          createString += String.format("%s with corner at (%d,%d), ", e.getKey(),
              shape.getPosition(shape.getAppearTime())[0],
              shape.getPosition(shape.getAppearTime())[1]);
          //width 50, height 100, and color.\n"
          createString += String.format("width %d, height %d, and color {%d, %d, %d}.\n",
              shape.getSize(shape.getAppearTime())[0],
              shape.getSize(shape.getAppearTime())[1], shape.getColor(shape.getAppearTime())[0],
              shape.getColor(shape.getAppearTime())[1],
              shape.getColor(shape.getAppearTime())[2]);
        }
        if (e.getValue() instanceof Oval) {
          //oval
          createString += "oval ";
          //"C with center at (500,100), "
          createString += String.format("%s with center at (%d,%d), ", e.getKey(),
              shape.getPosition(shape.getAppearTime())[0],
              shape.getPosition(shape.getAppearTime())[1]);
          //radius 60 and height 30\n"
          createString += String.format("radius %d and %d, and color {%d,%d,%d}.\n",
              shape.getSize(shape.getAppearTime())[0],
              shape.getSize(shape.getAppearTime())[1], shape.getColor(shape.getAppearTime())[0],
              shape.getColor(shape.getAppearTime())[1],
              shape.getColor(shape.getAppearTime())[2]);
        }
        allCreates += createString;
      } catch (NullPointerException e2) {
        System.out.println("Shape " + e.getKey() + " has no animations");
      }
    }
    allCreates += "\n"; //add extra newline to separate from next section.

    //this for loop will create a string like "R appears at time t=1 and disappears at time t=100.\n
    //and append it to allAppears.
    String allAppears = "";
    for (Map.Entry<String, Shape> e : shapes.entrySet()) {
      String appearString = "";
      appearString +=
          e.getKey() + String.format(" appears at time t=%d and disappears at time t=%d.\n",
              e.getValue().getAppearTime(), e.getValue().getDisappearTime());
      allAppears += appearString;
    }
    allAppears += "\n"; //add extra newline to separate from next section.

    //this for loop will create strings like:
    //C changes from blue to green from time t=50 to t=80\n
    //R Moves from (300,300) to (200,200) from time t=70 to t=100\n
    //R changes width from 50 to 25 from time t=51 to t=70\n
    //and append them to allChanges.
    String allChanges = "";
    for (Map.Entry<String, Shape> e : shapes.entrySet()) {
      if (!e.getValue().getChangeLog().equals("")) {
        List<LogNode> changelog = e.getValue().pullChangeLog();
        for (LogNode l : changelog) {
          allChanges += e.getKey() + " " + l.getChangeNotes() + "\n";
        }
      }
    }
    return allCreates + allAppears + allChanges;
  }

  @Override
  public void run() {
    try {
      output.append(this.getStringDescription());
    } catch (IOException e) {
      throw new IllegalStateException("Appendable error.");
    }
  }

  @Override
  public void run(int tps) {
    if (tps < 1) {
      throw new IllegalArgumentException("Ticks per second must be 1 or greater.");
    }
    try {
      output.append(this.getStringDescription());
    } catch (IOException e) {
      throw new IllegalStateException("Appendable error.");
    }
  }

  @Override
  public void run(int tps, String filepath) {
    if (tps < 1) {
      throw new IllegalArgumentException("Ticks per second must be 1 or greater.");
    }
    try {
      FileWriter write = new FileWriter(filepath);
      BufferedWriter output = new BufferedWriter(write);
      output.write(getStringDescription());
      output.close();
    } catch (IOException e) {
      throw new IllegalStateException("Error writing to file.");
    }
  }


  @Override
  public void run(String filepath) {
    try {
      FileWriter write = new FileWriter(filepath);
      BufferedWriter output = new BufferedWriter(write);
      output.write(getStringDescription());
      output.close();
    } catch (IOException e) {
      throw new IllegalStateException("Error writing to file.");
    }
  }

}
