package UserInterface;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TextInputController {
	
	@FXML private TextField textInput;
	
	@FXML private URL location;
	
	@FXML private ResourceBundle resources;
	
	public TextInputController() { }
	
	public TextField getTextInput() {
		
		return textInput;
	}
	
	
	public void setText( String text ) {

		textInput.setText( text );
	}
	
	@FXML
	private void initialize() {
		
		System.out.println("TextFieldController Initialized");
		
		Platform.runLater(() -> {
			
		});
	}
}
