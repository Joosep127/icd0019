package fp.examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Sum {

    @Test
    public void calculatesSum() {
        List<Integer> numbers = List.of(1, 2, 3, 4);

        double sum = numbers.stream()
                .mapToInt(each -> each)
                .sum();

        assertThat(sum).isEqualTo(10);
    }
}


