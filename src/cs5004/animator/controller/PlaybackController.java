package cs5004.animator.controller;

import cs5004.animator.model.Canvas;
import cs5004.animator.view.PlaybackView;
import java.util.Scanner;

public class PlaybackController {
  private Canvas model;
  private PlaybackView view;


  public PlaybackController(Canvas canvas) {
    this.model = canvas;
    this.view = new PlaybackView(this.model);
  }

  public String processCommand(String command) {
    StringBuilder output = new StringBuilder();
    Scanner input = new Scanner(command);

    while(input.hasNext()) {
      String commandAction = input.next();
      switch (commandAction) {
        case "start":
          System.out.println("Animation started");
          output.append("Animation started");
          break;
        case "pause":
          System.out.println("Animation paused");
          output.append("Animation paused");
          break;
        case "resume":
          System.out.println("Animation resumed");
          output.append("Animation resumed");
          break;
        case "restart":
          System.out.println("Animation restarted");
          output.append("Animation restarted");
          break;
        case "enableLoop":
          System.out.println("Animation looping enabled");
          output.append("Animation looping enabled");
          break;
        case "disableLoop":
          System.out.println("Animation looping disabled");
          output.append("Animation looping disabled");
          break;
        case "increase":
          System.out.println("Animation speed increased by 1");
          output.append("Animation speed increased by 1");
          break;
        case "decrease":
          System.out.println("Animation speed decreased by 1");
          output.append("Animation speed decreased by 1");
          break;
      }
    }

    return output.toString();
  }


}
