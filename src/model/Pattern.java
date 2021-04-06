package model;

public interface Pattern {

  void change(Integer frame1, Integer frame2, Integer[] values);

  int[] get(Integer time);

}