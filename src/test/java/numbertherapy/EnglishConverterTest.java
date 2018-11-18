package numbertherapy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("Test conversion of long integers to english words")
class EnglishConverterTest {
  private EnglishConverter converter;

  @BeforeEach
  void setup() {
    converter = new EnglishConverter();
  }

  @DisplayName("Should return english for all supplied numbers")
  @ParameterizedTest(name = "''{0}'' => ''{1}''")
  @CsvFileSource(resources = "/en_conversion.csv")
  void whenGivenNumberReturnWord(final long val, final String expectedResult) {
    assertEquals(expectedResult, converter.toWords(val));
  }

}
