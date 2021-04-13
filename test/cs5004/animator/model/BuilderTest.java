package cs5004.animator.model;

import cs5004.animator.util.AnimationReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class BuilderTest {
    Canvas canvas1;
    Canvas canvas2;

    @Before
    public void setUp() throws IOException {
        BufferedReader reader = new BufferedReader((new FileReader("resources/testText")));
        canvas1 = AnimationReader.parseFile(reader, new CanvasImpl.Builder());
        BufferedReader reader2 = new BufferedReader((new FileReader("resources/toh-12.txt")));
        canvas2 = AnimationReader.parseFile(reader2, new CanvasImpl.Builder());
    }

    @Test
    public void testTestTextPosition() {
        //test position in beginning, middle, end
        assertEquals(190, canvas1.getShape("disk1").getPosition(1)[0]);
        assertEquals(180, canvas1.getShape("disk1").getPosition(1)[1]);
        assertEquals(142, canvas1.getShape("disk1").getPosition(5)[0]);
        assertEquals(180, canvas1.getShape("disk1").getPosition(5)[1]);
        assertEquals(200, canvas1.getShape("disk1").getPosition(10)[0]);
        assertEquals(200, canvas1.getShape("disk1").getPosition(10)[1]);
    }

    @Test
    public void testTestTextSize() {
        //test size in beginning, middle, end
        assertEquals(20, canvas1.getShape("disk1").getSize(1)[0]);
        assertEquals(30, canvas1.getShape("disk1").getSize(1)[1]);
        assertEquals(20, canvas1.getShape("disk1").getSize(5)[0]);
        assertEquals(30, canvas1.getShape("disk1").getSize(5)[1]);
        assertEquals(30, canvas1.getShape("disk1").getSize(10)[0]);
        assertEquals(40, canvas1.getShape("disk1").getSize(10)[1]);
    }

    @Test
    public void testTestTextColor() {
        assertEquals("[190, 180]", Arrays.toString(canvas1.getShape("disk1").getPosition(1)));

        //test size in beginning, middle, end
        assertEquals(0, canvas1.getShape("disk1").getColor(1)[0]);
        assertEquals(49, canvas1.getShape("disk1").getColor(1)[1]);
        assertEquals(90, canvas1.getShape("disk1").getColor(1)[2]);
        assertEquals(0, canvas1.getShape("disk1").getColor(5)[0]);
        assertEquals(49, canvas1.getShape("disk1").getColor(5)[1]);
        assertEquals(90, canvas1.getShape("disk1").getColor(5)[2]);
        assertEquals(20, canvas1.getShape("disk1").getColor(10)[0]);
        assertEquals(100, canvas1.getShape("disk1").getColor(10)[1]);
        assertEquals(120, canvas1.getShape("disk1").getColor(10)[2]);

    }

    @Test
    public void testTween() {
        ColorPattern c = new ColorPattern();
        c.change(1, 10, new Integer[]{1, 1, 1}, new Integer[]{10, 10, 10});
        assertEquals(1, c.tween(1, 10, 1, 10, 1));
        assertEquals(5, c.tween(1, 10, 1, 10, 5));
        assertEquals(10, c.tween(1, 10, 1, 10, 10));
        assertEquals("[1, 1, 1]", Arrays.toString(c.getMap().get(1)));
        assertEquals("[5, 5, 5]", Arrays.toString(c.getMap().get(5)));
        assertEquals("[10, 10, 10]", Arrays.toString(c.getMap().get(10)));
    }

    @Test
    public void testShapeExists() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader((new FileReader("resources/toh-3.txt")));
        Canvas canvas2 = AnimationReader.parseFile(reader, new CanvasImpl.Builder());
        assertNotNull(canvas2);
        assertNotNull(canvas2.getShape("disk3"));
        assertNotNull(canvas2.getShape("disk3").getSizeChangePattern());
        assertNotNull(canvas2.getShape("disk3").getSizeChangePattern().getMap());
        assertEquals(302, canvas2.getShape("disk3").getSizeChangePattern().getMap().size());
    }

    @Test
    public void testFunctionalBuilder() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader((new FileReader("resources/toh-3.txt")));
        Canvas canvas2 = AnimationReader.parseFile(reader, new CanvasImpl.Builder());
        Map<Integer, int[]> colorMap = canvas2.getShape("disk3").getColorPattern().getMap();
        for(Map.Entry<Integer, int[]> i : colorMap.entrySet()) {
            System.out.print(i.getKey());
            System.out.println(Arrays.toString(i.getValue()));
        }
        //assertEquals("", canvas2.getShape("disk1").getColorPattern().getMap().toString());

    }


}
