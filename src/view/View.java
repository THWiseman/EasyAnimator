package view;

import cs5004.animator.model.Canvas;

/**
 * This is the common view interface that all views, regardless of type, will implement.
 */
public interface View {

  /**
   * Produces a view given valid inputs. Allows user to specify an output destination, which
   * will be used in the case of textual output.
   * @param output Destination file or location for the text output of the chosen view, if there
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
