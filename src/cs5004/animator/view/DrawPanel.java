package cs5004.animator.view;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * This class can display a JPanel view of a Canvas for any given time. It must be constructed with
 * a Canvas. It's internal time should be updated with the setTime method. Java Swing will
 * automatically display this panel if th paint() or repaint() method is called on it, since this
 * class overrides paintComponent.
 */
class DrawPanel extends JPanel {

  cs5004.animator.model.Canvas canvas;
  java.util.List<String> orderedShapeNames;
  java.util.List<Shape> shapes;
  int time = 1;

  /**
   * Constructor for the draw panel. Must pass in a valid Canvas object.
   *
   * @param canvas The Canvas object that the animation is being built from.
   */
  public DrawPanel(Canvas canvas) {
    this.canvas = canvas;
    this.orderedShapeNames = canvas.getOrderedShapeNames();
    this.shapes = new ArrayList<>();
    for (String s : this.orderedShapeNames) {
      shapes.add(canvas.getShapeMap().get(s));
    }
  }

  void setTime(int time) {
    if (time < 1) {
      throw new IllegalArgumentException("Time must be greater than zero.");
    }
    System.out.println(String.format("Current time: %d", time));
    this.time = time;
  }


  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.WHITE);

    for (Shape shape : this.shapes) {
      //shape attributes
      if (shape.getColor(time) == null) {
        continue;
      }
      Color color = new Color(shape.getColor(time)[0], shape.getColor(time)[1],
          shape.getColor(time)[2]);
      g.setColor(color);
      int xPos = shape.getPosition(time)[0];
      int yPos = shape.getPosition(time)[1];
      int shapeWidth = shape.getSize(time)[0];
      int shapeHeight = shape.getSize(time)[1];

      if (shape instanceof Rectangle) {
        g.fillRect(xPos, yPos, shapeWidth, shapeHeight);
      }
      if (shape instanceof Oval) {
        g.fillOval(xPos, yPos, shapeWidth, shapeHeight);
      }
    }

  }
}
