package cs5004.animator.view;

import java.io.IOException;

import cs5004.animator.model.Canvas;

public class TextView implements View {

  Appendable output;

  public TextView() {
    this.output = System.out;
  }



  @Override
  public void go(Appendable output, Canvas canvas) {
    try {
      output.append(canvas.getStringDescription());
    } catch (IOException e) {
      throw new IllegalStateException("Appendable error");
    }

  }

  @Override
  public void go(Canvas canvas) {

  }
}
