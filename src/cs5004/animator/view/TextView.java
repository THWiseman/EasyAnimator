package cs5004.animator.view;
import java.io.IOException;
import java.util.Map;
import cs5004.animator.model.Canvas;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;

/**
 * Text view of the Canvas model. Prints a string description of the canvas provided in
 * the constructor to the appendable provided in the constructor.
 */
public class TextView implements View {

  Appendable output;
  Canvas canvas;
  Map<String,Shape> shapes;

  /**
   * Constructor for the TextView. Must provided an appendable to output the text to, and
   * a Canvas from which to draw information.
   * @param output Appendable object to print text to when the go() method is called.
   * @param canvas Canvas object to get the information to display from.
   */
  public TextView(Appendable output, Canvas canvas) {

    this.output = output;
    this.canvas = canvas;
    this.shapes = canvas.getShapeMap();
  }


  /**
   * Returns a string that looks something like this:
   * Create rectangle R with corner at (200,200), width 50 and height 100, and color {255,255,255}.
   * Create oval C with center at (500,100), radius 60 and 30, and color {128,0,240}.
   * @return
   */
  public String getStringDescription() {
    String allCreates = "";

    //this for loop will create a string like "Create rectangle R with corner at (200,200),
    //width 50, height 100, and color {255,255,255}.\n" for each shape and append it to createString.
    for (Map.Entry<String, Shape> e : shapes.entrySet()) {
      String createString = "Create "; //"Create "
      createString += e.getValue().toString() + " "; //"rectangle "

      if (e.getValue() instanceof Rectangle) {
        //"R with corner at (200,200), "
        createString += String.format("%s with corner at (%d,%d), ", e.getKey(), e.getValue().getPosition(0)[0], e.getValue().getPosition(0)[1]);
        //width 50, height 100, and color.\n"
        createString += String.format("width %d, height %d, and color {%d, %d, %d}.\n", e.getValue().getSize(0)[0],
                e.getValue().getSize(0)[1],e.getValue().getColor(0)[0],e.getValue().getColor(0)[1],
                e.getValue().getColor(0)[2]);
      }
      if (e.getValue() instanceof Oval) {
        //"C with center at (500,100), "
        createString += String.format("%s with center at (%d,%d), ", e.getKey(), e.getValue().getPosition(0)[0], e.getValue().getPosition(0)[1]);
        //radius 60 and height 30\n"
        createString += String.format("radius %d and %d, and color {%d,%d,%d}.\n", e.getValue().getSize(0)[0],
                e.getValue().getSize(0)[1],e.getValue().getColor(0)[0],e.getValue().getColor(0)[1],
                e.getValue().getColor(0)[2]);
      }
      allCreates += createString;
    }
    allCreates += "\n"; //add extra newline to separate from next section.

    //this for loop will create a string like "R appears at time t=1 and disappears at time t=100.\n
    //and append it to allAppears.
    String allAppears = "";
    for (Map.Entry<String, Shape> e : shapes.entrySet()) {
      String appearString = "";
      //appearString += e.getKey() + String.format(" appears at time t=%d and disappears at time t=%d.\n", e.getValue().getAppearTime(), e.getValue().getDisappearTime());
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
      if(e.getValue().getChangeLog()!="") {
        allChanges += e.getKey() + " " + e.getValue().getChangeLog();
      }
    }
    return allCreates + allAppears + allChanges;
  }

  @Override
  public void go() {
    try {
     output.append(this.getStringDescription());
    } catch (IOException e) {
      throw new IllegalStateException("Appendable error");
    }
  }

}
