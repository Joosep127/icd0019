package fp.examples;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Grouping {

    @Test
    public void frequencies() {

        var result = Arrays.stream("A2J2J".split(""))
                .collect(Collectors.groupingBy(
                        rank -> rank,
                        Collectors.summingInt(rank -> 1)
                )).values()
                .stream()
                .sorted(Comparator.reverseOrder())
                .toList();

        System.out.println(result); // [2, 2, 1]

    }

    @Test
    public void groupSimilarItems() {
        var result = Arrays.stream("A9J9J".split(""))
                .collect(Collectors.<String, String, List<String>>toMap(
                        rank -> rank,
                        List::of,
                        (a, b) -> Stream.concat(a.stream(), b.stream()).toList()))
                .values();

        System.out.println(result); // [[A], [9, 9], [J, J]]
    }

    @Test
    public void groupToLists() {
        List<String> elements = List.of("ABCDEFGHIJK".split(""));

        var result = IntStream.range(0, elements.size())
                .filter(idx -> idx % 3 == 0)
                .mapToObj(idx -> elements.subList(idx, Math.min(idx + 3, elements.size())))
                .toList();

        System.out.println(result); // [[A, B, C], [D, E, F], [G, H, I], [J, K]]
    }

}


