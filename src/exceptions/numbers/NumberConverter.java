package exceptions.numbers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class NumberConverter {
  List<String> expected;
  Properties properties;
  String lang;
  boolean skipFuture;
  boolean addingAfterDelim;

  public NumberConverter(String lang) {
    this.lang = lang;
    try {
      properties = getProperties();
    } catch (IOException e) {
      throw new MissingLanguageFileException(lang, e);
    } catch (IllegalArgumentException e) {
      throw new BrokenLanguageFileException(lang, e);
    }
    try {
      expected = getExpected();
    } catch (IOException e) {
      expected = List.of();
      System.err.println("Warning: expected file missing for " + lang);
    }
  }

  private List<String> getExpected() throws IOException {
    String filePath = String.format("src/exceptions/numbers/expected-%s.txt", lang);
    return Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
  }

  private Properties getProperties() throws IllegalArgumentException, IOException {
    String filePath = String.format("src/exceptions/numbers/numbers_%s.properties", lang);

    Properties properties = new Properties();
    FileInputStream is;

    is = new FileInputStream(filePath);

    InputStreamReader reader = new InputStreamReader(
        is, StandardCharsets.UTF_8);

    properties.load(reader);

    close(is);

    return properties;
  }

  private static void close(FileInputStream is) {
    if (is == null) {
      return;
    }

    try {
      is.close();
    } catch (IOException ignored) {
    }
  }

  private String getNthLine(int n) {
    return expected.get(n);
  }

  private String getProp(String s) {
    return properties.getProperty(s);
  }

  private String getProp(String s, String def) {
    return properties.getProperty(s, def);
  }

  public int[] reduceNumber(int number) {
    int digits = String.valueOf(number).length();
    int[] output = new int[digits];

    int place = 1;
    int index = 0;

    while (place <= number) {
      int reduced = (number / place) * place;
      output[index++] = reduced;
      place *= 10;
    }

    return output;
  }

  private int[] checkExpectation(int number) {
    if (number == 1) {
      return new int[] {};
    }
    int original = number;

    int divisor = 10;
    int num = number;

    while (num > 0) {
      String temp = getProp(String.valueOf(num));

      if (temp != null && !temp.equals(String.valueOf(num))) {
        return new int[] {num, original - num};
      }

      int truncated = (original / divisor) * divisor;

      if (truncated == num) {
        divisor *= 10;
        continue;
      }

      num = truncated;
      divisor *= 10;
    }
    return new int[] {};
  }

  private String str(int num) {
    return String.valueOf(num);
  }

  private String applyPrefixSuffixAndDelimiters(String[] sufpre, String[] deLims, int number) {
    if (skipFuture) { // We know for a fact that we're on the teens here
      return deLims[0] + getProp(str(number)) + sufpre[1];
    }

    return sufpre[0] + getProp(str(number)) + deLims[0] + sufpre[1] + deLims[1];
  }

  private String applyNumberRules(int key, String value, int number) {
    number = number - (number / (key*10)) * key * 10; // Gets rid of already checked numbers of numbers that are beyond the range
                                             // of which I'm expected to turn in to strings

    if (skipFuture || !addingAfterDelim) {
      return "";
    }
    if (number == 0) {
      skipFuture = true;
    }

    int digit = number / key;
    if (digit == 0) {
      return "";
    }
    addingAfterDelim = !(number == digit * key);

    if (key == 10 && digit == 1 && addingAfterDelim && getProp("teen") != null) {
      skipFuture = true;
      value = "teen";
    }

    String beforeDelim = getProp(value + "-before-delimiter", "");
    String afterDelim = addingAfterDelim ? getProp(value + "-after-delimiter", "") : "";
    String fullWord = getProp(value);
    String prefix = fullWord != null ? "" : getProp(value + "-prefix", "");
    String suffix = fullWord != null ? fullWord : getProp(value + "-suffix", "");

    String[] deLims = { beforeDelim, afterDelim };
    String[] sufpre = { prefix, suffix };

    int[] exception = checkExpectation(number);

    if (exception.length == 0) {
      return applyPrefixSuffixAndDelimiters(sufpre, deLims, digit);
    }
    sufpre[1] = "";
    number = exception[1];
    return applyPrefixSuffixAndDelimiters(sufpre, deLims, exception[0]);
  }

  public String numberInWords(int number) {
    if (properties.isEmpty()) {
      throw new MissingTranslationException(lang);
    }
    if (-1 < number && number < 131) {
      return getNthLine(number);
    }

    if (number == 0) {
      return getProp("0");
    }

    addingAfterDelim = true;
    skipFuture = false;

    StringBuilder sb = new StringBuilder();
    Map<Integer, String> values = new LinkedHashMap<>();
    values.put(1000000000, "billion");
    values.put(1000000, "million");
    values.put(1000, "thousand");
    values.put(100, "hundred");
    values.put(10, "tens");
    values.put(1, "ones");
    values.forEach((key, value) -> sb.append(applyNumberRules(key, value, number)));

    return sb.toString();
  }
}
