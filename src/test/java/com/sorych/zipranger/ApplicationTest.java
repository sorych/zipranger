package com.sorych.zipranger;

import static org.junit.Assert.assertEquals;

import com.sorych.zipranger.receiver.ResultReceiver;
import org.junit.Test;

public class ApplicationTest {


  @Test
  public void testDefaultAppOutput() {
    ResultReceiver receiver = new TestResultReceiver();

    Application.setApplicationConfigurator(new TestApplicationConfigurator(receiver));

    Application.main();

    assertEquals("[00000,54231]", receiver.finalResult());
  }


}
