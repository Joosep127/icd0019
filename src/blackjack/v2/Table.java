package blackjack.v2;

public class Table {

	private final Dealer dealer;
	private final Player player;
	private int bet;

	public Table(Dealer dealer, Player player) {
		this.dealer = dealer;
		this.player = player;

		dealer.setTable(this);
		player.setTable(this);
	}

	public void placeBet(int chips) {
		this.bet = chips;
	}

	public Player getPlayer() {
		return player;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void reset() {

	}
}