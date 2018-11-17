package numbertherapy;

import com.google.common.base.Strings;

public class EnglishConverter implements IConverter {

  private static final String[] NAMES_UNDER_TWENTY = {
    "zero",
    "one",
    "two",
    "three",
    "four",
    "five",
    "six",
    "seven",
    "eight",
    "nine",
    "ten",
    "eleven",
    "twelve",
    "thirteen",
    "fourteen",
    "fifteen",
    "sixteen",
    "seventeen",
    "eighteen",
    "nineteen" };

  private static final String[] NAMES_TENS = {
    "",
    "",
    "twenty",
    "thirty",
    "forty",
    "fifty",
    "sixty",
    "seventy",
    "eighty",
    "ninety" };

  private static final String[] NAMES_BIG = {
    "hundred",
    "thousand",
    "million",
    "billion",
    "trillion",
    "quadrillion",
    "quintillion",
    "sextillion" };

  @Override
  public String toWords(final long value) {
    if (value == 0) {
      return upperCaseFirstLetter(NAMES_UNDER_TWENTY[0]);
    }

    if (value < 100) {
      return upperCaseFirstLetter(convertUnder100(value, false));
    }

    if (value < 1000) {
      return upperCaseFirstLetter(convertUnder1000(value, true));
    }

    final String result = upperCaseFirstLetter(convertOver999(value));
    return result;
  }

  private String convertUnder100(final long val, final boolean andFlag) {
    final int intVal = (int) val;
    if (intVal < 20 && !andFlag) {
      return NAMES_UNDER_TWENTY[intVal];
    } else if (intVal < 20 && andFlag) {
      return "and " + NAMES_UNDER_TWENTY[intVal];
    }
    final int ones = intVal % 10;
    final int tens = (intVal / 10);
    final String onesStr = (ones == 0) ? "" : " " + NAMES_UNDER_TWENTY[ones];
    String result = NAMES_TENS[tens] + onesStr;
    if (andFlag) {
      result = "and " + result;
    }
    return result;
  }

  private String convertUnder1000(final long val, final boolean andFlag) {
    final int intVal = (int) val;
    if (intVal < 100) {
      return convertUnder100(val, andFlag);
    }
    final int hundred = intVal / 100;
    final int tens = intVal % 100;

    String result = NAMES_UNDER_TWENTY[hundred] + " " + NAMES_BIG[0];
    if (tens > 0 && andFlag) {
      result += " " + convertUnder100(tens, andFlag);
    } else if (tens != 0) {
      result += " " + convertUnder100(tens, andFlag);
    }
    return result;
  }

  private String convertOver999(final long value) {
    for (int i = 0; i < NAMES_BIG.length; ++i) {
      final long size = new Double(Math.pow(1000, i)).longValue();
      // loop til we find how big a number we are dealing with...
      if (size > value || size == Long.MAX_VALUE) {
        final int stdIndex = i - 1;
        final long denominator = new Double(Math.pow(1000, stdIndex)).longValue();
        final long hundreds = value / denominator;
        final long remainder = value - (denominator * hundreds);
        String result = convertUnder1000(hundreds, value < 1000);
        if (value >= 1000) {
          result += " " + NAMES_BIG[stdIndex];
        }

        if (remainder > 0) {
          result += " " + convertOver999(remainder);
        }
        return result;
      }
    }
    throw new IllegalStateException("Should never get here, bad things have happened");

  }

  /**
   * Returns a copy of the given string with the first character converted to
   * upper case.
   *
   * @param value the string to upper case
   * @return copy with upper cased first letter or the original string in case of
   *         empty or null
   */
  private String upperCaseFirstLetter(final String value) {
    if (Strings.isNullOrEmpty(value)) {
      return value;
    }
    final String firstLetter = value.substring(0, 1);
    final String result = firstLetter.toUpperCase() + value.substring(1)
                                                           .trim();
    return result;
  }

  /**
   * Entry point.
   *
   * @param args not used;
   */
  public static void main(final String[] args) {
    final IConverter therapist = new EnglishConverter();
    final String result = therapist.toWords(101);
    System.err.println(result);
  }
}
