package com.sorych.zipranger;

import com.sorych.zipranger.reader.ZipRangesReader;
import java.util.Stack;
import java.util.stream.Stream;

public class TestZipRangesReader implements ZipRangesReader {

  public static final String DEFAULT_STRING_INPUT = "[00000,54231]";
  Stack<String> zipRanges = new Stack<>();

  public TestZipRangesReader() {
    String first = DEFAULT_STRING_INPUT;
    zipRanges.push(first);
  }

  public TestZipRangesReader(String values) {
    Stream.of(values.split(" ")).forEach(zipRanges::push);
  }


  @Override
  public String getNextZipRangeString() {
    return zipRanges.empty() ? null : zipRanges.pop();

  }
}
