package blackjack.v2;

import blackjack.common.Deck;
import blackjack.common.Hand;

public class Dealer {

	private Table table;
	private Deck deck;
	private Hand hand;

	public Dealer(Deck deck) {
		this.deck = deck;
		hand = new Hand();
	}

	public void playRound() {
		// deal first cards from deck

		table.getPlayer().act();

		if (table.getPlayer().getHand().isBust()) {
			table.reset();
			return;
		}

		// deal dealer cards

		// if player wins player.addChips(bet);
		// table.reset();
	}

	public void iWantACard() {
		table.getPlayer().addCard(deck.drawCard());
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Hand getHand() {
		return hand;
	}
}
