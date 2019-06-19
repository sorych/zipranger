package com.sorych.zipranger;

public class Application {

  private static ResultReceiver resultReceiver;

  final static String DEFAULT_OUTPUT = "hi there";

  public static void main(String... args) {
    resultReceiver.consume(DEFAULT_OUTPUT);
  }

  static void setResultReceiver(ResultReceiver resultReceiver) {
    Application.resultReceiver = resultReceiver;
  }
}
