package cs5004.animator.controller;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.*;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;
import cs5004.animator.model.CanvasImpl.Builder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.PlaybackView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The PlaybackController creates its own JFrame view and handles events that happen within that
 * view. It has many different buttons that allow the user to manipulate the animation in some way.
 */
public class PlaybackController implements ActionListener, SwingController {

    private Canvas model;
    private PlaybackView view;
    private int currentTime;
    private int endTime;
    private Boolean looping = false;
    private JButton loopButton;
    private int tps;


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
    int delay;
    Timer timer;


    /**
     * Constructor for the PlaybackController. It takes in a model to display as a parameter.
     *
     * @param canvas Model to display.
     */
    public PlaybackController(Canvas canvas, int tps) {
        this.model = canvas;
        this.view = new PlaybackView(this.model, this);
        this.currentTime = 1;
        this.endTime = model.getEndTime();
        this.loopButton = view.getLoopButton();
        this.tps = tps;
        this.delay = 1000 / tps;
        this.timer = new Timer(delay, autoRefresh);
        this.tps = tps;
    }

    @Override
    public void play() {
        this.timer.start();
    }

    @Override
    public void pause() {
        this.timer.stop();
    }

    @Override
    public void faster() {
        if (timer.getDelay() <= 11) {
            timer.setDelay(1);
            return;
        }
        timer.setDelay(timer.getDelay() - 10);
        return;

    }

    @Override
    public void slower() {
        timer.setDelay(timer.getDelay() + 10);
    }

    @Override
    public void loop() {
        if (looping) {
            looping = false;
            loopButton.setText("Looping: OFF");
            return;
        }
        if (!looping) {
            looping = true;
            System.out.println("Animation looping toggled on.");
            loopButton.setText("Looping: ON");
            return;
        }
        return;
    }

    @Override
    public void restart() {
        this.currentTime = 1;
        view.setTime(1);
        view.refresh();
        return;
    }

