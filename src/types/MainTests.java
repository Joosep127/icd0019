package types;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTests {

    @Test
    public void calculatesSumFromArrayOfIntegers() {
        assertThat(Code.sum(new int[] {1, 3, -2, 9})).isEqualTo(11);

        assertThat(Code.sum(new int[] {1, 3, 9})).isEqualTo(13);

        assertThat(Code.sum(new int[] {1, 3, -2, 9, 4})).isEqualTo(15);
    }

    @Test
    public void calculatesAverageFromArrayOfIntegers() {
        assertThat(Code.average(new int[]{1, 3, -2, 9})).is(closeTo(2.75));

        assertThat(Code.average(new int[] {1, 3, -2, 9, 5})).is(closeTo(3.2));

        assertThat(Code.average(new int[] {1, 2, 3, 4, 5, 6})).is(closeTo(3.5));
    }

    @Test
    public void findsMinimumElementFromArrayOfIntegers() {
        assertThat(Code.minimumElement(new int[] {1, 3, -2, 9})).isEqualTo(-2);

        assertThat(Code.minimumElement(new int[] {1, 2, 3, 4, 5, 0})).isEqualTo(0);

        assertThat(Code.minimumElement(new int[] {1})).isEqualTo(1);

        assertThat(Code.minimumElement(new int[] {})).isNull();
    }

    @Test
    public void findsModeFromCharactersInString() {
        assertThat(Code.mode("abcb")).isEqualTo('b');
        assertThat(Code.mode("abccbc")).isEqualTo('c');
        assertThat(Code.mode("abcacbaca")).isEqualTo('a');
        assertThat(Code.mode("")).isNull();
    }

    @Test
    public void squaresDigitsInString() {
        assertThat(Code.squareDigits("2")).isEqualTo("4");

        assertThat(Code.squareDigits("a2b")).isEqualTo("a4b");

        assertThat(Code.squareDigits("a22b")).isEqualTo("a44b");

        assertThat(Code.squareDigits("a9b2")).isEqualTo("a81b4");
    }

    @Test
    public void createsStringFromArray() {
        assertThat(Code.asString(new int[] { 1, 3, -2, 9 })).isEqualTo("1, 3, -2, 9");

        assertThat(Code.asString(new int[] { })).isEmpty();
    }

    @Test
    public void testsWhetherSquareIsIsolated() {
        assertThat(Code.isIsolated(0, 0)).isFalse();
        assertThat(Code.isIsolated(9, 9)).isFalse();
        assertThat(Code.isIsolated(5, 3)).isFalse();
        assertThat(Code.isIsolated(7, 1)).isFalse();
        assertThat(Code.isIsolated(0, 9)).isTrue();
        assertThat(Code.isIsolated(5, 7)).isTrue();
    }

    @Test
    public void findsIsolatedSquareCount() {
        assertThat(Code.isolatedSquareCount()).isEqualTo(2);
    }

    private Condition<Double> closeTo(double expected) {
        double precision = 0.0001;

        return new Condition<>() {
            @Override
            public boolean matches(Double actual) {
                return Math.abs(actual - expected) <= precision;
            }
        };
    }
}
