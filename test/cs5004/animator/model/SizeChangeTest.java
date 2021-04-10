
package cs5004.animator.model;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Test class for the SizeChangePattern class.
 */

public class SizeChangeTest {
  SizeChangePattern pattern1;

  @Before
  public void setup() {
    pattern1 = new SizeChangePattern();
  }

  @Test
  public void testGet() {
      assertNull(pattern1.get(0));
  }

  @Test
  public void testChange() {
    try {
      pattern1.change(10, 20, new Integer[] {40}, new Integer[] {100});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(10, 20, new Integer[] {40, 60, 80}, new Integer[] {100,200,300});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    pattern1.change(10, 20, new Integer[] {40, 60}, new Integer[] {80,120});

    assertEquals(40, pattern1.get(10)[0]);
    assertEquals(60, pattern1.get(10)[1]);
    assertEquals(60, pattern1.get(15)[0]);
    assertEquals(90, pattern1.get(15)[1]);
    assertEquals(80, pattern1.get(20)[0]);
    assertEquals(120, pattern1.get(20)[1]);

  }




  @Test
  public void testSecondChange() {
    pattern1.change(10, 20, new Integer[] {40, 60}, new Integer[] {80, 120});
    pattern1.change(50, 70, new Integer[] {20, 30}, new Integer[] {0,10});

      assertEquals(40, pattern1.get(10)[0]);
      assertEquals(60, pattern1.get(10)[1]);
      assertEquals(60, pattern1.get(15)[0]);
      assertEquals(90, pattern1.get(15)[1]);
      assertEquals(80, pattern1.get(20)[0]);
      assertEquals(120, pattern1.get(20)[1]);

      assertNull(pattern1.get(25));

      assertEquals(20, pattern1.get(50)[0]);
      assertEquals(30, pattern1.get(50)[1]);
      assertEquals(10, pattern1.get(60)[0]);
      assertEquals(20, pattern1.get(60)[1]);
      assertEquals(0, pattern1.get(70)[0]);
      assertEquals(10, pattern1.get(70)[1]);
  }


  @Test
  public void testBadChange() {
    try {
      pattern1.change(10, 20, new Integer[] {40, -60}, new Integer[] {-40,100});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
        //test passes
    }

    try {
      pattern1.change(-10, 20, new Integer[] {40, 60}, new Integer[] {100,200});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      pattern1.change(30, 20, new Integer[] {40, 60}, new Integer[] {0,0});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
  }

  @Test
  public void testChangeLog() {
    pattern1.change(10, 20, new Integer[] {40, 60}, new Integer[] {30,40});
    pattern1.change(50, 70, new Integer[] {20, 30}, new Integer[] {50,60});
    assertEquals("changes dimensions from length 40 by width 60 to length 30 by width 40, from time t=10 to t=20\n" +
            "changes dimensions from length 20 by width 30 to length 50 by width 60, from time t=50 to t=70", pattern1.getChangeLog());
  }


  @Test
  public void testToString() {
    pattern1.change(1,10,new Integer[] {10,20}, new Integer[] {30,0});
    assertEquals("Frame: 1  length: 10  width: 20\n" +
            "Frame: 2  length: 12  width: 18\n" +
            "Frame: 3  length: 14  width: 16\n" +
            "Frame: 4  length: 16  width: 14\n" +
            "Frame: 5  length: 18  width: 12\n" +
            "Frame: 6  length: 20  width: 10\n" +
            "Frame: 7  length: 22  width: 8\n" +
            "Frame: 8  length: 24  width: 6\n" +
            "Frame: 9  length: 26  width: 4\n" +
            "Frame: 10  length: 30  width: 0", pattern1.toString());
  }

}

