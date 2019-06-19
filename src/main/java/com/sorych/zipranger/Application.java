package com.sorych.zipranger;

public class Application {

  private static ApplicationConfigurator applicationConfigurator = new DefaultApplicationConfigurator();

  public static void main(String... args) {
    ZipRangesProcessor zipRangesProcessor = new TreeSetBasedZipRangesProcessor(
        applicationConfigurator.getResultReceiver(),
        applicationConfigurator.getZipRangesReader(args));
    zipRangesProcessor.proceedZipRangesTask();
  }

  public static void setApplicationConfigurator(
      ApplicationConfigurator applicationConfigurator) {
    Application.applicationConfigurator = applicationConfigurator;
  }
}
