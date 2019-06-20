package com.sorych.zipranger.receiver;

import com.sorych.zipranger.ZipRange;

public class ConsoleOutputResultReceiver implements ResultReceiver {

  @Override
  public void consume(ZipRange result) {
    System.out.print(result + " ");
  }

  @Override
  public void consumeError(String cause) {
    System.out.println(String.format("Error while processing zip ranges, details: %s", cause));
  }

  @Override
  public void startReceivingResults() {
    System.out.println("Ranges processing result:");
  }
}
