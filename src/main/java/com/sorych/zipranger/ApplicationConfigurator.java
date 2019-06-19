package com.sorych.zipranger;

import com.sorych.zipranger.reader.ZipRangesReader;
import com.sorych.zipranger.receiver.ResultReceiver;

public interface ApplicationConfigurator {

  String DEFAULT_ZIP_RANGE_REGEX = "\\[[0-9]{5}\\,[0-9]{5}\\]";
  String DEFAULT_DELIM = "[,]";

  ZipRangesReader getZipRangesReader(String... args);

  ResultReceiver getResultReceiver();

  default String getZipRangeRegex() {
    return DEFAULT_ZIP_RANGE_REGEX;
  }

  default String getRangeDelim() {
    return DEFAULT_DELIM;
  }
}
