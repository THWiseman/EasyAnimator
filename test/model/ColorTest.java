package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the SizeChangePattern class.
 */
public class ColorTest {
  ColorPattern pattern1;

  @Before
  public void setup() {
    pattern1 = new ColorPattern(20,20,20);
  }

  @Test
  public void testGetSize() {
    assertEquals(20, pattern1.getColor(2)[0]);
    assertEquals(20, pattern1.getColor(2)[1]);
    assertEquals(20, pattern1.getColor(2)[2]);
  }

  @Test
  public void testChange() {
    pattern1.change(10,20,40,60, 80);

    assertEquals(40, pattern1.getColor(20)[0]);
    assertEquals(20, pattern1.getColor(10)[0]);
    assertEquals(20, pattern1.getColor(10)[1]);
    assertEquals(60, pattern1.getColor(20)[1]);
    assertEquals(20, pattern1.getColor(10)[2]);
    assertEquals(80, pattern1.getColor(20)[2]);

    assertEquals(30, pattern1.getColor(15)[0]);
    assertEquals(40, pattern1.getColor(15)[1]);
    assertEquals(50, pattern1.getColor(15)[2]);

    assertEquals(40, pattern1.getColor(75)[0]);
    assertEquals(60, pattern1.getColor(75)[1]);
    assertEquals(80, pattern1.getColor(75)[2]);
  }

  @Test
  public void testSecondChange() {
    pattern1.change(10,20,40,60, 80);
    pattern1.change(50,70,20,20, 20);

    assertEquals(40, pattern1.getColor(20)[0]);
    assertEquals(20, pattern1.getColor(10)[0]);
    assertEquals(20, pattern1.getColor(10)[1]);
    assertEquals(60, pattern1.getColor(20)[1]);
    assertEquals(20, pattern1.getColor(10)[2]);
    assertEquals(80, pattern1.getColor(20)[2]);


    assertEquals(30, pattern1.getColor(15)[0]);
    assertEquals(40, pattern1.getColor(15)[1]);
    assertEquals(50, pattern1.getColor(15)[2]);

    assertEquals(40, pattern1.getColor(50)[0]);
    assertEquals(20, pattern1.getColor(70)[0]);
    assertEquals(60, pattern1.getColor(50)[1]);
    assertEquals(20, pattern1.getColor(70)[1]);
    assertEquals(80, pattern1.getColor(50)[2]);
    assertEquals(20, pattern1.getColor(70)[2]);

    assertEquals(30, pattern1.getColor(60)[0]);
    assertEquals(40, pattern1.getColor(60)[1]);
    assertEquals(50, pattern1.getColor(60)[2]);
  }
}
