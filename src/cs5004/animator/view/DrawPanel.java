package cs5004.animator.view;

import javax.swing.*;
import java.awt.*;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;

public class DrawPanel extends JPanel {

    cs5004.animator.model.Canvas canvas;
    java.util.List<Shape> shapes;

    public DrawPanel(Canvas canvas) {
        this.canvas = canvas;
        this.shapes = canvas.getAllShapes();
    }

    public void drawShape(Graphics g, Shape shape, int time) {
        Graphics g2d = (Graphics2D) g;

        //shape attributes
        Color color = new Color(shape.getColor(time)[0], shape.getColor(time)[1], shape.getColor(time)[2]);
        g2d.setColor(color);
        int xPos = shape.getPosition(time)[0];
        int yPos = shape.getPosition(time)[1];
        int shapeWidth = shape.getSize(time)[0];
        int shapeHeight = shape.getSize(time)[1];

        //JPanel attributes. Might need them to align the animation with the panel properly.
        Dimension size = getSize();
        int width = size.width;
        int height = size.height;
        int x = getX();
        int y = getY();

        if(shape instanceof Rectangle) {
            g2d.fillRect(xPos, yPos,shapeWidth,shapeHeight);
        }
        if(shape instanceof Oval) {
            g2d.fillOval(x +xPos,y + yPos,shapeWidth,shapeHeight);
        }
    }

    public void paintAtTime(Graphics g, int time) {
        super.paintComponent(g);
        for (Shape s : this.shapes) {
            drawShape(g, s, time);
        }

    }


}
