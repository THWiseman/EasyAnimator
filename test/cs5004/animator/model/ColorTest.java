/*
package cs5004.animator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

*/
/**
 * Test class for the ColorPattern class.
 *//*

public class ColorTest {
  ColorPattern pattern1;

  @Before
  public void setup() {
    pattern1 = new ColorPattern(20, 20, 20);
  }

  @Test
  public void testGet() {
    try {
      pattern1.get(150);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    assertEquals(20, pattern1.get(2)[0]);
    assertEquals(20, pattern1.get(2)[1]);
    assertEquals(20, pattern1.get(2)[2]);
  }

  @Test
  public void testChange() {
    try {
      pattern1.change(10, 20, new Integer[] {40, 60});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(10, 20, new Integer[] {40, 60, 80, 100});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    pattern1.change(10, 20, new Integer[] {40, 60, 80});

    assertEquals(40, pattern1.get(20)[0]);
    assertEquals(20, pattern1.get(10)[0]);
    assertEquals(20, pattern1.get(10)[1]);
    assertEquals(60, pattern1.get(20)[1]);
    assertEquals(20, pattern1.get(10)[2]);
    assertEquals(80, pattern1.get(20)[2]);

    assertEquals(30, pattern1.get(15)[0]);
    assertEquals(40, pattern1.get(15)[1]);
    assertEquals(50, pattern1.get(15)[2]);

    assertEquals(40, pattern1.get(75)[0]);
    assertEquals(60, pattern1.get(75)[1]);
    assertEquals(80, pattern1.get(75)[2]);
  }

  @Test
  public void testChange2() {
    try {
      pattern1.change(10, 20, new Integer[] {40, 60});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(10, 20, new Integer[] {40, 60, 80, 100});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    pattern1.change(10, 20, new Integer[] {40, 60, 80});

    assertEquals(40, pattern1.get(20)[0]);
    assertEquals(20, pattern1.get(10)[0]);
    assertEquals(20, pattern1.get(10)[1]);
    assertEquals(60, pattern1.get(20)[1]);
    assertEquals(20, pattern1.get(10)[2]);
    assertEquals(80, pattern1.get(20)[2]);

    assertEquals(30, pattern1.get(15)[0]);
    assertEquals(40, pattern1.get(15)[1]);
    assertEquals(50, pattern1.get(15)[2]);

    assertEquals(40, pattern1.get(75)[0]);
    assertEquals(60, pattern1.get(75)[1]);
    assertEquals(80, pattern1.get(75)[2]);
  }

  @Test
  public void testSecondChange() {
    pattern1.change(10, 20, new Integer[] {20, 20, 20}, new Integer[] {40, 60, 80});
    pattern1.change(50, 70, new Integer[] {40, 60, 80}, new Integer[] {20, 20, 20});

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
  public void testBadConstructors() {
    try {
      ColorPattern badPattern = new ColorPattern(280, 60, 165);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      ColorPattern badPattern = new ColorPattern(40, -60, 230);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
  }

  @Test
  public void testBadChange() {
    try {
      pattern1.change(10, 20, new Integer[] {40, 150, -60});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(10, 20, new Integer[] {400, 60, 230});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(10, 200, new Integer[] {40, 60, 80});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(-10, 20, new Integer[] {40, 60, 80});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(30, 20, new Integer[] {40, 60, 80});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
  }

  @Test
  public void testChangeLog() {
    pattern1.change(10, 20, new Integer[] {40, 60, 80});
    pattern1.change(50, 70, new Integer[] {20, 20, 20});
    assertEquals("changes color from RGB[20, 20, 20] to RGB[40, 60, 80], from time t=10 to t=20\n"
        + "changes color from RGB[40, 60, 80] to RGB[20, 20, 20], from time t=50 to t=70", pattern1.getChangeLog());
  }


  @Test
  public void testToString() {
    assertEquals("Frame: 0, R: 20, G: 20, B: 20\n"
        + "Frame: 1, R: 20, G: 20, B: 20\n"
        + "Frame: 2, R: 20, G: 20, B: 20\n"
        + "Frame: 3, R: 20, G: 20, B: 20\n"
        + "Frame: 4, R: 20, G: 20, B: 20\n"
        + "Frame: 5, R: 20, G: 20, B: 20\n"
        + "Frame: 6, R: 20, G: 20, B: 20\n"
        + "Frame: 7, R: 20, G: 20, B: 20\n"
        + "Frame: 8, R: 20, G: 20, B: 20\n"
        + "Frame: 9, R: 20, G: 20, B: 20\n"
        + "Frame: 10, R: 20, G: 20, B: 20\n"
        + "Frame: 11, R: 20, G: 20, B: 20\n"
        + "Frame: 12, R: 20, G: 20, B: 20\n"
        + "Frame: 13, R: 20, G: 20, B: 20\n"
        + "Frame: 14, R: 20, G: 20, B: 20\n"
        + "Frame: 15, R: 20, G: 20, B: 20\n"
        + "Frame: 16, R: 20, G: 20, B: 20\n"
        + "Frame: 17, R: 20, G: 20, B: 20\n"
        + "Frame: 18, R: 20, G: 20, B: 20\n"
        + "Frame: 19, R: 20, G: 20, B: 20\n"
        + "Frame: 20, R: 20, G: 20, B: 20\n"
        + "Frame: 21, R: 20, G: 20, B: 20\n"
        + "Frame: 22, R: 20, G: 20, B: 20\n"
        + "Frame: 23, R: 20, G: 20, B: 20\n"
        + "Frame: 24, R: 20, G: 20, B: 20\n"
        + "Frame: 25, R: 20, G: 20, B: 20\n"
        + "Frame: 26, R: 20, G: 20, B: 20\n"
        + "Frame: 27, R: 20, G: 20, B: 20\n"
        + "Frame: 28, R: 20, G: 20, B: 20\n"
        + "Frame: 29, R: 20, G: 20, B: 20\n"
        + "Frame: 30, R: 20, G: 20, B: 20\n"
        + "Frame: 31, R: 20, G: 20, B: 20\n"
        + "Frame: 32, R: 20, G: 20, B: 20\n"
        + "Frame: 33, R: 20, G: 20, B: 20\n"
        + "Frame: 34, R: 20, G: 20, B: 20\n"
        + "Frame: 35, R: 20, G: 20, B: 20\n"
        + "Frame: 36, R: 20, G: 20, B: 20\n"
        + "Frame: 37, R: 20, G: 20, B: 20\n"
        + "Frame: 38, R: 20, G: 20, B: 20\n"
        + "Frame: 39, R: 20, G: 20, B: 20\n"
        + "Frame: 40, R: 20, G: 20, B: 20\n"
        + "Frame: 41, R: 20, G: 20, B: 20\n"
        + "Frame: 42, R: 20, G: 20, B: 20\n"
        + "Frame: 43, R: 20, G: 20, B: 20\n"
        + "Frame: 44, R: 20, G: 20, B: 20\n"
        + "Frame: 45, R: 20, G: 20, B: 20\n"
        + "Frame: 46, R: 20, G: 20, B: 20\n"
        + "Frame: 47, R: 20, G: 20, B: 20\n"
        + "Frame: 48, R: 20, G: 20, B: 20\n"
        + "Frame: 49, R: 20, G: 20, B: 20\n"
        + "Frame: 50, R: 20, G: 20, B: 20\n"
        + "Frame: 51, R: 20, G: 20, B: 20\n"
        + "Frame: 52, R: 20, G: 20, B: 20\n"
        + "Frame: 53, R: 20, G: 20, B: 20\n"
        + "Frame: 54, R: 20, G: 20, B: 20\n"
        + "Frame: 55, R: 20, G: 20, B: 20\n"
        + "Frame: 56, R: 20, G: 20, B: 20\n"
        + "Frame: 57, R: 20, G: 20, B: 20\n"
        + "Frame: 58, R: 20, G: 20, B: 20\n"
        + "Frame: 59, R: 20, G: 20, B: 20\n"
        + "Frame: 60, R: 20, G: 20, B: 20\n"
        + "Frame: 61, R: 20, G: 20, B: 20\n"
        + "Frame: 62, R: 20, G: 20, B: 20\n"
        + "Frame: 63, R: 20, G: 20, B: 20\n"
        + "Frame: 64, R: 20, G: 20, B: 20\n"
        + "Frame: 65, R: 20, G: 20, B: 20\n"
        + "Frame: 66, R: 20, G: 20, B: 20\n"
        + "Frame: 67, R: 20, G: 20, B: 20\n"
        + "Frame: 68, R: 20, G: 20, B: 20\n"
        + "Frame: 69, R: 20, G: 20, B: 20\n"
        + "Frame: 70, R: 20, G: 20, B: 20\n"
        + "Frame: 71, R: 20, G: 20, B: 20\n"
        + "Frame: 72, R: 20, G: 20, B: 20\n"
        + "Frame: 73, R: 20, G: 20, B: 20\n"
        + "Frame: 74, R: 20, G: 20, B: 20\n"
        + "Frame: 75, R: 20, G: 20, B: 20\n"
        + "Frame: 76, R: 20, G: 20, B: 20\n"
        + "Frame: 77, R: 20, G: 20, B: 20\n"
        + "Frame: 78, R: 20, G: 20, B: 20\n"
        + "Frame: 79, R: 20, G: 20, B: 20\n"
        + "Frame: 80, R: 20, G: 20, B: 20\n"
        + "Frame: 81, R: 20, G: 20, B: 20\n"
        + "Frame: 82, R: 20, G: 20, B: 20\n"
        + "Frame: 83, R: 20, G: 20, B: 20\n"
        + "Frame: 84, R: 20, G: 20, B: 20\n"
        + "Frame: 85, R: 20, G: 20, B: 20\n"
        + "Frame: 86, R: 20, G: 20, B: 20\n"
        + "Frame: 87, R: 20, G: 20, B: 20\n"
        + "Frame: 88, R: 20, G: 20, B: 20\n"
        + "Frame: 89, R: 20, G: 20, B: 20\n"
        + "Frame: 90, R: 20, G: 20, B: 20\n"
        + "Frame: 91, R: 20, G: 20, B: 20\n"
        + "Frame: 92, R: 20, G: 20, B: 20\n"
        + "Frame: 93, R: 20, G: 20, B: 20\n"
        + "Frame: 94, R: 20, G: 20, B: 20\n"
        + "Frame: 95, R: 20, G: 20, B: 20\n"
        + "Frame: 96, R: 20, G: 20, B: 20\n"
        + "Frame: 97, R: 20, G: 20, B: 20\n"
        + "Frame: 98, R: 20, G: 20, B: 20\n"
        + "Frame: 99, R: 20, G: 20, B: 20\n"
        + "Frame: 100, R: 20, G: 20, B: 20", pattern1.toString());
  }
}
*/
