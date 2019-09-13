package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presenter.Message;

public class FormController {

	Message msg = Message.getInstance();

	@FXML private TextField field_name;
	@FXML private Button button_enter;
	@FXML private Label label_output;

	@FXML public void onButtonClicked() {
		label_output.setText("Hello " + field_name.getText() + " !!");
		label_output.setText(msg.showStartGameMsg());
	}

}
