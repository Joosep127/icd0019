package reflection;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import reflection.serializer.SerializerTests;
import reflection.tester.TestRunnerTests;

@Suite
@SelectClasses({
        SerializerTests.class,
        TestRunnerTests.class})
public class TestSuite {

}