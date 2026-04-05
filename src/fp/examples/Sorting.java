package fp.examples;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Sorting {

    List<SalesRecord> records = List.of(
            new SalesRecord("p2", 10),
            new SalesRecord("p1", 30),
            new SalesRecord("p3", 20));

    @Test
    public void sortingByNaturalOrder() {
        List<Integer> numbers = List.of(5, 2, 0, 3);

        List<Integer> sorted = numbers.stream()
                .sorted() // natural order
                .toList();

        assertThat(sorted).isEqualTo(List.of(0, 2, 3, 5));
    }

    @Test
    public void sortingByCustomCriteria() {
        List<String> sorted = records.stream()
                .sorted((a, b) -> a.itemsSold() - b.itemsSold())
                .map(Object::toString)
                .toList();

        assertThat(sorted).containsExactly("(p2; 10)", "(p3; 20)", "(p1; 30)");

        sorted = records.stream()
                .sorted(Comparator.comparing(each -> each.itemsSold()))
                .map(Object::toString)
                .toList();

        // Comparator.comparing(<function that extracts sorting key>)

        assertThat(sorted).containsExactly("(p2; 10)", "(p3; 20)", "(p1; 30)");

        sorted = records.stream()
                .sorted(Comparator.comparing(each -> each.productId()))
                .map(Object::toString)
                .toList();

        assertThat(sorted).containsExactly("(p1; 30)", "(p2; 10)", "(p3; 20)");
    }

    @Test
    public void sortingByCustomCriteriaWithProvidedUtilities() {
        List<String> numbers = List.of("123", "31", "23");

        List<String> sorted = numbers.stream()
                .sorted(Collections.reverseOrder(
                            Comparator.comparing(each -> each.charAt(0))))
                .toList();

        // wrapping Comparator with Collections.reverseOrder reverses the order

        assertThat(sorted).containsExactly("31", "23", "123");
    }

}


