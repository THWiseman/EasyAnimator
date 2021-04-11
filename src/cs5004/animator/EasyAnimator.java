package cs5004.animator;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.CanvasImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.SwingView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class EasyAnimator {
  public static void main(String[] args) throws IOException{
    Canvas canvas1;
    BufferedReader reader = new BufferedReader((new FileReader("src/toh-3.txt")));
    canvas1 = AnimationReader.parseFile(reader, new CanvasImpl.Builder());
    SwingView view = new SwingView(canvas1);
    view.go();
  }

}