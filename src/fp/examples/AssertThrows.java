package fp.examples;

import org.junit.jupiter.api.Test;

public class AssertThrows {

    @Test
    public void testForErrorConditions() {
        // allows testing multiple failure conditions.

        myAssertThrows(() -> first(new int[0]), IllegalArgumentException.class);

        myAssertThrows(() -> first(null), IllegalArgumentException.class);
    }

    private void myAssertThrows(Runnable code, Class<? extends Exception> expected) {
        try {
            code.run();
        } catch (Exception actual) {
            if (actual.getClass() != expected) {
                throw new AssertionError("Unexpected exception: " + actual);
            }

            return;
        }

        throw new AssertionError("Should throw: " + expected);
    }

    private int first(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException();
        }
        return numbers[0];
    }

}
