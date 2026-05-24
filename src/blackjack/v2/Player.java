package blackjack.v2;

import blackjack.common.Card;
import blackjack.common.Hand;

public class Player {

	private int chipCount;
	private Table table;
	private Hand hand = new Hand();

	public Player(int chipCount) {
		this.chipCount = chipCount;
	}

	public void placeBet(int chipCount) {
		table.placeBet(chipCount);

	}

	public void act() {
		while (shouldITakeACard()) {
			table.getDealer().iWantACard();
		}
	}

	private boolean shouldITakeACard() {
		Hand myHand = hand;
		Hand dealerHand = table.getDealer().getHand();

		return false;
	}

	public int getChipCount() {
		return chipCount;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public void addCard(Card card) {
		hand.addCard(card);
	}

	public Hand getHand() {
		return hand;
	}
}