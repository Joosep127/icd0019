package reflection.tester;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestRunnerTests {

    @Test
    public void runsTestsFromMultipleClasses() {

        List<String> testClassNames = List.of(
                "reflection.tester.ExampleTests1",
                "reflection.tester.ExampleTests2");

        TestRunner testRunner = new TestRunner();

        testRunner.runTests(testClassNames);

        assertThat(testRunner.getResult()).contains("""
            test1() - OK
            test2() - FAILED
            test3() - OK
            test4() - FAILED
            test5() - OK
            test6() - FAILED""");
    }

    @Test
    public void runsTestsUsingTagsFast() {

        assertThat(runTags("main")).contains("""
                    test7() - OK
                    test8() - OK
                    test9() - SKIPPED""");

        assertThat(runTags("fast")).contains("""
                    test7() - OK
                    test8() - SKIPPED
                    test9() - SKIPPED""");

        assertThat(runTags("extra")).contains("""
                    test7() - SKIPPED
                    test8() - SKIPPED
                    test9() - FAILED""");
    }

    private String runTags(String... tags) {
        List<String> testClassNames = List.of(
                "reflection.tester.ExampleTests3");

        TestRunner testRunner = new TestRunner();

        testRunner.runTests(testClassNames, tags);

        return testRunner.getResult();
    }

}