package numbertherapy;

import com.google.common.base.Strings;

/**
 * Implements the IConverter interface by converting the given long into it's
 * English language equivalent.
 * <p/>
 * The conversion by breaking up the long into <code>MAX_GROUPS</code> groups
 * each representing a 'thousand' or three digits of the number. These groups
 * are converted individually into English and then all groups are combined into
 * the final result by placing the appropriate to the groups original location
 * within the given value number between the groups.
 *
 *
 * @author Kyle Girard theycallmecoach@gmail.com
 *
 */
public class EnglishConverter implements IConverter {

  /** Maximum number of thousands groups for longs. */
  public static final int MAX_GROUPS = 7;

  private static final String WORD_NEGATIVE = "negative";
  private static final String WORD_AND = "and";

  /* English names for numbers under twenty */
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

  /*
   * English names for number multiples of 10 under 100. The empty entries
   * represent the values already present in the NAMES_UNDER_TWENTY and allow for
   * easier code.
   */
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

  /*
   * English names for the big numbers. Highest is quintillion since that is the
   * highest number for a long.
   */
  private static final String[] NAMES_BIG = {
    "hundred",
    "thousand",
    "million",
    "billion",
    "trillion",
    "quadrillion",
    "quintillion" };

  /**
   * Converts the given long to words in English.
   *
   * @see numbertherapy.IConverter#toWords(long)
   */
  @Override
  public String toWords(final long value) {
    if (value == 0) {
      return upperCaseFirstLetter(NAMES_UNDER_TWENTY[0]);
    }

    // guard against overflow
    final long posValue = lowerBoundsCheck(value);

    // split up the big number by 1000 groups
    final int[] groups = splitIntoGroups(posValue);

    // Adjust if we are converting the lower bound
    if (value == Long.MIN_VALUE) {
      groups[0]++;
    }

    // check if we are to insert 'and'
    boolean andFlag = false;
    if (posValue > 100 && (groups[0] % 100) != 0) {
      andFlag = true;
    }

    // convert each 1000's group to words
    final String[] groupString = convertGroupsToStrings(groups, andFlag);

    // Add in the big names between groups
    String result = combineGroupStrings(groups, groupString);

    // handle negative
    if (value < 0) {
      result = WORD_NEGATIVE + " " + result;
    }

    // pretty print
    result = upperCaseFirstLetter(result);
    return result;
  }

  /*
   * Combine group strings with the appropriate NAMES_BIG name depending
   * on the groups location.
   */
  private String combineGroupStrings(final int[] groups, final String[] groupString) {
    String result = groupString[0];
    for (int i = 1; i < MAX_GROUPS; ++i) {
      if (groups[i] == 0) {
        continue;
      }
      final String newStr = groupString[i] + " " + NAMES_BIG[i];
      result = newStr + " " + result;
    }
    return result;
  }

  /*
   * If our value is the lower bound we have to adjust it.
   */
  private long lowerBoundsCheck(final long value) {
    return Math.abs((value == Long.MIN_VALUE) ? value + 1 : value);
  }

  /*
   * Divide the given value into groups of a thousand
   */
  private int[] splitIntoGroups(final long value) {
    final int[] groups = new int[MAX_GROUPS];
    long curValue = value;
    for (int i = 0; i < MAX_GROUPS; ++i) {
      groups[i] = (int) (curValue % 1000);
      curValue /= 1000;
    }
    return groups;
  }

  /*
   * Converts each group to a string, andFlag only used in the first/smallest
   * group.
   */
  private String[] convertGroupsToStrings(final int[] groups, boolean andFlag) {
    final String[] groupString = new String[MAX_GROUPS];
    for (int i = 0; i < MAX_GROUPS; ++i) {
      groupString[i] = convertHundredsGroup(groups[i], andFlag);
      andFlag = false;
    }
    return groupString;
  }

  /*
   * Convert value which can at most be +/- 999 to English taking into account
   * when to add 'and'
   */
  private String convertHundredsGroup(final int value, final boolean andFlag) {
    final int hundreds = value / 100;
    final int tens = value % 100;

    String result = "";
    if (hundreds != 0) {
      result = NAMES_UNDER_TWENTY[hundreds] + " " + NAMES_BIG[0];
      if (andFlag) {
        result += " " + WORD_AND + " ";
      } else if (tens != 0) {
        result += " ";
      }
    }
    if (andFlag && value < 100) {
      result += WORD_AND + " ";
    }

    result += convertTensGroup(tens);
    return result;
  }

  /*
   * Convert value which is at most 99 to English
   */
  private String convertTensGroup(final int value) {
    final int tens = value / 10;
    final int ones = value % 10;

    String result = "";
    if (tens >= 2) {
      result += NAMES_TENS[tens];
      if (ones != 0) {
        result += " " + NAMES_UNDER_TWENTY[ones];
      }
    } else if (value != 0) {
      result = NAMES_UNDER_TWENTY[value];
    }

    return result;
  }

  /*
   * Returns a copy of the given string with the first character converted to
   * upper case.
   *
   * @param value the string to upper case
   *
   * @return copy with upper cased first letter or the original string in case of
   * empty or null
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

}
