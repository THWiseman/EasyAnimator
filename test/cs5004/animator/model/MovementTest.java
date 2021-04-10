
package cs5004.animator.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Test class for the MovementPattern class.
 */
public class MovementTest {
  MovementPattern pattern1;

  @Before
  public void setup() {
    pattern1 = new MovementPattern();
  }

  @Test (expected = NullPointerException.class)
  public void testGetNonexistant() {
    assertEquals(30, pattern1.get(2)[1]);
  }

  @Test
  public void testChange() {
    try {
      pattern1.change(10, 20, new Integer[] {40}, new Integer[] {40});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(10, 20, new Integer[] {40, 60, 80}, new Integer[] {40, 60, 80});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    pattern1.change(10, 20, new Integer[] {0, 0}, new Integer[] {40, 60});

    assertEquals(0, pattern1.get(10)[0]);
    assertEquals(0, pattern1.get(10)[1]);
    assertEquals(20, pattern1.get(15)[0]);
    assertEquals(30, pattern1.get(15)[1]);
    assertEquals(40, pattern1.get(20)[0]);
    assertEquals(60, pattern1.get(20)[1]);

  }

  @Test
  public void testSecondChange() {
    pattern1.change(10, 20, new Integer[] {20, 30}, new Integer[] {40, 60});
    pattern1.change(50, 70, new Integer[] {40, 60}, new Integer[] {20, 30});

    assertEquals(20, pattern1.get(10)[0]);
    assertEquals(30, pattern1.get(10)[1]);
    assertEquals(30, pattern1.get(15)[0]);
    assertEquals(45, pattern1.get(15)[1]);
    assertEquals(40, pattern1.get(20)[0]);
    assertEquals(60, pattern1.get(20)[1]);
    assertNull( pattern1.get(25));

    assertEquals(30, pattern1.get(15)[0]);
    assertEquals(45, pattern1.get(15)[1]);

    assertEquals(20, pattern1.get(70)[0]);
    assertEquals(40, pattern1.get(50)[0]);
    assertEquals(30, pattern1.get(70)[1]);
    assertEquals(60, pattern1.get(50)[1]);

    assertEquals(30, pattern1.get(60)[0]);
    assertEquals(50, pattern1.get(60)[1]);

  }

  @Test
  public void testBadChange() {
    try {
      pattern1.change(-10, 20, new Integer[] {40, 60}, new Integer[] {100,200});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(30, 20, new Integer[] {40, 60}, new Integer[] {100,200});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
  }


  @Test
  public void testChangeLog() {
    pattern1.change(0, 20, new Integer[] {20, 30}, new Integer[] {40, 60});
    pattern1.change(20, 50, new Integer[] {40, 60}, new Integer[] {20, 30});
    assertEquals("moves position from (20, 30) to (40, 60), from time t=0 to t=20\n"
        + "moves position from (40, 60) to (20, 30), from time t=20 to t=50", pattern1.getChangeLog());
  }


  @Test
  public void testToString() {
    pattern1.change(0, 20, new Integer[] {20, 30}, new Integer[] {40, 60});
    pattern1.change(20, 50, new Integer[] {40, 60}, new Integer[] {20, 30});
    assertEquals("Frame: 0  x: 20  y: 30\n" +
            "Frame: 1  x: 21  y: 31\n" +
            "Frame: 2  x: 22  y: 32\n" +
            "Frame: 3  x: 23  y: 33\n" +
            "Frame: 4  x: 24  y: 34\n" +
            "Frame: 5  x: 25  y: 35\n" +
            "Frame: 6  x: 26  y: 36\n" +
            "Frame: 7  x: 27  y: 37\n" +
            "Frame: 8  x: 28  y: 38\n" +
            "Frame: 9  x: 29  y: 39\n" +
            "Frame: 10  x: 30  y: 40\n" +
            "Frame: 11  x: 31  y: 41\n" +
            "Frame: 12  x: 32  y: 42\n" +
            "Frame: 13  x: 33  y: 43\n" +
            "Frame: 14  x: 34  y: 44\n" +
            "Frame: 15  x: 35  y: 45\n" +
            "Frame: 16  x: 36  y: 46\n" +
            "Frame: 17  x: 37  y: 47\n" +
            "Frame: 18  x: 38  y: 48\n" +
            "Frame: 19  x: 39  y: 49\n" +
            "Frame: 20  x: 40  y: 60\n" +
            "Frame: 21  x: 40  y: 59\n" +
            "Frame: 22  x: 40  y: 58\n" +
            "Frame: 23  x: 40  y: 57\n" +
            "Frame: 24  x: 40  y: 56\n" +
            "Frame: 25  x: 40  y: 55\n" +
            "Frame: 26  x: 40  y: 54\n" +
            "Frame: 27  x: 40  y: 53\n" +
            "Frame: 28  x: 40  y: 52\n" +
            "Frame: 29  x: 40  y: 51\n" +
            "Frame: 30  x: 40  y: 50\n" +
            "Frame: 31  x: 40  y: 49\n" +
            "Frame: 32  x: 40  y: 48\n" +
            "Frame: 33  x: 40  y: 47\n" +
            "Frame: 34  x: 40  y: 46\n" +
            "Frame: 35  x: 40  y: 45\n" +
            "Frame: 36  x: 40  y: 44\n" +
            "Frame: 37  x: 40  y: 43\n" +
            "Frame: 38  x: 40  y: 42\n" +
            "Frame: 39  x: 40  y: 41\n" +
            "Frame: 40  x: 40  y: 40\n" +
            "Frame: 41  x: 40  y: 39\n" +
            "Frame: 42  x: 40  y: 38\n" +
            "Frame: 43  x: 40  y: 37\n" +
            "Frame: 44  x: 40  y: 36\n" +
            "Frame: 45  x: 40  y: 35\n" +
            "Frame: 46  x: 40  y: 34\n" +
            "Frame: 47  x: 40  y: 33\n" +
            "Frame: 48  x: 40  y: 32\n" +
            "Frame: 49  x: 40  y: 31\n" +
            "Frame: 50  x: 20  y: 30", pattern1.toString());
  }
}

