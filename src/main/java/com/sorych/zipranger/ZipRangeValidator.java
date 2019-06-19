package com.sorych.zipranger;

public class ZipRangeValidator {

  private String zipRangeRegex;

  public ZipRangeValidator(String zipRangeRegex) {
    this.zipRangeRegex = zipRangeRegex;
  }

  public void validate(String stringValue) {
    if (!stringValue.matches(zipRangeRegex)) {
      System.out.println("wrong zip range format: " + stringValue);
      throw new IllegalArgumentException("wrong zip range format: " + stringValue);
    }

    //if end < begin  - fail

  }
}





