package cs5004.animator.controller;

public interface SwingController {


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
     * @param command String command to execute.
     * @return String of the command just executed, if the execution was successful.
     */
    String processCommand(String command);
}
