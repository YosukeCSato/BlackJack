import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

		System.out.println("あなたの現在のポイントは" + playerPoint + "です。");

		//プレイヤーがカードを引くフェーズ

		//ディーラーがげ札を17以上にするまでカードを引くフェーズ

		//ポイントを比較する
		System.out.println("あなたのポイントは" + "playerPoint");
		System.out.println("ディーラーのポイントは" + dealerPoint);




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

}
