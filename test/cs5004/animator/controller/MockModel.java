package cs5004.animator.controller;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MockModel implements Canvas {

  StringBuilder log;
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

  }

  @Override
  public void add(Shape s, String iD) {
    log.append("Shape added successfully");
  }

  public String getLog() {
    return log.toString();
  }
}
