package blackjack.v3;

import blackjack.common.Deck;

public class Main {

    public static void main(String[] args) {

        AccountInfo accountInfo = new AccountInfo();
        Table table = new Table(new Deck());
        Player player = table.registerPlayer(new Player());

        Dealer dealer = new Dealer(table, accountInfo);

        dealer.playRound();

        accountInfo.getPlayerBalance(player);
    }

}
