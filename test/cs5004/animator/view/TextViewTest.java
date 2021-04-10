
package cs5004.animator.view;
import cs5004.animator.model.*;
import cs5004.animator.util.AnimationReader;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.assertEquals;


public class TextViewTest {
    Canvas canvas1;
    Canvas canvas2;

    @Before
    public void setUp() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader((new FileReader("src/testText")));
        canvas1 = AnimationReader.parseFile(reader, new CanvasImpl.Builder());
        BufferedReader reader2 = new BufferedReader((new FileReader("src/toh-12.txt")));
        canvas2 = AnimationReader.parseFile(reader2, new CanvasImpl.Builder());
    }

    @Test
    public void testTextView() {
        TextView text = new TextView(System.out,canvas1);
        assertEquals("",text.getStringDescription());
    }
}

