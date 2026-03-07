package exceptions.translate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class ConfigurationLoader {

    public String readConfiguration(String configFilePath) throws Exception {
        try {
            return String.join("\n", Files.readAllLines(Paths.get(configFilePath)));
        } catch (IOException e) {
            throw new ConfigurationException(e);
        } catch (InvalidPathException e)  {
            throw new InstallationException(e);
        }
    }
}
