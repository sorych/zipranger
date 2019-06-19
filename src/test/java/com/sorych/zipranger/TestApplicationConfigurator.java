package com.sorych.zipranger;

import com.sorych.zipranger.reader.ZipRangesReader;
import com.sorych.zipranger.receiver.ResultReceiver;

public class TestApplicationConfigurator implements ApplicationConfigurator {

  public TestApplicationConfigurator(ResultReceiver resultReceiver) {
    this.resultReceiver = resultReceiver;
  }



  private ResultReceiver resultReceiver;

  public ZipRangesReader getZipRangesReader(String... args) {
    return new TestZipRangesReader();
  }

  public ResultReceiver getResultReceiver() {
    return resultReceiver;
  }

}
