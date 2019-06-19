package com.sorych.zipranger;

import com.sorych.zipranger.reader.ZipRangesReader;
import java.util.Stack;

public class TestZipRangesReader implements ZipRangesReader {

  Stack<String> zipRanges = new Stack<>();

  public TestZipRangesReader() {
    String first = "hi there";
    zipRanges.push(first);
  }


  @Override
  public String getNextZipRangeString() {
    return zipRanges.empty() ? null : zipRanges.pop();

  }
}
