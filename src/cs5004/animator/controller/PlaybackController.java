package cs5004.animator.controller;

import cs5004.animator.model.Canvas;
import cs5004.animator.view.PlaybackView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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


    public PlaybackController(Canvas canvas) {
        this.model = canvas;
        this.view = new PlaybackView(this.model, this);
        this.currentTime = 1;
        this.endTime = model.getEndTime();
        this.loopButton = view.getLoopButton();
    }

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
                    output.append("Add Shape");
                    break;
                case "AddMotion":
                    System.out.println("Add Motion");
                    AddMotionPane motionPane  = new AddMotionPane();
                    Integer[] colorValues = motionPane.getColorValues();
                    Integer[] sizeValues = motionPane.getSizeValues();
                    Integer[] positionValues = motionPane.getPositionValues();
                    for(int i = 0; i<6;i++) {
                        System.out.println(colorValues[i]);
                    }
                    for(int i = 0; i<4;i++) {
                        System.out.println(sizeValues[i]);
                        System.out.println(positionValues[i]);
                    }
                    output.append("Add Motion");
                    break;
                case "RemoveShape":
                    System.out.println("Remove Shape");
                    output.append("Remove Shape");
                    break;
                case "SaveFile":
                    System.out.println("Save File");
                    output.append("Save File");
                    break;
                case "LoadFile":
                    System.out.println("Load File");
                    output.append("Load File");
                    break;
            }
        }

        return output.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.processCommand(e.getActionCommand());
    }

    private class AddShapePane {
        JPanel pane = new JPanel();
        JTextField shapeNameField = new JTextField(3);
        String[] shapeTypes;
        JComboBox shapeDropDown;


        AddShapePane(){
            pane.setLayout(new GridLayout(2,2,2,2));
            pane.add(new JLabel("Unique name of the shape you wish to add:"));
            pane.add(shapeNameField);

            pane.add(new JLabel("Type of shape you wish to add"));
            shapeTypes = new String[2];
            shapeTypes[0] = "Rectangle";
            shapeTypes[1] = "Oval";
            shapeDropDown = new JComboBox(shapeTypes);
            pane.add(shapeDropDown);

            JOptionPane.showConfirmDialog(pane, pane, "Please fill all the fields", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

        }

        String[] getInput() {
        return new String[] {shapeNameField.getText(),(String) shapeDropDown.getSelectedItem()};
        }
    }

    private class AddMotionPane {
        JPanel pane = new JPanel();
        JTextField startingColor = new JTextField(3);
        JTextField endingColor = new JTextField(3);
        JTextField startingPosition = new JTextField(3);
        JTextField endingPosition = new JTextField(3);
        JTextField startingSize = new JTextField(3);
        JTextField endingSize = new JTextField(3);


        public AddMotionPane() {
            pane.setLayout(new GridLayout(3, 3, 2, 2));
            pane.add(new JLabel("Enter starting and ending colors in the form 'r,g,b'"));
            pane.add(startingColor);
            pane.add(endingColor);
            pane.add(new JLabel("Enter starting and ending position in the form 'x,y'"));

            pane.add(startingPosition);
            pane.add(endingPosition);
            pane.add(new JLabel ("Enter starting and ending size in the form 'l,w'"));

            pane.add(startingSize);
            pane.add(endingSize);

           JOptionPane.showConfirmDialog(pane, pane, "Please fill all the fields", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }

        Integer[] getPositionValues() {
            Integer[] positionValues = new Integer[4];
            String[] startingValues = startingPosition.getText().split(",");
            String[] endingValues = startingPosition.getText().split(",");

            for(int i = 0; i<2; i++) {
                positionValues[i] = Integer.parseInt(startingValues[i]);
                positionValues[i+2] = Integer.parseInt(endingValues[i]);
            }
            return positionValues;
        }

        Integer[] getColorValues() {
            Integer[] colorValues = new Integer[6];
            String[] startingValues = startingColor.getText().split(",");
            String[] endingValues = endingColor.getText().split(",");

            for(int i = 0; i<3; i++) {
                colorValues[i] = Integer.parseInt(startingValues[i]);
                colorValues[i+3] = Integer.parseInt(endingValues[i]);
            }
            return colorValues;
        }

        Integer[] getSizeValues() {
            Integer[] sizeValues = new Integer[4];
            String[] startingValues = startingSize.getText().split(",");
            String[] endingValues = startingSize.getText().split(",");

            for(int i = 0; i<2; i++) {
                sizeValues[i] = Integer.parseInt(startingValues[i]);
                sizeValues[i+2] = Integer.parseInt(endingValues[i]);
            }
            return sizeValues;
        }
    }


    }
