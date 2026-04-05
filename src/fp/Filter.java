package fp;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class Filter {

    @Test
    public void findsElementsThatMatchCondition() {

        var numbers = List.of(1, 2, 3, 4);

        assertThat(filter(numbers, x -> x > 2)).containsExactly(3, 4);

        assertThat(filter(numbers, x -> x % 2 == 0)).containsExactly(2, 4);
    }

    private List<Integer> filter(List<Integer> list, Predicate<Integer> predicate) {
        ArrayList<Integer> output = new ArrayList<Integer>();
        for (Integer i : list) {
            if (predicate.test(i)) {
                output.add(i);
            }
        }
        return output;
    }
}
