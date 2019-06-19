package com.sorych.zipranger;

import com.sorych.zipranger.receiver.ResultReceiver;
import java.util.ArrayList;
import java.util.List;

public class TestResultReceiver implements ResultReceiver {

  private List<String> applicationResults = new ArrayList<>();

  @Override
  public void consume(String resultBatch) {
    applicationResults.add(resultBatch);
  }

  @Override
  public String finalResult() {
    return String.join(" ", applicationResults);
  }
}
