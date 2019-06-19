package com.sorych.zipranger.comparator;

import static com.sorych.zipranger.ZipRange.fromInt;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.sorych.zipranger.ZipRange;
import org.junit.Test;

public class ZipRangesComparatorTest {

  private ZipRangesComparator comparator = new ZipRangesComparator();

  @Test
  public void firstShouldBeLess() {
    ZipRange first = fromInt(0, 1);
    ZipRange second = fromInt(1, 2);
    assertTrue(comparator.compare(first, second) < 0);
  }

  @Test
  public void secondShouldBeLess() {
    ZipRange first = fromInt(3, 1);
    ZipRange second = fromInt(1, 2);
    assertTrue(comparator.compare(first, second) > 0);
  }

  @Test
  public void shouldBeEqual() {
    ZipRange first = fromInt(1, 2);
    ZipRange second = fromInt(1, 2);
    assertEquals(0, comparator.compare(first, second));
  }

  @Test
  public void firstShouldBeLessByDiffEnds() {
    ZipRange first = fromInt(1, 2);
    ZipRange second = fromInt(1, 3);
    assertTrue(comparator.compare(first, second) < 0);
  }

  @Test
  public void secondShouldBeLessByDiffEnds() {
    ZipRange first = fromInt(1, 4);
    ZipRange second = fromInt(1, 3);
    assertTrue(comparator.compare(first, second) > 0);
  }


}