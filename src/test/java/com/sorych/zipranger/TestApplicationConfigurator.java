package com.sorych.zipranger;

import com.sorych.zipranger.configurator.ApplicationConfigurator;
import com.sorych.zipranger.reader.ZipRangesReader;
import com.sorych.zipranger.receiver.ResultReceiver;

public class TestApplicationConfigurator implements ApplicationConfigurator {

  public TestApplicationConfigurator(ResultReceiver resultReceiver,
      ZipRangesReader reader) {
    this.resultReceiver = resultReceiver;
    this.rangesReader = reader;
  }

  private ZipRangesReader rangesReader;

  private ResultReceiver resultReceiver;

  public ZipRangesReader getZipRangesReader(String... args) {
    return rangesReader;
  }

  public ResultReceiver getResultReceiver() {
    return resultReceiver;
  }

}
