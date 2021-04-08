package cs5004.animator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the SizeChangePattern class.
 */
public class SizeChangeTest {
  SizeChangePattern pattern1;

  @Before
  public void setup() {
    pattern1 = new SizeChangePattern(20, 30);
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
      SizeChangePattern badPattern = new SizeChangePattern(150, 60);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      SizeChangePattern badPattern = new SizeChangePattern(40, -60);
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
    assertEquals("changes dimensions from length 20 by width 30 to length 40 by width 60, from time t=10 to t=20\n"
        + "changes dimensions from length 40 by width 60 to length 20 by width 30, from time t=50 to t=70", pattern1.getChangeLog());
  }


  @Test
  public void testToString() {
    assertEquals("Frame: 0, Length: 20, Width: 30\n"
        + "Frame: 1, Length: 20, Width: 30\n"
        + "Frame: 2, Length: 20, Width: 30\n"
        + "Frame: 3, Length: 20, Width: 30\n"
        + "Frame: 4, Length: 20, Width: 30\n"
        + "Frame: 5, Length: 20, Width: 30\n"
        + "Frame: 6, Length: 20, Width: 30\n"
        + "Frame: 7, Length: 20, Width: 30\n"
        + "Frame: 8, Length: 20, Width: 30\n"
        + "Frame: 9, Length: 20, Width: 30\n"
        + "Frame: 10, Length: 20, Width: 30\n"
        + "Frame: 11, Length: 20, Width: 30\n"
        + "Frame: 12, Length: 20, Width: 30\n"
        + "Frame: 13, Length: 20, Width: 30\n"
        + "Frame: 14, Length: 20, Width: 30\n"
        + "Frame: 15, Length: 20, Width: 30\n"
        + "Frame: 16, Length: 20, Width: 30\n"
        + "Frame: 17, Length: 20, Width: 30\n"
        + "Frame: 18, Length: 20, Width: 30\n"
        + "Frame: 19, Length: 20, Width: 30\n"
        + "Frame: 20, Length: 20, Width: 30\n"
        + "Frame: 21, Length: 20, Width: 30\n"
        + "Frame: 22, Length: 20, Width: 30\n"
        + "Frame: 23, Length: 20, Width: 30\n"
        + "Frame: 24, Length: 20, Width: 30\n"
        + "Frame: 25, Length: 20, Width: 30\n"
        + "Frame: 26, Length: 20, Width: 30\n"
        + "Frame: 27, Length: 20, Width: 30\n"
        + "Frame: 28, Length: 20, Width: 30\n"
        + "Frame: 29, Length: 20, Width: 30\n"
        + "Frame: 30, Length: 20, Width: 30\n"
        + "Frame: 31, Length: 20, Width: 30\n"
        + "Frame: 32, Length: 20, Width: 30\n"
        + "Frame: 33, Length: 20, Width: 30\n"
        + "Frame: 34, Length: 20, Width: 30\n"
        + "Frame: 35, Length: 20, Width: 30\n"
        + "Frame: 36, Length: 20, Width: 30\n"
        + "Frame: 37, Length: 20, Width: 30\n"
        + "Frame: 38, Length: 20, Width: 30\n"
        + "Frame: 39, Length: 20, Width: 30\n"
        + "Frame: 40, Length: 20, Width: 30\n"
        + "Frame: 41, Length: 20, Width: 30\n"
        + "Frame: 42, Length: 20, Width: 30\n"
        + "Frame: 43, Length: 20, Width: 30\n"
        + "Frame: 44, Length: 20, Width: 30\n"
        + "Frame: 45, Length: 20, Width: 30\n"
        + "Frame: 46, Length: 20, Width: 30\n"
        + "Frame: 47, Length: 20, Width: 30\n"
        + "Frame: 48, Length: 20, Width: 30\n"
        + "Frame: 49, Length: 20, Width: 30\n"
        + "Frame: 50, Length: 20, Width: 30\n"
        + "Frame: 51, Length: 20, Width: 30\n"
        + "Frame: 52, Length: 20, Width: 30\n"
        + "Frame: 53, Length: 20, Width: 30\n"
        + "Frame: 54, Length: 20, Width: 30\n"
        + "Frame: 55, Length: 20, Width: 30\n"
        + "Frame: 56, Length: 20, Width: 30\n"
        + "Frame: 57, Length: 20, Width: 30\n"
        + "Frame: 58, Length: 20, Width: 30\n"
        + "Frame: 59, Length: 20, Width: 30\n"
        + "Frame: 60, Length: 20, Width: 30\n"
        + "Frame: 61, Length: 20, Width: 30\n"
        + "Frame: 62, Length: 20, Width: 30\n"
        + "Frame: 63, Length: 20, Width: 30\n"
        + "Frame: 64, Length: 20, Width: 30\n"
        + "Frame: 65, Length: 20, Width: 30\n"
        + "Frame: 66, Length: 20, Width: 30\n"
        + "Frame: 67, Length: 20, Width: 30\n"
        + "Frame: 68, Length: 20, Width: 30\n"
        + "Frame: 69, Length: 20, Width: 30\n"
        + "Frame: 70, Length: 20, Width: 30\n"
        + "Frame: 71, Length: 20, Width: 30\n"
        + "Frame: 72, Length: 20, Width: 30\n"
        + "Frame: 73, Length: 20, Width: 30\n"
        + "Frame: 74, Length: 20, Width: 30\n"
        + "Frame: 75, Length: 20, Width: 30\n"
        + "Frame: 76, Length: 20, Width: 30\n"
        + "Frame: 77, Length: 20, Width: 30\n"
        + "Frame: 78, Length: 20, Width: 30\n"
        + "Frame: 79, Length: 20, Width: 30\n"
        + "Frame: 80, Length: 20, Width: 30\n"
        + "Frame: 81, Length: 20, Width: 30\n"
        + "Frame: 82, Length: 20, Width: 30\n"
        + "Frame: 83, Length: 20, Width: 30\n"
        + "Frame: 84, Length: 20, Width: 30\n"
        + "Frame: 85, Length: 20, Width: 30\n"
        + "Frame: 86, Length: 20, Width: 30\n"
        + "Frame: 87, Length: 20, Width: 30\n"
        + "Frame: 88, Length: 20, Width: 30\n"
        + "Frame: 89, Length: 20, Width: 30\n"
        + "Frame: 90, Length: 20, Width: 30\n"
        + "Frame: 91, Length: 20, Width: 30\n"
        + "Frame: 92, Length: 20, Width: 30\n"
        + "Frame: 93, Length: 20, Width: 30\n"
        + "Frame: 94, Length: 20, Width: 30\n"
        + "Frame: 95, Length: 20, Width: 30\n"
        + "Frame: 96, Length: 20, Width: 30\n"
        + "Frame: 97, Length: 20, Width: 30\n"
        + "Frame: 98, Length: 20, Width: 30\n"
        + "Frame: 99, Length: 20, Width: 30\n"
        + "Frame: 100, Length: 20, Width: 30", pattern1.toString());
  }

}
