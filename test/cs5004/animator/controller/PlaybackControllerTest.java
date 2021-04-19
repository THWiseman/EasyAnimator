package cs5004.animator.controller;


import static org.junit.Assert.assertEquals;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.CanvasImpl;
import org.junit.Before;
import org.junit.Test;

public class PlaybackControllerTest {

  Canvas canvas1 = new CanvasImpl();
  //PlaybackController controller = new PlaybackController(canvas1);

  @Before
  public void setUp() {
    return;
  }

  @Test
  public void test1() {
    return;
  }
/*
  @Test
  public void testProcessCommandStart() {
    assertEquals("Animation started", controller.processCommand("start"));
  }
  @Test
  public void testProcessCommandPause() {
    assertEquals("Animation paused", controller.processCommand("pause"));
  }
  @Test
  public void testProcessCommandResume() {
    assertEquals("Animation resumed", controller.processCommand("resume"));
  }
  @Test
  public void testProcessCommandRestart() {
    assertEquals("Animation restarted", controller.processCommand("restart"));
  }
  @Test
  public void testProcessCommandEnableLoop() {
    assertEquals("Animation looping enabled", controller.processCommand("enableLoop"));
  }
  @Test
  public void testProcessCommandDisableLoop() {
    assertEquals("Animation looping disabled", controller.processCommand("disableLoop"));
  }
  @Test
  public void testProcessCommandIncrease() {
    assertEquals("Animation speed increased by 1", controller.processCommand("increase"));
  }
  @Test
  public void testProcessCommandDecrease() {
    assertEquals("Animation speed decreased by 1", controller.processCommand("decrease"));
  }
*/
}
