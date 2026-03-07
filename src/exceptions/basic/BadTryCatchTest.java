package exceptions.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BadTryCatchTest {

    @Test
    public void findsStringsWithNoDuplicateLetters() {

        assertTrue(Code.containsSingleLetters("abc"));

        assertFalse(Code.containsSingleLetters("aabc"));

        assertTrue(Code.containsSingleLetters("aba"));

        assertFalse(Code.containsSingleLetters(null));

        assertFalse(Code.containsSingleLetters(""));
    }

}
