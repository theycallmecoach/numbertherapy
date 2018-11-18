package numbertherapy;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents the therapist who helps facilitate numbers ability to communicate
 * in English.
 *
 * @author Kyle Girard theycallmecoach@gmail.com
 *
 */
public class Therapist {

  private final IConverter converter;

  private InputStream in = System.in;
  private PrintStream out = System.out;

  /**
   * Creates a therapist that will use the given converter within therapy sessions
   * to facilitate communication with numbers.
   *
   * @param converter instance of converter cannot be null
   */
  public Therapist(final IConverter converter) {
    this.converter = checkNotNull(converter);
  }

  void setInputStream(final InputStream is) {
    in = is;
  }

  void setPrintStream(final PrintStream o) {
    out = o;
  }

  /**
   * Starts the interactive therapy session where numbers are encouraged to
   * communicate to the user.
   */
  public void startTherapySession() {

    out.println("Welcome to Number Therapy");
    out.println("==========================\n");
    out.println("In this session we will help the integers between \n" + Long.MIN_VALUE + " and "
        + Long.MAX_VALUE + " to communicate in english\n");
    out.println("Please note that spaces and commas are ignored in these sessions");
    out.println("You can end the session at any time by entering the safe words 'pineapple' or 'exit'\n");

    // create a scanner so we can read the command-line input
    try (final Scanner scanner = new Scanner(in)) {
      while (true) {
        try {
          out.print("Please enter an integer:");
          out.flush();
          String input = scanner.nextLine();
          if (input.equalsIgnoreCase("pineapple") || input.equalsIgnoreCase("exit")) {
            break;
          }
          input = input.replaceAll("\\s+|,", "");
          final long value = Long.valueOf(input);
          final String result = converter.toWords(value);
          out.println("Your number says: " + result);
        } catch (final NumberFormatException ex) {
          out.println("Sorry that doesn't appear to be a valid integer");
        }
      }
    }
    // get their input as a String
    out.println("Thank you for visiting Number Therapy.  See you again soon!");
  }

}
