package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

}
