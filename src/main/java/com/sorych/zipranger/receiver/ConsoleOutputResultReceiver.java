package com.sorych.zipranger.receiver;

import com.sorych.zipranger.ZipRange;

public class ConsoleOutputResultReceiver implements ResultReceiver {

  @Override
  public void consume(ZipRange result) {
    System.out.println(result);
  }

  @Override
  public void informJobIsAboutDone() {
    System.out.println("Ranges processing result:");
  }
}
