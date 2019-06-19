package com.sorych.zipranger;

import com.sorych.zipranger.comparator.ZipRangesComparator;
import com.sorych.zipranger.reader.ZipRangesReader;
import com.sorych.zipranger.receiver.ResultReceiver;
import com.sorych.zipranger.util.ZipRangeUtil;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetBasedZipRangesProcessor implements ZipRangesProcessor {

  public TreeSetBasedZipRangesProcessor(ApplicationConfigurator configurator) {
    this.resultReceiver = configurator.getResultReceiver();
    this.zipRangesReader = configurator.getZipRangesReader();
    this.zipRangeUtil = new ZipRangeUtil(configurator.getZipRangeRegex(),
        configurator.getRangeDelim());
  }

  private ResultReceiver resultReceiver;
  private ZipRangesReader zipRangesReader;
  private ZipRangeUtil zipRangeUtil;

  private Set<ZipRange> zipRanges = new TreeSet<>(new ZipRangesComparator());

  @Override
  public void proceedZipRangesTask() {
    if (resultReceiver == null || zipRangesReader == null) {
      throw new IllegalStateException("not set up");
    }

    while (true) {
      String nextRange = zipRangesReader.getNextZipRangeString();
      if (nextRange == null) {
        break;
      }
      ZipRange zipRange = zipRangeUtil.fromString(nextRange);
      zipRangeUtil.validate(zipRange);
      processRange(zipRange);
    }
    finalizeProcessing();

  }

  private void finalizeProcessing() {
    //todo
    for (Object zipRange : zipRanges.toArray()) {
      resultReceiver.consume(zipRange.toString());
    }
  }

  private void processRange(ZipRange zipRange) {
    zipRanges.add(zipRange);
  }

}
