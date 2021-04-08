package cs5004.animator.model;

import cs5004.animator.util.AnimationReader;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BuilderTest {
    Canvas canvas1;

    @Before
    public void setUp() throws IOException {
        Path path = Paths.get("src/toh-3.txt");
        BufferedReader reader = Files.newBufferedReader(path);
        canvas1 = AnimationReader.parseFile(reader, new CanvasImpl.Builder());
    }

    @Test
    public void testShapeCount() {
        assertEquals(3,canvas1.getAllShapes().size());
    }


}
