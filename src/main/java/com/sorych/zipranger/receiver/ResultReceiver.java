package com.sorych.zipranger.receiver;

import com.sorych.zipranger.ZipRange;

public interface ResultReceiver {

  void consume(final ZipRange result);

  default String getFinalResult() {
    return "job is done";
  }

  default void informJobIsAboutDone() {
  }
}
