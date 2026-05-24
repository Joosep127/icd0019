package reflection.tester;

import static reflection.tester.TestRunner.assertThrows;

public class ExampleTests2 {

    @MyTest
    public void test3() {
        assertThrows(() -> {
            throw new IllegalStateException();
        }, RuntimeException.class);
    }

    @MyTest
    public void test4() {
        assertThrows(() -> {
            throw new RuntimeException();
        }, IllegalStateException.class);
    }

    @MyTest
    public void test5() {
        assertThrows(() -> {
            throw new IllegalStateException();
        }, IllegalStateException.class);
    }

    @MyTest
    public void test6() {
        assertThrows(() -> {}, IllegalStateException.class);
    }

    public void helperMethod() {
    }


}
