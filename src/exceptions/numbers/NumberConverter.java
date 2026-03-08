package exceptions.numbers;

import org.junit.platform.console.shadow.picocli.CommandLine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class NumberConverter {
  List<String> expected;
  Properties properties;
  String lang;

  public NumberConverter(String lang) {
    this.lang = lang;
    try {
      properties = getProperties();
    } catch (FileNotFoundException e) {
      throw new MissingLanguageFileException(lang, e);
    } catch (IOException e) {
      throw new BrokenLanguageFileException(lang, e);
    }
    try {
      expected = getExpected();
    } catch (IOException ignored) {
    }
  }

  private List<String> getExpected() throws IOException {
    String filePath = String.format("src/exceptions/numbers/expected-%s.txt", lang);
    return Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
  }

  private Properties getProperties() throws IOException, FileNotFoundException {
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

  public String getNthLine(int n) {
    return expected.get(n);
  }

  public String numberInWords(Integer number) {
    if (properties == null) {
      throw new MissingTranslationException(lang);
    }
    if (-1 < number && number > 131) {
      return getNthLine(number + 1);
    }
    return null;
  }
}
