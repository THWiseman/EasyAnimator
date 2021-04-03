package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the MovementPattern class.
 */
public class MovementTest {
  MovementPattern pattern1;

  @Before
  public void setup() {
    pattern1 = new MovementPattern(20,30);
  }

  @Test
  public void testGetPosition() {
    assertEquals(30, pattern1.getPosition(2)[1]);
    assertEquals(20, pattern1.getPosition(2)[0]);
  }

  @Test
  public void testChange() {
    pattern1.change(10,20,40,60);

    assertEquals(40, pattern1.getPosition(20)[0]);
    assertEquals(20, pattern1.getPosition(10)[0]);
    assertEquals(30, pattern1.getPosition(10)[1]);
    assertEquals(60, pattern1.getPosition(20)[1]);

    assertEquals(30, pattern1.getPosition(15)[0]);
    assertEquals(45, pattern1.getPosition(15)[1]);

    assertEquals(40, pattern1.getPosition(75)[0]);
    assertEquals(60, pattern1.getPosition(75)[1]);
  }

  @Test
  public void testSecondChange() {
    pattern1.change(10,20,40,60);
    pattern1.change(50,70,20,30);

    assertEquals(40, pattern1.getPosition(20)[0]);
    assertEquals(20, pattern1.getPosition(10)[0]);
    assertEquals(30, pattern1.getPosition(10)[1]);
    assertEquals(60, pattern1.getPosition(20)[1]);

    assertEquals(30, pattern1.getPosition(15)[0]);
    assertEquals(45, pattern1.getPosition(15)[1]);

    assertEquals(20, pattern1.getPosition(70)[0]);
    assertEquals(40, pattern1.getPosition(50)[0]);
    assertEquals(30, pattern1.getPosition(70)[1]);
    assertEquals(60, pattern1.getPosition(50)[1]);

    assertEquals(30, pattern1.getPosition(60)[0]);
    assertEquals(45, pattern1.getPosition(60)[1]);

  }
}
