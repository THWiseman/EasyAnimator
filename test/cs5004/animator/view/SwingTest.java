package cs5004.animator.view;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.CanvasImpl;
import cs5004.animator.util.AnimationReader;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SwingTest {

  Canvas canvas1;


  @Before
  public void setUp() throws FileNotFoundException {
    BufferedReader reader = new BufferedReader((new FileReader("resources/toh-3.txt")));
    canvas1 = AnimationReader.parseFile(reader, new CanvasImpl.Builder());
    canvas1.setDimensions(200, 600, 200, 600);

  }

  @Test
  public void testView() throws InterruptedException {
    SwingView view = new SwingView(canvas1);
  }
}
