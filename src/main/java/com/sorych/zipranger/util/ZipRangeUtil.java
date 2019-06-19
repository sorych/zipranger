package com.sorych.zipranger.util;

import static com.sorych.zipranger.ZipRange.fromInt;

import com.sorych.zipranger.ZipRange;
import java.util.StringTokenizer;

public class ZipRangeUtil {

  private String zipRangeRegex;
  private String zipRangeDelim;

  public ZipRangeUtil(String zipRangeRegex, String zipRangeDelim) {
    this.zipRangeRegex = zipRangeRegex;
    this.zipRangeDelim = zipRangeDelim;
  }

  public void validate(ZipRange zipRange) {
    if (zipRange.end < zipRange.begin) {
      throw new IllegalArgumentException("wrong zip range format, begin > end: " + zipRange);
    }
  }

  public ZipRange fromString(String value) {
    if (!value.matches(zipRangeRegex)) {
      throw new IllegalArgumentException("wrong zip range format: " + value);
    }
    StringTokenizer multiTokenizer = new StringTokenizer(value, zipRangeDelim);
    if (multiTokenizer.countTokens() < 2) {
      throw new IllegalStateException(
          String.format("zip range cannot be tokenized using %s delimeter", zipRangeDelim));
    }
    return fromInt(Integer.parseInt(multiTokenizer.nextToken()),
        Integer.parseInt(multiTokenizer.nextToken()));
  }

  public boolean overlap(ZipRange zr1, ZipRange zr2) {
    return (zr1.end - zr2.begin) >= 0;
  }

  /**
   * merges two ZipRange to one if overlap
   * @param zr1
   * @param zr2
   * @return the ZipRange result of the merge, null if not overlapping
   */
  public ZipRange merge(ZipRange zr1, ZipRange zr2) {
    if (!overlap(zr1, zr2)) {
      return null;
    }
    return fromInt(zr1.begin > zr2.begin ? zr2.begin : zr1.begin, zr1.end > zr2.end ? zr1.end : zr2.end);
  }
}





