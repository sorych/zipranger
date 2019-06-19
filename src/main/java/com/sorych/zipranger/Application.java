package com.sorych.zipranger;

public class Application {

  static ApplicationConfigurator applicationConfigurator = new DefaultApplicationConfigurator();

  public static void main(String... args) {
    ZipRangesProcessor zipRangesProcessor = new ZipRangesProcessor();
    zipRangesProcessor.setZipRangesReader(applicationConfigurator.getZipRangesReader(args));
    zipRangesProcessor.setResultReceiver(applicationConfigurator.getResultReceiver());
    zipRangesProcessor.proceedZipRangesTask();
  }

  public static void setApplicationConfigurator(
      ApplicationConfigurator applicationConfigurator) {
    Application.applicationConfigurator = applicationConfigurator;
  }
}
