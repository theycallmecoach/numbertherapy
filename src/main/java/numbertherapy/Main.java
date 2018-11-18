package numbertherapy;

/**
 * Entry point for number Therapy.
 *
 * @author Kyle Girard theycallmecoach@gmail.com
 *
 */
public class Main {
  /**
   * Main entry point for Number Therapy.
   *
   * @param args - Ignored, not used.
   */
  public static void main(final String[] args) {
    try {
      final Therapist therapist = new Therapist(new EnglishConverter());
      therapist.startTherapySession();
    } catch (final Exception e) {
      System.err.println("Uncaught Exception: " + e);
    }

  }
}
