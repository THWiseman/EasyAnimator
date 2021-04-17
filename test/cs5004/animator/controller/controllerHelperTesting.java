package cs5004.animator.controller;

import cs5004.animator.model.Canvas;

public class controllerHelperTesting {

  private Canvas model;
  private PlaybackController controller;

  public controllerHelperTesting(Canvas model) {
    //load in our model.
    this.model = model;
    this.controller = new PlaybackController(model);
  }


}
