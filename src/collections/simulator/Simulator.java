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
        int[] masterDeck = createMasterDeck();
        int[] deck = new int[52];

        for (int i = 0; i < iterations; i++) {
            simulateHand(masterDeck, deck, ht);
        }

        return computeResults(ht);
    }

    private int[] createMasterDeck() {
        int[] deck = new int[52];
        for (int i = 0; i < 52; i++) {
            deck[i] = i;
        }
        return deck;
    }

    private void simulateHand(int[] masterDeck, int[] deck, int[] ht) {
        boolean isFlush = true;
        boolean isStraight = false;
        int defSuit = -1;

        int[] rankCounts = new int[13];
        int[] suitCounts = new int[4];

        System.arraycopy(masterDeck, 0, deck, 0, 52);

        drawHand(deck, rankCounts, suitCounts);
        defSuit = rankCountsFlushCheck(deck, rankCounts, suitCounts);

        isFlush = checkFlush(deck, defSuit);
        isStraight = checkStraight(rankCounts);

        HandType handType = Hand.getHT(isFlush, isStraight, rankCounts, suitCounts);
        ht[handType.ordinal()]++;
    }

    private void drawHand(int[] deck, int[] rankCounts, int[] suitCounts) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        for (int y = 0; y < 5; y++) {
            int j = rnd.nextInt(y, 52);
            swap(deck, y, j);

            int card = deck[y];
            int rank = card % 13;
            int suit = card / 13;

            rankCounts[rank]++;
            suitCounts[suit]++;
        }
    }

    private int rankCountsFlushCheck(int[] deck, int[] rankCounts, int[] suitCounts) {
        int defSuit = deck[0] / 13;
        for (int i = 1; i < 5; i++) {
            if (deck[i] / 13 != defSuit) {
                return -1;
            }
        }
        return defSuit;
    }

    private boolean checkFlush(int[] deck, int defSuit) {
        return defSuit != -1;
    }

    private boolean checkStraight(int[] rankCounts) {
        for (int j = 0; j <= 8; j++) {
            if (rankCounts[j] > 0 &&
                    rankCounts[j + 1] > 0 &&
                    rankCounts[j + 2] > 0 &&
                    rankCounts[j + 3] > 0 &&
                    rankCounts[j + 4] > 0) {
                return true;
            }
        }
        return rankCounts[12] > 0 &&
                rankCounts[0] > 0 &&
                rankCounts[1] > 0 &&
                rankCounts[2] > 0 &&
                rankCounts[3] > 0;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private Map<HandType, Double> computeResults(int[] ht) {
        Map<HandType, Double> results = new EnumMap<>(HandType.class);
        HandType[] types = HandType.values();

        for (int i = 0; i < ht.length; i++) {
            double percentage = ((double) ht[i] / iterations) * 100;
            System.out.println(types[i] + ": " + percentage + "%");
            results.put(types[i], percentage);
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

        return result / iterations * 104;
    }
}