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