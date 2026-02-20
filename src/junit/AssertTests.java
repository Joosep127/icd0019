package junit;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import static junit.Code.mode;
import static junit.Code.longestStreak;
import static junit.Code.removeDuplicates;

public class AssertTests {

    @Test
    public void testsMode() {
        String[] input = {null, "", "abcb", "cbbc"};
        Character[] expected = {null, null, 'b', 'c'};
        for (int i = 0; i < input.length; i++) {
            assertEquals(expected[i], mode(input[i]));
        }
    }

    @Test
    public void testsLongestStreak() {
        String[] input = {"", "a", "abbcccaaaad", "abbcccaaaa"};
        int[] expected = {0, 1, 4, 4};
        for (int i = 0; i < input.length; i++) {
            assertEquals(expected[i], longestStreak(input[i]));
        }
    }

    @Test
    public void testsRemoveDuplicates() {
        int[][] input = {{}, {1, 2, 1, 3, 2}};
        int[][] expected = {{}, {1,2,3}};
        for (int i = 0; i < input.length; i++) {
            assertArrayEquals(expected[i], removeDuplicates(input[i]));
        }
    }

    @Test
    public void testsAssertEqualsOrder() {
        assertEquals(sum(1, 2), 4);
    }

    @Test
    public void testsAssertThatOrder() {
        assertThat(4).isEqualTo(sum(1, 2));
    }

    @Test
    public void testsAssertEqualsArray() {
        int[] a = {1, 2};
        int[] b = {1, 2};

        assertEquals(a, b);
    }

    @Test
    public void testsAssertThatArray() {
        int[] a = {1, 2};
        int[] b = {1, 2};

        assertThat(a).isEqualTo(b);
    }

    @Test
    public void testsAssertThatArrayWithNot() {
        int[] a = {1, 2};
        int[] b = {1, 3};

        assertThat(a).isNotEqualTo(b);
    }

    @Test
    public void equalityAndToStringAreDifferentThings() {
        Point a = new Point(1, 2);
        Point b = new Point(1, 2);

        assertThat(a).isEqualTo(a);

        // possibly unexpected result and error message
        assertThat(a).isEqualTo(b);
    }

    private int sum(int a, int b) {
        return a + b;
    }

}
