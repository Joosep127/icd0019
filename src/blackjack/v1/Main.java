package blackjack.v1;

import blackjack.common.Deck;

public class Main {

    public static void main(String[] args) {

        Player player = new Player();

        Dealer dealer = new Dealer(new Deck());

        player.placeBet(5);

        dealer.playRoundWith(player);

        player.getChipCount();

    }

}
