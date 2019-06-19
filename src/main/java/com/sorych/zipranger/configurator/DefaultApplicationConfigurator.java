package com.sorych.zipranger.configurator;

import com.sorych.zipranger.reader.ConsoleZipcodeRangeReader;
import com.sorych.zipranger.reader.FileZipcodeRangeReader;
import com.sorych.zipranger.reader.ZipRangesReader;
import com.sorych.zipranger.receiver.ConsoleOutputResultReceiver;
import com.sorych.zipranger.receiver.ResultReceiver;

public class DefaultApplicationConfigurator implements ApplicationConfigurator {

  public ZipRangesReader getZipRangesReader(String... args) {
    if (args.length > 0) {
      return new FileZipcodeRangeReader(args[0]);   //just to demonstrate how we can add another reader here
    } else {
      return new ConsoleZipcodeRangeReader();
    }
  }

  public ResultReceiver getResultReceiver() {
    return new ConsoleOutputResultReceiver();
  }

}
