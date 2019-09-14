package presenter;

import deck.Deck;
import gamer.Gamer;

public class PlayerData {

	private static PlayerData playerData = new PlayerData();
	private Gamer player;
	private Gamer dealer;
	private Deck deck;

	private PlayerData() {

	}

	public static PlayerData getInstance() {
		return playerData;
	}

	public Gamer getPlayer() {
		return player;
	}
	public void setPlayer(Gamer player) {
		this.player = player;
	}
	public Gamer getDealer() {
		return dealer;
	}
	public void setDealer(Gamer dealer) {
		this.dealer = dealer;
	}
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}



}
