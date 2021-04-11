package cs5004.animator.view;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.CanvasImpl;
import cs5004.animator.model.LogNode;
import cs5004.animator.model.Oval;
import cs5004.animator.model.PatternType;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;
import cs5004.animator.util.AnimationReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


  @Override
  public void go() {
    String svgText = this.docBuilder();
    try {
      FileWriter write = new FileWriter("view.svg");
      BufferedWriter output = new BufferedWriter(write);
      output.write(svgText);
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }



  public String docBuilder() {
 // String startTag = "<svg width=\"" + canvas.getDimensions()[0] + "\" height=\""
   //   + canvas.getDimensions()[1] + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n";
    String startTag = "<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n";
  String endTag = "</svg>";
  String doc = startTag + this.shapeBuilder() + endTag;
  return doc;
  }


  public String shapeBuilder() {
    StringBuilder str = new StringBuilder();
    String endTag = "";
    for (Map.Entry<String, Shape> shape : shapes.entrySet()) {
      if (shape.getValue() instanceof Rectangle) {
        str.append("   <rect id=\"").append(shape.getKey()).append("\" x=\"")
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
        endTag = "   </rect>\n";
      } else if (shape.getValue() instanceof Oval) {
        str.append("   <ellipse id=\"").append(shape.getKey()).append("\" cx=\"")
            .append(shape.getValue().getPosition(shape.getValue().getAppearTime())[0])
            .append("\"c y=\"")
            .append(shape.getValue().getPosition(shape.getValue().getAppearTime())[1])
            .append("\" rx=\"")
            .append(shape.getValue().getSize(shape.getValue().getAppearTime())[0])
            .append("\" ry=\"")
            .append(shape.getValue().getSize(shape.getValue().getAppearTime())[1])
            .append("\" fill=\"rgb(")
            .append(shape.getValue().getColor(shape.getValue().getAppearTime())[0]).append(",")
            .append(shape.getValue().getColor(shape.getValue().getAppearTime())[1]).append(",")
            .append(shape.getValue().getColor(shape.getValue().getAppearTime())[2])
            .append(")\" visibility=\"visible\" >\n");
        endTag = "   </ellipse>\n";
      } else {
        str.append("not working");
      }
      str.append(buildAnimation(shape.getValue()));
      str.append(endTag);
    }
    return str.toString();
  }


  public String buildAnimation(Shape shape) {
    StringBuilder str = new StringBuilder();
    List<LogNode> altered = shape.pullChangeLog();


    altered = altered.stream().filter(l -> ((!Arrays.equals(l.getStartValues(), l.getEndValues()) && l.getType().equals(PatternType.COLOR)) )).collect(
        Collectors.toList());

    //for (LogNode l : altered) {

    for (LogNode l : shape.pullChangeLog()) {

      if (l.getType() == PatternType.COLOR) {
        if (Arrays.equals(l.getStartValues(), l.getEndValues())) {
          str.append("");
        } else {
          str.append("      <animate attributeName=\"fill\" attributeType=\"CSS\" begin=\"")
              .append(l.getFrame1() / speed).append("s\"").append(" end=\"")
              .append(l.getFrame2() / speed)
              .append("s\"").append(" from=\"rbg(")
              .append(l.getStartValues()[0]).append(", ").append(l.getStartValues()[1]).append(", ")
              .append(l.getStartValues()[2]).append(")\" to=\"rgb(").append(l.getEndValues()[0])
              .append(", ")
              .append(l.getEndValues()[1]).append(", ").append(l.getEndValues()[2])
              .append(")\" fill=\"freeze\" />\n");
        }

      } else if (l.getType() == PatternType.MOVEMENT) {
        str.append("      <animate attributeType=\"xml\" begin=\"").append(l.getFrame1()/speed).append("s\"")
            .append(" end=\"").append(l.getFrame2()/speed).append("s\"")
            .append(" attributeName=\"x\" from=\"").append(l.getStartValues()[0])
            .append("\" to=\"").append(l.getEndValues()[0]).append("\" fill=\"freeze\" />\n")
            .append("      <animate attributeType=\"xml\" begin=\"").append(l.getFrame1()/speed).append("s\"")
            .append(" end=\"").append(l.getFrame2()/speed).append("s\"")
            .append(" attributeName=\"y\" from=\"").append(l.getStartValues()[1])
            .append("\" to=\"").append(l.getEndValues()[1])
            .append("\" fill=\"freeze\" />\n");

      } else if (l.getType() == PatternType.SIZECHANGE) {
        str.append("      <animate attributeType=\"xml\" begin=\"").append(l.getFrame1()/speed)
            .append("s\"").append(" end=\"").append(l.getFrame2()/speed).append("s\"")
            .append(" attributeName=\"width\" from=\"").append(l.getStartValues()[0])
            .append("\" to=\"").append(l.getEndValues()[0]).append("\" fill=\"freeze\" />\n")
            .append("      <animate attributeType=\"xml\" begin=\"").append(l.getFrame1()/speed)
            .append("s\"").append(" end=\"").append(l.getFrame2()/speed).append("s\"")
            .append(" attributeName=\"height\" from=\"")
            .append(l.getStartValues()[1]).append("\" to=\"").append(l.getEndValues()[1])
            .append("\" fill=\"freeze\" />\n");
      } else {
        str.append("");
      }
    }
    return str.toString();
  }


}

class Main {

  public static void main(String args[]) throws IOException {
    Canvas canvas1;
    BufferedReader reader = null;
    try {
      reader = new BufferedReader((new FileReader("src/toh-3.txt")));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    canvas1 = AnimationReader.parseFile(reader, new CanvasImpl.Builder());
    SVGView SVG = new SVGView(System.out, canvas1);
    SVG.go();

    }
  }
