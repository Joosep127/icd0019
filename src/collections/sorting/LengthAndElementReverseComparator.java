package collections.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LengthAndElementReverseComparator implements Comparator<List<Character>> {

    @Override
    public int compare(List<Character> a, List<Character> b) {
        if (a.isEmpty() || b.isEmpty()) {
            return 0;
        }
        return a.size() == b.size() ? Character.compare(b.getFirst(), a.getFirst()) : Integer.compare(b.size(), a.size());
    }
}
