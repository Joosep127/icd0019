package collections.simulator;

import com.sun.source.tree.Tree;

import java.util.*;

public class Hand implements Iterable<Card>, Comparable<Hand> {

    private final List<Card> cards = new ArrayList<>();
    private HandType ht;
    private List<Integer> ns;
    private int[] rankCounts = new int[13];
    private int[] suitCounts = new int[4];
    private boolean dirty = true;

    public void addCard(Card card) {
        cards.add(card);
        rankCounts[card.getValue().ordinal()]++;
        suitCounts[card.getSuit().ordinal()]++;
        dirty = true;
    }

    public void addCards(Card[] cards) {
        for (Card c: cards) {
            addCard(c);
        }
    }

    public void addCards(List<Card> cards) {
        for (Card c: cards) {
            addCard(c);
        }
    }

    public void setCard(Card[] newCards) {
        Arrays.fill(rankCounts, 0);
        Arrays.fill(suitCounts, 0);
        cards.clear();

        for (Card card : newCards) {
            cards.add(card);
            rankCounts[card.getValue().ordinal()]++;
            suitCounts[card.getSuit().ordinal()]++;
        }

        dirty = true;
    }

    public void setCard(List<Card> newCards) {
        Arrays.fill(rankCounts, 0);
        Arrays.fill(suitCounts, 0);
        cards.clear();

        for (Card card : newCards) {
            cards.add(card);
            rankCounts[card.getValue().ordinal()]++;
            suitCounts[card.getSuit().ordinal()]++;
        }

        dirty = true;
    }

    public void sortCards() {
        cards.sort(Collections.reverseOrder());
    }
    public boolean isStraight(int i) {
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
        if (!isStraight && rankCounts[12] == 1 && rankCounts[0] == 1 && rankCounts[1] == 1 && rankCounts[2] == 1 && rankCounts[3] == 1) {
            isStraight = true;
        }
        return isStraight;
    }

    public boolean isStraight() {
        if (cards.size() < 5) return false;

        int[] rankCounts = new int[13];
        for (Card c : cards) {
            rankCounts[c.getValue().ordinal()]++;
        }

        for (int start = 0; start <= 8; start++) { // 0..8 => last possible straight starts at 10
            boolean straight = true;
            for (int i = 0; i < 5; i++) {
                if (rankCounts[start + i] == 0) {
                    straight = false;
                    break;
                }
            }
            if (straight) return true;
        }

        // Check for Ace-low straight (A-2-3-4-5)
        if (rankCounts[12] > 0 && rankCounts[0] > 0 && rankCounts[1] > 0 && rankCounts[2] > 0 && rankCounts[3] > 0) {
            return true;
        }

        return false;
    }

    private TreeMap<Integer, List<Integer>> buildCountMap() {
        TreeMap<Integer, List<Integer>> countMap = new TreeMap<>(Collections.reverseOrder());
        for (int r = 12; r >= 0; r--) {
            int count = rankCounts[r];
            if (count > 0) {
                countMap.computeIfAbsent(count, k -> new ArrayList<>()).add(r);
            }
        }
        return countMap;
    }

    private void countRanks(TreeMap<Integer, List<Integer>> countMap) {
        ns = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> e : countMap.entrySet()) {
            List<Integer> ranks = e.getValue();
            ranks.sort(Collections.reverseOrder());
            ns.addAll(ranks);
        }
    }

    public static HandType getHT(boolean isFlush, boolean isStraight,
                                 int[] rankCounts, int[] suitCounts) {
        int pairs = 0;
        int trips = 0;
        int quads = 0;

        for (int count : rankCounts) {
            switch (count) {
                case 2 -> pairs++;
                case 3 -> trips++;
                case 4 -> quads++;
            }
        }

        if (isFlush && isStraight) {
            return HandType.STRAIGHT_FLUSH;
        }

        if (quads == 1) {
            return HandType.FOUR_OF_A_KIND;
        }

        if (trips == 1 && pairs == 1) {
            return HandType.FULL_HOUSE;
        }

        if (isFlush) {
            return HandType.FLUSH;
        }

        if (isStraight) {
            return HandType.STRAIGHT;
        }

        if (trips == 1) {
            return HandType.TRIPS;
        }

        if (pairs == 2) {
            return HandType.TWO_PAIRS;
        }

        if (pairs == 1) {
            return HandType.ONE_PAIR;
        }

        return HandType.HIGH_CARD;
    }

    private void determineHandType(boolean isFlush, boolean isStraight, TreeMap<Integer, List<Integer>> countMap) {
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
                if (countMap.get(2).size() == 2) {
                    ht = HandType.TWO_PAIRS;
                }
                else {
                    ht = HandType.ONE_PAIR;
                }
                break;
            default: ht = HandType.HIGH_CARD;
        }
    }

    private void computeHandTypeIfNeeded() {
        if (!dirty) {
            return;
        }
        if (cards.size() > 5) {
            removeWorst();
        }

        boolean isFlush = Arrays.stream(suitCounts).anyMatch(c -> c == cards.size());
        boolean isStraight = isStraight();

        TreeMap<Integer, List<Integer>> countMap = buildCountMap();

        countRanks(countMap);

        determineHandType(isFlush, isStraight, countMap);

        dirty = false;
    }

    private void removeWorst() {

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

    public List<Card> getCards() {
        return cards;
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

        if (this.ht != other.ht) {
            return ht.compareTo(other.ht);
        }

        for (int i = 0; i < Math.min(ns.size(), other.ns.size()); i++) {
            int cmp = Integer.compare(ns.get(i), other.ns.get(i));
            if (cmp != 0) {
                return cmp;
            }
        }

        return Integer.compare(ns.size(), other.ns.size());
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
