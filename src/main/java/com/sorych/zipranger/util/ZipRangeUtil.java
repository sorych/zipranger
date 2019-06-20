package com.sorych.zipranger.util;

import static com.sorych.zipranger.ZipRange.fromInt;

import com.sorych.zipranger.ZipRange;
import com.sorych.zipranger.exception.ZipRangeProcessingException;
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
      throw new ZipRangeProcessingException("wrong zip range format, begin > end: " + zipRange);
    }
  }

  public ZipRange fromString(String value) {
    if (!value.matches(zipRangeRegex)) {
      throw new ZipRangeProcessingException(
          String.format("zip range format %s doen't match the regex %s", value, zipRangeRegex));
    }
    StringTokenizer multiTokenizer = new StringTokenizer(value, zipRangeDelim);
    if (multiTokenizer.countTokens() < 2) {
      throw new ZipRangeProcessingException(
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
   *
   * @return the ZipRange result of the merge, null if not overlapping
   */
  public ZipRange merge(ZipRange zr1, ZipRange zr2) {
    if (!overlap(zr1, zr2)) {
      return null;
    }
    return fromInt(zr1.begin > zr2.begin ? zr2.begin : zr1.begin,
        zr1.end > zr2.end ? zr1.end : zr2.end);
  }
}





