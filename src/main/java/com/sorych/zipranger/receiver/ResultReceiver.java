package com.sorych.zipranger.receiver;

import com.sorych.zipranger.ZipRange;

public interface ResultReceiver {

  void consume(final ZipRange result);

  void consumeError(String cause);

  default void startReceivingResults() {
  }

}
