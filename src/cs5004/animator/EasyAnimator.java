package cs5004.animator;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.CanvasImpl;
import cs5004.animator.model.Rectangle;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.SwingView;

import java.io.FileNotFoundException;
import java.io.FileReader;

public final class EasyAnimator {
  public static void main(String[] args) {
    Readable file;
    try {
      file = new FileReader("toh-3.txt");
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    }
    Canvas canvas =  AnimationReader.parseFile(file, new CanvasImpl.Builder);
    canvas.setDimensions(200,600,200,600);
    canvas.addShape(new Rectangle(),"rec1");
    SwingView view = new SwingView(canvas);

  }

}