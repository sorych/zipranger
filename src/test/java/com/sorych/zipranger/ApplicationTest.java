package com.sorych.zipranger;

import static com.sorych.zipranger.Application.DEFAULT_OUTPUT;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ApplicationTest {

  @Test
  public void testDefaultAppOutput() {
    ResultReceiver receiver = new TestResultReceiver();

    Application.setResultReceiver(receiver);

    Application.main();

    assertEquals(DEFAULT_OUTPUT, receiver.finalResult());
  }
}
