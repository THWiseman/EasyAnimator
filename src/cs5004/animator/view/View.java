package cs5004.animator.view;

import cs5004.animator.model.Canvas;
//look into java swing timer

/**
 * This is the common cs5004.animator.view interface that all views, regardless of type, will implement.
 */
public interface View {

  /**
   * Causes the view to display the model that it was constructed with. Different types of views will
   * do different things depending on their responsibility. Refer to the documentation for each view.
   */
  public void go();

}
