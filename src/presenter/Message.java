package presenter;

import java.util.Scanner;

import deck.Deck;
import gamer.Gamer;

public class Message {

	private static Message msg = new Message();
	private Scanner scanner = new Scanner(System.in);
	private CardOrganizer organizer = CardOrganizer.getInstance();

	private Message() {
	}

	public static Message getInstance() {
		return msg;
	}

	public String showStartGameMsg() {
		return "ゲームを開始します";
	}

	public void showCardMsg(Gamer gamer, int number) {
		int rawNumber = gamer.getList().get(number - 1);
		System.out.println(gamer.getName() + "の" + number + "枚目のカードは" + organizer.toDesc(rawNumber) + "です。");
	}

	public void showDealersSecondCardMsg() {
		System.out.println("ディーラーの2枚めのカードは秘密です。");
	}

	public void showPointMsg(Gamer gamer) {
		System.out.println(gamer.getName() + "の現在のポイントは" + gamer.getPoint() + "です。");
	}

	public void showAskTakeACardMsg(Gamer gamer, Deck deck) {
		System.out.println("カードを引きますか？");
		System.out.println("1:はい 2:いいえ");
		int i = this.scanner.nextInt();
		while (true) {
			switch (i) {
			case 1:
				gamer.takeACard(deck);
			case 2:
				break;
			default:
				System.out.println("正しい数字を入力してください");
			}
		}
	}


}
