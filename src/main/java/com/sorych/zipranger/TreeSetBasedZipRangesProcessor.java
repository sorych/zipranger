package com.sorych.zipranger;

import com.sorych.zipranger.comparator.ZipRangesComparator;
import com.sorych.zipranger.reader.ZipRangesReader;
import com.sorych.zipranger.receiver.ResultReceiver;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetBasedZipRangesProcessor implements ZipRangesProcessor {

  public TreeSetBasedZipRangesProcessor(ResultReceiver resultReceiver,
      ZipRangesReader zipRangesReader) {
    this.resultReceiver = resultReceiver;
    this.zipRangesReader = zipRangesReader;
  }

  private ResultReceiver resultReceiver;
  private ZipRangesReader zipRangesReader;

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
      ZipRange zipRange = fromString(nextRange);
      processRange(zipRange);
    }
    finalizeProcessing();

  }

  private static ZipRange fromString(String nextRange) {
    ZipRange zipRange = new ZipRange();
    return zipRange;
  }

  private void finalizeProcessing() {
    for (Object zipRange : zipRanges.toArray()) {
      resultReceiver.consume(zipRange.toString());
    }
  }

  private void processRange(ZipRange zipRange) {
    zipRanges.add(zipRange);
  }

}
