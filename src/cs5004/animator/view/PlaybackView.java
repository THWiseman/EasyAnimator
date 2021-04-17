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
  private JPanel buttonPanel;
  private JPanel buttonPanelExtra;
  private JButton playButton,pauseButton, restartButton, slowButton, fastButton, loopButton;
  private JButton addShape,addMotion,removeShape,loadFile,saveFile;

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
    playButton.setActionCommand("Play");
    buttonPanel.add(playButton);

    pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("Pause");
    buttonPanel.add(pauseButton);

    restartButton = new JButton("Restart");
    restartButton.setActionCommand("Restart");
    buttonPanel.add(restartButton);

    slowButton = new JButton("Slower");
    slowButton.setActionCommand("Slower");
    buttonPanel.add(slowButton);

    fastButton = new JButton("Faster");
    fastButton.setActionCommand("Faster");
    buttonPanel.add(fastButton);

    loopButton = new JButton("Looping: OFF");
    loopButton.setActionCommand("Loop");
    buttonPanel.add(loopButton);

    //create the right button panel for the extra credit buttons
    buttonPanelExtra = new JPanel();
    buttonPanelExtra.setLayout(new GridLayout(5,1));
    this.add(buttonPanelExtra, BorderLayout.EAST);

    addShape = new JButton("Add Shape");
    addShape.setActionCommand("Add Shape");
    buttonPanelExtra.add(addShape);

    addMotion = new JButton("Add Motion");
    addMotion.setActionCommand("Add Motion");
    buttonPanelExtra.add(addMotion);

    removeShape = new JButton("Remove Shape");
    removeShape.setActionCommand("Remove Shape");
    buttonPanelExtra.add(removeShape);

    loadFile = new JButton("Load File");
    loadFile.setActionCommand("Load File");
    buttonPanelExtra.add(loadFile);

    saveFile = new JButton("Save File");
    saveFile.setActionCommand("Save File");
    buttonPanelExtra.add(saveFile);

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
    if(time < this.endTime) {
      System.out.print("Refresh");
      p.setTime(time); //this is setting the time of the drawPanel based on what the time of the view is.
      p.repaint();
    }
  }

  //sets up the buttons that we already added to the window to have action listeners.
  public void setActionListeners(ActionListener buttonListener) {
    playButton.addActionListener(buttonListener);
    pauseButton.addActionListener(buttonListener);
    restartButton.addActionListener(buttonListener);
    slowButton.addActionListener(buttonListener);
    fastButton.addActionListener(buttonListener);
    loopButton.addActionListener(buttonListener);
  }

  public JButton getLoopButton() {
    return this.loopButton;
  }

  //this sets the time of the view. it does NOT set the time of the drawPanel.
  public void setTime(int time) {
    if(time < 1 || time > this.endTime) {
      throw new IllegalArgumentException("Time must be greater than one and less than the endTime of the model.");
    }
    this.time = time;
  }

}
