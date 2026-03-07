package exceptions.basic;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Code {

    public String readDataFrom(FakeFile file) {
        file.open();
        String text;
        try {
          text = file.read();
        } catch (Exception IOException) {
            file.close();
            return "some default value";
        }
        file.close();
        return text;
    }

    public static Integer minimumElement(int[] integers) {
        if (integers == null || integers.length == 0) {
            throw new IllegalArgumentException();
        }

        int minimumElement = integers[0];

        for (int current : integers) {
            if (current < minimumElement) {
                minimumElement = current;
            }
        }

        return minimumElement;
    }

    public static boolean containsSingleLetters(String s) {

        if (s == null || s.equals("")) {
            return false;
        }
        int index = 0;

        try {
            while (index < s.length()) {
                if (s.charAt(index) == s.charAt(index + 1)) {
                    return false;
                }

                index++;
            }
        } catch (Exception e) {
            return true;
        }

        return true;
    }
}
