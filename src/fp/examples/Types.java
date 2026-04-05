package fp.examples;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class Types {

    @Test
    public void lambdaExamples() {

        Predicate<Integer> f1 = x -> x > 2;

        assertThat(f1.test(3)).isTrue();

        MyPredicate f2 = x -> x > 2;

        assertThat(f2.check(3)).isTrue();

        Function<Integer, Double> f3 = x -> Math.pow(x, 2);

        assertThat(f3.apply(3)).isCloseTo(9.0, within(0.1));

        BiFunction<Integer, Integer, Double> f4 = (x, y) -> Math.pow(x, y);

        assertThat(f4.apply(3, 3)).isCloseTo(27.0, within(0.1));

        Runnable l1 = () -> System.out.println("hello!");

        l1.run();

        Consumer<String> l2 = x -> System.out.println(x);

        l2.accept("hello!");

        Consumer<String> l3 = System.out::println;

        l3.accept("hello!");

    }

    @FunctionalInterface
    private interface MyPredicate {
        boolean check(int x);
    }


}


