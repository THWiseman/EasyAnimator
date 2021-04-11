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
    int time = 1;


    public DrawPanel(Canvas canvas) {
        this.canvas = canvas;
        this.shapes = canvas.getAllShapes();
    }

    public void setTime(int time) {
        this.time = time;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);

        for(Shape shape : this.shapes) {
        //shape attributes
        Color color = new Color(shape.getColor(time)[0], shape.getColor(time)[1], shape.getColor(time)[2]);
        g.setColor(color);
        int xPos = shape.getPosition(time)[0];
        int yPos = shape.getPosition(time)[1];
        int shapeWidth = shape.getSize(time)[0];
        int shapeHeight = shape.getSize(time)[1];

        if(shape instanceof Rectangle) {
            g.fillRect(xPos, yPos,shapeWidth,shapeHeight);
        }
        if(shape instanceof Oval) {
            g.fillOval(xPos,yPos,shapeWidth,shapeHeight);
        }
    }

    }
}
