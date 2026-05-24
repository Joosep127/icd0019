package bonus.dna;

import java.util.Set;

public record GenerationResult(String first,
                               String second,
                               Set<String> overLaps) {
}
