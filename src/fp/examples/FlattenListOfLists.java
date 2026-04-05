package fp.examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FlattenListOfLists {

    @Test
    public void flatMapExample() {
        var listOfLists = List.of(
                List.of(0, 1),
                List.of(2, 3, 4),
                List.of(5));

        List<Integer> flattened = listOfLists
                .stream()
                .flatMap(eachList -> eachList.stream())
                .toList();

        assertThat(flattened).isEqualTo(List.of(0, 1, 2, 3, 4, 5));
    }
}


