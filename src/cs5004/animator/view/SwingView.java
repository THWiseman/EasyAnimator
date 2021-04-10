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
        setLocation(200, 200);
        //setBounds(x,y,width,height) sets the dimensions of the window. x and y are the top left corner.
        setBounds(model.getDimensions()[0], model.getDimensions()[2], model.getDimensions()[1], model.getDimensions()[3]);
    }

    private DrawPanel createDrawPanel() {
        //Create a panel to contain our drawing. This panel will be added to the JFrame.
        DrawPanel p = new DrawPanel(model);
        p.setSize(600, 600);
        p.setLocation(300, 300);
        p.setBackground(Color.pink);
        return p;
    }

    @Override
    public void go() {
        setUp();
        //make the JFrame visible
        setVisible(true);

        //get the Graphics Context of the JFrame
        Graphics g = this.getGraphics();

        //Paint the components of the JFrame to the screen.
        paintComponents(g);

        DrawPanel p = createDrawPanel();

        //Call the paint at time method of the DrawFrame to draw all shapes at the specified time.
        try {
            for (int i = model.getStartTime(); i <= model.getEndTime(); i++) {
                p.paintAtTime(g, i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            throw new IllegalStateException("Error with Thread.sleep");
        }

    }
}
