package collections.simulator;

import java.util.*;

public class Simulator {

    public Simulator(double iterations) {
    }

    public Map<HandType, Double> calculateProbabilities() {

        // use special map when using enum keys
        Map<HandType, Double> results = new EnumMap<>(HandType.class);

        throw new RuntimeException("not implemented yet");
    }

    public double getWinningOdds(Hand player1hand, Hand player2hand) {
        throw new RuntimeException("not implemented yet");
    }

}
