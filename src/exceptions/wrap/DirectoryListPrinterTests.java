package exceptions.wrap;

import org.junit.jupiter.api.Test;

public class DirectoryListPrinterTests {

    @Test
    public void readingThrowsExample() throws Exception {
        new DirectoryListPrinter().printDirectoryList();
    }

}
