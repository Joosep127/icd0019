package collections.sorting;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberStringTests {

    @Test
    public void findsStreaks() {
        assertThat(getStreakList("").toString()).isEqualTo("[]");

        assertThat(getStreakList("a").toString()).isEqualTo("[[a]]");

        assertThat(getStreakList("ab").toString()).isEqualTo("[[a], [b]]");

        assertThat(getStreakList("abbc").toString())
                .isEqualTo("[[a], [b, b], [c]]");

        assertThat(getStreakList("abb").toString()).isEqualTo("[[a], [b, b]]");

        assertThat(getStreakList("aab").toString()).isEqualTo("[[a, a], [b]]");
    }

    @Test
    public void comparatorTests() {
        List<List<Character>> input = new ArrayList<>(List.of(
                List.of('6'),
                List.of('3', '3'),
                List.of('1', '1', '1'),
                List.of('2')));

        input.sort(new LengthAndElementReverseComparator());

        assertThat(input.toString()).isEqualTo("[[1, 1, 1], [3, 3], [6], [2]]");
    }

    @Test
    public void sortNumberGroups() {
        NumberString g1 = new NumberString("112");
        NumberString g2 = new NumberString("113");
        NumberString g3 = new NumberString("1134");
        NumberString g4 = new NumberString("1133");
        NumberString g5 = new NumberString("12232");

        List<NumberString> groups = new ArrayList<>(List.of(g1, g2, g3, g4, g5));

        Collections.swap(groups, 0, 4); // disturb the order
        Collections.swap(groups, 3, 2);

        Collections.sort(groups);

        List<String> values = getValues(groups);

        assertThat(values).containsExactly("112", "113", "1134", "1133", "12232");
    }

    private List<List<Character>> getStreakList(String input) {
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }

        return NumberString.getStreakList(characters);
    }

    private List<String> getValues(List<NumberString> groups) {
        List<String> values = new ArrayList<>();
        for (NumberString group : groups) {
            values.add(group.initialValue());
        }
        return values;
    }


}
