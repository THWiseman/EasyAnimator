package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the SizeChangePattern class.
 */
public class VisibilityTest {
  VisibilityPattern pattern1;
  VisibilityPattern pattern2;

  @Before
  public void setup() {
    pattern1 = new VisibilityPattern();
    pattern2 = new VisibilityPattern(true);
  }

  @Test
  public void testGetVisibility() {
    try {
      pattern1.getVisibility(150);
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    assertFalse(pattern1.getVisibility(10));
    assertTrue(pattern2.getVisibility(10));
  }

  @Test
  public void testChange() {
    pattern1.change(20, true);
    assertFalse(pattern1.getVisibility(10));
    assertTrue(pattern1.getVisibility(25));
    pattern2.change(50, false);
    assertTrue(pattern2.getVisibility(25));
    assertFalse(pattern2.getVisibility(55));
  }

  @Test
  public void testSecondChange() {
    pattern1.change(20, true);
    pattern1.change(50,false);
    assertFalse(pattern1.getVisibility(10));
    assertTrue(pattern1.getVisibility(25));
    assertFalse(pattern1.getVisibility(60));
  }

  @Test
  public void testToString() {
    assertEquals("Frame: 0, is visible: false\n" +"Frame: 1, is visible: false\n"
        + "Frame: 2, is visible: false\n"
        + "Frame: 3, is visible: false\n"
        + "Frame: 4, is visible: false\n"
        + "Frame: 5, is visible: false\n"
        + "Frame: 6, is visible: false\n"
        + "Frame: 7, is visible: false\n"
        + "Frame: 8, is visible: false\n"
        + "Frame: 9, is visible: false\n"
        + "Frame: 10, is visible: false\n"
        + "Frame: 11, is visible: false\n"
        + "Frame: 12, is visible: false\n"
        + "Frame: 13, is visible: false\n"
        + "Frame: 14, is visible: false\n"
        + "Frame: 15, is visible: false\n"
        + "Frame: 16, is visible: false\n"
        + "Frame: 17, is visible: false\n"
        + "Frame: 18, is visible: false\n"
        + "Frame: 19, is visible: false\n"
        + "Frame: 20, is visible: false\n"
        + "Frame: 21, is visible: false\n"
        + "Frame: 22, is visible: false\n"
        + "Frame: 23, is visible: false\n"
        + "Frame: 24, is visible: false\n"
        + "Frame: 25, is visible: false\n"
        + "Frame: 26, is visible: false\n"
        + "Frame: 27, is visible: false\n"
        + "Frame: 28, is visible: false\n"
        + "Frame: 29, is visible: false\n"
        + "Frame: 30, is visible: false\n"
        + "Frame: 31, is visible: false\n"
        + "Frame: 32, is visible: false\n"
        + "Frame: 33, is visible: false\n"
        + "Frame: 34, is visible: false\n"
        + "Frame: 35, is visible: false\n"
        + "Frame: 36, is visible: false\n"
        + "Frame: 37, is visible: false\n"
        + "Frame: 38, is visible: false\n"
        + "Frame: 39, is visible: false\n"
        + "Frame: 40, is visible: false\n"
        + "Frame: 41, is visible: false\n"
        + "Frame: 42, is visible: false\n"
        + "Frame: 43, is visible: false\n"
        + "Frame: 44, is visible: false\n"
        + "Frame: 45, is visible: false\n"
        + "Frame: 46, is visible: false\n"
        + "Frame: 47, is visible: false\n"
        + "Frame: 48, is visible: false\n"
        + "Frame: 49, is visible: false\n"
        + "Frame: 50, is visible: false\n"
        + "Frame: 51, is visible: false\n"
        + "Frame: 52, is visible: false\n"
        + "Frame: 53, is visible: false\n"
        + "Frame: 54, is visible: false\n"
        + "Frame: 55, is visible: false\n"
        + "Frame: 56, is visible: false\n"
        + "Frame: 57, is visible: false\n"
        + "Frame: 58, is visible: false\n"
        + "Frame: 59, is visible: false\n"
        + "Frame: 60, is visible: false\n"
        + "Frame: 61, is visible: false\n"
        + "Frame: 62, is visible: false\n"
        + "Frame: 63, is visible: false\n"
        + "Frame: 64, is visible: false\n"
        + "Frame: 65, is visible: false\n"
        + "Frame: 66, is visible: false\n"
        + "Frame: 67, is visible: false\n"
        + "Frame: 68, is visible: false\n"
        + "Frame: 69, is visible: false\n"
        + "Frame: 70, is visible: false\n"
        + "Frame: 71, is visible: false\n"
        + "Frame: 72, is visible: false\n"
        + "Frame: 73, is visible: false\n"
        + "Frame: 74, is visible: false\n"
        + "Frame: 75, is visible: false\n"
        + "Frame: 76, is visible: false\n"
        + "Frame: 77, is visible: false\n"
        + "Frame: 78, is visible: false\n"
        + "Frame: 79, is visible: false\n"
        + "Frame: 80, is visible: false\n"
        + "Frame: 81, is visible: false\n"
        + "Frame: 82, is visible: false\n"
        + "Frame: 83, is visible: false\n"
        + "Frame: 84, is visible: false\n"
        + "Frame: 85, is visible: false\n"
        + "Frame: 86, is visible: false\n"
        + "Frame: 87, is visible: false\n"
        + "Frame: 88, is visible: false\n"
        + "Frame: 89, is visible: false\n"
        + "Frame: 90, is visible: false\n"
        + "Frame: 91, is visible: false\n"
        + "Frame: 92, is visible: false\n"
        + "Frame: 93, is visible: false\n"
        + "Frame: 94, is visible: false\n"
        + "Frame: 95, is visible: false\n"
        + "Frame: 96, is visible: false\n"
        + "Frame: 97, is visible: false\n"
        + "Frame: 98, is visible: false\n"
        + "Frame: 99, is visible: false\n"
        + "Frame: 100, is visible: false", pattern1.toString());
  }
}
