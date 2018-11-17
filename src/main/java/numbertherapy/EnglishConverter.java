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
      return upperCaseFirstLetter(convertUnder100(value));
    }

    if (value < 1000) {
      return upperCaseFirstLetter(convertUnder1000(value));
    }


    String result = "";
    result = upperCaseFirstLetter(result);

    return result;
  }

  private String convertUnder100(final long val) {
    final int intVal = (int) val;
    if (intVal < 20) {
      return NAMES_UNDER_TWENTY[intVal];
    }
    final int ones = intVal % 10;
    final int tens = (intVal / 10);
    final String onesStr = (ones == 0) ? "" : " " + NAMES_UNDER_TWENTY[ones];
    final String result = NAMES_TENS[tens] + onesStr;
    return result;
  }

  private String convertUnder1000(final long val) {
    final int intVal = (int) val;
    if (intVal < 100) {
      return convertUnder100(val);
    }
    final int hundred = intVal / 100;
    final int tens = intVal % 100;

    String result = NAMES_UNDER_TWENTY[hundred] + " " + NAMES_BIG[0];
    if (tens > 0) {
      result += " and "  + convertUnder100(tens);
    } else if (tens != 0) {
      result += " " + convertUnder100(tens);
    }
    return result;
  }


  /**
   * Returns a copy of the given string with the first character converted to upper case.
   *
   * @param value the string to upper case
   * @return copy with upper cased first letter or the original string in case of empty or null
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

  public static void main(final String[] args) {
    final IConverter therapist = new EnglishConverter();
    final String result = therapist.toWords(10000001);
  }
}
