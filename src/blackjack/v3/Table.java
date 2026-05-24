package blackjack.v3;

import blackjack.common.Deck;
import blackjack.common.Hand;

public class Table {

    private final Deck deck;
    private Player player;

    private final Hand playerHand = new Hand();
    private final Hand dealerHand = new Hand();

    public Table(Deck deck) {
        this.deck = deck;
    }

    public Player getPlayer() {
        return player;
    }

    public Deck getDeck() {
        return deck;
    }

    public Hand getPlayerCards() {
        return playerHand;
    }

    public Hand getDealerCards() {
        return dealerHand;
    }

    public Player registerPlayer(Player player) {
        this.player = player;
        return player;
    }
}
