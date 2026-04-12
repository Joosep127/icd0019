package poly;

import generics.converter.Converter;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.within;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class AverageTest {

    @Test
    public void computesAverageFromListOfNumbers() {

        Double result = getAverage(List.of(3.9, 4, 1.2, 0, 9, 2));

        assertThat(result).isCloseTo(3.35, within(0.01));
    }

    public Double getAverage(List<? extends Number> numbers) {
        return numbers.stream()
                .mapToDouble(Number::doubleValue)
                .average()
                .orElse(0.0);
    }

}
