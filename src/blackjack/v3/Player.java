package blackjack.v3;

import blackjack.common.Card;
import blackjack.common.Hand;

public class Player {

	public enum PlayerAction { HIT, STAY }

	public int getBetSize() {
		return 5;
	}

	public PlayerAction decide(Hand myCards, Card dealersCard) {
		return PlayerAction.HIT; // or PlayerAction.STAY;
	}

}