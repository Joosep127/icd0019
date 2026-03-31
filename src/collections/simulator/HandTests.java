package collections.simulator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static collections.simulator.Card.CardSuit.*;
import static collections.simulator.Card.CardValue.*;
import static collections.simulator.Helpers.getFlushHand;
import static collections.simulator.Helpers.getHand;
import static org.assertj.core.api.Assertions.assertThat;

public class HandTests {

    @Test
    public void cardsCanBeCompared() {
        assertThat(new Card(A, C).compareTo(new Card(A, S))).isEqualTo(0);
        assertThat(new Card(A, C).compareTo(new Card(K, S))).isGreaterThan(0);
        assertThat(new Card(K, H).compareTo(new Card(A, H))).isLessThan(0);
    }

    @Test
    public void cardsCanBeSorted() {
        List<Card> cards = Arrays.asList(
                new Card(K, H),
                new Card(Q, H),
                new Card(J, H),
                new Card(S10, S),
                new Card(A, C));

        Collections.sort(cards);

        assertThat(cards.toString())
                .isEqualTo("[(S10, S), (J, H), (Q, H), (K, H), (A, C)]");
    }

    @Test
    public void getHandIsHelperMethodForCreatingHandWithTheSpecifiedCards() {
        Hand hand = getHand("AK"); // suits are arbitrary

        assertThat(hand.toString()).isEqualTo("[(A, S), (K, C)]");
    }

    @Test
    public void handKnowsWhetherItContainsOnePair() {
        assertThat(getHand("AA").getHandType())
                .isEqualTo(HandType.ONE_PAIR);

        assertThat(getHand("AK").getHandType())
                .isNotEqualTo(HandType.ONE_PAIR);

        assertThat(getHand("AAKK").getHandType())
                .isNotEqualTo(HandType.ONE_PAIR);

        assertThat(getHand("AAA").getHandType())
                .isNotEqualTo(HandType.ONE_PAIR);

        assertThat(getHand("KKAAA").getHandType())
                .isNotEqualTo(HandType.ONE_PAIR);
    }

    @Test
    public void handKnowsWhetherItContainsTwoPairs() {
        assertThat(getHand("AAKK").getHandType())
                .isEqualTo(HandType.TWO_PAIRS);

        assertThat(getHand("AAKKK").getHandType())
                .isNotEqualTo(HandType.TWO_PAIRS);
    }

    @Test
    public void handKnowsWhetherItContainsTrips() {
        assertThat(getHand("AAA").getHandType())
                .isEqualTo(HandType.TRIPS);

        assertThat(getHand("AAKKK").getHandType())
                .isNotEqualTo(HandType.TRIPS);
    }

    @Test
    public void handKnowsWhetherItContainsStraight() {
        assertThat(getHand("A2345").getHandType())
                .isEqualTo(HandType.STRAIGHT);

        assertThat(getHand("TJQKA").getHandType())
                .isEqualTo(HandType.STRAIGHT);

        assertThat(getHand("2345").getHandType())
                .isNotEqualTo(HandType.STRAIGHT);

        assertThat(getHand("23567").getHandType())
                .isNotEqualTo(HandType.STRAIGHT);

        assertThat(getHand("JQKA2").getHandType())
                .isNotEqualTo(HandType.STRAIGHT);
    }

    @Test
    public void handKnowsWhetherItContainsFlush() {
        assertThat(getFlushHand("23567").getHandType())
                .isEqualTo(HandType.FLUSH);

        assertThat(getHand("23456").getHandType())
                .isNotEqualTo(HandType.FLUSH);

        assertThat(getFlushHand("23456").getHandType())
                .isNotEqualTo(HandType.FLUSH);
    }

    @Test
    public void handKnowsWhetherItContainsFullHouse() {
        assertThat(getFlushHand("AKAAK").getHandType())
                .isEqualTo(HandType.FULL_HOUSE);

        assertThat(getHand("AAAAK").getHandType())
                .isNotEqualTo(HandType.FULL_HOUSE);
    }

    @Test
    public void handKnowsWhetherItContainsFourOfAKind() {
        assertThat(getHand("AAAA").getHandType())
                .isEqualTo(HandType.FOUR_OF_A_KIND);
    }

    @Test
    public void handKnowsWhetherItContainsStraightFlush() {
        assertThat(getFlushHand("23456").getHandType())
                .isEqualTo(HandType.STRAIGHT_FLUSH);

        assertThat(getHand("23456").getHandType())
                .isNotEqualTo(HandType.STRAIGHT_FLUSH);

        assertThat(getFlushHand("23567").getHandType())
                .isNotEqualTo(HandType.STRAIGHT_FLUSH);
    }
}
