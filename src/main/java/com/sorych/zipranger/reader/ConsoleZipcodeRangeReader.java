package com.sorych.zipranger.reader;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class ConsoleZipcodeRangeReader implements ZipRangesReader {

  Queue<String> queue = new ArrayDeque<>();

  private final String END_INPUT_STRING = "X";


  @Override
  public String getNextZipRangeString() {
    if (queue.peek() == null) {
      System.out.println("please insert next line or type 'X' to finish input");
      Scanner scanner = new Scanner(System.in);
      String inputString = scanner.nextLine();
      if (END_INPUT_STRING.equals(inputString)) {
        return null;
      }
      queue.addAll(Arrays.asList(inputString.split(" ")));
    }
    return queue.poll();
  }
}
