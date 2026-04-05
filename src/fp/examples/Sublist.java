package fp.examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Sublist {

    @Test
    public void takesSublist() {
        List<Integer> numbers = List.of(0, 1, 2, 3, 4, 5);

        List<Integer> sublist = numbers.stream()
                .skip(2)
                .limit(3)
                .toList();

        assertThat(sublist).containsExactly(2, 3, 4);
    }
}


