
package cs5004.animator.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;


/**
 * Test class for the Shape objects. Currently testing Rectangles and Ovals.
 */

public class ShapeTest {
  Shape rec1;
  Shape rec2;
  Oval ov1;
  Shape ov2;

  ColorPattern c1 = new ColorPattern();
  MovementPattern m1 = new MovementPattern();
  SizeChangePattern s1 = new SizeChangePattern();


  ColorPattern c2 = new ColorPattern();
  MovementPattern m2 = new MovementPattern();
  SizeChangePattern s2 = new SizeChangePattern();


  @Before
  public void setUp() {
    rec1 = new Rectangle(c1,m1,s1);
    c2.change(1,100,new Integer[] {0,128,0}, new Integer[] {255,255,255});
    m2.change(1,50,new Integer[] {50,100}, new Integer[] {200,200});
    s2.change(1,100, new Integer[] {200,100}, new Integer[] {100,500});
    rec2 = new Rectangle(c2, m2, s2);
    ov1 = new Oval(c1,m1,s1);
    ov2 = new Oval(c2, m2, s2);
  }


  @Test
  public void testGetPositionRec() {
    try {
      assertTrue(Arrays.equals(m1.get(-1), rec2.getPosition(-1)));
    } catch (IllegalArgumentException e) {
      //test passes because frame must be positive.
    }
    assertNull(rec2.getPosition(0));
    assertTrue(Arrays.equals(m2.get(1), rec2.getPosition(1)));
    assertTrue(Arrays.equals(m2.get(50), rec2.getPosition(50)));
    assertTrue(Arrays.equals(m2.get(100), rec2.getPosition(100)));
  }

  @Test
  public void testGetPositionOv() {
    try {
      assertTrue(Arrays.equals(m1.get(-1), ov2.getPosition(-1)));
    } catch (IllegalArgumentException e) {
      //test passes because frame must be positive.
    }
    assertNull(ov2.getPosition(0));
    assertTrue(Arrays.equals(m2.get(1), ov2.getPosition(1)));
    assertTrue(Arrays.equals(m2.get(50), ov2.getPosition(50)));
    assertTrue(Arrays.equals(m2.get(100), ov2.getPosition(100)));
  }

  @Test
  public void getColor() {
    try {
      assertTrue(Arrays.equals(c1.get(-1), rec2.getColor(-1)));
    } catch (IllegalArgumentException e) {
      //test passes because frame must be positive.
    }
    assertNull(rec2.getColor(0));
    assertTrue(Arrays.equals(c2.get(1), rec2.getColor(1)));
    assertTrue(Arrays.equals(c2.get(50), rec2.getColor(50)));
    assertTrue(Arrays.equals(c2.get(100), rec2.getColor(100)));
  }

  @Test
  public void getSize() {
    try {
      assertTrue(Arrays.equals(s1.get(-1), rec2.getSize(-1)));
    } catch (IllegalArgumentException e) {
      //test passes because frame must be positive.
    }
    assertNull(rec2.getSize(0));
    assertTrue(Arrays.equals(s2.get(1), rec2.getSize(1)));
    assertTrue(Arrays.equals(s2.get(50), rec2.getSize(50)));
    assertTrue(Arrays.equals(s2.get(100), rec2.getSize(100)));
  }

  @Test
  public void getVisibility() {
    try {
      assertEquals(false, rec2.getVisibility(-1));
    } catch (IllegalArgumentException e) {
      //test passes because frame must be positive.
    }
    assertEquals(false, rec2.getVisibility(0));
    assertEquals(true, rec2.getVisibility(1));
    assertEquals(true, rec2.getVisibility(70));
    assertEquals(true, rec2.getVisibility(100));
    assertEquals(false,rec2.getVisibility(101));
  }

  @Test
  public void testSetGetColorPattern() {
    assertEquals(c2.toString(), rec2.getColorPattern().toString());
    assertNotEquals(c1.toString(), rec2.getColorPattern().toString());
    rec2.setColorPattern(c1);
    assertEquals(c1.toString(), rec2.getColorPattern().toString());
    assertNotEquals(c2.toString(), rec2.getColorPattern().toString());
  }

  @Test
  public void testSetGetMovementPattern() {
    assertEquals(m2.toString(), rec2.getMovementPattern().toString());
    assertNotEquals(m1.toString(), rec2.getMovementPattern().toString());
    rec2.setMovementPattern(m1);
    assertEquals(m1.toString(), rec2.getMovementPattern().toString());
    assertNotEquals(m2.toString(), rec2.getMovementPattern().toString());
  }

  @Test
  public void testSetGetSizePattern() {
    assertEquals(s2.toString(), rec2.getSizeChangePattern().toString());
    assertNotEquals(s1.toString(), rec2.getSizeChangePattern().toString());
    rec2.setSizeChangePattern(s2);
    assertEquals(s2.toString(), rec2.getSizeChangePattern().toString());
    assertNotEquals(s1.toString(), rec2.getSizeChangePattern().toString());
  }

  @Test
  public void testSetGetVisibility() {
    assertEquals(1, rec2.getAppearTime());
    assertEquals(100, rec2.getDisappearTime());
  }


  @Test
  public void testCopy() {
    Shape copyRec = rec2.copy();
    assertEquals(copyRec.getColorPattern(), rec2.getColorPattern());
    assertEquals(copyRec.getSizeChangePattern(), rec2.getSizeChangePattern());
    assertEquals(copyRec.getMovementPattern(), rec2.getMovementPattern());
    assertEquals(copyRec.getAppearTime(), rec2.getAppearTime());
    assertEquals(copyRec.getDisappearTime(), rec2.getDisappearTime());
  }

  @Test
  public void testGetChangeLog() {
    c2.change(1,100,new Integer[] {0,128,0}, new Integer[] {255,255,255});
    m2.change(1,50,new Integer[] {50,100}, new Integer[] {200,200});
    s2.change(1,100, new Integer[] {200,100}, new Integer[] {100,500});
    rec2 = new Rectangle(c2, m2, s2);
    assertEquals("changes color from RGB[0, 128, 0] to RGB[255, 255, 255], from time t=1 to t=100\n" +
            "moves position from (50, 100) to (200, 200), from time t=1 to t=50\n" +
            "changes dimensions from width 200 by height 100 to width 100 by height 500, from time t=1 to t=100", rec2.getChangeLog());
  }
}
