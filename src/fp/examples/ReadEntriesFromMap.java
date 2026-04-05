package fp.examples;

import org.junit.jupiter.api.Test;

import java.util.Map;

public class ReadEntriesFromMap {

    @Test
    public void entryExample() {
        var map = Map.of("p1", 30,
                         "p2", 20);

        map.entrySet().stream()
                .map(e -> String.format("%s -> %s", e.getKey(), e.getValue()))
                .forEach(System.out::println);
    }


    // Not used here. Just for illustration.
    // Map.entrySet() produces a list with similar items.

    private class MyEntry {
        // getKey()
        // getValue()
        // equals()
        // ...
    }
}


