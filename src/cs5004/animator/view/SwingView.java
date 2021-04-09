package cs5004.animator.view;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SwingView extends JFrame implements View {
    private Canvas model;
    private java.awt.Canvas background;
    private JFrame frame;
    private Map<String, Shape> shapes;
    private Component[] shapeComponents;
    private int hi;

    public SwingView(Canvas model) {
        //load in our model.
        this.model = model;

        //The SwingView object is a "JFrame" which will be the entire window that opens. We can use JFrame methods
        //to configure its attributes.
        setBounds(model.getDimensions()[0],model.getDimensions()[2],model.getDimensions()[1],model.getDimensions()[3]);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Easy Animator");
        setLocation(200,200);

        //Create a panel to contain our drawing. This panel will be added to the JFrame.
        JPanel p = new JPanel();
        p.setSize(50,50);

        //A Canvas (not to be confused with our model by the same name) is a rectangular area which can be drawn on.
        this.background = new java.awt.Canvas();
        this.background.setSize(25,25);
        //this.background.setBounds(model.getDimensions()[0],model.getDimensions()[2],50,50);
        this.background.setBackground(new Color(128,0,128));

        p.add(this.background);

        this.add(p);

        setVisible(true);
    }


    @Override
    public void go() {


    }
}
