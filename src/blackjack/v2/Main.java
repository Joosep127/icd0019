package blackjack.v2;

import blackjack.common.Deck;

public class Main {

    public static void main(String[] args) {

        Player player = new Player(10);

        Dealer dealer = new Dealer(new Deck());

        new Table(dealer, player);

        player.placeBet(5);

        dealer.playRound();

        player.getChipCount();

    }

}
