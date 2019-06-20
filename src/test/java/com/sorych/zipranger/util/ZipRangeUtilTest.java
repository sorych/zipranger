package com.sorych.zipranger.util;

import static com.sorych.zipranger.ZipRange.fromInt;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.sorych.zipranger.configurator.ApplicationConfigurator;
import com.sorych.zipranger.exception.ZipRangeProcessingException;
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

  @Test(expected = ZipRangeProcessingException.class)
  public void shouldFailWrongFormat() {
    String invalid = "2313,41214";
    zipRangeUtil.fromString(invalid);
  }

  @Test(expected = ZipRangeProcessingException.class)
  public void shouldFailLess5Digits() {
    String invalid = "[2313,41214]";
    zipRangeUtil.fromString(invalid);
  }

  @Test(expected = ZipRangeProcessingException.class)
  public void shouldFailOnlyOneValue() {
    String invalid = "[41214]";
    zipRangeUtil.fromString(invalid);
  }

  @Test(expected = ZipRangeProcessingException.class)
  public void shouldFailSecondLess() {
    String valid = "[99999,41451]";
    zipRangeUtil.validate(zipRangeUtil.fromString(valid));
  }

  @Test
  public void shouldPassValidation() {
    String valid = "[22312,41451]";
    zipRangeUtil.validate(fromInt(22312, 41451));
  }

  @Test
  public void shouldOverlap() {
    assertTrue(zipRangeUtil.overlap(fromInt(22312, 41451), fromInt(22300, 22312)));
  }

  @Test
  public void shouldOverlapTheSame() {
    assertTrue(zipRangeUtil.overlap(fromInt(22312, 22312), fromInt(22312, 22312)));
  }

  @Test
  public void shouldOverlap2() {
    assertTrue(zipRangeUtil.overlap(fromInt(11312, 22312), fromInt(22312, 32312)));
  }

  @Test
  public void shouldNotOverlap() {
    assertFalse(zipRangeUtil.overlap(fromInt(22312, 22312), fromInt(72312, 92312)));
  }

  @Test
  public void shouldBeMergedParticallyOverlap() {
    assertEquals(fromInt(12312, 52312),
        zipRangeUtil.merge(fromInt(12312, 32312), fromInt(22312, 52312)));
  }

  @Test
  public void shouldBeMergedFirstCoversSecond() {
    assertEquals(fromInt(12312, 52312),
        zipRangeUtil.merge(fromInt(12312, 52312), fromInt(22312, 32312)));
  }

  @Test
  public void shouldNotBeMerged() {
    assertNull(zipRangeUtil.merge(fromInt(12312, 32312), fromInt(42312, 92312)));
  }

  @Test
  public void shouldBeMergedTheSame() {
    assertEquals(fromInt(12312, 52312),
        zipRangeUtil.merge(fromInt(12312, 52312), fromInt(12312, 52312)));
  }

  @Test
  public void shouldBeMergedEndsAndStartsInOnePoint() {
    assertEquals(fromInt(12312, 32312),
        zipRangeUtil.merge(fromInt(12312, 22312), fromInt(22312, 32312)));
  }
}