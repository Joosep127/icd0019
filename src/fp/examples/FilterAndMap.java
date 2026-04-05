package fp.examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FilterAndMap {

    @Test
    public void findSquaresOfPositiveNumbers() {
        List<Integer> numbers = List.of(5, -2, 0, -3, 2);

        List<Integer> result = numbers.stream()
                .filter(x -> x >= 0)
                .map(x -> x * x)
                .toList();

        assertThat(result).isEqualTo(List.of(25, 0, 4));
    }

}


