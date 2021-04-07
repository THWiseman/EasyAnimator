package cs5004.animator.view;

import java.io.IOException;
import java.util.Map;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.Shape;

public class TextView implements View {

  Appendable output;

  public TextView() {
    this.output = System.out;
  }


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

  @Override
  public void go(Appendable output, Canvas canvas) {
    try {
      output.append(canvas.getStringDescription());
    } catch (IOException e) {
      throw new IllegalStateException("Appendable error");
    }

  }

  @Override
  public void go(Canvas canvas) {

  }
}
