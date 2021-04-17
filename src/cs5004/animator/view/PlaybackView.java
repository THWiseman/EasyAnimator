package cs5004.animator.view;

import cs5004.animator.controller.PlaybackController;
import cs5004.animator.model.Canvas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class PlaybackView extends JFrame {

  private Canvas model;
  private int time;
  private int ticksPerSecond;
  private int endTime; //don't want to ever go past this time.
  DrawPanel p;
  private JPanel buttonPanel;
  private JButton playButton,pauseButton, rewindButton;

  public PlaybackView(Canvas model) {

    //set up some  basic parameters for our JFrame Window.
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("Easy Animator");
    setPreferredSize(new Dimension(model.getDimensions()[1], model.getDimensions()[3]));
    this.setLayout(new BorderLayout());

    //set up a buttonPanel to contain our buttons
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    //lets add some buttons to the button panel.
    playButton = new JButton("Play");
    buttonPanel.add(playButton);

    pauseButton = new JButton("Pause");
    buttonPanel.add(pauseButton);

    rewindButton = new JButton("Rewind");
    buttonPanel.add(rewindButton);

    //load in our model.
    this.model = model;
    this.endTime = model.getEndTime();

    //for now, let's initialize both the time and ticksPerSecond to one. We can change them later.
    this.time = 1;
    this.ticksPerSecond = 1;

    //load in the draw panel where the animation will take place. While we're add it, add that draw panel to a
    //scrollable pane. Once we're done setting up the DrawPanel and pane, add the completed pane to this JFrame.
    this.p = new DrawPanel(model);
    p.setPreferredSize(new Dimension(model.getDimensions()[1], model.getDimensions()[3]));
    JScrollPane pane = new JScrollPane(p);
    pane.setPreferredSize(p.getPreferredSize());
    pane.setVisible(true);
    this.add(pane, BorderLayout.CENTER);



    //make the JFrame visible
    this.pack();
    this.setVisible(true);

  }

  //causes the draw panel to redraw itself based on the current time. Maybe we want to call this from the controller.
  public void refresh() {
    p.setTime(time);
    p.repaint();
  }


  //once this function is called, the screen will start refreshing and will never stop. Not sure what happens if
  //called multiple times.
  public void autoRefresh() {
    int delay = 1000 / ticksPerSecond;
    new Timer(delay, screenRefresher).start();
  }

  //The only reason screenRefresher is an actionListener is so that it will work with a timer. This thing, when
  //hooked up to a timer, will constantly refresh the screen/
  ActionListener screenRefresher = new ActionListener() {

    public void actionPerformed(ActionEvent evt) {
      p.setTime(time);
      p.repaint();
    }
  };
}
