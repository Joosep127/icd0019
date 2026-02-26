package oo.hide;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EqualityTests {

    @Test
    public void testsEquality() {

        assertFalse(new Point(1, 2) == new Point(1, 2));

        assertTrue(new Point(1, 2).equals(new Point(1, 2)));

        assertThat(new Point(1, 2)).isEqualTo(new Point(1, 2));

        assertThat(new Point(1, 1)).isNotEqualTo(new Point(1, 2));

        assertThat(new Point(1, 1)).isNotNull();
    }


}
