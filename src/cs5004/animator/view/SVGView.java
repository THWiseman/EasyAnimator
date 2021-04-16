package cs5004.animator.view;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.LogNode;
import cs5004.animator.model.Oval;
import cs5004.animator.model.PatternType;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class can be used to create an SVG animation from a given Canvas. This animation can be
 * retrieved by writing an SVG interpretation of the animation to a given Appendable or directly to
 * the console output.
 */
public class SVGView implements View {

  Appendable appendable;
  Canvas canvas;
  Map<String, Shape> shapes;
  double speed = 10;

  /**
   * Creates a new SVGView object from a given Canvas, which can output to a given location.
   *
   * @param appendable The output of the animation.
   * @param canvas The Canvas from which the animation will be built.
   */
  public SVGView(Appendable appendable, Canvas canvas) {
    this.appendable = appendable;
    this.canvas = canvas;
    this.shapes = canvas.getShapeMap();
  }

  /**
   * Creates a new SVGView object from a given Canvas.
   *
   * @param canvas The Canvas from which the animation will be built.
   */
  public SVGView(Canvas canvas) {
    this.canvas = canvas;
    this.shapes = canvas.getShapeMap();
  }

  @Override
  public void run() {
    String svgText = this.docBuilder();
    try {
      appendable.append(svgText);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // System.exit(0);
  }

  @Override
  public void run(int tps) {
    if (tps < 1) {
      throw new IllegalArgumentException("Ticks per second must be an integer 1 or greater");
    }

    this.speed = tps;
    String svgText = this.docBuilder();
    try {
      appendable.append(svgText);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // System.exit(0);
  }

  @Override
  public void run(int tps, String filepath) {
    if (tps < 1) {
      throw new IllegalArgumentException("Ticks per second must be an integer 1 or greater");
    }

    this.speed = tps;
    // File file = new File("./" + File.separator + "resources" + File.separator + filepath);
    String svgText = this.docBuilder();
    try {
      FileWriter write = new FileWriter(filepath); //  file);
      BufferedWriter output = new BufferedWriter(write);
      output.write(svgText);
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // System.exit(0);
  }

  @Override
  public void run(String filepath) {
    this.speed = 1;
    //   File file = new File("./" + File.separator + "resources" + File.separator + filepath);
    String svgText = this.docBuilder();
    try {
      FileWriter write = new FileWriter(filepath); //(file)
      BufferedWriter output = new BufferedWriter(write);
      output.write(svgText);
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    //  System.exit(0);
  }


  private String docBuilder() {
    String startTag = "<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n";
    String endTag = "</svg>";
    String doc = startTag + this.shapeBuilder() + endTag;
    return doc;
  }


  private String shapeBuilder() {
    StringBuilder str = new StringBuilder();
    String endTag = "";

    for (String id : canvas.getOrderedShapeNames()) {
      Shape shape = shapes.get(id);
      if (shape instanceof Rectangle) {
        str.append("   <rect id=\"").append(id).append("\" x=\"")
            .append(shape.getPosition(shape.getAppearTime())[0])
            .append("\" y=\"")
            .append(shape.getPosition(shape.getAppearTime())[1])
            .append("\" width=\"")
            .append(shape.getSize(shape.getAppearTime())[0])
            .append("\" height=\"")
            .append(shape.getSize(shape.getAppearTime())[1])
            .append("\" fill=\"rgb(")
            .append(shape.getColor(shape.getAppearTime())[0]).append(",")
            .append(shape.getColor(shape.getAppearTime())[1]).append(",")
            .append(shape.getColor(shape.getAppearTime())[2])
            .append(")\" visibility=\"visible\" >\n");
        endTag = "   </rect>\n";
      } else if (shape instanceof Oval) {
        str.append("   <ellipse id=\"").append(id).append("\" cx=\"")
            .append(shape.getPosition(shape.getAppearTime())[0])
            .append("\" cy=\"")
            .append(shape.getPosition(shape.getAppearTime())[1])
            .append("\" rx=\"")
            .append(shape.getSize(shape.getAppearTime())[0])
            .append("\" ry=\"")
            .append(shape.getSize(shape.getAppearTime())[1])
            .append("\" fill=\"rgb(")
            .append(shape.getColor(shape.getAppearTime())[0]).append(",")
            .append(shape.getColor(shape.getAppearTime())[1]).append(",")
            .append(shape.getColor(shape.getAppearTime())[2])
            .append(")\" visibility=\"visible\" >\n");
        endTag = "   </ellipse>\n";
      } else {
        str.append("not working");
      }
      str.append(buildAnimation(shape));
      str.append(endTag);
    }

    return str.toString();
  }

  private String buildAnimation(Shape shape) {
    StringBuilder str = new StringBuilder();
    List<LogNode> altered = shape.pullChangeLog();

    for (LogNode l : shape.pullChangeLog()) {

      if (l.getType() == PatternType.COLOR) {
        str.append("      <animate attributeName=\"fill\" attributeType=\"fill\" begin=\"")
            .append(l.getFrame1() / speed).append("s\"").append(" dur=\"")
            .append((l.getFrame2() - l.getFrame1()) / speed)
            .append("s\"").append(" from=\"rgb(")
            .append(l.getStartValues()[0]).append(", ").append(l.getStartValues()[1]).append(", ")
            .append(l.getStartValues()[2]).append(")\" to=\"rgb(").append(l.getEndValues()[0])
            .append(", ")
            .append(l.getEndValues()[1]).append(", ").append(l.getEndValues()[2])
            .append(")\" fill=\"freeze\" />\n");

      } else if (l.getType() == PatternType.MOVEMENT) {
        str.append("      <animate attributeType=\"xml\" begin=\"").append(l.getFrame1() / speed)
            .append("s\"")
            .append(" dur=\"").append((l.getFrame2() - l.getFrame1()) / speed).append("s\"")
            .append(" attributeName=\"x\" from=\"").append(l.getStartValues()[0])
            .append("\" to=\"").append(l.getEndValues()[0]).append("\" fill=\"freeze\" />\n")
            .append("      <animate attributeType=\"xml\" begin=\"").append(l.getFrame1() / speed)
            .append("s\"")
            .append(" dur=\"").append((l.getFrame2() - l.getFrame1()) / speed).append("s\"")
            .append(" attributeName=\"y\" from=\"").append(l.getStartValues()[1])
            .append("\" to=\"").append(l.getEndValues()[1])
            .append("\" fill=\"freeze\" />\n");

      } else if (l.getType() == PatternType.SIZECHANGE) {
        str.append("      <animate attributeType=\"xml\" begin=\"").append(l.getFrame1() / speed)
            .append("s\"").append(" dur=\"").append((l.getFrame2() - l.getFrame1()) / speed)
            .append("s\"")
            .append(" attributeName=\"width\" from=\"").append(l.getStartValues()[0])
            .append("\" to=\"").append(l.getEndValues()[0]).append("\" fill=\"freeze\" />\n")
            .append("      <animate attributeType=\"xml\" begin=\"").append(l.getFrame1() / speed)
            .append("s\"").append(" dur=\"").append((l.getFrame2() - l.getFrame1()) / speed)
            .append("s\"")
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