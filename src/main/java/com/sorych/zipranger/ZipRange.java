package com.sorych.zipranger;

public class ZipRange {

  public int begin;
  public int end;


  public static ZipRange fromInt(int begin, int end) {
    ZipRange result = new ZipRange();
    result.begin = begin;
    result.end = end;
    return result;
  }

  @Override
  public String toString() {
    return "[" +
        String.format("%05d", begin) +
        "," + String.format("%05d", end) +
        ']';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ZipRange zipRange = (ZipRange) o;

    if (begin != zipRange.begin) {
      return false;
    }
    return end == zipRange.end;
  }

  @Override
  public int hashCode() {
    int result = begin;
    result = 31 * result + end;
    return result;
  }
}
