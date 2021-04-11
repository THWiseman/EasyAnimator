package cs5004.animator.view;

import cs5004.animator.model.Canvas;

import javax.swing.*;
import java.awt.*;

public class SwingView extends JFrame implements View {
    private Canvas model;


    public SwingView(Canvas model) {
        //load in our model.
        this.model = model;
    }

    private void setUp() {
        //SwingView 'is a' "JFrame" which will be the entire window that opens. We can use JFrame methods
        //to configure its attributes.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Easy Animator");
        setPreferredSize(new Dimension(model.getDimensions()[1],model.getDimensions()[3]));
    }


    @Override
    public void go() {
        setUp();

        //this panel is where the animation occurs.
        DrawPanel p = new DrawPanel(model);
        p.setPreferredSize(new Dimension(model.getDimensions()[1]*2,model.getDimensions()[3]*2));
        //add the animation panel to a scrollable pane.
        JScrollPane pane = new JScrollPane(p);
        pane.setPreferredSize(p.getPreferredSize());
        pane.setVisible(true);
        //add the scrollable pane to the JFrame
        this.add(pane);
        this.setBounds(model.getDimensions()[0],model.getDimensions()[2],model.getDimensions()[1]+this.getInsets().left
                +this.getInsets().right,model.getDimensions()[3]+this.getInsets().top + this.getInsets().bottom);
        //this.pack();

        this.setVisible(true);
        //make the JFrame visible.


        //Call the paint at time method of the DrawFrame to draw all shapes at the specified time.
        try {
            for (int i = model.getStartTime(); i <= model.getEndTime(); i++) {
                p.setTime(i);
                p.repaint();
                System.out.println(String.format("X: %d Y: %d Width: %d Height: %d",this.getX(),this.getY(),this.getWidth(),this.getHeight()));
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            throw new IllegalStateException("Error with Thread.sleep");
        }

    }
}
