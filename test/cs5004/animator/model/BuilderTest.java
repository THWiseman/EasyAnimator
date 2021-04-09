package cs5004.animator.model;

import cs5004.animator.util.AnimationReader;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
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
    public void testShapeCount() throws IOException {
        //assertEquals(3,canvas1.getAllShapes().size());
        Canvas canvas1;
        Path path = Paths.get("src/toh-3.txt");
        BufferedReader reader = Files.newBufferedReader(path);
        canvas1 = AnimationReader.parseFile(reader, new CanvasImpl.Builder());

        System.out.println(canvas1.toString());
        System.out.println(canvas1.getShape("disk3").getColorPattern().getMap().get(256));
        assertNotNull(canvas1);
        assertNotNull(canvas1.getShape("disk3"));
        assertNotNull(canvas1.getShape("disk3").getSizeChangePattern());
        assertNotNull(canvas1.getShape("disk3").getSizeChangePattern().getMap());
        assertEquals(142,canvas1.getShape("disk3").getSizeChangePattern().getMap().size());
        Map<Integer, Integer[]> map = canvas1.getShape("disk3").getSizeChangePattern().getMap();
        for(Integer key : map.keySet() ) {
            System.out.println(key.toString());
            System.out.println(Arrays.toString(map.get(key)));
        }
        //assertNotNull(canvas1);
        //assertEquals(190, (int) canvas1.getShape("disk3").getPosition(2)[0]);
    }


}
