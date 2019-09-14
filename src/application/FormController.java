package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import deck.Deck;
import gamer.Dealer;
import gamer.Gamer;
import gamer.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import presenter.Judger;
import presenter.PlayerData;
import presenter.ScoreMarker;

public class FormController implements Initializable {

	@FXML
	private TextField field_name;
	@FXML
	private Button button_enter;
	@FXML
	private Label label_output;
	@FXML
	private Label label_output_name;

	private PlayerData playerData = PlayerData.getInstance();





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
		playerData.setDeck(new Deck());

		//プレイヤー・ディーラーを作成
		playerData.setDealer(new Dealer("トランプマン"));

		ScoreMarker marker = ScoreMarker.getInstance();
		Judger judger = Judger.getInstance();

	}

	@FXML
	public void onButtonClicked(ActionEvent event) {
		//TextFieldにプレイヤーの名前を入力して、ボタンを押すとゲームが始まる
		Gamer player = new Player(field_name.getText());
		playerData.setPlayer(player);
		try {
			Scene scene = ((Node) event.getSource()).getScene();
			Window window = scene.getWindow();
			window.hide();
			showSecondWindow();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void showSecondWindow() throws IOException {

		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("SecondWindow.fxml"));
		System.out.println("ssss");
		Scene scene = new Scene(root, 400, 400);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		Stage stage = new Stage();
		stage.setTitle("SecondWindow");
		stage.setScene(scene);
		stage.showAndWait();
	}

}
