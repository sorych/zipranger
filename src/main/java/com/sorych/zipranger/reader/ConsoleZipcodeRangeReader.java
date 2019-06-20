package com.sorych.zipranger.reader;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class ConsoleZipcodeRangeReader implements ZipRangesReader {

  private Queue<String> queue = new ArrayDeque<>();

  private final String END_INPUT_STRING = "X";
  private final String SPLIT_SYMPOL = " ";


  @Override
  public String getNextZipRangeString() {
    if (queue.peek() == null) {
      System.out.println("please insert next line or type '" + END_INPUT_STRING + "' to complete input");
      Scanner scanner = new Scanner(System.in);
      String inputString = scanner.nextLine();
      if (END_INPUT_STRING.equals(inputString)) {
        return null;
      }
      queue.addAll(Arrays.asList(inputString.split(SPLIT_SYMPOL)));
    }
    return queue.poll();
  }
}
