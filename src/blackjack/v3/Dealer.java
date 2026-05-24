package blackjack.v3;

import blackjack.common.Card;
import blackjack.common.Hand;

public class Dealer {

	private Table table;
	private AccountInfo accountInfo;

	public Dealer(Table table, AccountInfo accountInfo) {
		this.table = table;
		this.accountInfo = accountInfo;
	}

	public void playRound() {
		int betSize = table.getPlayer().getBetSize();
		accountInfo.withdraw(table.getPlayer(), betSize);

		dealInitialCards();

		Hand playerHand = new Hand(table.getPlayerCards());
		Card dealersVisibleCard = null;

		while (table.getPlayer().decide(playerHand, dealersVisibleCard)
					== Player.PlayerAction.HIT) {

			table.getPlayerCards().addCard(table.getDeck().drawCard());

			if (table.getPlayerCards().isBust()) {
				return;
			}
		}

		Hand dealerCards = table.getDealerCards();
		while (!dealerCards.isBust() && dealerCards.lessThanDealerMin()) {
			dealerCards.addCard(table.getDeck().drawCard());
		}

		if (playerWins(table.getPlayerCards(), dealerCards)) {
			accountInfo.deposit(table.getPlayer(), betSize * 2);
		}
	}

	private void dealInitialCards() {

	}

	private boolean playerWins(Hand playerCards, Hand dealerCards) {
		return true;
	}
}
