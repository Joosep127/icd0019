package collections.simulator;

import exceptions.basic.FakeFile;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Simulator {
    private double iterations;

    public Simulator(double iterations) {
        this.iterations = iterations;
    }

    public Map<HandType, Double> calculateProbabilities() {
        int[] ht = new int[HandType.values().length];

        int[] rankCounts = new int[13];
        int[] suitCounts = new int[4];

        boolean isStraight;
        boolean isFlush;
        int defSuit;

        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        int[] masterDeck = new int[52];
        for (int i = 0; i < 52; i++) masterDeck[i] = i;
        int[] deck = new int[52];

        for (int i = 0; i < iterations; i++) {
            isStraight = false;
            isFlush = true;
            defSuit = -1;

            rankCounts = new int[13];
            suitCounts = new int[4];

            System.arraycopy(masterDeck, 0, deck, 0, 52);

            for (int y = 0; y < 5; y++) {
                int j = rnd.nextInt(y, 52);
                int temp = deck[y];
                deck[y] = deck[j];
                deck[j] = temp;

                int card = deck[y];
                int rank = card % 13;
                int suit = card / 13;

                if (y == 0) defSuit = suit;
                else if (defSuit != suit) isFlush = false;

                rankCounts[rank]++;
                suitCounts[suit]++;
            }

            for (int j = 0; j <= 8; j++) {
                if (rankCounts[j] > 0 &&
                        rankCounts[j + 1] > 0 &&
                        rankCounts[j + 2] > 0 &&
                        rankCounts[j + 3] > 0 &&
                        rankCounts[j + 4] > 0) {
                    isStraight = true;
                    break;
                }
            }

            if (!isStraight &&
                    rankCounts[12] > 0 &&
                    rankCounts[0] > 0 &&
                    rankCounts[1] > 0 &&
                    rankCounts[2] > 0 &&
                    rankCounts[3] > 0) {
                isStraight = true;
            }

            ht[Hand.getHT(isFlush, isStraight, rankCounts, suitCounts).ordinal()]++;

        }

        Map<HandType, Double> results = new EnumMap<>(HandType.class);

        HandType[] types = HandType.values();

        for (int i = 0; i < ht.length; i++) {
            double percentage = (double) ht[i] / iterations * 100;
            System.out.println(types[i] + ": " + percentage + "%");
            results.put(HandType.values()[i], percentage);
        }

        return results;

    }

    public double getWinningOdds(Hand player1hand, Hand player2hand) {
        Card[] table = new Card[5];
        Card.CardValue[] cardValues = Card.CardValue.values();
        Card.CardSuit[] cardSuits = Card.CardSuit.values();

        int[] masterDeck = new int[52 - player1hand.getCards().size() - player2hand.getCards().size()];
        boolean[] isUsed = new boolean[52];

        for (Card card : player1hand.getCards()) {
            int index = card.getValue().ordinal() + card.getSuit().ordinal() * 13;
            isUsed[index] = true;
        }
        for (Card card : player2hand.getCards()) {
            int index = card.getValue().ordinal() + card.getSuit().ordinal() * 13;
            isUsed[index] = true;
        }

        int ptr = 0;
        for (int i = 0; i < 52; i++) {
            if (!isUsed[i]) {
                masterDeck[ptr++] = i;
            }
        }

        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        Hand a = new Hand();
        Hand b = new Hand();
        double result = 0;

        int[] deck = new int[masterDeck.length];

        for (int iterCount = 0; iterCount < iterations; iterCount++) {
            a.setCard(player1hand.getCards());
            b.setCard(player2hand.getCards());

            System.arraycopy(masterDeck, 0, deck, 0, masterDeck.length);
            int deckSize = deck.length;

            for (int t = 0; t < 5; t++) {
                int idx = rnd.nextInt(deckSize);
                int cardInt = deck[idx];

                deck[idx] = deck[deckSize - 1];
                deckSize--;

                int rank = cardInt % 13;
                int suit = cardInt / 13;
                table[t] = new Card(cardValues[rank], cardSuits[suit]);
            }

            a.addCards(table);
            b.addCards(table);

            if (a.compareTo(b) > 0) {
                result += 1;
            }
        }

        return (result / iterations) * 104;
    }
}