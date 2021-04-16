package cs5004.animator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test for the LogNode class. No invalid arguments should be able to be passed in, as they
 * will be caught by the Patterns. Redundant tests included just to be safe.
 */
public class LogNodeTest {

  LogNode node1;
  LogNode node2;
  LogNode node3;

  @Before
  public void setup() {
    node1 = new LogNode(PatternType.COLOR, 0, 20, new Integer[]{20, 20, 20},
        new Integer[]{40, 60, 80});
    node2 = new LogNode(PatternType.SIZECHANGE, 0, 20, new Integer[]{20, 20},
        new Integer[]{40, 60});
    node3 = new LogNode(PatternType.MOVEMENT, 0, 20, new Integer[]{50, 50},
        new Integer[]{60, 60});
  }

  @Test
  public void testBadConstructors() {
    //These should never occur due to the implementation of the Patterns, but testing just in case.
    try {
      LogNode badNode = new LogNode(PatternType.MOVEMENT, -6, 20,
          new Integer[]{20, 20, 20},
          new Integer[]{40, 60, 80});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
    try {
      LogNode badNode = new LogNode(PatternType.MOVEMENT, 20, 4,
          new Integer[]{20, 20, 20},
          new Integer[]{40, 60, 80});
      fail("An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //test passes
    }
  }

  @Test
  public void testGetFrame1() {
    assertEquals(0, node1.getFrame1());
    assertEquals(0, node2.getFrame1());
    assertEquals(0, node3.getFrame1());
  }

  @Test
  public void testGetFrame2() {
    assertEquals(20, node1.getFrame2());
    assertEquals(20, node2.getFrame2());
    assertEquals(20, node3.getFrame2());
  }

  @Test
  public void testGetChangeNotes() {
    assertEquals("changes color from RGB[20, 20, 20] to RGB[40, 60, 80],"
            + " from time t=0 to t=20", node1.getChangeNotes());
    assertEquals(
        "changes dimensions from width 20 by height 20 to width 40 by height 60, "
            + "from time t=0 to t=20", node2.getChangeNotes());
    assertEquals("moves position from (50, 50) to (60, 60), from time t=0 to t=20",
        node3.getChangeNotes());
  }

  @Test
  public void testGetType() {
    assertEquals(PatternType.COLOR, node1.getType());
    assertEquals(PatternType.SIZECHANGE, node2.getType());
    assertEquals(PatternType.MOVEMENT, node3.getType());
  }

  @Test
  public void testGetStartValues() {
    assertEquals(20, node1.getStartValues()[0]);
    assertEquals(20, node1.getStartValues()[1]);
    assertEquals(20, node1.getStartValues()[2]);
    assertEquals(20, node2.getStartValues()[0]);
    assertEquals(20, node2.getStartValues()[1]);
    assertEquals(50, node3.getStartValues()[0]);
    assertEquals(50, node3.getStartValues()[1]);
  }

  @Test
  public void testGetEndValues() {
    assertEquals(40, node1.getEndValues()[0]);
    assertEquals(60, node1.getEndValues()[1]);
    assertEquals(80, node1.getEndValues()[2]);
    assertEquals(40, node2.getEndValues()[0]);
    assertEquals(60, node2.getEndValues()[1]);
    assertEquals(60, node3.getEndValues()[0]);
    assertEquals(60, node3.getEndValues()[1]);
  }
}