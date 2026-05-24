package blackjack.v1;

import blackjack.common.Deck;
import blackjack.common.Hand;

public class Dealer {

	private Hand hand;
	private Deck deck;

	public Dealer(Deck deck) {
		this.deck = deck;
		hand = new Hand();
	}

	public void playRoundWith(Player player) {

		int betSize = player.getBetSize();

		dealInitialCards(player);

		player.act();

		// ...
	}

	private void dealInitialCards(Player player) {
		player.getHand().addCard(deck.drawCard());
		hand.addCard(deck.drawCard());
		player.getHand().addCard(deck.drawCard());
		hand.addCard(deck.drawCard());
	}
}
