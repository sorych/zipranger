package com.sorych.zipranger;

import com.sorych.zipranger.receiver.ResultReceiver;
import java.util.ArrayList;
import java.util.List;

public class TestResultReceiver implements ResultReceiver {

  private List<String> applicationResults = new ArrayList<>();

  @Override
  public void consume(ZipRange result) {
    applicationResults.add(result.toString());
  }

  @Override
  public void consumeError(String cause) {
    throw new RuntimeException(cause);
  }

  public String getFinalResult() {
    return String.join(" ", applicationResults);
  }
}
