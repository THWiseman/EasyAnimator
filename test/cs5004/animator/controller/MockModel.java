package cs5004.animator.controller;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A mock class to allow the PlaybackController tests to run effectively.
 */
public class MockModel implements Canvas {
  private List<String> orderedShapeNames;

  public MockModel() {
    orderedShapeNames = new ArrayList<>();
  }

  @Override
  public int getStartTime() {
    return 0;
  }

  @Override
  public int getEndTime() {
    return 100;
  }

  @Override
  public List<Shape> getAllShapes() {
    return null;
  }

  @Override
  public List<String> getAllShapeIDs() {
    return null;
  }

  @Override
  public void addShape(Shape s, String iD) {
    //No action needed.
  }

  @Override
  public boolean removeShape(String iD) {
    return false;
  }

  @Override
  public Shape getShape(String iD) {
    return null;
  }

  @Override
  public void setDimensions(int leftmostX, int width, int topmostY, int height) {
    //No action needed.
  }

  @Override
  public int[] getDimensions() {
    return new int[]{40, 500, 40, 500};
  }

  @Override
  public Map<String, Shape> getShapeMap() {
    return null;
  }

  @Override
  public List<String> getOrderedShapeNames() {
    return this.orderedShapeNames;
  }

  @Override
  public void remove(String iD) {
    //No action needed.
  }

  @Override
  public void add(Shape s, String iD) {
    //No action needed.
  }

}
