package presenter;

import gamer.Gamer;

public class ScoreMarker {

	private static ScoreMarker marker = new ScoreMarker();
	private CardOrganizer organizer = CardOrganizer.getInstance();

	private ScoreMarker() {

	}
	public static ScoreMarker getInstance() {
		return marker;
	}


	//手札がバーストしているか判定するメソッド
	public boolean isBusted(int point) {
		if (point >= 21) {
			return true;
		}
		return false;
	}

	//現在の合計ポイントを計算するメソッド
	public int sumPoint(Gamer gamer) {
		int sumPoint = 0;
		for (int i = 0; i < gamer.getList().size(); i++) {
			int cardNumber = this.organizer.toNumber(gamer.getList().get(i));
			sumPoint += this.toPoint(cardNumber);
		}
		return sumPoint;

	}

	//カードの数字を得点計算用のポイントに変換するメソッド。J/Q/Kは10とする
	public int toPoint(int cardNumber) {
		if (cardNumber >= 11) {
			return 10;
		} else {
			return cardNumber;
		}
	}

}
