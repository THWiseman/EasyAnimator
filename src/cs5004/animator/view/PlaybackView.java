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
  private int endTime; //don't want to ever go past this time.
  private int ticksPerSecond = 1;
  DrawPanel p;
  private JPanel buttonPanelBottom;
  private JPanel buttonPanelRight;
  private JButton loopButton;
  private PlaybackController controller;

  /**
   * This class creates a JFrame window that will automatically display an animation in a JPanel
   * that can be manipulated in real time. It must be constructed with a valid Canvas object and a
   * valid PlaybackController object.
   */
  public PlaybackView(Canvas model, PlaybackController controller) {

    //load in our model and controller
    this.model = model;
    this.endTime = model.getEndTime();
    this.controller = controller;

    //set up some  basic parameters for our JFrame Window.
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("Easy Animator");
    setPreferredSize(new Dimension(model.getDimensions()[1], model.getDimensions()[3]));
    this.setLayout(new BorderLayout());

    setUpButtonPanelBottom();
    setUpButtonPanelRight();

    //for now, let's initialize both the time and ticksPerSecond to one. We can change them later.
    this.time = 1;
    this.ticksPerSecond = 1;

    //load in the draw panel where the animation will take place. While we're add it,
    // add that draw panel to a scrollable pane. Once we're done setting up the DrawPanel and pane,
    // add the completed pane to this JFrame.
    this.p = new DrawPanel(model);
    p.setPreferredSize(new Dimension(model.getDimensions()[1], model.getDimensions()[3]));
    JScrollPane pane = new JScrollPane(p);
    pane.setPreferredSize(p.getPreferredSize());
    pane.setVisible(true);
    this.add(pane, BorderLayout.CENTER);

    //make the JFrame visible
    this.pack();
    this.setSize(model.getDimensions()[1] + buttonPanelRight.getWidth() + 10,
        model.getDimensions()[3] + buttonPanelBottom.getHeight() + 35);
    this.setVisible(true);

  }

  /**
   * This method changes or sets the Canvas object from which the animation is built.
   *
   * @param model the Canvas object the animation is built from.
   */
  public void setModel(Canvas model) {
    this.model = model;
    this.endTime = model.getEndTime();
    this.getContentPane().remove(p);
    this.p = new DrawPanel(model);
    p.setPreferredSize(new Dimension(model.getDimensions()[1], model.getDimensions()[3]));
    JScrollPane pane = new JScrollPane(p);
    pane.setPreferredSize(p.getPreferredSize());
    pane.setVisible(true);
    this.add(pane, BorderLayout.CENTER);
    this.validate();
    this.repaint();
  }

  /**
   * Causes the draw panel to redraw itself based on the current time.
   */
  public void refresh() {
    if (time < this.endTime) {
      System.out.print("Refresh");
      p.setTime(
          time); //this is setting the time of the drawPanel based on what the time of the view is.
      p.repaint();
    }
  }


  private void setUpButtonPanelBottom() {
    //set up a buttonPanel to contain our buttons
    buttonPanelBottom = new JPanel();
    buttonPanelBottom.setLayout(new FlowLayout());
    this.add(buttonPanelBottom, BorderLayout.SOUTH);

    //lets add some buttons to the button panel.
    JButton playButton = new JButton("Play");
    playButton.setActionCommand("Play");
    buttonPanelBottom.add(playButton);

    JButton pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("Pause");
    buttonPanelBottom.add(pauseButton);

    JButton restartButton = new JButton("Restart");
    restartButton.setActionCommand("Restart");
    buttonPanelBottom.add(restartButton);

    JButton slowButton = new JButton("Slower");
    slowButton.setActionCommand("Slower");
    buttonPanelBottom.add(slowButton);

    JButton fastButton = new JButton("Faster");
    fastButton.setActionCommand("Faster");
    buttonPanelBottom.add(fastButton);

    loopButton = new JButton("Looping: OFF");
    loopButton.setActionCommand("Loop");
    buttonPanelBottom.add(loopButton);

    playButton.addActionListener(controller);
    pauseButton.addActionListener(controller);
    restartButton.addActionListener(controller);
    slowButton.addActionListener(controller);
    fastButton.addActionListener(controller);
    loopButton.addActionListener(controller);


  }

  private void setUpButtonPanelRight() {
    //create the right button panel for the extra credit buttons
    buttonPanelRight = new JPanel();
    buttonPanelRight.setLayout(new GridLayout(5, 1));
    this.add(buttonPanelRight, BorderLayout.EAST);

    JButton addShape = new JButton("Add Shape");
    addShape.setActionCommand("AddShape");
    buttonPanelRight.add(addShape);

    JButton addMotion = new JButton("Add Motion");
    addMotion.setActionCommand("AddMotion");
    buttonPanelRight.add(addMotion);

    JButton removeShape = new JButton("Remove Shape");
    removeShape.setActionCommand("RemoveShape");
    buttonPanelRight.add(removeShape);

    JButton loadFile = new JButton("Load File");
    loadFile.setActionCommand("LoadFile");
    buttonPanelRight.add(loadFile);

    JButton saveFile = new JButton("Save File");
    saveFile.setActionCommand("SaveFile");
    buttonPanelRight.add(saveFile);

    addShape.addActionListener(controller);
    addMotion.addActionListener(controller);
    removeShape.addActionListener(controller);
    saveFile.addActionListener(controller);
    loadFile.addActionListener(controller);

  }

  public JButton getLoopButton() {
    return this.loopButton;
  }

  /**
   * Sets the time of the view. It does NOT set the time of the DrawPanel.
   */
  public void setTime(int time) {
    if (time < 1 || time > this.endTime) {
      throw new IllegalArgumentException(
          "Time must be greater than one and less than the endTime of the model.");
    }
    this.time = time;
  }

}
