package bonus.dna;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SequenceFinderTest {

    @Test
    public void findsSimpleSequences() {

        int minSubsequenceLength = 2;
        int maxErrorsBetween = 0;
        Set<String> found = new SequenceFinder(
                minSubsequenceLength, maxErrorsBetween)
                .findMatchingSubsequences("--12--", "...12");

        assertThat(found).containsExactly("12");
    }

    @Test
    public void findsBrokenSequences() {

        int minSubsequenceLength = 2;
        int maxErrorsBetween = 1;
        Set<String> found = new SequenceFinder(
                minSubsequenceLength, maxErrorsBetween)
                .findMatchingSubsequences("--12345--", "...12845.");

        assertThat(found).containsExactly("12345");
    }

    @Test
    public void findsMatchingSequences() {

        var generated = new TestDataGenerator().generate();

        String firstSequence = generated.first();
        String secondSequence = generated.second();
        Set<String> subsequencesToBeFound = generated.overLaps();

        int minSubsequenceLength = 15;
        int maxErrorsBetween = 3;
        Set<String> found = new SequenceFinder(
                minSubsequenceLength, maxErrorsBetween)
                .findMatchingSubsequences(firstSequence, secondSequence);

        assertThat(found).isEqualTo(subsequencesToBeFound);
    }
}
