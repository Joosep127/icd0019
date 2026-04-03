package generics.methods;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultValueExample {

    @Test
    public void exampleWithoutHelperMethodConstantExists() {

        Integer constant = getConstantForZone("A");

        if (constant == null) {
            constant = 1; // set default value if it was null
        }

        Integer result = 100 * constant;

        assertThat(result).isEqualTo(200); // constant for zone A exists
    }

    @Test
    public void exampleWithoutHelperMethodConstantDoesNotExists() {

        Integer constant = getConstantForZone("B");

        if (constant == null) {
            constant = 1; // set default value if it was null
        }

        Integer result = 100 * constant;

        assertThat(result).isEqualTo(100); // constant for zone b does not exist
                                     // and default was used
    }

    @Test
    public void exampleWithHelperForIntegers() {

        Integer constant = defaultIfNull(getConstantForZone("A"), 1);

        assertThat(100 * constant).isEqualTo(200);

        constant = defaultIfNull(getConstantForZone("B"), 1);

        assertThat(100 * constant).isEqualTo(100);
    }

    @Test
    public void exampleWithHelperForDoubles() {

        Double constant = defaultIfNull(getConstantForZoneAsDouble("A"), 1.0);

        assertThat(100 * constant).isEqualTo(150);

        constant = defaultIfNull(getConstantForZoneAsDouble("B"), 1.0);

        assertThat(100 * constant).isEqualTo(100);

    }

    public <T> T defaultIfNull(T value, T defaultvalue) { return value != null ? value : defaultvalue; }

    private Integer getConstantForZone(String key) {

        // simulates code that produces Integer for certain key

        if ("A".equals(key)) {
            return 2;
        } else {
            return null;
        }
    }

    private Double getConstantForZoneAsDouble(String key) {

        // simulates code that produces Double for certain key

        if ("A".equals(key)) {
            return 1.5;
        } else {
            return null;
        }
    }

}
