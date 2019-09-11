package gamer;

import java.util.ArrayList;
import java.util.List;

import deck.Deck;

public abstract class Gamer {

	private String name;
	private int point;
	private List<Integer> list = new ArrayList<Integer>();

	public Gamer(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public List<Integer> getList() {
		return this.list;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	//カードを一枚引いて、引いたカードをリストから削除
	public void takeACard(Deck deck) {
		this.list.add(deck.getDeck().get(0));
		deck.getDeck().remove(0);
	}

}
