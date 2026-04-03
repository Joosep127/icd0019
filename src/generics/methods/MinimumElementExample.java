package generics.methods;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MinimumElementExample {

    @Test
    public void findsMinimumElementFromList() {
        assertThat(minimumElement(Arrays.asList(1, 2, -5))).isEqualTo(-5);

        assertThat(minimumElementForStrings(Arrays.asList("b", "a", "c"))).isEqualTo("a");
    }

    public <T extends Comparable<T>> T minimumElement(List<T> input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("should contain at least one element");
        }

        T minimumElement = input.getFirst();

        for (T current : input) {
            if (current.compareTo(minimumElement) < 0) {
                minimumElement = current;
            }
        }

        return minimumElement;
    }

    public String minimumElementForStrings(List<String> input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("should contain at least one element");
        }

        String minimumElement = input.get(0);

        for (String current : input) {
            if (current.compareTo(minimumElement) < 0) {
                minimumElement = current;
            }
        }

        return minimumElement;
    }
}
