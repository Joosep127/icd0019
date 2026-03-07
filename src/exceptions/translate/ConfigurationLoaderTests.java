package exceptions.translate;

import org.junit.jupiter.api.Test;

public class ConfigurationLoaderTests {

    @Test
    public void exceptionTranslationExample() throws Exception {
        String configPath = "./";

        new ConfigurationLoader().readConfiguration(configPath);
    }

}
