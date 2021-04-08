package cs5004.animator.view;
import cs5004.animator.model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TextViewTest {
    Canvas defaultCanvas;
    Canvas small;
    Canvas big;

    Shape rec1;
    Shape rec2;
    Shape ov1;
    Shape ov2;
    ColorPattern c1;
    MovementPattern m1;
    SizeChangePattern s1;

    @Before
    public void setUp() {
        c1 = new ColorPattern();
        c1.change(25,50, new Integer[]{128,128,0});
       // c1.change(40,70, new Integer[]{200,100,50});
        m1 = new MovementPattern();
        s1 = new SizeChangePattern();

        rec1 = new Rectangle();
        rec2 = new Rectangle(c1, m1, s1, 0, 100);
        ov1 = new Oval();
        ov2 = new Oval(c1, m1, s1, 0, 100);


        defaultCanvas = new CanvasImpl();
        small = new CanvasImpl(5);
        small.addShape(rec1, "1");
        big = new CanvasImpl(200);
        big.addShape(rec1, "R1");
        big.addShape(rec2, "R2");
        big.addShape(ov1, "O1");
    }

    @Test
    public void testTextView() {
        TextView text = new TextView(System.out,big);
        assertEquals("",text.getStringDescription());
    }
}
