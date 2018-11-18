package numbertherapy;

/**
 * Interface for all converters.
 *
 * @author Kyle Girard theycallmecoach@gmail.com
 *
 */
public interface IConverter {
  /**
   * Convert the given long value to a string.
   *
   * @param value a long integer
   * @return a string
   */
  String toWords(long value);
}
