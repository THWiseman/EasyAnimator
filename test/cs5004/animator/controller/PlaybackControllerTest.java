package cs5004.animator.controller;


import static org.junit.Assert.assertEquals;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.CanvasImpl;
import cs5004.animator.model.ColorPattern;
import cs5004.animator.model.MovementPattern;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Shape;
import cs5004.animator.model.SizeChangePattern;
import org.junit.Before;
import org.junit.Test;

public class PlaybackControllerTest {
  Shape rec1;
  Shape rec2;
  Shape rec3;
  Shape ov1;
  Shape ov2;
  Shape ov3;
  ColorPattern c1;
  MovementPattern m1;
  SizeChangePattern s1;

  ColorPattern c2;
  MovementPattern m2;
  SizeChangePattern s2;

  Canvas canvas1 = new CanvasImpl();

  PlaybackController controller; //= new PlaybackController(canvas1, 5);

  @Before
  public void setUp() {
    c1 = new ColorPattern();
    m1 = new MovementPattern();
    s1 = new SizeChangePattern();

    c2 = new ColorPattern();
    c2.change(1, 10, new Integer[]{1, 2, 3}, new Integer[]{100, 150, 200});
    m2 = new MovementPattern();
    m2.change(20, 50, new Integer[]{100, 200}, new Integer[]{300, 400});
    s2 = new SizeChangePattern();
    s2.change(100, 200, new Integer[]{1, 4}, new Integer[]{50, 50});

    rec1 = new Rectangle();
    rec2 = new Rectangle(c1, m1, s1);
    rec3 = new Rectangle(c2, m2, s2);
    ov1 = new Oval();
    ov2 = new Oval(c1, m1, s1);
    ov3 = new Oval(c2, m1, s2);

    canvas1.add(rec1, "rec1");
    canvas1.add(rec2, "rec2");
    canvas1.add(rec3, "rec3");
    canvas1.add(ov1, "ov1");
    canvas1.add(ov2, "ov2");
    canvas1.add(ov3, "ov3");
    controller = new PlaybackController(canvas1, 5);
  }


  @Test
  public void testInvalidCommand() {
    assertEquals("Not a valid command", controller.processCommand("MakeThingsHappen!"));
  }

  @Test
  public void testProcessCommandStart() {
    assertEquals("Animation started", controller.processCommand("Play"));
  }
  @Test
  public void testProcessCommandPause() {
    assertEquals("Animation paused", controller.processCommand("Pause"));
  }
  @Test
  public void testProcessCommandRestart() {
    assertEquals("Animation restarted", controller.processCommand("Restart"));
  }
  @Test
  public void testProcessCommandEnableLoop() {
    assertEquals("Animation looping toggled on", controller.processCommand("Loop"));
    assertEquals("Animation looping toggled off", controller.processCommand("Loop"));
  }

  @Test
  public void testProcessCommandIncrease() {
    assertEquals("Animation made faster", controller.processCommand("Faster"));
  }
  @Test
  public void testProcessCommandDecrease() {
    assertEquals("Animation made slower", controller.processCommand("Slower"));
  }

}
