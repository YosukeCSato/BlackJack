package deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

	private List<Integer> deck = new ArrayList<Integer>();

	//山札（deck）に値を入れ、シャッフルするコンストラクタ
	public Deck() {
		//リストに1-52の連番を代入
		for (int i = 1; i < 53; i++) {
			this.deck.add(i);
		}

		//山札をシャッフル
		Collections.shuffle(this.deck);

		//確認のために表示させる
		for (int i = 0; i < this.deck.size(); i++) {
		System.out.println((i + 1) + " : " + this.deck.get(i));
		}
	}

	public List<Integer> getDeck() {
		return this.deck;
	}


}
