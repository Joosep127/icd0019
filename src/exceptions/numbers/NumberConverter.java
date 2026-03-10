package exceptions.numbers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NumberConverter {
  String[] strValues = {"billion", "million", "thousand", "hundred", "tens", "ones"};
  int[] intValues = {1000000000, 1000000, 1000, 100, 10, 1};

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

  private Properties getProperties() throws IOException {
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
      int reduced = number / place * place;
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
        return new int[] { num, original - num };
      }

      int truncated = original / divisor * divisor;

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

  private String applyPrefixSuffixAndDelimiters(String[] sufpre, String[] deLims, String number) {
    if (skipFuture) { // We know for a fact that we're on the teens here
      return deLims[0] + number + sufpre[1];
    }

    return sufpre[0] + number + deLims[0] + sufpre[1] + deLims[1];
  }

  private String applyNumberRules(int idx, int number) {
    int key = intValues[idx];
    String value = strValues[idx];

    int previous = idx == 0 ? 0 : intValues[idx-1];
    if (idx != 0) {
      number -= number / previous * previous; // Gets rid of already checked numbers of numbers that are beyond
      // the range
      // of which I'm expected to turn in to strings
    }

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
    addingAfterDelim = number != digit * key;

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
      if (digit > 10) {
        return applyPrefixSuffixAndDelimiters(sufpre, deLims, numberInWords(digit));
      }
      return applyPrefixSuffixAndDelimiters(sufpre, deLims, getProp(str(digit)));
    }
    sufpre[1] = "";
    return applyPrefixSuffixAndDelimiters(sufpre, deLims, getProp(str(exception[0])));
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

    for (int i = 0; i < intValues.length; i++) {
      sb.append(applyNumberRules(i, number));
    }

    return sb.toString();
  }
}
