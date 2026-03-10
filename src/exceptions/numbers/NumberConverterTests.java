package exceptions.numbers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberConverterTests {

  @Test
  public void selectingMissingLanguageThrows() {
    assertThrows(MissingLanguageFileException.class,
        () -> new NumberConverter("ru"));
  }

  @Test
  public void selectingBrokenLanguageFileThrows() {
    assertThrows(BrokenLanguageFileException.class,
        () -> new NumberConverter("fr"));
  }

  @Test
  public void missingEssentialTranslationThrows() {
    assertThrows(MissingTranslationException.class,
        () -> new NumberConverter("es").numberInWords(1));

    assertThrows(MissingTranslationException.class,
        () -> new NumberConverter("es").numberInWords(2));

    assertThrows(MissingTranslationException.class,
        () -> new NumberConverter("es").numberInWords(3));
  }

  @Test
  public void canConvertNumbersToEnglish() {
    NumberConverter converter = new NumberConverter("et");

    assertThat(converter.numberInWords(654000)).isEqualTo("kuussada viiskümmend neli tuhat");
  }

  @Test
  public void canConvertNumbersUpTo130ToEnglish() {
    assertCanConvertNumbersUpTo(130, "en");
  }

  @Test
  public void canConvertNumbersUpTo130ToEstonian() {
    assertCanConvertNumbersUpTo(130, "et");
  }

  @Test
  public void canConvertNumbersUpTo130ToCustomLanguage() {
    assertCanConvertNumbersUpTo(130, "cu");
  }

  private void assertCanConvertNumbersUpTo(int upperBound, String lang) {
    for (int i = 0; i <= upperBound; i++) {
      String numberInWords = new NumberConverter(lang).numberInWords(i);

      assertThat(numberInWords).isEqualTo(getExpected(lang, i));
    }
  }

  private String getExpected(String lang, int index) {
    List<String> strings = map.get(lang);

    if (strings == null) {
      throw new RuntimeException("unexpected language: " + lang);
    }

    if (index < 0 || index >= strings.size()) {
      throw new RuntimeException("no test data for number: " + index);
    }

    return strings.get(index);
  }

  private final Map<String, List<String>> map = new HashMap<>();

  @BeforeEach
  public void setUp() throws IOException {
    String template = "src/exceptions/numbers/expected-%s.txt";

    map.put("en", getAllLines(template, "en"));
    map.put("et", getAllLines(template, "et"));
    map.put("cu", getAllLines(template, "cu"));
  }

  private static List<String> getAllLines(String template, String lang) throws IOException {
    return Files.readAllLines(Paths.get(String.format(template, lang)),
        StandardCharsets.UTF_8);
  }
}
