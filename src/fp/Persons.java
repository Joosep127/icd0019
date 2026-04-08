package fp;

import org.junit.jupiter.api.Test;

import java.util.List;


public class Persons {

    private List<Person> persons = List.of(
            new Person(1, "Alice", 22),
            new Person(2, "Bob", 20),
            new Person(3, "Carol", 21));

    @Test
    public void findsPersonById() {
        Integer id = 1;
        System.out.println(persons.stream().filter(x -> x.id().equals(id)).toList());
    }

    @Test
    public void removePersonById() {
        Integer id = 1;
        System.out.println(persons.stream().filter(x -> x.id().equals(id)).toList());
    }

    @Test
    public void findsPersonNames() {
        String name = "Alice";
        System.out.println(persons.stream().filter(p -> p.name().equals(name)).toList());
    }

    @Test
    public void reverseSortedByAge() {
        System.out.println(persons
                .stream()
                .sorted((a,b) -> Integer.compare(a.age(), b.age()))
                .toList());
    }

}
