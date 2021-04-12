package cs5004.animator;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.CanvasImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.SwingView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class EasyAnimator {
  public static void main(String[] args){
    String inputFileName = "";
    String outputFileName = "";
    String typeOfView = "";
    String ticksPerSecond = "";

    //Parse the command line arguments
    for(int i = 0; i<args.length-1;i++) {
      if(args[i].equals("-in")) {
        inputFileName = args[i+1];
      }
      if(args[i].equals("-out")) {
        outputFileName = args[i+1];
      }
      if(args[i].equals("-view")) {
        typeOfView = args[i+1];
      }
      if(args[i].equals("-speed")) {
        ticksPerSecond = args[i+1];
      }
    }

    //load in the inputFile.
    BufferedReader reader;
    Canvas canvas1 = new CanvasImpl();
    try {
      reader = new BufferedReader((new FileReader(inputFileName)));
      canvas1 = AnimationReader.parseFile(reader, new CanvasImpl.Builder());
    } catch (IOException e) {
      //need to make this error a JPane
      throw new IllegalArgumentException("Invalid filename");
    }

    //parse ticks per second.
    int tps = 1;
    try {
      tps = Integer.parseInt(ticksPerSecond);
    } catch (NumberFormatException e) {
      //Do nothing... continue to use the default tick time of 1 tick per second.
    }


    //Visual View
    if(typeOfView.equals("visual")) {
      SwingView view = new SwingView(canvas1);
      view.go(tps);
      //make sure that the view calls System.exit(0) when it is done.
    }

    //SVG view
    if(typeOfView.equals("svg")) {
      //parse output file name
      //try/catch to create output file using the 'tps' variable.
      //make sure SVG view calls System.exit(0) upon successful completion.
    }

    //Text view
    if(typeOfView.equals("text")) {
      //parse output file name
      //create output file with try/catch
      //make sure text view calls System.exit(0) upon successful completion.
    }

    //throw error here saying that view must be 'visual', 'svg' or 'text'

  }

}