package cs5004.animator.model;

import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

public class BuilderTest {


    @Before
    public void setUp() throws IOException {

    }

    @Test
    public void testTestText() throws FileNotFoundException {
        Canvas canvas1;
        Path path = Paths.get("src/toh-3.txt");
        BufferedReader reader = new BufferedReader((new FileReader("src/testText")));
        canvas1 = AnimationReader.parseFile(reader, new CanvasImpl.Builder());
        assertEquals("[190, 180]",Arrays.toString(canvas1.getShape("disk1").getPosition(1)));
        assertEquals(190,canvas1.getShape("disk1").getPosition(1)[0]);
        assertEquals(178,canvas1.getShape("disk1").getPosition(2)[0]);
        assertEquals(166,canvas1.getShape("disk1").getPosition(3)[0]);
        assertEquals(80,canvas1.getShape("disk1").getPosition(10)[0]);
    }

    @Test
    public void testTween() {
        ColorPattern c = new ColorPattern();
        c.change(1,10,new Integer[]{1,1,1},new Integer[]{10,10,10});
        assertEquals(1,c.tween(1,10,1,10,1));
        assertEquals(5,c.tween(1,10,1,10,5));
        assertEquals(10,c.tween(1,10,1,10,10));
        assertEquals("[1, 1, 1]",Arrays.toString(c.getMap().get(1)));
        assertEquals("[5, 5, 5]",Arrays.toString(c.getMap().get(5)));
        assertEquals("[10, 10, 10]",Arrays.toString(c.getMap().get(10)));
    }

    @Test
    public void testShapeCount() throws IOException {
        //assertEquals(3,canvas1.getAllShapes().size());
        Canvas canvas1;
        Path path = Paths.get("src/toh-3.txt");
        BufferedReader reader = new BufferedReader((new FileReader("src/toh-3.txt")));
        canvas1 = AnimationReader.parseFile(reader, new CanvasImpl.Builder());

        assertNotNull(canvas1);
        assertNotNull(canvas1.getShape("disk3"));
        assertNotNull(canvas1.getShape("disk3").getSizeChangePattern());
        assertNotNull(canvas1.getShape("disk3").getSizeChangePattern().getMap());
        assertEquals(302,canvas1.getShape("disk3").getSizeChangePattern().getMap().size());
        Map<Integer, int[]> map = canvas1.getShape("disk3").getSizeChangePattern().getMap();
        for(Integer key : map.keySet() ) {
            System.out.println(key.toString());
            System.out.println(Arrays.toString(map.get(key)));
        }
    }



}
