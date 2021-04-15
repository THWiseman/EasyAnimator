package cs5004.animator;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.CanvasImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.SwingView;
import cs5004.animator.view.TextView;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class holds the main() method for the EasyAnimator application.
 * Main can take the following arguments in any order:
 * -in [filepath]: This argument is mandatory and tells the program which text file to use in order to display the
 * animation.
 * * -view [viewtype]: This argument is mandatory and tells the program which view to display. Currently 'text', 'svg',
 * * and 'visual' are the supported options.
 * -out [filepath]: This argument is optional and tells the program what to call the output file is outputs (and
 * where to put it). This argument is ignored for some views. If text output is a requirement of a view but no
 * output parameter is provided, System.out will be used by default.
 * -speed [int]: This argument is optional and tells the program how many frams per second the animation should be
 * displayed ay. Any integer one or greater is allowed.
 * Example: ./EasyAnimator.jar -in src/toh-12.txt -view svg -speed 100 -out output.svg
 */
public final class EasyAnimator {

    /**
     * Entry point into the program. See above for more information about valid command line arguments.
     *
     * @param args
     */
    public static void main(String[] args) {
        String inputFileName = "";
        String outputFileName = "";
        String typeOfView = "";
        String ticksPerSecond = "";

        //Parse the command line arguments
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-in")) {
                inputFileName = args[i + 1];
            }
            if (args[i].equals("-out")) {
                outputFileName = args[i + 1];
            }
            if (args[i].equals("-view")) {
                typeOfView = args[i + 1];
            }
            if (args[i].equals("-speed")) {
                ticksPerSecond = args[i + 1];
            }
        }

        if(inputFileName.equals("") || typeOfView.equals("")) {
          JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Must supply an input filename and " +
                  "the type of view.");
          System.exit(-1);
        }

        //load in the inputFile.
        BufferedReader reader;
        Canvas canvas1 = new CanvasImpl();
        try {
            reader = new BufferedReader((new FileReader(inputFileName)));
            canvas1 = AnimationReader.parseFile(reader, new CanvasImpl.Builder());
        } catch (IOException e) {
          JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Invalid input filepath.");
          System.exit(-1);
        }

        //parse ticks per second.
        int tps = 1;
        try {
            tps = Integer.parseInt(ticksPerSecond);
            if(tps < 1) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Invalid speed. Proceeding with" +
                        " default speed of one tick-per-second.");
                tps = 1;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Invalid speed. Proceeding with default" +
                    " speed of one tick-per-second.");
        }


        //Visual View
        if (typeOfView.equals("visual")) {
            SwingView view = new SwingView(canvas1);
            view.go(tps);
        }

        //SVG view
        if (typeOfView.equals("svg")) {
            if (outputFileName.equals("")) {
                SVGView view = new SVGView(System.out, canvas1);
                view.go(tps);
                System.exit(0);
            } else {
                SVGView view = new SVGView(canvas1);
                view.go(tps, outputFileName);
                System.exit(0);
            }
        }

        //Text view
        if (typeOfView.equals("text")) {
            //parse output file name
          if (outputFileName.equals("")) {
            TextView view = new TextView(System.out, canvas1);
            view.go();
          } else {
            TextView view = new TextView(canvas1);
            view.go(outputFileName);
          }

        }
        if(!typeOfView.equals("text") && !typeOfView.equals("svg") && !typeOfView.equals("visual")) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "-view argument must be 'visual', 'text' or " +
                    "'svg'.");
        }

    }

}