package bonus.dna;

import java.util.*;

public class TestDataGenerator {

    private static final int SEQUENCE_COUNT = 100;
    private static final int MIN_SEQUENCE_LENGTH = 40;
    private static final int MAX_SEQUENCE_LENGTH = 50;
    private static final int MAX_ERROR_LENGTH = 3;

    private final Random rand = new Random(0);

    public GenerationResult generate() {
        List<String> overlaps = new ArrayList<>();
        for (int i = 0; i < SEQUENCE_COUNT; i++) {
            overlaps.add(generateSequence(MIN_SEQUENCE_LENGTH, MAX_SEQUENCE_LENGTH));
        }

        String firstSequence = joinWithRandomSequences(overlaps, "AAT");
        String secondSequence = joinWithRandomSequences(
                addErrors(shuffled(overlaps)), "GGC");

        return new GenerationResult(firstSequence, secondSequence, new HashSet<>(overlaps));
    }

    private List<String> addErrors(List<String> strings) {
        List<String> result = new ArrayList<>();
        for (String each : strings) {
            StringBuilder sb = new StringBuilder(each);
            int middle = each.length() / 2 - MAX_ERROR_LENGTH / 2;
            for (int i = 0; i < MAX_ERROR_LENGTH; i++) {
                sb.setCharAt(middle + i, generateSequence(1, 1).charAt(0));
            }
            result.add(sb.toString());
        }

        return result;
    }

    private List<String> shuffled(List<String> strings) {
        List<String> tmp = new ArrayList<>(strings);
        Collections.shuffle(tmp, rand);
        return tmp;
    }

    private String joinWithRandomSequences(List<String> strings, String delimiter) {
        LinkedList<String> tmp = new LinkedList<>(strings);

        String first = tmp.removeLast();

        StringBuilder sb = new StringBuilder();
        sb.append(first);
        for (String each : tmp) {
            sb.append(delimiter);
            sb.append(generateSequence(10, 20));
            sb.append(delimiter);
            sb.append(each);
        }

        return sb.toString();
    }

    private String generateSequence(int from, int to) {
        int diff = to - from;
        int length = diff > 0 ? from + rand.nextInt(diff) : from;

        StringBuilder sb = new StringBuilder();
        String alphabet = "ACTG";

        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(alphabet.length());
            sb.append(alphabet.charAt(index));
        }

        return sb.toString();
    }
}
