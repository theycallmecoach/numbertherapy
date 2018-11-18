package numbertherapy;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Scanner;

/**
 * Represents the therapist who helps facilitate numbers ability to communicate
 * using a converter.
 *
 * @author Kyle Girard theycallmecoach@gmail.com
 *
 */
public class Therapist {

  private final IConverter converter;

  /**
   * Creates a therapist that will use the given converter within therapy sessions
   * to facilitate communication with numbers.
   *
   * @param converter instance of converter cannot be null
   */
  public Therapist(final IConverter converter) {
    this.converter = checkNotNull(converter);
  }

  /**
   * Starts the interactive therapy session where numbers are encouraged to
   * communicate to the user.
   */
  public void startTherapySession() {

    System.out.println("Welcome to Number Therapy");
    System.out.println("==========================\n");
    System.out.println("In this session we will help the integers between \n" + Long.MIN_VALUE + " and "
        + Long.MAX_VALUE + " to communicate in english\n");
    System.out.println("Please note that spaces and commas are ignored in these sessions");
    System.out.println("You can end the session at any time by entering the safe words 'pineapple' or 'exit'\n");

    // create a scanner so we can read the command-line input
    try (final Scanner scanner = new Scanner(System.in, "UTF-8")) {
      while (true) {
        try {
          System.out.print("Please enter an integer: ");
          System.out.flush();
          String input = scanner.nextLine();
          if (input.equalsIgnoreCase("pineapple") || input.equalsIgnoreCase("exit")) {
            break;
          }
          input = input.replaceAll("\\s+|,", "");
          final long value = Long.parseLong(input);
          final String result = converter.toWords(value);
          System.out.println("Your number says: " + result);
        } catch (final NumberFormatException ex) {
          System.out.println("Sorry that doesn't appear to be a valid integer");
        }
      }
    }
    System.out.println("Thank you for visiting Number Therapy.  See you again soon!");
  }

}
