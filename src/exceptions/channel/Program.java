package exceptions.channel;

import java.io.IOException;

public class Program {

    /* default */ ConstantProvider provider = new ConstantProvider();

    public void run(int input) {
        try {
            double result = calculate(input);

            String formatted = format(String.valueOf(result));

            present(formatted);
        } catch (MissingConstantException e) {
            System.out.println(formatError("Constant is missing"));
        } catch (CorruptConfigurationException e) {
            System.out.println(formatError("Configuration file is corrupt"));
        }


    }

    private double calculate(int input) {
        // an arbitrary calculation that uses some
        // data from external source

        return (input + 42) * provider.getMultiplier();
    }

    private String format(String data) {
        return "### Result is %s ###".formatted(data);
    }

    private String formatError(String message) {
        return "### Error: %s ###".formatted(message);
    }

    private void present(String data) {
        System.out.println(data);
    }
}
