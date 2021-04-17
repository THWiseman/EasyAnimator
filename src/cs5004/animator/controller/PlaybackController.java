package cs5004.animator.controller;

import cs5004.animator.model.Canvas;
import cs5004.animator.view.PlaybackView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class PlaybackController implements ActionListener {
  private Canvas model;
  private PlaybackView view;
  private int currentTime;
  private int endTime;
  private Boolean looping = false;
  private JButton loopButton;


  //this is the event that the timer will call repeatedly.
  ActionListener autoRefresh = new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
      if(looping && currentTime >= endTime) {
        currentTime = 1;
      }
      if(currentTime<endTime) {
        System.out.print(currentTime);
        currentTime++;
        view.setTime(currentTime);
        view.refresh();
      }
    }
  };
  //here's the timer itself.
  int delay = 50;
  Timer timer = new Timer(delay, autoRefresh);


  public PlaybackController(Canvas canvas) {
    this.model = canvas;
    this.view = new PlaybackView(this.model);
    this.currentTime = 1;
    this.endTime = model.getEndTime();
    this.view.setActionListeners(this);
    this.loopButton = view.getLoopButton();
  }

  public String processCommand(String command) {
    StringBuilder output = new StringBuilder();
    Scanner input = new Scanner(command);

    while(input.hasNext()) {
      String commandAction = input.next();
      switch (commandAction) {
        case "Play":
          System.out.println("Pressed Play");
          timer.start();
          output.append("Animation started");
          break;
        case "Pause":
          System.out.println("Pressed Pause");
          timer.stop();
          output.append("Animation paused");
          break;
        case "Slower":
          System.out.println("Animation made slower");
          timer.setDelay(timer.getDelay()+10);
          output.append("Animation resumed");
          break;
        case "Faster":
          if(timer.getDelay() <= 11) {
            timer.setDelay(1);
            break;
          }
          System.out.println("Animation made faster");
          timer.setDelay(timer.getDelay()-10);
          output.append("Animation restarted");
          break;
        case "Loop":
          if(looping) {
            looping = false;
            loopButton.setText("Looping: OFF");
            System.out.println("Animation looping toggled off.");
            break;
          }
          if(!looping) {
            looping = true;
            System.out.println("Animation looping toggled on.");
            loopButton.setText("Looping: ON");
            output.append("Animation looping toggled on");
            break;
          }
          break;
        case "Restart":
          System.out.println("Animation restarted");
          this.currentTime = 1;
          view.refresh();
          output.append("Animation restarted");
          break;
      }
    }

    return output.toString();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.processCommand(e.getActionCommand());
  }



}
