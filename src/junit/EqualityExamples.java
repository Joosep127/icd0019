package junit;

import org.junit.jupiter.api.Test;

public class EqualityExamples {

    @Test
    public void primitiveEquality() {
        System.out.println(1 == 1);
        System.out.println(1 == 2);
    }

    @Test
    public void objectEqualityWithSmallNumbers() {
        Integer x = 1;
        Integer y = 1;
        System.out.println(x == y);
    }
}
