package generics.list;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MyListTest {

    @Test
    public void addingListOfSubtypesExample() {
        MyList<Number> list = new MyList<>();

        list.add(1);
        list.add(2.0);

        List<Integer> integers = List.of(3, 4);

        list.addAll(integers);

        assertThat(list.toString()).isEqualTo("[1, 2.0, 3, 4]");
    }
}
