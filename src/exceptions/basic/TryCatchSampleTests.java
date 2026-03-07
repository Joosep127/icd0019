package exceptions.basic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TryCatchSampleTests {

    @Test
    public void readingSucceedsExample() {
        // prepare fake file to return value "stuff" when reading from it.
        // testing normal case when the reading is successful
        FakeFile file = new FakeFile().setData("stuff");

                                            // use the fake fail
        String data = new Code().readDataFrom(file);

        assertThat(data).isEqualTo("stuff"); // check that our code read the data from the file
        assertTrue(file.isClosed()); // check that the file was closed
    }

    @Test
    public void readingThrowsExample() {
        // prepare fake file to throw an exception when reading from it.
        FakeFile file = new FakeFile().throwOnRead();

                                                   // use the fake file
        String data = new Code().readDataFrom(file);

        assertThat(data).isEqualTo("some default value"); // check that our code returns the default value
        assertTrue(file.isClosed());  // check that the file was closed
    }
}
