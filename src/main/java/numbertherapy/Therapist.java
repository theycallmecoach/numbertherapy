package numbertherapy;

import java.util.Scanner;

public class Therapist {

  private final IConverter converter;

  public Therapist(final IConverter converter) {
    this.converter = converter;
  }

  public void startTherapySession() {

    System.out.println("Welcome to Number Therapy");
    System.out.println("=============================\n");
    System.out.println("In this session we will help the integers between \n" + Long.MIN_VALUE + " and "
        + Long.MAX_VALUE + " to communicate in english\n");
    System.out.println("Please note that spaces and commas are ignored in these sessions");
    System.out.println("You can end the session at any time by entering the safe word 'pineapple'\n");

    // create a scanner so we can read the command-line input
    try (final Scanner scanner = new Scanner(System.in)) {
      while (true) {
        try {
          System.out.print("Please enter an integer:");
          String input = scanner.nextLine();
          if (input.equalsIgnoreCase("pineapple") || input.equalsIgnoreCase("exit")) {
            break;
          }
          input = input.replaceAll("\\s+|,", "");
          final long value = Long.valueOf(input);
          final String result = converter.toWords(value);
          System.out.println("Your number says: " + result);
        } catch (final NumberFormatException ex) {
          System.out.println("Sorry that doesn't appear to be a valid integer");
        }
      }
    }
    // get their input as a String
    System.out.println("Thank you for visiting Number Therapy.  See you again soon!");

  }

}
