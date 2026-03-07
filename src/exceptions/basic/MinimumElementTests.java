package exceptions.basic;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MinimumElementTests {

    @Test
    public void findsMinimumFromArrayOfNumbers() {

        assertThat(Code.minimumElement(new int[] { 1, 3 })).isEqualTo(1);

        assertThat(Code.minimumElement(new int[] { 1, 0 })).isEqualTo(0);
    }


}
