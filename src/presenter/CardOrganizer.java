package presenter;

public class CardOrganizer {

	private static CardOrganizer organizer = new CardOrganizer();

	private CardOrganizer() {
	}

	public static CardOrganizer getInstance() {
		return organizer;
	}

	//山札の数をカードの数に置き換えて
	//カード番号からランクとスートを表示させる
	public String toDesc(int rawNumber) {
		int cardNumber = this.toNumber(rawNumber);
		String suit = this.toSuit(rawNumber);
		String rank = this.toRank(cardNumber);
		return suit + "の" + rank;
	}

	//山札の数をカードの数に置き換えるメソッド
	// 1 - 52 の数字を1 - 13に置き換える
	public int toNumber(int rawNumber) {
		int cardNumber = rawNumber % 13;
		if (cardNumber == 0) {
			return 13;
		}
		return cardNumber;
	}

	// 山札の数をスート（ハートやスペードなどのマーク）に置き換えるメソッド
	public String toSuit(int rawNumber) {
		switch ((rawNumber - 1) / 13) {
		case 0:
			return "ハート";
		case 1:
			return "ダイヤ";
		case 2:
			return "スペード";
		case 3:
			return "クローバー";
		default:
			return "例外です";
		}
	}

	//カード番号をランク（AやJ,Q,K）に変換するメソッド
	public String toRank(int number) {
		switch (number) {
		case 13:
			return "King";
		case 12:
			return "Queen";
		case 11:
			return "Jack";
		case 1:
			return "Ace";
		default:
			return String.valueOf(number);
		}
	}






}
