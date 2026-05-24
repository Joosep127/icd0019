package reflection.tester;

public class ExampleTests3 {

    @MyTest(tags = { "main", "fast" })
    public void test7() {
    }

    @MyTest(tags = { "main", "slow" })
    public void test8() {
    }

    @MyTest(tags = { "extra" })
    public void test9() {
        throw new RuntimeException("not ready yet");
    }


}
