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
   * we assume here that first is less than second
   * @param zp1
   * @param zr2
   * @return the ZipRange result of the merge
   */
  public ZipRange merge(ZipRange zp1, ZipRange zr2) {
    return fromInt(zp1.begin, zp1.end > zr2.end ? zp1.end : zr2.end);
  }
}





