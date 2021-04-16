package cs5004.animator.view;


/**
 * This is the common cs5004.animator.view interface that all views, regardless of type, will
 * implement.
 */
public interface View {

  /**
   * Causes the view to display the model that it was constructed with. Different types of views
   * will do different things depending on their responsibility. Refer to the documentation for each
   * view.
   */
  void run();

  /**
   * Causes the view to display the model that it was constructed with. Different types of views
   * will do different things depending on their responsibility. Refer to the documentation for each
   * view.
   *
   * @param tps the number of ticks per second in the animation.
   */
  void run(int tps);

  /**
   * Causes the view to display the model that it was constructed with. Different types of views
   * will do different things depending on their responsibility. Refer to the documentation for each
   * view.
   *
   * @param filepath the filepath of the output file.
   */
  void run(String filepath);

  /**
   * Causes the view to display the model that it was constructed with. Different types of views
   * will do different things depending on their responsibility. Refer to the documentation for each
   * view.
   *
   * @param tps      the number of ticks per second in the animation.
   * @param filepath the filepath of the output file.
   */
  void run(int tps, String filepath);

}
