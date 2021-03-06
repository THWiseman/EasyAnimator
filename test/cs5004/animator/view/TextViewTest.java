
package cs5004.animator.view;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.CanvasImpl;
import cs5004.animator.util.AnimationReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test for the TextView class.
 */
public class TextViewTest {

  Canvas canvas1;
  Canvas canvas2;

  @Before
  public void setUp() throws FileNotFoundException {
    BufferedReader reader = new BufferedReader((new FileReader("resources/toh-3.txt")));
    canvas1 = AnimationReader.parseFile(reader, new CanvasImpl.Builder());
    BufferedReader reader2 = new BufferedReader((new FileReader("resources/toh-12.txt")));
    canvas2 = AnimationReader.parseFile(reader2, new CanvasImpl.Builder());
  }

  //test for accurate output
  @Test
  public void testTextView() {
    TextView text = new TextView(System.out, canvas1);
    assertEquals(
        "Create rectangle disk3 with corner at (145,240), width 110, height 30, and color" +
            " {11, 45, 175}.\n" +
            "Create rectangle disk1 with corner at (190,180), width 20, height 30, and color" +
            " {0, 49, 90}.\n" +
            "Create rectangle disk2 with corner at (167,210), width 65, height 30, and color" +
            " {6, 247, 41}.\n" +
            "\n" +
            "disk3 appears at time t=1 and disappears at time t=302.\n" +
            "disk1 appears at time t=1 and disappears at time t=302.\n" +
            "disk2 appears at time t=1 and disappears at time t=302.\n" +
            "\n" +
            "disk3 moves position from (145, 240) to (145, 50), from time t=121 to t=131\n" +
            "disk3 moves position from (145, 50) to (445, 50), from time t=132 to t=142\n" +
            "disk3 moves position from (445, 50) to (445, 240), from time t=143 to t=153\n" +
            "disk3 changes color from RGB[11, 45, 175] to RGB[0, 255, 0], "
            + "from time t=153 to t=161\n"
            +
            "disk1 moves position from (190, 180) to (190, 50), from time t=25 to t=35\n" +
            "disk1 moves position from (190, 50) to (490, 50), from time t=36 to t=46\n" +
            "disk1 moves position from (490, 50) to (490, 240), from time t=47 to t=57\n" +
            "disk1 moves position from (490, 240) to (490, 50), from time t=89 to t=99\n" +
            "disk1 moves position from (490, 50) to (340, 50), from time t=100 to t=110\n" +
            "disk1 moves position from (340, 50) to (340, 210), from time t=111 to t=121\n" +
            "disk1 moves position from (340, 210) to (340, 50), from time t=153 to t=163\n" +
            "disk1 moves position from (340, 50) to (190, 50), from time t=164 to t=174\n" +
            "disk1 moves position from (190, 50) to (190, 240), from time t=175 to t=185\n" +
            "disk1 moves position from (190, 240) to (190, 50), from time t=217 to t=227\n" +
            "disk1 moves position from (190, 50) to (490, 50), from time t=228 to t=238\n" +
            "disk1 moves position from (490, 50) to (490, 180), from time t=239 to t=249\n" +
            "disk1 changes color from RGB[0, 49, 90] to RGB[0, 255, 0], "
            + "from time t=249 to t=257\n"
            +
            "disk2 moves position from (167, 210) to (167, 50), from time t=57 to t=67\n" +
            "disk2 moves position from (167, 50) to (317, 50), from time t=68 to t=78\n" +
            "disk2 moves position from (317, 50) to (317, 240), from time t=79 to t=89\n" +
            "disk2 moves position from (317, 240) to (317, 50), from time t=185 to t=195\n" +
            "disk2 moves position from (317, 50) to (467, 50), from time t=196 to t=206\n" +
            "disk2 moves position from (467, 50) to (467, 210), from time t=207 to t=217\n" +
            "disk2 changes color from RGB[6, 247, 41] to RGB[0, 255, 0], "
            + "from time t=217 to t=225\n",
        text.getStringDescription());
  }

  //test passing in a string builder as appendable.
  @Test
  public void testAppendableWrite() {
    Appendable testAppendable = new StringBuilder();
    TextView testTextView = new TextView(testAppendable, canvas1);
    testTextView.run();
    assertEquals(testTextView.getStringDescription(), testAppendable.toString());
  }


  //test passing in a file writer as appendable and actually reading that file again.
  @Test
  public void testWriteFile() throws IOException {
    File newFile = new File("testTextView.txt");
    FileWriter write = new FileWriter(newFile);
    BufferedWriter output = new BufferedWriter(write);
    TextView testTextView = new TextView(output, canvas1);
    testTextView.run();
    output.close();
    assertEquals(testTextView.getStringDescription(),
        Files.readString(Paths.get("testTextView.txt")));
  }

  //make sure exception is thrown with illegal speed
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGo() throws IOException {
    File newFile = new File("testTextView.txt");
    FileWriter write = new FileWriter(newFile);
    BufferedWriter output = new BufferedWriter(write);
    TextView testTextView = new TextView(output, canvas1);
    testTextView.run(0);
    output.close();
  }

  //make sure exception is thrown with illegal speed but legal filepath
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGo2() throws IOException {
    File newFile = new File("testTextView.txt");
    FileWriter write = new FileWriter(newFile);
    BufferedWriter output = new BufferedWriter(write);
    TextView testTextView = new TextView(output, canvas1);
    testTextView.run(-1, "hi");
    output.close();
  }

  //test writing to a file and reading it again with the string file path in go.
  @Test
  public void testWriteFileNamedHi() throws IOException {
    TextView testTextView = new TextView(canvas1);
    testTextView.run("Hi");
    assertEquals(testTextView.getStringDescription(), Files.readString(Paths.get("Hi")));
  }

  @Test
  public void testWriteFileNamedHi2() throws IOException {
    TextView testTextView = new TextView(canvas1);
    testTextView.run(10, "Hi");
    assertEquals(testTextView.getStringDescription(), Files.readString(Paths.get("Hi")));
  }

  @Test
  public void testWriteFileNamedHi3() throws IOException {
    TextView testTextView = new TextView(canvas2);
    testTextView.run("Hi");
    assertEquals(testTextView.getStringDescription(), Files.readString(Paths.get("Hi")));
  }

  @Test
  public void testWriteFileNamedHi4() throws IOException {
    TextView testTextView = new TextView(canvas2);
    testTextView.run(10, "Hi");
    assertEquals(testTextView.getStringDescription(), Files.readString(Paths.get("Hi")));
  }

}

