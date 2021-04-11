package cs5004.animator.view;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.CanvasImpl;
import cs5004.animator.model.LogNode;
import cs5004.animator.model.Oval;
import cs5004.animator.model.PatternType;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class SVGView implements View {
  Appendable output;
  Canvas canvas;
  Map<String, Shape> shapes;
  int speed = 10;

  public SVGView(Appendable output, Canvas canvas) throws IOException {
    this.output = output;
    this.canvas = canvas;
    this.shapes = canvas.getShapeMap();
  }

  // Creates a FileWriter
  FileWriter file = new FileWriter("view.svg");

 // <ellipse id="E" cx="500" cy="100" rx="60" ry="30" fill="rgb(255,128,0)" visibility="visible" >

  public String shapeBuilder() {
   StringBuilder str = new StringBuilder();
   String endTag = "";
   for (Map.Entry<String, Shape> shape : shapes.entrySet()) {
     if (shape.getValue() instanceof Rectangle) {
       str.append("<rect id=\"").append(shape.getKey()).append("\" x=\"")
           .append(shape.getValue().getPosition(shape.getValue().getAppearTime())[0])
           .append("\" y=\"")
           .append(shape.getValue().getPosition(shape.getValue().getAppearTime())[1])
           .append("\" width=\"")
           .append(shape.getValue().getSize(shape.getValue().getAppearTime())[0])
           .append("\" height=\"")
           .append(shape.getValue().getSize(shape.getValue().getAppearTime())[1])
           .append("\" fill=\"rgb(")
           .append(shape.getValue().getColor(shape.getValue().getAppearTime())[0]).append(",")
           .append(shape.getValue().getColor(shape.getValue().getAppearTime())[1]).append(",")
           .append(shape.getValue().getColor(shape.getValue().getAppearTime())[2])
           .append(")\" visibility=\"visible\" >\n");
       endTag = "</rect>\n";
     } else if (shape.getValue() instanceof Oval) {
       str.append("<ellipse id=\"").append(shape.getKey()).append("\" cx=\"")
           .append(shape.getValue().getPosition(shape.getValue().getAppearTime())[0])
           .append("\"c y=\"")
           .append(shape.getValue().getPosition(shape.getValue().getAppearTime())[1])
           .append("\" rx=\"").append(shape.getValue().getSize(shape.getValue().getAppearTime())[0])
           .append("\" ry=\"").append(shape.getValue().getSize(shape.getValue().getAppearTime())[1])
           .append("\" fill=\"rgb(")
           .append(shape.getValue().getColor(shape.getValue().getAppearTime())[0]).append(",")
           .append(shape.getValue().getColor(shape.getValue().getAppearTime())[1]).append(",")
           .append(shape.getValue().getColor(shape.getValue().getAppearTime())[2])
           .append(")\" visibility=\"visible\" >\n");
       endTag = "</ellipse>\n";
     } else {
       str.append("not working");
     }
     str.append(buildAnimation(shape.getValue()));
     str.append(endTag);
   }
   return str.toString();
  }



  public String buildAnimation(Shape shape) {

    // Creates a BufferedWriter
    //BufferedWriter output = new BufferedWriter(file);

    StringBuilder str = new StringBuilder();

      for (LogNode l : shape.pullChangeLog()) {


        if (l.getType() == PatternType.COLOR) {
          str.append("   <animate attributeName=\"fill\" attributeType=\"CSS\" begin=\"")
              .append(l.getFrame1()).append("s\"").append(" end=\"").append(l.getFrame2())
              .append("s\"").append(" attributeName=\"x\" from=\"rbg(")
              .append(l.getStartValues()[0]).append(", ").append(l.getStartValues()[1]).append(", ")
              .append(l.getStartValues()[2]).append(") to=\"rgb(").append(l.getEndValues()[0]).append(", ")
              .append(l.getEndValues()[1]).append(", ").append(l.getEndValues()[2])
              .append(")\" fill=\"freeze\" />\n");

        } else if (l.getType() == PatternType.MOVEMENT) {
          str.append("   <animate attributeType=\"xml\" begin=\"").append(l.getFrame1()).append("s\"")
              .append(" end=\"").append(l.getFrame2()).append("s\"")
              .append(" attributeName=\"x\" from=\"").append(l.getStartValues()[0])
              .append("\" to=\"").append(l.getEndValues()[0]).append("\" fill=\"freeze\" />\n")
              .append("   <animate attributeType=\"xml\" begin=\"").append(l.getFrame1())
              .append("000ms\"").append(" attributeName=\"y\" from=\"")
              .append(l.getStartValues()[1]).append("\" to=\"").append(l.getEndValues()[1])
              .append("\" fill=\"freeze\" />\n");

        } else if (l.getType() == PatternType.SIZECHANGE) {
          str.append("   <animate attributeType=\"xml\" begin=\"").append(l.getFrame1())
              .append("000ms\"").append(" end=\"").append(l.getFrame2()).append("s\"")
              .append(" attributeName=\"width\" from=\"").append(l.getStartValues()[0])
              .append("\" to=\"").append(l.getEndValues()[0]).append("\" fill=\"freeze\" />\n")
              .append("   <animate attributeType=\"xml\" begin=\"").append(l.getFrame1())
              .append("000ms\"").append(" attributeName=\"height\" from=\"")
              .append(l.getStartValues()[1]).append("\" to=\"").append(l.getEndValues()[1])
              .append("\" fill=\"freeze\" />\n");
        } else {
          str.append("");
        }
    }
      return str.toString();
  }

  @Override
  public void go() {

  }
}
