package junit;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class MainTests {

    @Test
    public void findsSpecialNumbers() {
        assertTrue(Code.isSpecial(0));

        assertTrue(Code.isSpecial(0));
        assertTrue(Code.isSpecial(1));
        assertTrue(Code.isSpecial(2));
        assertTrue(Code.isSpecial(3));
        assertFalse(Code.isSpecial(4));

        assertTrue(Code.isSpecial(11));
        assertFalse(Code.isSpecial(15));

        assertTrue(Code.isSpecial(36));
        assertFalse(Code.isSpecial(37));
    }

    @Test
    public void findsLongestStreak() {
        assertThat(Code.longestStreak("")).isEqualTo(0);

        assertThat(Code.longestStreak("a")).isEqualTo(1);

        assertThat(Code.longestStreak("abc")).isEqualTo(1);

        assertThat(Code.longestStreak("abbb")).isEqualTo(3);

        assertThat(Code.longestStreak("abbcccaaaad")).isEqualTo(4);
    }

    @Test
    public void findsModeFromCharactersInString() {

        assertThat(Code.mode(null)).isNull();

        // other test cases for mode() method
    }

    @Test
    public void removesDuplicates() {
        assertThat(Code.removeDuplicates(arrayOf(1, 1))).isEqualTo(arrayOf(1));

        assertThat(Code.removeDuplicates(arrayOf(1, 2, 1, 3, 2))).isEqualTo(arrayOf(1, 2, 3));

        assertThat(Code.removeDuplicates(arrayOf(1, 2, 3))).isEqualTo(arrayOf(1, 2, 3));

        assertThat(Code.removeDuplicates(arrayOf(100, 0, 3, 100, 0, 4, 562, 4)))
                .isEqualTo(arrayOf(100, 0, 3, 4, 562));
    }

    @Test
    public void sumsIgnoringDuplicates() {
        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 1))).isEqualTo(1);

        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 2, 1, 3, 2))).isEqualTo(6);

        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 2, 3))).isEqualTo(6);
    }

    private int[] arrayOf(int... numbers) {
        return numbers;
    }
}
