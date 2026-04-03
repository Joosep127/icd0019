package bonus.connection;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class ConnectionTests {

    @Test
    public void findsConnectionToDestination() {

        var finder = new ConnectionFinder();

        finder.add(new Connection("a", "b"));

        assertThat(finder.hasConnection("a", "b")).isTrue();
        assertThat(finder.hasConnection("b", "a")).isTrue();

        finder.add(new Connection("b", "c"));

        assertThat(finder.hasConnection("a", "c")).isTrue();
        assertThat(finder.hasConnection("c", "a")).isTrue();

        finder.add(new Connection("e", "f"));

        assertThat(finder.hasConnection("a", "f")).isFalse();
    }

    @Test
    public void findsConnectionToDestinationInLargeDataset() {
        var generator = new TestDataGenerator();

        var connections = generator.getConnections();

        var finder = new ConnectionFinder();
        finder.addAll(connections);

        assertThat(finder.hasConnection("E00001", "E33522")).isTrue();

        assertValidConnection("E00001", "E33522",
                finder.findConnection("E00001", "E33522"), connections);

        assertThat(finder.hasConnection("E00001", "E33601")).isFalse();


        assertThat(finder.hasConnection("E00001", "E59799")).isTrue();

        assertValidConnection("E00001", "E59799",
                finder.findConnection("E00001", "E59799"), connections);

        assertThat(finder.hasConnection("E00001", "E59801")).isFalse();
    }

    private void assertValidConnection(
            String from, String to,
            List<String> path, List<Connection> connections) {

        if (path.size() < 2) {
            throw new IllegalArgumentException("path should be at least 2 elements");
        }

        Iterator<String> iterator = path.iterator();

        String current = iterator.next();

        if (!current.equals(from)) {
            throw new AssertionError("path should start with " + from);
        }

        while (iterator.hasNext()) {
            String next = iterator.next();
            Connection c = TestDataGenerator.getConnection(current, next);
            if (!connections.contains(c)) {
                throw new AssertionError(String.format("connection %s does not exist", c));
            }
            current = next;
        }

        if (!Objects.equals(current, to)) {
            throw new AssertionError("path should end with " + to);
        }
    }

}
