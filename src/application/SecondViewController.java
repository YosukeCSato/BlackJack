package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import presenter.Message;
import presenter.PlayerData;

public class SecondViewController implements Initializable {

	@FXML
	private TextField field_name;
	@FXML
	private Button button_enter;
	@FXML
	private Label label_output;
	@FXML
	private Label label_output_name;

	private Message msg = Message.getInstance();

	private PlayerData playerData = PlayerData.getInstance();



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		label_output.setText(msg.showStartGameMsg());
		label_output_name.setText(playerData.getPlayer().getName() + "さん！");

	}

	@FXML
	void onCloseAction(ActionEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Window window = scene.getWindow();
		window.hide();
	}

}
