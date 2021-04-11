
package cs5004.animator.view;
import cs5004.animator.model.*;
import cs5004.animator.util.AnimationReader;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.assertEquals;


public class SVGTest {
  Canvas canvas1;
  Canvas canvas2;

  public SVGTest() throws IOException {
  }

  @Before
  public void setUp() throws FileNotFoundException {
    BufferedReader reader = new BufferedReader((new FileReader("src/toh-3.txt")));
    canvas1 = AnimationReader.parseFile(reader, new CanvasImpl.Builder());
  }

  @Test
  public void testBuildAnimation() throws IOException {
    SVGView SVG = new SVGView(System.out, canvas1);
    //assertEquals("hello", );
    assertEquals("hello", SVG.buildAnimation(canvas1.getShape("disk1")));
  }

  @Test
  public void testShapeBuilder() throws IOException {
    SVGView SVG = new SVGView(System.out, canvas1);
    //assertEquals("i", canvas1.getShapeMap());
    assertEquals("hello", SVG.shapeBuilder());
  }

  @Test
  public void testDocBuilder() throws IOException {
    SVGView SVG = new SVGView(System.out, canvas1);
    assertEquals("hello", SVG.docBuilder());
  }


}