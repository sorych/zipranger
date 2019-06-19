package com.sorych.zipranger.comparator;

import com.sorych.zipranger.ZipRange;
import java.util.Comparator;

public class ZipRangesComparator implements Comparator<ZipRange> {

  @Override
  public int compare(ZipRange o1, ZipRange o2) {
    if (o1.begin == o2.begin) {
      return o1.end - o2.end;
    }
    return o1.begin - o2.begin ;
  }
}
