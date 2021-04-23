package cs5004.animator.controller;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.CanvasImpl;
import java.awt.event.ActionEvent;
import org.junit.Before;
import org.junit.Test;

public class PlaybackControllerTest {

  Canvas canvas1 = new MockModel();
  PlaybackController controller = new PlaybackController(canvas1,10);

  @Before
  public void setUp() {
    return;
  }

  @Test
  public void testProcessInvalidCommand() {
    assertEquals("Not a valid command", controller.processCommand("MakeThingsHappen!"));
  }
  @Test
  public void testProcessPlay() {
    controller.processCommand("Play");
    assertTrue(controller.timer.isRunning());
  }
  @Test
  public void testProcessPause() {
    controller.processCommand("Pause");
    assertFalse(controller.timer.isRunning());
  }
  @Test
  public void testProcessCommandRestart() {
    assertEquals("Animation restarted", controller.processCommand("Restart"));
  }
  @Test
  public void testProcessCommandLoop() {
    assertEquals("Looping toggled", controller.processCommand("Loop"));
  }
  @Test
  public void testProcessCommandFaster() {
    int previousSpeed = controller.timer.getDelay();
    controller.processCommand("Faster");
    assertEquals(previousSpeed - 10, controller.timer.getDelay());
  }
  @Test
  public void testProcessCommandSlower() {
    int previousSpeed = controller.timer.getDelay();
    controller.processCommand("Slower");
    assertEquals(previousSpeed + 10, controller.timer.getDelay());
  }

  @Test
  public void testActionPerformed() {
    ActionEvent testEvent = new ActionEvent(controller, 1, "Play");
    ActionEvent testEvent2 = new ActionEvent(controller, 2, "MakeThingsHappen!");
    controller.actionPerformed(testEvent);
    assertTrue(controller.timer.isRunning());
  }
}
