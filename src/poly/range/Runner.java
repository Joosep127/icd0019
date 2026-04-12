package poly.range;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class Runner {

    @Test
    public void canIterateRange() {
        for (Integer integer : range(1, 7)) {
            System.out.println(integer);
        }
    }

    private Iterable<Integer> range(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .boxed()
                .toList();
    }


}
