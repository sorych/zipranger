package com.sorych.zipranger.processor;

import com.sorych.zipranger.ZipRange;
import com.sorych.zipranger.comparator.ZipRangesComparator;
import com.sorych.zipranger.configurator.ApplicationConfigurator;
import com.sorych.zipranger.reader.ZipRangesReader;
import com.sorych.zipranger.receiver.ResultReceiver;
import com.sorych.zipranger.util.ZipRangeUtil;
import java.util.Iterator;
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
    resultReceiver.informJobIsAboutDone();

    Iterator<ZipRange> first = zipRanges.iterator();
    Iterator<ZipRange> second = zipRanges.iterator();

    ZipRange zr1 = first.next();
    second.next();
    ZipRange zr2;
    if (second.hasNext()) {
      zr2 = second.next();
    } else {
      resultReceiver.consume(zr1);
      return;
    }

    while (true) {
      if (zipRangeUtil.overlap(zr1, zr2)) {
        zr1 = zipRangeUtil.merge(zr1, zr2);
      } else {
        resultReceiver.consume(zr1);
        zr1 = first.next();
      }
      if (!second.hasNext()) {
        resultReceiver.consume(zr1);
        break;
      }
      zr2 = second.next();
    }
  }


  private void processRange(ZipRange zipRange) {
    zipRanges.add(zipRange);
  }

}
