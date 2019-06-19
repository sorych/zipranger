package com.sorych.zipranger.reader;

public interface ZipRangesReader {

  /**
   * @return next ZipRange String read from input or null if input is over
   */
  String getNextZipRangeString();

}
