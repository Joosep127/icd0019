package collections.simulator;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static collections.simulator.Helpers.*;
import static org.assertj.core.api.Assertions.*;

public class SimulatorTests {


    @Test
    public void myTest() {
        System.out.println(getHand("AA"));
    }

    @Test
    public void sameHandTypesMayDifferInStrength() {
        assertThatComparable(getHand("2345A")).isGreaterThan(getHand("J345K"));

        assertThatComparable(getHand("AA")).isGreaterThan(getHand("KK"));

        assertThatComparable(getHand("AAK")).isGreaterThan(getHand("AAQ"));

        assertThatComparable(getHand("22AA")).isGreaterThan(getHand("QQKK"));

        assertThatComparable(getHand("22AA4")).isGreaterThan(getHand("22AA3"));

        assertThatComparable(getHand("AAAK")).isGreaterThan(getHand("AAAQ"));

        assertThatComparable(getHand("34567")).isGreaterThan(getHand("23456"));

        assertThatComparable(getHand("AAAKK")).isGreaterThan(getHand("AAKKK"));

        assertThatComparable(getFlushHand("23856"))
                .isGreaterThan(getFlushHand("23756"));
    }

    @Test
    public void calculatesProbabilitiesUsingSimulation() {

        Simulator simulator = new Simulator(5e6);

        Map<HandType, Double> map = simulator.calculateProbabilities();

        // https://en.wikipedia.org/wiki/Poker_probability#Frequency_of_5-card_poker_hands

        assertThat(map.get(HandType.HIGH_CARD)).isCloseTo(50.118, within(0.06));
        assertThat(map.get(HandType.ONE_PAIR)).isCloseTo(42.257, within(0.06));
        assertThat(map.get(HandType.TWO_PAIRS)).isCloseTo(4.754, within(0.06));
        assertThat(map.get(HandType.TRIPS)).isCloseTo(2.113, within(0.06));
    }

    @Test
    public void calculatesWinningsOddsForHoldEmHand() {

        Hand hand1 = getSuitedHand("9h9s");
        Hand hand2 = getSuitedHand("AdKc");

        Simulator simulator = new Simulator(1e5);

        double winningOdds = simulator.getWinningOdds(hand1, hand2);

        // https://www.cardplayer.com/poker-tools/odds-calculator/texas-holdem

        assertThat(winningOdds).isCloseTo(55.53, within(0.5));
    }
}
