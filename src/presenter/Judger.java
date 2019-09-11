package presenter;

import gamer.Gamer;

public class Judger {

	private static Judger judger = new Judger();
	private Message msg = Message.getInstance();
	private ScoreMarker marker = ScoreMarker.getInstance();

	private Judger() {

	}

	public static Judger getInstance() {
		return judger;
	}

	public void judge(Gamer player, Gamer dealer) {

		//ポイントを比較する
		msg.showPointMsg(player);
		msg.showPointMsg(dealer);

		int playerPoint = player.getPoint();
		int dealerPoint = dealer.getPoint();

		if(player.getPoint() == dealer.getPoint()
				|| (marker.isBusted(playerPoint) && marker.isBusted(dealerPoint))) {
			System.out.println("引き分けです。");
		} else if (playerPoint > dealerPoint
				|| (!marker.isBusted(playerPoint) && marker.isBusted(dealerPoint))
				|| marker.isBusted(dealerPoint)) {
			System.out.println("勝ちました！");
		} else {
			System.out.println("負けました・・・");
		}
	}
}

