package com.sorych.zipranger;

public class ZipRange {

  public int begin;
  public int end;



  public static ZipRange fromInt(int first, int second) {
    ZipRange result = new ZipRange();
    result.begin = first;
    result.end = second;
    return result;
  }

  @Override
  public String toString() {
    return "[" +
        begin +
        "," + end +
        ']';
  }
}
