package com.sorych.zipranger;

import static com.sorych.zipranger.TestZipRangesReader.DEFAULT_STRING_INPUT;
import static org.junit.Assert.assertEquals;

import com.sorych.zipranger.reader.ZipRangesReader;
import com.sorych.zipranger.receiver.ResultReceiver;
import org.junit.Test;

public class ApplicationTest {

  private ResultReceiver receiver = new TestResultReceiver();
  private ZipRangesReader reader = new TestZipRangesReader();


  @Test
  public void testWhenDefaultReader() {
    Application.setApplicationConfigurator(new TestApplicationConfigurator(receiver, reader));
    Application.main();
    assertEquals(DEFAULT_STRING_INPUT, receiver.getFinalResult());
  }

  @Test
  public void testCustomInput1() {
    Application.setApplicationConfigurator(
        new TestApplicationConfigurator(receiver, new TestZipRangesReader("[23451,98765]")));
    Application.main();
    assertEquals("[23451,98765]", receiver.getFinalResult());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyInput() {
    Application.setApplicationConfigurator(
        new TestApplicationConfigurator(receiver, new TestZipRangesReader("")));
    Application.main();
  }

  @Test
  public void testCustomInput2() {
    Application.setApplicationConfigurator(new TestApplicationConfigurator(receiver,
        new TestZipRangesReader("[23451,38765] [65432,98312]")));
    Application.main();
    assertEquals("[23451,38765] [65432,98312]", receiver.getFinalResult());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldFailWrongFormat() {
    Application.setApplicationConfigurator(
        new TestApplicationConfigurator(receiver, new TestZipRangesReader("[23451,98765")));
    Application.main();
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldFailWrongZipFormat() {
    Application.setApplicationConfigurator(
        new TestApplicationConfigurator(receiver, new TestZipRangesReader("[231,98765")));
    Application.main();
  }

  @Test
  public void shouldBeInReversedOrder() {
    Application.setApplicationConfigurator(new TestApplicationConfigurator(receiver,
        new TestZipRangesReader("[65432,98312] [23451,38765]")));
    Application.main();
    assertEquals("[23451,38765] [65432,98312]", receiver.getFinalResult());
  }

  @Test
  public void shouldBeMerged() {
    Application.setApplicationConfigurator(new TestApplicationConfigurator(receiver,
        new TestZipRangesReader("[15432,98312] [23451,38765]")));
    Application.main();
    assertEquals("[15432,98312]", receiver.getFinalResult());
  }

  @Test
  public void shouldBeMerged2() {
    Application.setApplicationConfigurator(new TestApplicationConfigurator(receiver,
        new TestZipRangesReader("[15432,98312] [23451,38765] [13451,38795]")));
    Application.main();
    assertEquals("[13451,98312]", receiver.getFinalResult());
  }

  @Test
  public void shouldNotBeMerged() {
    Application.setApplicationConfigurator(new TestApplicationConfigurator(receiver,
        new TestZipRangesReader("[15432,18312] [23451,38765] [43451,78795]")));
    Application.main();
    assertEquals("[15432,18312] [23451,38765] [43451,78795]", receiver.getFinalResult());
  }


}
