package poly.undo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;


public class CalculatorTest {

    @Test
    public void calculatorCanAddAndMultiply() {

        Calculator c = new Calculator();

        c.input(2);

        c.add(4);

        c.multiply(3);

        assertThat(c.getResult()).isCloseTo(18.0, within(0.1));
    }

    @Test
    public void operationsCanBeUndone() {

        Calculator c = new Calculator();

        c.input(2);

        c.multiply(3);

        c.add(1);

        assertThat(c.getResult()).isCloseTo(7, within(0.1));

        c.undo();

        assertThat(c.getResult()).isCloseTo(6, within(0.1));

        c.undo();

        assertThat(c.getResult()).isCloseTo(2, within(0.1));

        c.undo();

        assertThat(c.getResult()).isCloseTo(0, within(0.1));
    }
}
