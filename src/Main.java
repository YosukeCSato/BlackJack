import java.util.Scanner;

import deck.Deck;
import gamer.Dealer;
import gamer.Player;
import presenter.Judger;
import presenter.Message;
import presenter.ScoreMarker;

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
		//山札をシャッフル
		//deckをnew時点でコンストラクタでシャッフルをするように実装
		Deck deck = new Deck();

		//プレイヤー・ディーラーを作成
		Player player = new Player("わいざ");
		Dealer dealer = new Dealer("トランプマン");

		Message msg = Message.getInstance();
		ScoreMarker marker = ScoreMarker.getInstance();
		Judger judger = Judger.getInstance();

		msg.showStartGameMsg();

		//プレイヤー・ディーラーがカードを2枚引く
		for (int i = 0; i < 2; i++) {
			player.takeACard(deck);
			dealer.takeACard(deck);
		}

		//山札の進行状況を記録する変数deckCountを定義
		//何枚引いたか？
		//int deckCount = 52 - deck.getDeck().size();

		//プレイヤーの手札枚数を記録する変数playerHandsを定義
		int playerHands = player.getList().size();

		//プレイヤー・ディーラーの手札のポイントを表示
		msg.showCardMsg(player, 1);
		msg.showCardMsg(dealer, 1);
		msg.showCardMsg(player, 2);
		msg.showDealersSecondCardMsg();

		//プレイヤー・ディーラーのポイントを集計
		player.setPoint(marker.sumPoint(player));
		msg.showPointMsg(player);

		//プレイヤーがカードを引くフェーズ
		Scanner scanner = new Scanner(System.in);
		System.out.println("カードを引きますか？");
		System.out.println("1:はい 2:いいえ");
		int select = scanner.nextInt();
		for (; select == 1;) {
			player.takeACard(deck);
			msg.showCardMsg(player, player.getList().size());
			marker.sumPoint(player);
			msg.showPointMsg(player);
			System.out.println("カードを引きますか？");
			System.out.println("1:はい 2:いいえ");
			select = scanner.nextInt();
		}
		player.setPoint(marker.sumPoint(player));

		//ディーラーがげ札を17以上にするまでカードを引くフェーズ
		dealer.setPoint(marker.sumPoint(dealer));
		while (dealer.getPoint() < 17) {
			dealer.takeACard(deck);
			dealer.setPoint(marker.sumPoint(dealer));
		}

		judger.judge(player, dealer);

	}

}
