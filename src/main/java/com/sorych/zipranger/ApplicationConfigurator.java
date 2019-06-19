package com.sorych.zipranger;

import com.sorych.zipranger.reader.ZipRangesReader;
import com.sorych.zipranger.receiver.ResultReceiver;

public interface ApplicationConfigurator {

  ZipRangesReader getZipRangesReader(String... args);

  ResultReceiver getResultReceiver();
}
