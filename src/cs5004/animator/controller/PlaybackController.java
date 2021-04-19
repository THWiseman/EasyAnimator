package cs5004.animator.controller;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.CanvasImpl;
import cs5004.animator.model.CanvasImpl.Builder;
import cs5004.animator.model.ColorPattern;
import cs5004.animator.model.MovementPattern;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.SizeChangePattern;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.PlaybackView;

import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The PlaybackController creates its own JFrame view and handles events that happen within that
 * view. It has many different buttons that allow the user to manipulate the animation in some way.
 */
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
      if (looping && currentTime >= endTime) {
        currentTime = 1;
      }
      if (currentTime < endTime) {
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


  /**
   * Constructor for the PlaybackController. It takes in a model to display as a parameter.
   *
   * @param canvas Model to display.
   */
  public PlaybackController(Canvas canvas) {
    this.model = canvas;
    this.view = new PlaybackView(this.model, this);
    this.currentTime = 1;
    this.endTime = model.getEndTime();
    this.loopButton = view.getLoopButton();
  }

  /**
   * Will perform the appropriate command when a String of that command is passed in. This is
   * tightly coupled with the action listener of this controller.
   *
   * @param command String of the command to be performed
   * @return String of all the commands that this controller performed during its life.
   */
  public String processCommand(String command) {
    StringBuilder output = new StringBuilder();
    Scanner input = new Scanner(command);

    while (input.hasNext()) {
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
          timer.setDelay(timer.getDelay() + 10);
          output.append("Animation resumed");
          break;
        case "Faster":
          if (timer.getDelay() <= 11) {
            timer.setDelay(1);
            break;
          }
          System.out.println("Animation made faster");
          timer.setDelay(timer.getDelay() - 10);
          output.append("Animation restarted");
          break;
        case "Loop":
          if (looping) {
            looping = false;
            loopButton.setText("Looping: OFF");
            System.out.println("Animation looping toggled off.");
            break;
          }
          if (!looping) {
            looping = true;
            System.out.println("Animation looping toggled on.");
            loopButton.setText("Looping: ON");
            output.append("Animation looping toggled on");
            break;
          }
        case "Restart":
          System.out.println("Animation restarted");
          this.currentTime = 1;
          view.refresh();
          output.append("Animation restarted");
          break;
        case "AddShape":
          System.out.println("Add Shape");
          AddShapePane shapePane = new AddShapePane();
          System.out.println(shapePane.getInput()[0]);
          System.out.println(shapePane.getInput()[1]);
          if (shapePane.getInput()[1].equals("Rectangle")) {
            model.addShape(new Rectangle(), shapePane.getInput()[0]);
          }
          if (shapePane.getInput()[1].equals("Oval")) {
            model.addShape(new Oval(), shapePane.getInput()[0]);
          }

           ColorPattern tempColor = model.getShape(shapePane.getInput()[0]).getColorPattern();
           tempColor.change(
           0,2,new Integer[] {255, 255, 255}, new Integer[] {255, 255, 255});
           model.getShape(shapePane.getInput()[0]).setColorPattern(tempColor);
           MovementPattern tempMove = model.getShape(shapePane.getInput()[0]).getMovementPattern();
           tempMove.change(
           0,2,new Integer[] {200, 200}, new Integer[] {200, 200});
           model.getShape(shapePane.getInput()[0]).setMovementPattern(tempMove);
           SizeChangePattern tempSize = model.getShape(shapePane.getInput()[0]).getSizeChangePattern();
           tempSize.change(
           0,2,new Integer[] {20, 20}, new Integer[] {20, 20});
           model.getShape(shapePane.getInput()[0]).setSizeChangePattern(tempSize);
           view.refresh();


          output.append("Add Shape");
          break;
        case "AddMotion":
          System.out.println("Add Motion");
          AddMotionPane motionPane = new AddMotionPane();
          Integer[] colorValues = motionPane.getColorValues();
          Integer[] sizeValues = motionPane.getSizeValues();
          Integer[] positionValues = motionPane.getPositionValues();
          String shapeToChange = motionPane.getShapeName();
          for (int i = 0; i < 6; i++) {
            System.out.println(colorValues[i]);
          }
          for (int i = 0; i < 4; i++) {
            System.out.println(sizeValues[i]);
            System.out.println(positionValues[i]);
          }
          output.append("Add Motion");
          break;
        case "RemoveShape":
          System.out.println("Remove Shape");
          RemoveShapePane removeShapePrompt = new RemoveShapePane();
          String shapeToRemove = removeShapePrompt.getInput();
          this.model.remove(shapeToRemove);
          this.view.setModel(this.model);
          //this.view = new PlaybackView(this.model, this);
          view.refresh();
          output.append("Remove Shape");
          break;
        case "SaveFile":
          System.out.println("Save File");
          SaveFilePane savePrompt = new SaveFilePane();
          String savePath = savePrompt.getInput()[0];
          if (savePrompt.getInput()[1].equals("SVG")) {
            SVGView saveFileSVG = new SVGView(model);
            saveFileSVG.run(savePath);
          }
          if (savePrompt.getInput()[1].equals("Text")) {
            TextView saveFileText = new TextView(model);
            saveFileText.run(savePath);
          }
          output.append("Save File");
          break;
        case "LoadFile":
          System.out.println("Load File");
          SingleBoxPrompt loadPrompt = new SingleBoxPrompt("Load File",
              "Enter the file path of the animation you wish to load.");
          String loadPath = loadPrompt.getInput();
          BufferedReader reader;
          try {
            reader = new BufferedReader((new FileReader(loadPath)));
            CanvasImpl newModel = (CanvasImpl) AnimationReader.parseFile(reader, new Builder());
            this.model = newModel;
            view.setModel(newModel);
            view.refresh();
          } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Invalid input filepath.");
            System.exit(-1);
          }
      }

      output.append("Load File");
      break;
    }

    return output.toString();
  }

  /**
   * The action listener portion of this command. In the current implementation, this action
   * listener is hooked up to all the buttons in the view.
   *
   * @param e Action event from a button.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    this.processCommand(e.getActionCommand());
  }

  /**
   * This creates a popup text box that asks the user for one line of input. It is constructed with
   * a string for the title and a string for the prompt.
   */
  private class SingleBoxPrompt {

    JPanel pane = new JPanel();
    JTextField userInputBox = new JTextField(3);

    /**
     * Constructor for a text box prompt.
     *
     * @param title  String that you with the title of the tex tbox to be.
     * @param prompt String that you want the prompt next to the text box to be.
     */
    SingleBoxPrompt(String title, String prompt) {
      pane.setLayout(new GridLayout(1, 2, 2, 2));
      pane.add(new JLabel(prompt));
      pane.add(userInputBox);
      JOptionPane.showConfirmDialog(pane, pane, title, JOptionPane.DEFAULT_OPTION,
          JOptionPane.INFORMATION_MESSAGE);
    }

    String getInput() {
      return userInputBox.getText();
    }
  }

  private class RemoveShapePane {

    JPanel pane = new JPanel();
    String[] shapeNames = model.getAllShapeIDs().toArray(new String[0]);
    JComboBox shapeDropDown = new JComboBox(shapeNames);

    RemoveShapePane() {
      pane.setLayout(new GridLayout(1, 2, 2, 2));
      pane.add(new JLabel("Select which shape you with to remove:"));
      pane.add(shapeDropDown);
      JOptionPane.showConfirmDialog(pane, pane, "Remove Shape", JOptionPane.DEFAULT_OPTION,
          JOptionPane.INFORMATION_MESSAGE);
    }

    String getInput() {
      return (String) shapeDropDown.getSelectedItem();
    }
  }

  /**
   * Creates a popup prompt that asks the user for a shape name and shape type. Shape types are in a
   * dropdown menu.
   */
  private class AddShapePane {

    JPanel pane = new JPanel();
    JTextField shapeNameField = new JTextField(3);
    String[] shapeTypes;
    JComboBox shapeDropDown;


    AddShapePane() {
      pane.setLayout(new GridLayout(2, 2, 2, 2));
      pane.add(new JLabel("Unique name of the shape you wish to add:"));
      pane.add(shapeNameField);

      pane.add(new JLabel("Type of shape you wish to add"));
      shapeTypes = new String[2];
      shapeTypes[0] = "Rectangle";
      shapeTypes[1] = "Oval";
      shapeDropDown = new JComboBox(shapeTypes);
      pane.add(shapeDropDown);

      JOptionPane.showConfirmDialog(pane, pane, "Add Shape", JOptionPane.DEFAULT_OPTION,
          JOptionPane.INFORMATION_MESSAGE);

    }

    String[] getInput() {
      return new String[]{shapeNameField.getText(), (String) shapeDropDown.getSelectedItem()};
    }
  }

  private class SaveFilePane {

    JPanel pane = new JPanel();
    JTextField filepathField = new JTextField(3);
    String[] fileTypes;
    JComboBox fileDropDown;


    SaveFilePane() {
      pane.setLayout(new GridLayout(2, 2, 2, 2));
      pane.add(new JLabel("Enter the file path of where you wish to save the current animation."));
      pane.add(filepathField);

      pane.add(new JLabel("Type of file you wish to save as:"));
      fileTypes = new String[2];
      fileTypes[0] = "SVG";
      fileTypes[1] = "Text";
      fileDropDown = new JComboBox(fileTypes);
      pane.add(fileDropDown);

      JOptionPane.showConfirmDialog(pane, pane, "Add Shape", JOptionPane.DEFAULT_OPTION,
          JOptionPane.INFORMATION_MESSAGE);

    }

    String[] getInput() {
      return new String[]{filepathField.getText(), (String) fileDropDown.getSelectedItem()};
    }
  }

  /**
   * Creates a text box that asks the user for a shape name starting and ending values for that
   * shape's transformation.
   */
  private class AddMotionPane {

    JPanel pane = new JPanel();
    String[] shapeNames = model.getAllShapeIDs().toArray(new String[0]);
    JComboBox shapeDropDown = new JComboBox(shapeNames);
    JTextField startingColor = new JTextField(3);
    JTextField endingColor = new JTextField(3);
    JTextField startingPosition = new JTextField(3);
    JTextField endingPosition = new JTextField(3);
    JTextField startingSize = new JTextField(3);
    JTextField endingSize = new JTextField(3);


    AddMotionPane() {
      pane.setLayout(new GridLayout(4, 3, 2, 2));
      pane.add(new JLabel("Enter starting and ending colors in the form 'r,g,b'"));
      pane.add(startingColor);
      pane.add(endingColor);
      pane.add(new JLabel("Enter starting and ending position in the form 'x,y'"));

      pane.add(startingPosition);
      pane.add(endingPosition);
      pane.add(new JLabel("Enter starting and ending size in the form 'l,w'"));

      pane.add(startingSize);
      pane.add(endingSize);

      pane.add(new JLabel("Select which shape you with to add motion to:"));
      pane.add(shapeDropDown);

      JOptionPane.showConfirmDialog(pane, pane, "Add Motion", JOptionPane.DEFAULT_OPTION,
          JOptionPane.INFORMATION_MESSAGE);
    }

    Integer[] getPositionValues() {
      Integer[] positionValues = new Integer[4];
      String[] startingValues = startingPosition.getText().split(",");
      String[] endingValues = startingPosition.getText().split(",");

      for (int i = 0; i < 2; i++) {
        positionValues[i] = Integer.parseInt(startingValues[i]);
        positionValues[i + 2] = Integer.parseInt(endingValues[i]);
      }
      return positionValues;
    }

    Integer[] getColorValues() {
      Integer[] colorValues = new Integer[6];
      String[] startingValues = startingColor.getText().split(",");
      String[] endingValues = endingColor.getText().split(",");

      for (int i = 0; i < 3; i++) {
        colorValues[i] = Integer.parseInt(startingValues[i]);
        colorValues[i + 3] = Integer.parseInt(endingValues[i]);
      }
      return colorValues;
    }

    Integer[] getSizeValues() {
      Integer[] sizeValues = new Integer[4];
      String[] startingValues = startingSize.getText().split(",");
      String[] endingValues = startingSize.getText().split(",");

      for (int i = 0; i < 2; i++) {
        sizeValues[i] = Integer.parseInt(startingValues[i]);
        sizeValues[i + 2] = Integer.parseInt(endingValues[i]);
      }
      return sizeValues;
    }

    String getShapeName() {
      return (String) shapeDropDown.getSelectedItem();
    }
  }


}
