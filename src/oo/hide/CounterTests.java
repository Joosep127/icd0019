package oo.hide;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CounterTests {

    @Test
    public void keepsCount() {
        Counter counter = new Counter(1, 1);

        assertThat(counter.nextValue()).isEqualTo(1);
        assertThat(counter.nextValue()).isEqualTo(2);
        assertThat(counter.nextValue()).isEqualTo(3);
    }

    @Test
    public void keepsCountWithStep() {
        Counter counter = new Counter(1, 2);

        assertThat(counter.nextValue()).isEqualTo(1);
        assertThat(counter.nextValue()).isEqualTo(3);
        assertThat(counter.nextValue()).isEqualTo(5);
        assertThat(counter.nextValue()).isEqualTo(7);
    }


}
