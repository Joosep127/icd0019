package fp.examples;

import fp.Pair;
import fp.Person;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class TemporaryData {

    List<Person> employees = List.of(
            new Person(1, "Alice", 22),
            new Person(2, "Bob", 20),
            new Person(3, "Carol", 21),
            new Person(4, "David", 21));

    @Test
    public void sortingWasteful() {

        List<String> sorted = employees.stream()
                .sorted(Comparator.comparingDouble(p -> getSalary(p)))
                .map(Person::name)
                .toList();

        assertThat(sorted).containsExactly("Bob", "Alice", "David", "Carol");
    }

    @Test
    public void sortingOptimized() {

        List<String> sorted = employees.stream()
                .map(person -> new Pair<>(person, getSalary(person)))
                .sorted(Comparator.comparingDouble(pair -> pair.second()))
                .map(pair -> pair.first().name())
                .toList();

        assertThat(sorted).containsExactly("Bob", "Alice", "David", "Carol");
    }

    @Test
    public void shuffle() {
        List<Integer> integers = List.of(1, 5, 9, 2, 3);

        Random random = new Random();

        List<Integer> shuffled = integers.stream()
                .map(person -> new Pair<>(person, random.nextInt()))
                .sorted(Comparator.comparingInt(Pair::second))
                .map(Pair::first)
                .toList();

        System.out.println(shuffled);
    }


    private double getSalary(Person employee) {
        System.out.print(".");

        // simulates somewhat expensive method.
        // avoid calling it too often

        Map<Integer, Integer> map = Map.of(1, 2000, 2, 1800, 3, 2500, 4, 2100);

        return map.get(employee.id());
    }
}


