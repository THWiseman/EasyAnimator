package cs5004.animator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the MovementPattern class.
 */
public class MovementTest {
  MovementPattern pattern1;

  @Before
  public void setup() {
    pattern1 = new MovementPattern(20, 30);
  }

  @Test
  public void testGet() {
    try {
      pattern1.get(150);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    assertEquals(30, pattern1.get(2)[1]);
    assertEquals(20, pattern1.get(2)[0]);
  }

  @Test
  public void testChange() {
    try {
      pattern1.change(10, 20, new Integer[] {40});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(10, 20, new Integer[] {40, 60, 80});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    pattern1.change(10, 20, new Integer[] {40, 60});

    assertEquals(40, pattern1.get(20)[0]);
    assertEquals(20, pattern1.get(10)[0]);
    assertEquals(30, pattern1.get(10)[1]);
    assertEquals(60, pattern1.get(20)[1]);

    assertEquals(30, pattern1.get(15)[0]);
    assertEquals(45, pattern1.get(15)[1]);

    assertEquals(40, pattern1.get(75)[0]);
    assertEquals(60, pattern1.get(75)[1]);
  }

  @Test
  public void testChange2() {
    try {
      pattern1.change(10, 20, new Integer[] {40});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(10, 20, new Integer[] {40, 60, 80});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    pattern1.change(10, 20, new Integer[] {20, 30}, new Integer[] {40, 60});

    assertEquals(40, pattern1.get(20)[0]);
    assertEquals(20, pattern1.get(10)[0]);
    assertEquals(30, pattern1.get(10)[1]);
    assertEquals(60, pattern1.get(20)[1]);

    assertEquals(30, pattern1.get(15)[0]);
    assertEquals(45, pattern1.get(15)[1]);

    assertEquals(40, pattern1.get(75)[0]);
    assertEquals(60, pattern1.get(75)[1]);
  }

  @Test
  public void testSecondChange() {
    pattern1.change(10, 20, new Integer[] {40, 60});
    pattern1.change(50, 70, new Integer[] {20, 30});

    assertEquals(40, pattern1.get(20)[0]);
    assertEquals(20, pattern1.get(10)[0]);
    assertEquals(30, pattern1.get(10)[1]);
    assertEquals(60, pattern1.get(20)[1]);

    assertEquals(30, pattern1.get(15)[0]);
    assertEquals(45, pattern1.get(15)[1]);

    assertEquals(20, pattern1.get(70)[0]);
    assertEquals(40, pattern1.get(50)[0]);
    assertEquals(30, pattern1.get(70)[1]);
    assertEquals(60, pattern1.get(50)[1]);

    assertEquals(30, pattern1.get(60)[0]);
    assertEquals(45, pattern1.get(60)[1]);

  }

  @Test
  public void testBadConstructors() {
    try {
      MovementPattern badPattern = new MovementPattern(1500, 60);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      MovementPattern badPattern = new MovementPattern(40, -60);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
  }

  @Test
  public void testBadChange() {
    try {
      pattern1.change(10, 20, new Integer[] {40, -60});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(10, 20, new Integer[] {1400, 60});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(10, 200, new Integer[] {40, 60});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(-10, 20, new Integer[] {40, 60});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(30, 20, new Integer[] {40, 60});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
  }

  @Test
  public void testChangeLog() {
    pattern1.change(10, 20, new Integer[] {40, 60});
    pattern1.change(50, 70, new Integer[] {20, 30});
    assertEquals("moves position from (20, 30) to (40, 60), from time t=10 to t=20\n"
        + "moves position from (40, 60) to (20, 30), from time t=50 to t=70", pattern1.getChangeLog());
  }


  @Test
  public void testToString() {
    assertEquals("Frame: 0, X: 20, Y: 30\n"
        + "Frame: 1, X: 20, Y: 30\n"
        + "Frame: 2, X: 20, Y: 30\n"
        + "Frame: 3, X: 20, Y: 30\n"
        + "Frame: 4, X: 20, Y: 30\n"
        + "Frame: 5, X: 20, Y: 30\n"
        + "Frame: 6, X: 20, Y: 30\n"
        + "Frame: 7, X: 20, Y: 30\n"
        + "Frame: 8, X: 20, Y: 30\n"
        + "Frame: 9, X: 20, Y: 30\n"
        + "Frame: 10, X: 20, Y: 30\n"
        + "Frame: 11, X: 20, Y: 30\n"
        + "Frame: 12, X: 20, Y: 30\n"
        + "Frame: 13, X: 20, Y: 30\n"
        + "Frame: 14, X: 20, Y: 30\n"
        + "Frame: 15, X: 20, Y: 30\n"
        + "Frame: 16, X: 20, Y: 30\n"
        + "Frame: 17, X: 20, Y: 30\n"
        + "Frame: 18, X: 20, Y: 30\n"
        + "Frame: 19, X: 20, Y: 30\n"
        + "Frame: 20, X: 20, Y: 30\n"
        + "Frame: 21, X: 20, Y: 30\n"
        + "Frame: 22, X: 20, Y: 30\n"
        + "Frame: 23, X: 20, Y: 30\n"
        + "Frame: 24, X: 20, Y: 30\n"
        + "Frame: 25, X: 20, Y: 30\n"
        + "Frame: 26, X: 20, Y: 30\n"
        + "Frame: 27, X: 20, Y: 30\n"
        + "Frame: 28, X: 20, Y: 30\n"
        + "Frame: 29, X: 20, Y: 30\n"
        + "Frame: 30, X: 20, Y: 30\n"
        + "Frame: 31, X: 20, Y: 30\n"
        + "Frame: 32, X: 20, Y: 30\n"
        + "Frame: 33, X: 20, Y: 30\n"
        + "Frame: 34, X: 20, Y: 30\n"
        + "Frame: 35, X: 20, Y: 30\n"
        + "Frame: 36, X: 20, Y: 30\n"
        + "Frame: 37, X: 20, Y: 30\n"
        + "Frame: 38, X: 20, Y: 30\n"
        + "Frame: 39, X: 20, Y: 30\n"
        + "Frame: 40, X: 20, Y: 30\n"
        + "Frame: 41, X: 20, Y: 30\n"
        + "Frame: 42, X: 20, Y: 30\n"
        + "Frame: 43, X: 20, Y: 30\n"
        + "Frame: 44, X: 20, Y: 30\n"
        + "Frame: 45, X: 20, Y: 30\n"
        + "Frame: 46, X: 20, Y: 30\n"
        + "Frame: 47, X: 20, Y: 30\n"
        + "Frame: 48, X: 20, Y: 30\n"
        + "Frame: 49, X: 20, Y: 30\n"
        + "Frame: 50, X: 20, Y: 30\n"
        + "Frame: 51, X: 20, Y: 30\n"
        + "Frame: 52, X: 20, Y: 30\n"
        + "Frame: 53, X: 20, Y: 30\n"
        + "Frame: 54, X: 20, Y: 30\n"
        + "Frame: 55, X: 20, Y: 30\n"
        + "Frame: 56, X: 20, Y: 30\n"
        + "Frame: 57, X: 20, Y: 30\n"
        + "Frame: 58, X: 20, Y: 30\n"
        + "Frame: 59, X: 20, Y: 30\n"
        + "Frame: 60, X: 20, Y: 30\n"
        + "Frame: 61, X: 20, Y: 30\n"
        + "Frame: 62, X: 20, Y: 30\n"
        + "Frame: 63, X: 20, Y: 30\n"
        + "Frame: 64, X: 20, Y: 30\n"
        + "Frame: 65, X: 20, Y: 30\n"
        + "Frame: 66, X: 20, Y: 30\n"
        + "Frame: 67, X: 20, Y: 30\n"
        + "Frame: 68, X: 20, Y: 30\n"
        + "Frame: 69, X: 20, Y: 30\n"
        + "Frame: 70, X: 20, Y: 30\n"
        + "Frame: 71, X: 20, Y: 30\n"
        + "Frame: 72, X: 20, Y: 30\n"
        + "Frame: 73, X: 20, Y: 30\n"
        + "Frame: 74, X: 20, Y: 30\n"
        + "Frame: 75, X: 20, Y: 30\n"
        + "Frame: 76, X: 20, Y: 30\n"
        + "Frame: 77, X: 20, Y: 30\n"
        + "Frame: 78, X: 20, Y: 30\n"
        + "Frame: 79, X: 20, Y: 30\n"
        + "Frame: 80, X: 20, Y: 30\n"
        + "Frame: 81, X: 20, Y: 30\n"
        + "Frame: 82, X: 20, Y: 30\n"
        + "Frame: 83, X: 20, Y: 30\n"
        + "Frame: 84, X: 20, Y: 30\n"
        + "Frame: 85, X: 20, Y: 30\n"
        + "Frame: 86, X: 20, Y: 30\n"
        + "Frame: 87, X: 20, Y: 30\n"
        + "Frame: 88, X: 20, Y: 30\n"
        + "Frame: 89, X: 20, Y: 30\n"
        + "Frame: 90, X: 20, Y: 30\n"
        + "Frame: 91, X: 20, Y: 30\n"
        + "Frame: 92, X: 20, Y: 30\n"
        + "Frame: 93, X: 20, Y: 30\n"
        + "Frame: 94, X: 20, Y: 30\n"
        + "Frame: 95, X: 20, Y: 30\n"
        + "Frame: 96, X: 20, Y: 30\n"
        + "Frame: 97, X: 20, Y: 30\n"
        + "Frame: 98, X: 20, Y: 30\n"
        + "Frame: 99, X: 20, Y: 30\n"
        + "Frame: 100, X: 20, Y: 30", pattern1.toString());
  }
}
