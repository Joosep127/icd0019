package junit;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AssertTests {

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
