package exceptions.basic;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class MinimumElementTests {

    @Test
    public void findsMinimumFromArrayOfNumbers() {

        assertThat(Code.minimumElement(new int[] { 1, 3 })).isEqualTo(1);

        assertThat(Code.minimumElement(new int[] { 1, 0 })).isEqualTo(0);
    }

    @Test
    public void testMinimumExceptions() {
        try {
            Code.minimumElement(new int[] {});
            fail("Expected IllegalArgumentException for empty array");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            Code.minimumElement(null);
            fail("Expected IllegalArgumentException for null array");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
}
