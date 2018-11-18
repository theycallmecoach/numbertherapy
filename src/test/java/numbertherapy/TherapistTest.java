package numbertherapy;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TherapistTest {

  private ByteArrayOutputStream bo;
  private PrintStream out;
  private Therapist therapist;

  @BeforeEach
  void setup() {
    bo = new ByteArrayOutputStream();
    out = new PrintStream(bo);
    therapist = new Therapist(new EnglishConverter());
    therapist.setPrintStream(out);
  }

  @Test
  void testTherapistWithEnglishConverter() {
    final ByteArrayInputStream in = new ByteArrayInputStream("10\nexit".getBytes());
    therapist.setInputStream(in);
    therapist.startTherapySession();
    final String allWrittenLines = new String(bo.toByteArray());
    assertTrue(allWrittenLines.contains("Your number says: Ten"));
  }

  @Test
  void testTherapistWithPineappleSafeword() {
    final ByteArrayInputStream in = new ByteArrayInputStream("10\npineapple".getBytes());
    therapist.setInputStream(in);
    therapist.startTherapySession();
    final String allWrittenLines = new String(bo.toByteArray());
    assertTrue(allWrittenLines.contains("Your number says: Ten"));
  }


  @Test
  void testStartTherapySession() {
    final IConverter pinapple = value -> "The safeword is pineapple";
    final Therapist t = new Therapist(pinapple);
    final ByteArrayInputStream in = new ByteArrayInputStream("1034234234\nexit".getBytes());
    t.setInputStream(in);
    t.setPrintStream(out);
    t.startTherapySession();
    final String allWrittenLines = new String(bo.toByteArray());
    assertTrue(allWrittenLines.contains("The safeword is pineapple"));
  }

  @Test
  void testInvalidInput() {
    final ByteArrayInputStream in = new ByteArrayInputStream("abc\nexit".getBytes());
    therapist.setInputStream(in);
    therapist.startTherapySession();
    final String allWrittenLines = new String(bo.toByteArray());
    assertTrue(allWrittenLines.contains("Sorry that doesn't appear to be a valid integer"));
  }

  @Test
  void testInputWithCommas() {
    final ByteArrayInputStream in = new ByteArrayInputStream("123,123\nexit".getBytes());
    therapist.setInputStream(in);
    therapist.startTherapySession();
    final String allWrittenLines = new String(bo.toByteArray());
    assertTrue(allWrittenLines.contains("Your number says: One hundred twenty three thousand one hundred and twenty three"));
  }

  @Test
  void testInputWithWhitespace() {
    final ByteArrayInputStream in = new ByteArrayInputStream("9373 9383 3 3 \nexit".getBytes());
    therapist.setInputStream(in);
    therapist.setPrintStream(out);
    therapist.startTherapySession();
    final String allWrittenLines = new String(bo.toByteArray());
    assertTrue(allWrittenLines.contains("Your number says: Nine billion three hundred seventy three million nine hundred thirty eight thousand three hundred and thirty three"));
  }

}
