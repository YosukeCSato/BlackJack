package application;

import java.net.URL;
import java.util.ResourceBundle;

import deck.Deck;
import gamer.Dealer;
import gamer.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presenter.Judger;
import presenter.Message;
import presenter.ScoreMarker;

public class FormController implements Initializable {

	@FXML
	private TextField field_name;
	@FXML
	private Button button_enter;
	@FXML
	private Label label_output;
	private Message msg = Message.getInstance();

	/** ************************************************************
	 * 初期化処理
	 * 画面表示時に行いたい処理を実装する。
	 * @param location
	 * @param resources
	 * *************************************************************/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//空の山札を作成
		//山札をシャッフル
		//deckをnew時点でコンストラクタでシャッフルをするように実装
		Deck deck = new Deck();

		//プレイヤー・ディーラーを作成
		Dealer dealer = new Dealer("トランプマン");

		ScoreMarker marker = ScoreMarker.getInstance();
		Judger judger = Judger.getInstance();

	}

	@FXML
	public void onButtonClicked() {
		//TextFieldにプレイヤーの名前を入力して、ボタンを押すとゲームが始まる
		Player player = new Player(field_name.getText());
		label_output.setText(msg.showStartGameMsg());
	}

}
