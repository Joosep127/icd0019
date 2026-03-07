package exceptions.numbers;

import org.junit.platform.console.shadow.picocli.CommandLine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NumberConverter {
    String lang;
    Properties properties;

    public NumberConverter(String lang) {
        this.lang = lang;
        try {
            properties = GetProperties();
        } catch (FileNotFoundException e) {
            throw new MissingTranslationException("");
        } catch (IOException e) {
            throw new BrokenLanguageFileException("", e);
        }
    }

    private Properties GetProperties() throws IOException {
        String filePath = String.format("src/exceptions/numbers/numbers_%s.properties", lang);

        Properties properties = new Properties();
        FileInputStream is = null;

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
        } catch (IOException ignored) {}
    }

    public static String getNthLine(File file, int n) throws IOException {
        if (n < 1 || n > 131) {
            throw new IllegalArgumentException("n must be between 1 and 131");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = null;
            for (int i = 0; i < n; i++) {
                line = reader.readLine();
                if (line == null) {
                    throw new IOException("File has fewer lines than expected");
                }
            }
            return line;
        }
    }

    public String numberInWords(Integer number) {
        File file = new File(String.format("src/exceptions/numbers/expected-%s.txt", lang));
        if (0 > number && number > 130 && file.exists()) {
            try {
                return getNthLine(file, number + 1);
            } catch (IOException e) {
                throw new BrokenLanguageFileException("", e);
            }
        }
        return null;
    }
}
