package generics.recursion;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RecursionTests {

    @Test
    public void pathElementsAccessExample() {

        Path path = Paths.get("a/b/c/d.txt");

        assertThat(path.getFileName().toString()).isEqualTo("d.txt");

        assertThat(path
                .getParent()
                .getFileName().toString()).isEqualTo("c");

        assertThat(path
                .getParent()
                .getParent()
                .getFileName().toString()).isEqualTo("b");

        // etc...
    }

    @Test
    public void getElementsUsingIteration() {

        Path path = Paths.get("a/b/c/d.txt");

        var elements = new Recursion().getParts(path);

        System.out.println(elements);
    }

    @Test
    public void printElementsUsingRecursion() {

        Path path = Paths.get("a/b/c/d.txt");

        new Recursion().getParts2(path);
    }

    @Test
    public void getElementsUsingRecursionAndCollectorList() {

        Path path = Paths.get("a/b/c/d.txt");

        List<String> parts = new ArrayList<>();

        new Recursion().getParts3(path, parts);

        System.out.println(parts);
    }

    @Test
    public void getElementsUsingRecursion() {

        Path path = Paths.get("a/b/c/d.txt");

        var parts = new Recursion().getParts4(path);

        System.out.println(parts);
    }
}
