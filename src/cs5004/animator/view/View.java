package cs5004.animator.view;

import cs5004.animator.model.Canvas;

/**
 * This is the common cs5004.animator.view interface that all views, regardless of type, will implement.
 */
public interface View {

  /**
   * Produces a cs5004.animator.view given valid inputs. Allows user to specify an output destination, which
   * will be used in the case of textual output.
   * @param output Destination file or location for the text output of the chosen cs5004.animator.view, if there
   *               is any.
   * @param canvas Canvas model from which to draw information.
   */
  public void go(Appendable output, Canvas canvas);

  /**
   * Standard go method that uses System.out for all text output (if there is any).
   * @param canvas Canvas model from which to draw information.
   */
  public void go(Canvas canvas);

}
