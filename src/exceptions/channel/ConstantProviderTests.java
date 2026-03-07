package exceptions.channel;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConstantProviderTests {

    @Test
    public void returnsConstant() {
        double multiplier = new ConstantProvider().getMultiplier();

        assertThat(multiplier).is(closeTo(1.5));
    }

    @Test
    public void canThrowMissingConstantException() {
        ConstantProvider provider = new ConstantProvider();

        // simulate error condition
        provider.makeItThrowMissingConstantException();

        assertThrows(MissingConstantException.class,
                () -> provider.getMultiplier());
    }

    @Test
    public void canThrowCorruptConfigurationException() {
        ConstantProvider provider = new ConstantProvider();

        // simulate error condition
        provider.makeItThrowCorruptConfigurationException();

        assertThrows(CorruptConfigurationException.class,
                () -> provider.getMultiplier());
    }

    private Condition<Double> closeTo(double expected) {
        double precision = 0.0001;

        return new Condition<>() {
            @Override
            public boolean matches(Double actual) {
                return Math.abs(actual - expected) <= precision;
            }
        };
    }


}