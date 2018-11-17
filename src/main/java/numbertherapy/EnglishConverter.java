package numbertherapy;

public class EnglishConverter implements IConverter {

  @Override
  public String toWords(final long value) {
    if (value == 0) {
      return "Zero";
    }
    return null;
  }

}
