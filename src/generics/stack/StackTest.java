package generics.stack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StackTest {

    @Test
    public void elementsArePoppedInReverseOrder() {
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        stack.push(2);

        Integer first = stack.pop();
        Integer second = stack.pop();

        assertThat(first).isEqualTo(2);
        assertThat(second).isEqualTo(1);
    }

}
