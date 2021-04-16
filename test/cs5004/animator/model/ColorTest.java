package cs5004.animator.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Test class for the ColorPattern class.
 */

public class ColorTest {

  ColorPattern pattern1;

  @Before
  public void setup() {
    pattern1 = new ColorPattern();
    pattern1.change(1, 10, new Integer[]{10, 10, 10}, new Integer[]{100, 100, 100});
  }

  @Test
  public void testGet() {
    assertNull(pattern1.get(150));
    assertEquals(10, pattern1.get(1)[0]);
    assertEquals(20, pattern1.get(2)[1]);
    assertEquals(30, pattern1.get(3)[2]);
  }

  //pull time from nonexistant entry
  @Test(expected = NullPointerException.class)
  public void testChange() {
    pattern1.change(1, 20, new Integer[]{0, 0, 0}, new Integer[]{40, 60, 80});
    assertEquals(1, pattern1.get(0)[0]);
  }


  @Test
  public void testChange1() {
    try {
      pattern1.change(10, 20, new Integer[]{40, 60}, new Integer[]{-1, 0});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(10, 20, new Integer[]{40, 60, 80, 100}, new Integer[]{100, 100, 100, 100});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    pattern1.change(10, 20, new Integer[]{0, 0, 0}, new Integer[]{40, 60, 80});

    assertEquals(0, pattern1.get(10)[0]);
    assertEquals(0, pattern1.get(10)[1]);
    assertEquals(0, pattern1.get(10)[2]);

    assertEquals(20, pattern1.get(15)[0]);
    assertEquals(30, pattern1.get(15)[1]);
    assertEquals(40, pattern1.get(15)[2]);

    assertEquals(40, pattern1.get(20)[0]);
    assertEquals(60, pattern1.get(20)[1]);
    assertEquals(80, pattern1.get(20)[2]);

  }

  @Test
  public void testSecondChange() {
    pattern1.change(10, 20, new Integer[]{20, 20, 20}, new Integer[]{40, 60, 80});
    pattern1.change(50, 70, new Integer[]{40, 60, 80}, new Integer[]{20, 20, 20});

    assertEquals(40, pattern1.get(20)[0]);
    assertEquals(20, pattern1.get(10)[0]);
    assertEquals(20, pattern1.get(10)[1]);
    assertEquals(60, pattern1.get(20)[1]);
    assertEquals(20, pattern1.get(10)[2]);
    assertEquals(80, pattern1.get(20)[2]);

    assertEquals(30, pattern1.get(15)[0]);
    assertEquals(40, pattern1.get(15)[1]);
    assertEquals(50, pattern1.get(15)[2]);

    assertEquals(40, pattern1.get(50)[0]);
    assertEquals(20, pattern1.get(70)[0]);
    assertEquals(60, pattern1.get(50)[1]);
    assertEquals(20, pattern1.get(70)[1]);
    assertEquals(80, pattern1.get(50)[2]);
    assertEquals(20, pattern1.get(70)[2]);

    assertEquals(30, pattern1.get(60)[0]);
    assertEquals(40, pattern1.get(60)[1]);
    assertEquals(50, pattern1.get(60)[2]);
  }

  @Test
  public void testOverlappingChange() {
    pattern1.change(1, 10, new Integer[]{20, 30, 40}, new Integer[]{20, 30, 40});
    pattern1.change(5, 15, new Integer[]{50, 100, 200}, new Integer[]{30, 10, 255});

    assertEquals(20, pattern1.get(1)[0]);
    assertEquals(30, pattern1.get(1)[1]);
    assertEquals(40, pattern1.get(1)[2]);

    assertEquals(20, pattern1.get(3)[0]);
    assertEquals(30, pattern1.get(3)[1]);
    assertEquals(40, pattern1.get(3)[2]);

    assertEquals(50, pattern1.get(5)[0]);
    assertEquals(100, pattern1.get(5)[1]);
    assertEquals(200, pattern1.get(5)[2]);

    assertEquals(46, pattern1.get(7)[0]);
    assertEquals(82, pattern1.get(7)[1]);
    assertEquals(210, pattern1.get(7)[2]);
  }


  @Test
  public void testBadChange() {
    try {
      pattern1.change(10, 20, new Integer[]{0, 0, 0}, new Integer[]{40, 150, -60});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(10, 20, new Integer[]{0, 0, 0}, new Integer[]{400, 60, 230});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(-10, 20, new Integer[]{40, 60, 80}, new Integer[]{40, 60, 80});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(30, 20, new Integer[]{40, 60, 80}, new Integer[]{40, 60, 80});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
  }

  @Test
  public void testChangeLog() {
    pattern1.change(0, 20, new Integer[]{0, 0, 0}, new Integer[]{40, 60, 80});
    pattern1.change(20, 70, new Integer[]{10, 20, 30}, new Integer[]{20, 20, 20});
    assertEquals(
        "changes color from RGB[10, 10, 10] to RGB[100, 100, 100], from time t=1 to t=10\n" +
            "changes color from RGB[0, 0, 0] to RGB[40, 60, 80], from time t=0 to t=20\n" +
            "changes color from RGB[10, 20, 30] to RGB[20, 20, 20], from time t=20 to t=70",
        pattern1.getChangeLog());
  }


  @Test
  public void testToString() {
    assertEquals("Frame: 1, R: 10, G: 10, B: 10\n" +
        "Frame: 2, R: 20, G: 20, B: 20\n" +
        "Frame: 3, R: 30, G: 30, B: 30\n" +
        "Frame: 4, R: 40, G: 40, B: 40\n" +
        "Frame: 5, R: 50, G: 50, B: 50\n" +
        "Frame: 6, R: 60, G: 60, B: 60\n" +
        "Frame: 7, R: 70, G: 70, B: 70\n" +
        "Frame: 8, R: 80, G: 80, B: 80\n" +
        "Frame: 9, R: 90, G: 90, B: 90\n" +
        "Frame: 10, R: 100, G: 100, B: 100", pattern1.toString());
  }
}
