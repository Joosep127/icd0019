package fp.examples;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectToMap {

    @Test
    public void calculatesSalesTotal() {
        var records = List.of(
                new SalesRecord("p1", 10),
                new SalesRecord("p1", 20),
                new SalesRecord("p2", 20));

        var map = records.stream().collect(
                Collectors.toMap(
                        each -> each.productId(), // function that produces a key (from each item)
                        each -> each.itemsSold(), // function that produces a value
                        (a, b) -> a + b));  // what to do when there already is a value for this key
                                            // a is an existing value under the key
                                            // b is a value from the new item

        assertThat(map).isEqualTo(Map.of("p1", 30, "p2", 20));
    }




}
