package fp.examples;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Performance {

    @Test
    public void intermediateConversionToList() {
        AtomicInteger counter = new AtomicInteger();

        List<Integer> result = IntStream.range(0, 10_000)
                .boxed()
                .peek(i -> counter.getAndIncrement())
                // .toList().stream()
                .limit(3)
                .toList();

        System.out.println(counter.get()); // 10000 vs 3
    }

    @Test
    public void parallel() {
        double start = System.currentTimeMillis();

        long count = IntStream.range(0, 200_000)
                // .parallel()
                .filter(Performance::isPrime)
                .count();

        System.out.println(count);

        System.out.println((System.currentTimeMillis() - start) / 1000);

    }

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        return IntStream
                .rangeClosed(2, number / 2)
                .noneMatch(i -> number % i == 0);
    }
}


