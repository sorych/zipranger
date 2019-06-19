package com.sorych.zipranger.receiver;

public interface ResultReceiver {

  void consume(String resultBatch);

  default String finalResult() {
    return "job is done";
  }
}