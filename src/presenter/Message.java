package presenter;

import gamer.Gamer;

public class Message {

	private static Message msg = new Message();
	private CardOrganizer organizer = CardOrganizer.getInstance();

	private Message() {
	}

	public static Message getInstance() {
		return msg;
	}

	public void showStartGameMsg() {
		System.out.println("ゲームを開始します");
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

}
