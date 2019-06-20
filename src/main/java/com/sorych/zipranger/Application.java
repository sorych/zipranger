package com.sorych.zipranger;

import com.sorych.zipranger.configurator.ApplicationConfigurator;
import com.sorych.zipranger.configurator.DefaultApplicationConfigurator;
import com.sorych.zipranger.processor.TreeSetBasedZipRangesProcessor;
import com.sorych.zipranger.processor.ZipRangesProcessor;

public class Application {

  private static ApplicationConfigurator applicationConfigurator = new DefaultApplicationConfigurator();

  public static void main(String... args) {
    ZipRangesProcessor zipRangesProcessor = new TreeSetBasedZipRangesProcessor(
        applicationConfigurator);
    zipRangesProcessor.runTask();
  }

  /**
   * setter to "inject" configurator,
   * used in test
   *
   * @param applicationConfigurator
   */
  public static void setApplicationConfigurator(
      ApplicationConfigurator applicationConfigurator) {
    Application.applicationConfigurator = applicationConfigurator;
  }
}