    @Override
    public void addShape() {
        AddShapePane shapePane = new AddShapePane();
        String shapeID = shapePane.getInput()[0];
        while (model.getShapeMap().get(shapeID) != null) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Shape already exists." +
                    " Please enter a unique name.");
            shapePane = new AddShapePane();
            shapeID = shapePane.getInput()[0];
        }
        System.out.println(shapePane.getInput()[0]);
        System.out.println(shapePane.getInput()[1]);

        if (shapePane.getInput()[1].equals("Rectangle")) {
            model.add(new Rectangle(), shapePane.getInput()[0]);
        }
        if (shapePane.getInput()[1].equals("Oval")) {
            model.add(new Oval(), shapePane.getInput()[0]);
        }

        this.view.setModel(this.model);
        view.refresh();
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Shape added!");
        return;
    }

    @Override
    public void addMotion() {
        AddMotionPane motionPane = new AddMotionPane();
        while (!motionPane.isInputValid()) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Invalid input. All values must integers.\n" +
                    " Time values must be greater than 0 with end time > start time.\n" +
                    " Color values must be between 0 and 255. \n" +
                    "Size values must be greater than zero.\n");
            motionPane = new AddMotionPane();
            if (motionPane.isInputEmpty()) {
                break;
            }
        }

        //if the user doesn't type anything in twice in a row, just stop trying to add motion.
        if (motionPane.isInputEmpty()) {
            return;
        }
        Integer[] colorValues = motionPane.getColorValues();
        Integer[] sizeValues = motionPane.getSizeValues();
        Integer[] positionValues = motionPane.getPositionValues();
        Integer[] times = motionPane.getTimes();
        String shapeName = motionPane.getShapeName();
        Shape shapeToChange = model.getShape(shapeName);
        shapeToChange.getColorPattern().change(times[0], times[1],
                new Integer[]{colorValues[0], colorValues[1], colorValues[2]},
                new Integer[]{colorValues[3], colorValues[4], colorValues[5]});
        shapeToChange.getSizeChangePattern().change(times[0], times[1],
                new Integer[]{sizeValues[0], sizeValues[1]}, new Integer[]{sizeValues[2], sizeValues[3]});
        shapeToChange.getMovementPattern().change(times[0], times[1], new Integer[]{positionValues[0], positionValues[1]},
                new Integer[]{positionValues[2], positionValues[3]});
        this.view.setModel(this.model);
        view.refresh();

        for (int i = 0; i < 6; i++) {
            System.out.println(colorValues[i]);
        }
        for (int i = 0; i < 4; i++) {
            System.out.println(sizeValues[i]);
            System.out.println(positionValues[i]);
        }
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Motion Added!");
        return;
    }

    @Override
    public void removeShape() {
        RemoveShapePane removeShapePrompt = new RemoveShapePane();
        String shapeToRemove = removeShapePrompt.getInput();
        this.model.remove(shapeToRemove);
        this.view.setModel(this.model);
        view.refresh();
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Shape removed!");
        return;
    }

    @Override
    public void saveFile() {
        SaveFilePane savePrompt = new SaveFilePane();
        String savePath = savePrompt.getInput()[0];
        if (savePrompt.getInput()[1].equals("SVG")) {
            SVGView saveFileSVG = new SVGView(model);
            saveFileSVG.run(tps, savePath + ".svg");
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "File saved!");
        }
        if (savePrompt.getInput()[1].equals("Text")) {
            TextView saveFileText = new TextView(model);
            saveFileText.run(savePath + ".txt");
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "File saved!");
        }
        return;
    }

    @Override
    public void loadFile() {
        String loadPath = new SingleBoxPrompt("Load File",
                "Enter the file path of the animation you with to load.").getInput();
        Path filePath = Paths.get(loadPath);
        Boolean fileExists = Files.exists(filePath);
        while (!fileExists) {
            SingleBoxPrompt loadPrompt = new SingleBoxPrompt("Load File",
                    "Invalid filepath. Please enter the file path of the animation you wish to load.");
            loadPath = loadPrompt.getInput();
            filePath = Paths.get(loadPath);
            fileExists = Files.exists(filePath);
        }
        BufferedReader reader;
        try {
            reader = new BufferedReader((new FileReader(loadPath)));
            CanvasImpl newModel = (CanvasImpl) AnimationReader.parseFile(reader, new Builder());
            this.model = newModel;
            this.endTime = newModel.getEndTime();
            view.setModel(newModel);
            view.refresh();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Error loading file.");
            System.exit(-1);
        }
    }



    /**
     * Will perform the appropriate command when a String of that command is passed in. This is
     * tightly coupled with the action listener of this controller.
     *
     * @param command String of the command to be performed
     * @return String of all the commands that this controller performed during its life.
     */
    @Override
    public String processCommand(String command) {
        StringBuilder output = new StringBuilder();
        Scanner input = new Scanner(command);

        while (input.hasNext()) {
            String commandAction = input.next();
            switch (commandAction) {
                case "Play":
                    play();
                    output.append("Animation started");
                    break;
                case "Pause":
                    pause();
                    output.append("Animation paused");
                    break;
                case "Slower":
                    slower();
                    output.append("Animation resumed");
                    break;
                case "Faster":
                    faster();
                    output.append("Animation restarted");
                    break;
                case "Loop":
                    loop();
                    output.append("Looping toggled");
                    break;
                case "Restart":
                    restart();
                    output.append("Animation restarted");
                    break;
                case "AddShape":
                    addShape();
                    output.append("Add Shape");
                    break;
                case "AddMotion":
                    addMotion();
                    output.append("Add Motion");
                    break;
                case "RemoveShape":
                    removeShape();
                    output.append("Remove Shape");
                    break;
                case "SaveFile":
                    saveFile();
                    output.append("Save File");
                    break;
                case "LoadFile":
                    loadFile();
                    output.append("Load File");
                    break;
            }
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
        public void actionPerformed (ActionEvent e){
            this.processCommand(e.getActionCommand());
        }


        /**
         * This creates a popup text box that asks the user for one line of input. It is constructed with
         * a string for the title and a string for the prompt.
         */
        class SingleBoxPrompt {

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
            JTextField startTime = new JTextField(3);
            JTextField endTime = new JTextField(3);


            AddMotionPane() {
                pane.setLayout(new GridLayout(5, 3, 2, 2));
                pane.add(new JLabel("Enter the start and end time of the change as integers."));
                pane.add(startTime);
                pane.add(endTime);
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


                JOptionPane.showConfirmDialog(pane, pane, "Add Motion", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
            }

            Integer[] getPositionValues() {
                Integer[] positionValues = new Integer[4];
                String[] startingValues = startingPosition.getText().split(",");
                String[] endingValues = endingPosition.getText().split(",");

                positionValues[0] = Integer.parseInt(startingValues[0]);
                positionValues[1] = Integer.parseInt(startingValues[1]);
                positionValues[2] = Integer.parseInt(endingValues[0]);
                positionValues[3] = Integer.parseInt(endingValues[1]);

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
                String[] endingValues = endingSize.getText().split(",");

                for (int i = 0; i < 2; i++) {
                    sizeValues[i] = Integer.parseInt(startingValues[i]);
                    sizeValues[i + 2] = Integer.parseInt(endingValues[i]);
                }
                return sizeValues;
            }

            Integer[] getTimes() {
                Integer[] times = new Integer[2];
                times[0] = Integer.parseInt(startTime.getText());
                times[1] = Integer.parseInt(endTime.getText());
                return times;
            }

            String getShapeName() {
                return (String) shapeDropDown.getSelectedItem();
            }

            Boolean isInputEmpty() {
                return (startTime.getText().isEmpty() && endTime.getText().isEmpty());
            }

            Boolean isInputValid() {

                //this try catch verifies that everything entered is an integer.
                try {
                    this.getColorValues();
                    this.getPositionValues();
                    this.getSizeValues();
                    this.getTimes();
                } catch (Exception e) {
                    return false;
                }

                //check to make sure the color values are between 0 and 255.
                for (int i : this.getColorValues()) {
                    if (i < 0 || i > 255) {
                        return false;
                    }
                }

                //check to make sure the endTime is greater than the start time.
                if (this.getTimes()[1] < this.getTimes()[0]) {
                    return false;
                }

                //check to make sure the start and end time are greater than zero.
                for (int i : this.getTimes()) {
                    if (i <= 0) {
                        return false;
                    }
                }

                //check to make sure that all size values are greater than 0.
                for (int i : this.getSizeValues()) {
                    if (i <= 0) {
                        return false;
                    }
                }
                return true;
            }

        }
    }
