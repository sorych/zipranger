package com.sorych.zipranger;

import static com.sorych.zipranger.TestZipRangesReader.DEFAULT_STRING_INPUT;
import static org.junit.Assert.assertEquals;

import com.sorych.zipranger.reader.ZipRangesReader;
import org.junit.Test;

public class ApplicationTest {

  private TestResultReceiver receiver = new TestResultReceiver();
  private ZipRangesReader reader = new TestZipRangesReader();


  @Test
  public void testWhenDefaultReader() {
    Application.setApplicationConfigurator(new TestApplicationConfigurator(receiver, reader));
    Application.main();
    assertEquals(DEFAULT_STRING_INPUT, receiver.getFinalResult());
  }

  @Test
  public void testCustomInput1() {
    setupInput("[23451,98765]");
    Application.main();
    assertEquals("[23451,98765]", receiver.getFinalResult());
  }

  @Test
  public void testZipStartsWithZero() {
    setupInput("[00051,68765] [01312,77777]");
    Application.main();
    assertEquals("[00051,77777]", receiver.getFinalResult());
  }

  @Test(expected = RuntimeException.class)
  public void testEmptyInput() {
    setupInput("");
    Application.main();
  }

  @Test
  public void testCustomInput2() {
    setupInput("[23451,38765] [65432,98312]");
    Application.main();
    assertEquals("[23451,38765] [65432,98312]", receiver.getFinalResult());
  }

  @Test(expected = RuntimeException.class)
  public void shouldFailWrongFormat() {
    setupInput("[23451,98765");
    Application.main();
  }

  @Test(expected = RuntimeException.class)
  public void shouldFailWrongZipFormat() {
    setupInput("[231,98765");
    Application.main();
  }

  @Test
  public void shouldBeInReversedOrder() {
    setupInput("[65432,98312] [23451,38765]");
    Application.main();
    assertEquals("[23451,38765] [65432,98312]", receiver.getFinalResult());
  }

  @Test
  public void shouldBeMerged() {
    setupInput("[15432,98312] [23451,38765]");
    Application.main();
    assertEquals("[15432,98312]", receiver.getFinalResult());
  }

  @Test
  public void shouldBeMerged2() {
    setupInput("[15432,98312] [23451,38765] [13451,38795]");
    Application.main();
    assertEquals("[13451,98312]", receiver.getFinalResult());
  }

  @Test
  public void shouldBeMerged3() {
    setupInput(
        "[15432,18312] [15432,18312] [75432,98312] [15432,18312] [15432,18312] [15432,18312] [15432,18312] [13451,38795]");
    Application.main();
    assertEquals("[13451,38795] [75432,98312]", receiver.getFinalResult());
  }

  @Test
  public void shouldNotBeMerged() {
    setupInput("[15432,18312] [23451,38765] [43451,78795]");
    Application.main();
    assertEquals("[15432,18312] [23451,38765] [43451,78795]", receiver.getFinalResult());
  }


  private void setupInput(String s) {
    Application.setApplicationConfigurator(new TestApplicationConfigurator(receiver,
        new TestZipRangesReader(s)));
  }


}
