package cs5004.animator;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.CanvasImpl;
import cs5004.animator.model.Rectangle;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.SwingView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class EasyAnimator {
  public static void main(String[] args) throws IOException {
    Canvas canvas = new CanvasImpl();
    canvas.setDimensions(200,600,200,600);
    canvas.addShape(new Rectangle(),"rec1");
    SwingView view = new SwingView(canvas);

  }

}