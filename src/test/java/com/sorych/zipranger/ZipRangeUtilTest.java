package com.sorych.zipranger;

import static com.sorych.zipranger.ZipRange.fromInt;
import static org.junit.Assert.assertEquals;

import com.sorych.zipranger.util.ZipRangeUtil;
import org.junit.Test;

public class ZipRangeUtilTest {

  private ZipRangeUtil zipRangeUtil = new ZipRangeUtil(
      ApplicationConfigurator.DEFAULT_ZIP_RANGE_REGEX,
      ApplicationConfigurator.DEFAULT_DELIM);

  @Test
  public void shouldPassParsing() {
    String valid = "[22312,41451]";
    assertEquals(fromInt(22312, 41451), zipRangeUtil.fromString(valid));
  }

  @Test
  public void shouldPassSameValues() {
    String valid = "[22312,22312]";
    assertEquals(fromInt(22312, 22312), zipRangeUtil.fromString(valid));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldFailWrongFormat() {
    String invalid = "2313,41214";
    zipRangeUtil.fromString(invalid);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldFailLess5Digits() {
    String invalid = "[2313,41214]";
    zipRangeUtil.fromString(invalid);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldFailOnlyOneValue() {
    String invalid = "[41214]";
    zipRangeUtil.fromString(invalid);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldFailSecondLess() {
    String valid = "[99999,41451]";
    zipRangeUtil.validate(zipRangeUtil.fromString(valid));
  }

  @Test
  public void shouldPassValidation() {
    String valid = "[22312,41451]";
    zipRangeUtil.validate(fromInt(22312, 41451));
  }
}