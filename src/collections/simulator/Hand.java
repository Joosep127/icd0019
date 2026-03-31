package collections.simulator;

import java.util.*;

public class Hand implements Iterable<Card>, Comparable<Hand> {

    private final List<Card> cards = new ArrayList<>();
    private HandType ht;
    private List<Integer> ns;
    private boolean dirty = true;

    public void addCard(Card card) {
        cards.add(card);
        dirty = true;
    }

    public void sortCards() {
        cards.sort(Collections.reverseOrder());
    }

    private void computeHandTypeIfNeeded() {
        if (!dirty) return;

        int[] rankCounts = new int[13];
        int[] suitCounts = new int[4];
        int minRank = 12, maxRank = 0;

        for (Card c : cards) {
            int r = c.getValue().ordinal();
            int s = c.getSuit().ordinal();
            rankCounts[r]++;
            suitCounts[s]++;
            minRank = Math.min(minRank, r);
            maxRank = Math.max(maxRank, r);
        }

        boolean isFlush = Arrays.stream(suitCounts).anyMatch(c -> c == cards.size());
        boolean isStraight = false;

        int consecutive = 0;
        for (int r = 0; r < 13; r++) {
            if (rankCounts[r] > 0) {
                consecutive++;
                if (consecutive == cards.size()) {
                    isStraight = true;
                    break;
                }
            } else {
                consecutive = 0;
            }
        }
        if (!isStraight && rankCounts[12] == 1) {
            if (rankCounts[0] == 1 && rankCounts[1] == 1 && rankCounts[2] == 1 && rankCounts[3] == 1) {
                isStraight = true;
            }
        }

        TreeMap<Integer, List<Integer>> countMap = new TreeMap<>(Collections.reverseOrder());
        for (int r = 12; r >= 0; r--) {
            int count = rankCounts[r];
            if (count > 0) {
                countMap.computeIfAbsent(count, k -> new ArrayList<>()).add(r);
            }
        }
        ns = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> e : countMap.entrySet()) {
            List<Integer> ranks = e.getValue();
            ranks.sort(Collections.reverseOrder());
            ns.addAll(ranks);
        }

        int maxCount = countMap.firstKey();

        if (cards.size() >= 5) {
            boolean triggered = false;
            if (isFlush && isStraight) {
                triggered = true;
                ht = HandType.STRAIGHT_FLUSH;
            }
            else if (isFlush) {
                triggered = true;
                ht = HandType.FLUSH;
            }
            else if (isStraight) {
                triggered = true;
                ht = HandType.STRAIGHT;
            }
            if (triggered && maxCount <= 2) {
                maxCount = -1;
            }
        }
        switch (maxCount) {
            case -1: break;
            case 4: ht = HandType.FOUR_OF_A_KIND; break;
            case 3: ht = countMap.get(2) != null ? HandType.FULL_HOUSE : HandType.TRIPS; break;
            case 2:
                if (countMap.get(2).size() == 2) ht = HandType.TWO_PAIRS;
                else ht = HandType.ONE_PAIR;
                break;
            default: ht = HandType.HIGH_CARD;
        }

        dirty = false;
    }

    public HandType getHandType() {
        computeHandTypeIfNeeded();
        return ht;
    }

    public boolean contains(Card card) {
        return cards.contains(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public int compareTo(Hand other) {
        if (other.isEmpty() || this.isEmpty()) {
            return Integer.compare(this.size(), other.size());
        }

        this.computeHandTypeIfNeeded();
        other.computeHandTypeIfNeeded();

        if (this.ht != other.ht) return ht.compareTo(other.ht);

        for (int i = 0; i < Math.min(ns.size(), other.ns.size()); i++) {
            int cmp = Integer.compare(ns.get(i), other.ns.get(i));
            if (cmp != 0) return cmp;
        }

        return Integer.compare(ns.size(), other.ns.size());
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
