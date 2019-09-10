import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	/*カード枚数は52枚。ジョーカーは含めない。カードの重複が無いように山札を構築する。
	プレイヤー、ディーラーの一対一で対戦するものとし、以下の挙動を取る
	初期設定として、プレイヤー・ディーラーが交互に1枚ずつ山札からカードを取り手札とする。
	プレイヤーからは自分の手札すべてと、ディーラーの1枚めの手札が確認できる。（ディーラーの2枚目移行の手札はわからない）

	手札はAが1ポイント、2-10がそれぞれ2-10ポイント、J/Q/Kが10ポイントとして計算される。

	プレイヤーは手札を1枚追加するか、しないかを選択できる。
	手札を追加した場合、21ポイントを超えるとバーストとなり、ゲームに敗北する。
	プレイヤーはバーストするか、好きなタイミングで止めるまで手札にカードを追加できる。
	ディーラーは手札の合計ポイントが17以上になるまで山札を引き続ける。
	ディーラーの手札が21ポイントを超えた場合、バーストしてプレイヤーの勝利。
	ディーラーの手札が18以上21以下になったとき次の段階に移行する。

	プレイヤー・ディーラーの手札のポイントを比較して、大きいほうが勝利。

	ダブルダウン・スプリット・サレンダーなどの特殊ルールは無し。*/

	public static void main(String[] args) {

		System.out.println("ゲームを開始します。");

		//空の山札を作成
		List<Integer> deck = new ArrayList<Integer>();

		//山札をシャッフル
		shuffleDeck(deck);

		//プレイヤー・ディーラーの手札リストを作成
		List<Integer> player = new ArrayList<Integer>();
		List<Integer> dealer = new ArrayList<Integer>();

		//プレイヤー・ディーラーがカードを2枚引く
		for (int i = 0; i < 2; i++) {
			player.add(deck.get(0));
			deck.remove(0);
			dealer.add(deck.get(0));
			deck.remove(0);
		}

		//山札の進行状況を記録する変数deckCountを定義
		//何枚引いたか？
		int deckCount = 52 - deck.size();

		//プレイヤーの手札枚数を記録する変数playerHandsを定義
		int playerHands = player.size();

		//プレイヤー・ディーラーの手札のポイントを表示
		System.out.println("あなたの1枚目のカードは" + toDesc(player.get(0)));
		System.out.println("ディーラーの1枚目のカードは" + toDesc(dealer.get(0)));
		System.out.println("あなたの2枚目のカードは" + toDesc(player.get(1)));
		System.out.println("ディーラーの2枚めのカードは秘密です。");

		//プレイヤー・ディーラーのポイントを集計
		int playerPoint = sumPoint(player);
		System.out.println("あなたの現在のポイントは" + playerPoint + "です。");

		//プレイヤーがカードを引くフェーズ
		Scanner scanner = new Scanner(System.in);
		System.out.println("カードを引きますか？");
		System.out.println("1:はい 2:いいえ");
		int select = scanner.nextInt();
		for (; select == 1;) {
			player.add(deck.get(0));
			deck.remove(0);
			System.out.println(sumPoint(player));
			System.out.println("カードを引きますか？");
			System.out.println("1:はい 2:いいえ");
			select = scanner.nextInt();
		}
		playerPoint = sumPoint(player);

		//ディーラーがげ札を17以上にするまでカードを引くフェーズ
		int dealerPoint = sumPoint(dealer);
		while (dealerPoint < 17) {
			dealer.add(deck.get(0));
			deck.remove(0);
			dealerPoint = sumPoint(dealer);
		}

		//ポイントを比較する
		System.out.println("あなたのポイントは" + playerPoint);
		System.out.println("ディーラーのポイントは" + dealerPoint);

		if (playerPoint == dealerPoint || (isBusted(playerPoint) && isBusted(dealerPoint))) {
			System.out.println("引き分けです。");
		} else if (playerPoint > dealerPoint && (!isBusted(playerPoint) && isBusted(dealerPoint)) || isBusted(dealerPoint)) {
			System.out.println("勝ちました！");
		} else {
			System.out.println("負けました・・・");
		}

	}

	//山札（deck）に値を入れ、シャッフルするメソッド
	private static void shuffleDeck(List<Integer> deck) {

		//リストに1-52の連番を代入
		for (int i = 1; i < 53; i++) {
			deck.add(i);
		}

		//山札をシャッフル
		Collections.shuffle(deck);

		//リストの中身を確認（デバッグ用）
		for (int i : deck) {
			System.out.println(deck.indexOf(i) + " : " + i);
		}
	}

	//手札がバーストしているか判定するメソッド
	private static boolean isBusted(int point) {
		if (point >= 21) {
			return true;
		}
		return false;
	}

	//現在の合計ポイントを計算するメソッド
	private static int sumPoint(List<Integer> list) {
		int sumPoint = 0;
		for (int i : list) {
			sumPoint += toPoint(toNumber(i));
		}
		return sumPoint;

	}

	//山札の通し番号を得点計算用のポイントに変換するメソッド。J/Q/Kは10とする
	private static int toPoint(int num) {
		if (num >= 11) {
			return 10;
		} else {
			return num;
		}

	}

	//山札の数を（スート）の（ランク）の文字列に置き換えるメソッド
	private static String toDesc(int cardNumber) {
		String rank = toRank(toNumber(cardNumber));
		if (cardNumber >= 40) {
			return "ハートの" + rank;
		} else if (cardNumber >= 27) {
			return "ダイヤの" + rank;
		} else if (cardNumber >= 14) {
			return "スペードの" + rank;
		} else {
			return "クローバーの" + rank;
		}

	}

	//山札の数をカードの数に置き換えるメソッド
	private static int toNumber(int cardNumber) {
		if (cardNumber >= 40) {
			return cardNumber - 39;
		} else if (cardNumber >= 27) {
			return cardNumber - 26;
		} else if (cardNumber >= 14) {
			return cardNumber - 13;
		} else {
			return cardNumber;
		}
	}

	//カード番号をランク（AやJ,Q,K）に変換するメソッド
	private static String toRank(int number) {
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
