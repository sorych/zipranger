package com.sorych.zipranger;

public class ZipRange {

  int begin;
  int end;

  @Override
  public String toString() {
    return "[" +
        begin +
        "," + end +
        ']';
  }
}
