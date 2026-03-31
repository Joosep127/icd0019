package collections.sorting;

import java.util.*;
import java.util.stream.Collectors;

public class NumberString implements Comparable<NumberString> {
    public String initialValue;
    public List<List<Character>> streaks;

    public NumberString(String input) {
        initialValue = input;
        streaks = getStreakList(input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList()));
        streaks.sort(new LengthAndElementReverseComparator());
    }

    public String initialValue() {
        return initialValue;
    }

    public static List<List<Character>> getStreakList(List<Character> characters) {
        List<List<Character>> output = new ArrayList<>();
        HashMap<Character, Integer> seen = new HashMap<>();

        for (Character c : characters) {
            if (seen.containsKey(c)) {
                output.get(seen.get(c)).add(c);
            } else {
                seen.put(c, output.size());
                output.add(new ArrayList<>(List.of(c)));
            }
        }
        return output;
    }

    @Override
    public int compareTo(NumberString e) {
        int minStreaks = Math.min(this.streaks.size(), e.streaks.size());

        for (int i = 0; i < minStreaks; i++) {
            List<Character> s1 = this.streaks.get(i);
            List<Character> s2 = e.streaks.get(i);

            int cmp = Integer.compare(s1.size(), s2.size());
            if (cmp != 0) return cmp;

            cmp = Character.compare(s1.getFirst(), s2.getFirst());
            if (cmp != 0) return cmp;
        }

        return Integer.compare(streaks.size(), e.streaks.size());
    }
}
