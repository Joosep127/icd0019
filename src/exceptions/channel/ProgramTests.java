package exceptions.channel;

import org.junit.jupiter.api.Test;

public class ProgramTests {

    @Test
    public void normalCondition() {
        Program program = new Program();

        program.run(7); // 7 is arbitrary value

        // Prints: ### Result is 73.5 ###
    }

    @Test
    public void handlesMissingConstantException() {
        Program program = new Program();

        // simulate error condition
        program.provider.makeItThrowMissingConstantException();

        program.run(7); // 7 is arbitrary value

        // Should print: ### Error: Constant is missing ###
    }

    @Test
    public void handlesCorruptConfigurationException() {
        Program program = new Program();

        // simulate error condition
        program.provider.makeItThrowCorruptConfigurationException();

        program.run(7); // 7 is arbitrary value

        // Should print: ### Error: Configuration file is corrupt ###
    }

    @Test
    public void handlesIOException() {
        Program program = new Program();

        // to simulate error condition
        // modify calculate() method in the Program.

        program.run(7); // 7 is arbitrary value

        // Should print: ### Error: can't find configuration file ###
    }
}
