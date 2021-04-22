package cs5004.animator.controller;

public interface SwingController {

    /**
     * Starts the animation at the current speed.
     */
    void play();

    /**
     * Stops the animation at the current time.
     */
    void pause();

    /**
     * Reduces the delay between frames by 10ms, to minimum of 1ms.
     */
    void faster();

    /**
     * Inceases the delay between frames by 10ms.
     */
    void slower();

    /**
     * Toggles looping on and off and updates the view accordingly. If looping is on, the time will automatically reset
     * to one when the animation reaches its end time.
     */
    void loop();

    /**
     * Resets the current time of the animation to one and updates the view.
     */
    void restart();

    /**
     * Creates a JPane that prompts the user for a name and shape type. Creates that shape within the model and updates
     * the view.
     */
    void addShape();

    /**
     * Creates a JPane that prompts the user for starting and ending time, color, size, and location values. Asks user
     * to choose an available shape from a dropdown menu. Changes the shapes attributes in the model to incorporate
     * the motion and updates the view with the updated model.
     */
    void addMotion();

    /**
     * Creates a JPane that allows the user to select a shape from a drop down menu. Removes the selected shape from
     * the animation.
     */
    void removeShape();

    /**
     * Creates a JPane that allows the user to enter a file path and select 'SVG' or 'Text'. Saves the current
     * animation to that location.
     */
    void saveFile();

    /**
     * Creates a JPane that allows the user to enter a file path in order to load a new animation. Loads that animation
     * and attempts to create a new model from that file.
     */
    void loadFile();

    /**
     * Takes in a string command (usually from an action listener) and executes that command within the current model
     * and view. Returns a string of all commands executed, assuming that they successfully completed
     * (this is mostly for testing purposes).
     * Supported Commands:
     * Play: Starts the animation.
     * Pause: Stops the animation at the current time.
     * Slower: Increases the delay between frames by 10ms.
     * Faster: Decreases the delay between frames by 10ms.
     * Loop: When the animation reaches the last frame, resets the animation time to time 1 and keeps plays it again.
     * Restart: Resets the animation time to 1.
     * AddShape: Creates a dialog box that lets the user add a shape to the current animation. Must give the shape a
     * unique name and select one of the supported shape types from the dropdown.
     * AddMotion: Allows a user to change the attributes (size, position, color) of a shape over time. Must provide
     * valid starting and ending times in addition to valid values for size, position, and color. All values must be
     * provided. User selects a shape from a dropdown menu of all shapes currently in the animation.
     * RemoveShape: Allows a user to select a shape from a dropdown menu to remove from the animation.
     * SaveFile: Creates a dialog box that prompts the user for a file path/name and output type (SVG or Text). Once
     * provided and validated, creates and writes a file to that location.
     * LoadFile: Loads a new animation file from the path provided by the user.
     *
     * @param command String command to execute.
     * @return String of the command just executed, if the execution was successful.
     */
    String processCommand(String command);


}
