package cs5004.animator.view;

import cs5004.animator.model.Canvas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 * This class creates a JFrame window that will automatically display an animation in a JPanel when
 * the go() method is called. It must be constructed with a valid Canvas object.
 */
public class SwingView extends JFrame implements View {

  private Canvas model;

  /**
   * Constructor for the SwingView. Must pass in a valid canvas object.
   *
   * @param model The Canvas from which the animation will be built.
   */
  public SwingView(Canvas model) {
    //load in our model.
    this.model = model;
  }

  private void setUp() {
    //SwingView 'is a' "JFrame" which will be the entire window that opens.
    //We can use JFrame methods to configure its attributes.
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("Easy Animator");
    setPreferredSize(new Dimension(model.getDimensions()[1], model.getDimensions()[3]));
  }

  /**
   * This method starts the animation in a new JFrame window at 1 tick per second.
   */
  @Override
  public void run() {
    int ticksPerSecond = 1;
    setUp();

    //this panel is where the animation occurs.
    DrawPanel p = new DrawPanel(model);
    p.setPreferredSize(new Dimension(model.getDimensions()[1], model.getDimensions()[3]));
    //add the animation panel to a scrollable pane.
    JScrollPane pane = new JScrollPane(p);
    pane.setPreferredSize(p.getPreferredSize());
    pane.setVisible(true);
    //add the scrollable pane to the JFrame
    this.add(pane);
    this.setBounds(model.getDimensions()[0], model.getDimensions()[2], model.getDimensions()[1]
            + this.getInsets().left
            + this.getInsets().right,
        model.getDimensions()[3] + this.getInsets().top + this.getInsets().bottom);
    //this.pack();

    //make the JFrame visible.
    this.setVisible(true);

    ActionListener taskPerformer = new ActionListener() {
      int tick = 1;
      int endTime = model.getEndTime();

      public void actionPerformed(ActionEvent evt) {
        if (tick >= endTime) {
          System.exit(0);
        }
        p.setTime(tick);
        p.repaint();
        tick++;
      }
    };
    int delay = 1000 / ticksPerSecond;
    new Timer(delay, taskPerformer).start();
  }

  /**
   * This method starts the animation in a new JFrame window.
   *
   * @param tps the number of ticks per second in the animation.
   */
  @Override
  public void run(int tps) {
    int ticksPerSecond = tps;
    setUp();

    //this panel is where the animation occurs.
    DrawPanel p = new DrawPanel(model);
    p.setPreferredSize(new Dimension(model.getDimensions()[1], model.getDimensions()[3]));
    //add the animation panel to a scrollable pane.
    JScrollPane pane = new JScrollPane(p);
    pane.setPreferredSize(p.getPreferredSize());
    pane.setVisible(true);
    //add the scrollable pane to the JFrame
    this.add(pane);
    this.setBounds(model.getDimensions()[0], model.getDimensions()[2],
        model.getDimensions()[1] + this.getInsets().left
            + this.getInsets().right, model.getDimensions()[3]
            + this.getInsets().top + this.getInsets().bottom);
    //this.pack();

    //make the JFrame visible.
    this.setVisible(true);

    ActionListener taskPerformer = new ActionListener() {
      int tick = 1;
      int endTime = model.getEndTime();

      public void actionPerformed(ActionEvent evt) {
        if (tick >= endTime) {
          System.exit(0);
        }
        p.setTime(tick);
        p.repaint();
        tick++;
      }
    };
    int delay = 1000 / ticksPerSecond;
    new Timer(delay, taskPerformer).start();
  }

  /**
   * Since this view does not create any output files, it will ignore the filepath argument. This
   * method starts the animation in a new JFrame window.
   *
   * @param tps      the number of ticks or 'frames' per second in the animation.
   * @param filepath the filepath of the output file. Ignored for this view.
   */
  @Override
  public void run(int tps, String filepath) {
    int ticksPerSecond = tps;
    setUp();

    //this panel is where the animation occurs.
    DrawPanel p = new DrawPanel(model);
    p.setPreferredSize(new Dimension(model.getDimensions()[1], model.getDimensions()[3]));
    //add the animation panel to a scrollable pane.
    JScrollPane pane = new JScrollPane(p);
    pane.setPreferredSize(p.getPreferredSize());
    pane.setVisible(true);
    //add the scrollable pane to the JFrame
    this.add(pane);
    this.setBounds(model.getDimensions()[0], model.getDimensions()[2],
        model.getDimensions()[1] + this.getInsets().left
            + this.getInsets().right, model.getDimensions()[3]
            + this.getInsets().top + this.getInsets().bottom);
    //this.pack();

    //make the JFrame visible.
    this.setVisible(true);

    ActionListener taskPerformer = new ActionListener() {
      int tick = 1;
      int endTime = model.getEndTime();

      public void actionPerformed(ActionEvent evt) {
        if (tick >= endTime) {
          System.exit(0);
        }
        p.setTime(tick);
        p.repaint();
        tick++;
      }
    };
    int delay = 1000 / ticksPerSecond;
    new Timer(delay, taskPerformer).start();
  }

  /**
   * Since the SwingView does not output any files, it will simply ignore any filepath passed into
   * it.
   *
   * @param filepath the filepath of the output file. Does nothing for this view.
   */
  @Override
  public void run(String filepath) {
    int ticksPerSecond = 1;
    setUp();

    //this panel is where the animation occurs.
    DrawPanel p = new DrawPanel(model);
    p.setPreferredSize(new Dimension(model.getDimensions()[1], model.getDimensions()[3]));
    //add the animation panel to a scrollable pane.
    JScrollPane pane = new JScrollPane(p);
    pane.setPreferredSize(p.getPreferredSize());
    pane.setVisible(true);
    //add the scrollable pane to the JFrame
    this.add(pane);
    this.setBounds(model.getDimensions()[0], model.getDimensions()[2],
        model.getDimensions()[1] + this.getInsets().left
            + this.getInsets().right, model.getDimensions()[3]
            + this.getInsets().top + this.getInsets().bottom);
    //this.pack();

    //make the JFrame visible.
    this.setVisible(true);

    ActionListener taskPerformer = new ActionListener() {
      int tick = 1;
      int endTime = model.getEndTime();

      public void actionPerformed(ActionEvent evt) {
        if (tick >= endTime) {
          System.exit(0);
        }
        p.setTime(tick);
        p.repaint();
        tick++;
      }
    };
    int delay = 1000 / ticksPerSecond;
    new Timer(delay, taskPerformer).start();
  }
}
