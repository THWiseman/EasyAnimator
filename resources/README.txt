Changes to the model:
Since the last assignment, we only had two significant changes to the model:

1.) In our previous version of the model, our shapes were initialized to default values, and the
'change' function was used to 'tween' them from their current value to a new value. This worked fine,
as the shape could change instantly from one value to another if necessary. However, due to the
nature of the input files, we slightly changed the way 'change' was implemented. Shapes are no longer
initialized to default values, and the 'change' function now takes in both starting and ending values,
rather than just ending values. This makes parsing the input files much simpler.

2.) Patterns now also contain an ArrayList called a ChangeLog, which
acts as a directory of a Shape's animations. Every time change() is called, the new changeTracker()
method is also called, which logs all of the data about the change() into the ChangeLog. The
ChangeLog stores this data in an object called LogNode, which has getters for all of its fields, but
no setters, as once a change() has been made to the animation, it should not be able to be altered.
This feature was added to our model to make it easier for the TextView and the SVGView to pull data
from each individual animation, as for these two Views it is not necessary to be able to access
the state of the animation at any given frame as it is in the SwingView.


View explanation:

We implemented text, visual, and SVG views. They all share a common "View" interface. Currently,
this interface only has one method (although several overloaded variations are available.) The 'run'
method starts a view. Each type of view implements this method differently, but it essentially
allows speeds and output files to be specified (or left to default values) and allows the view
to continue autonomously from the beginning to the end of its display behavior. We chose to implement
an overloaded run() method so that all views could implement the same interface (despite their differences)
while also being able to pass in specific parameters when necessary. Because of this implementation, all views
can handle being passed any combination of speed and output file paths with their own internal implementation.

Visual View Implementation:
The visual view (called 'SwingView' in this project) extends the JFrame interface. It is constructed
with a version of our model and then goes through some basic initial setup. It contains a customized
JPanel (called DrawPanel in this project) that does the actual display of the animation. The draw panel
is constructed with our model and only has two methods: setTime and an overridden 'paintComponent' method.
Set time allows the user (in this case a JPanel) to set the interal time of the draw panel. The
paintComponent method uses its internal time to draw data from our model and paint all relevant shapes
to the panel at that time. Because of the way Java Swing works, whenever 'paint' or 'repaint' is called
on that JFrame, it will display the model at that given time.

The JFrame has a Timer linked to an Action Listener. When the Action Listener is called, it will increment
current time of itself (and the DrawPanel) by one, and then call the repaint method on the draw panel. This
will cause the DrawPanel to display the animation at the current time. It will continue to do this until
the endTime of the model is reached, at which point the ActionListener will do nothing. The timer will call
that action listener repeatedly after a specified delay. This specified delay is calculated by the 'speed'
parameter that the user can input.

Text View Implementation:
The text view is constructed with a version of our model and an optional appendable. Its primary method is
'getStringDescription' which gathers information about all shapes in the model through their various getter
methods and associated log entries, and then concatenates all that information into a nicely formatted string
summary of the animation. Then, when go() is called, that method is used to generate some sort of output.
Depending on the parameters passed into go(), the TextView will either output its string into the appendable
it was constructed with or into a new file that it creates with a provided filepath. It ignores all references
to speed as those are not relevant to the text view.

SVG View Implementation:
The SVGView works by writing a String to either a file it creates in the resources directory, or
directly to the console. This String is formatted in SVG code to produce the given animation when
opened in a web browser. To do this, the SVGView relies on three helper methods. The first,
docBuilder(), creates a String with the opening SVG tag, and the closing tag. It then appends the
contents of the next helper method, shapeBuilder() in between the two. ShapeBuilder() iterates
through the list of Shapes in the given Canvas, and creates a String formatted in SVG that will
initialize them, and an end tag to close them. In between these two tags, it calls the final
method, buildAnimation(), and appends its results to the String. BuildAnimation() iterates through
changeLog of the Shape passed to it, and creates a String describing each element in its changeLog,
formatted in SVG. The end result of these three helper methods is a String that when written to a
document and subsequently opened in a web browser, produces a working SVG animation.

Controller README:
We made no changes to the model or views submitted in the previous assignment.

Our controller 'PlaybackController' is constructed with a model and an initial ticks-per-second speed. It will then
launch its own custom view 'PlaybackView' that was designed for this implementation.

The PlaybackView is very similar to the old 'SwingView' that was created for the last assignment. It is constructed with
a model. It creates a custom JFrame that has a Jpanel that is capable of displaying the animation. In this implementation,
the JFrame also includes buttons for each function (both required and extra credit) in the controller (Play, pause,
faster, slower, reset, loop, save file, load file, add shape, add motion, and remove shape). Each button is hooked up
to the same action listener in the controller, but will pass the action listener a different string depending on its
identity. The controller works by listening for the action commands from all the buttons, and then calling its appropriate
method whenever that button is pressed.

The controller is what contains the timer that controls the animation (rather than the view, like last time). The
controller is capable of affecting both the model and the view. It generally works by constructing a view, and then
updating that view on every tick of the timer. While every controller method is unique, they all tend to work by
either affecting the timer in some way or changing the model and reconstructing parts of the vew with that updated model.
In this implementation, the controller is capable of launching custom built JPanes to gather and verify user input for
the extra-credit functionality. 