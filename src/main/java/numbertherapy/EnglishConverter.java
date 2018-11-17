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
    "twenty",
    "thirty",
    "forty",
    "fifty",
    "sixty",
    "seventy",
    "eighty",
    "ninety" };


  private static final String[] NAMES_BIG = {
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
