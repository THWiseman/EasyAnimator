Readme for the Graders:

For this implementation, a "Canvas" is our model. The canvas is what can store all of the shapes,
information about time, and ways to add/get/remove/change information about both.

"Shapes" are stored in the canvas. Shapes all implement the same interface and inherit methods
from AbstractShape. Shapes themselves don't store any information directly, but rather store
"patterns" (discussed below) which can calculate or store the information they need. At this point,
shapes have patterns to store color information, movement information, size information, and
visibility information, although more could be added if needed.

"Patterns" are what are able to store and calculate information about shapes. Patterns are designed
to be flexible and portable. In the current implementation, they store a hashmap of values based
on the time, but there's no reason that other patterns couldn't be created with different
ways to calculate and return information about the shape. Currently, they all have a "change" method
which will gradually change their current state to a new state between a given start and end time.

As an update to our original model, Patterns now also contain an ArrayList called a ChangeLog, which
acts as a directory of a Shape's animations. Every time change() is called, the new changeTracker()
method is also called, which logs all of the data about the change() into the ChangeLog. The
ChangeLog stores this data in an object called LogNode, which has getters for all of its fields, but
no setters, as once a change() has been made to the animation, it should not be able to be altered.
This feature was added to our model to make it easier for the TextView and the SVGView to pull data
from each individual animation, as for these two Views it is not necessary to be able to access
the state of the animation at any given frame as it is in the SwingView.

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