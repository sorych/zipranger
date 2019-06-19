package com.sorych.zipranger;

import static com.sorych.zipranger.ZipRange.fromString;

import com.sorych.zipranger.comparator.ZipRangesComparator;
import com.sorych.zipranger.reader.ZipRangesReader;
import com.sorych.zipranger.receiver.ResultReceiver;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetBasedZipRangesProcessor implements ZipRangesProcessor {

  public TreeSetBasedZipRangesProcessor(ApplicationConfigurator configurator) {
    this.resultReceiver = configurator.getResultReceiver();
    this.zipRangesReader = configurator.getZipRangesReader();
    this.zipRangeDelim = configurator.getRangeDelim();
    this.zipRangeValidator = new ZipRangeValidator(configurator.getZipRangeRegex());
  }

  private String zipRangeDelim;
  private ResultReceiver resultReceiver;
  private ZipRangesReader zipRangesReader;
  private ZipRangeValidator zipRangeValidator;

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
      zipRangeValidator.validate(nextRange);
      processRange(fromString(nextRange, zipRangeDelim));
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
