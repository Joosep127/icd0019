package oo.hide;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FibonacciTests {

    @Test
    public void keepsCount() {
        Fibonacci fib = new Fibonacci();

        assertThat(fib.nextValue()).isEqualTo(0);
        assertThat(fib.nextValue()).isEqualTo(1);
        assertThat(fib.nextValue()).isEqualTo(1);
        assertThat(fib.nextValue()).isEqualTo(2);
        assertThat(fib.nextValue()).isEqualTo(3);
        assertThat(fib.nextValue()).isEqualTo(5);
        assertThat(fib.nextValue()).isEqualTo(8);
        assertThat(fib.nextValue()).isEqualTo(13);
    }



}
