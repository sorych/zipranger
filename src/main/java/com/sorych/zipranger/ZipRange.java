package com.sorych.zipranger;

import java.util.StringTokenizer;

public class ZipRange {

  public int begin;
  public int end;


  public static ZipRange fromInt(int begin, int end) {
    ZipRange result = new ZipRange();
    result.begin = begin;
    result.end = end;
    return result;
  }

  public static ZipRange fromString(String value, String delim) {
    StringTokenizer multiTokenizer = new StringTokenizer(value, delim);
    if (multiTokenizer.countTokens() < 2) {
      throw new IllegalStateException(
          String.format("zip range cannot be tokenized using %s delimeter", delim));
    }
    return fromInt(Integer.parseInt(multiTokenizer.nextToken()),
        Integer.parseInt(multiTokenizer.nextToken()));
  }

  @Override
  public String toString() {
    return "[" +
        String.format("%05d", begin) +
        "," + String.format("%05d", end) +
        ']';
  }
}
