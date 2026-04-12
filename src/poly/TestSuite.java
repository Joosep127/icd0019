package poly;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import poly.customer.CustomerTests;
import poly.customer.RepositoryTests;
import poly.fruit.FruitTest;
import poly.shapes.ShapeTest;
import poly.undo.CalculatorTest;

@Suite
@SelectClasses({
        FruitTest.class,
        ShapeTest.class,
        CalculatorTest.class,
        AverageTest.class,
        RepositoryTests.class,
        CustomerTests.class})
public class TestSuite {

}