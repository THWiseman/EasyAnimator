package model;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Shape objects. Currently testing Rectangles and Ovals.
 */
public class ShapeTest {
  Shape rec1;
  Shape rec2;
  Oval ov1;
  Shape ov2;

  ColorPattern c1 = new ColorPattern();
  MovementPattern m1 = new MovementPattern();
  SizeChangePattern s1 = new SizeChangePattern();
  VisibilityPattern v1 = new VisibilityPattern();

  @Before
  public void setUp() {
    rec1 = new Rectangle();
    rec2 = new Rectangle(c1, m1, s1, v1);
    ov1 = new Oval();
    ov2 = new Oval(c1, m1, s1, v1);
  }

  @Test
  public void getPosition() {
  }

  @Test
  public void getColor() {
  }

  @Test
  public void getSize() {
  }

  @Test
  public void getVisibility() {
  }

  @Test
  public void update() {
  }

  @Test
  public void getAppearTime() {
  }

  @Test
  public void getDisappearTime() {
  }

  @Test
  public void setMasterPattern() {
  }

  @Test
  public void getMasterPatternCopy() {
  }

  @Test
  public void copy() {
  }
}