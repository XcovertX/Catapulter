package UserInterface;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ConsoleController {
	
	@FXML private TextArea console;

	@FXML private URL location;
	
	@FXML private ResourceBundle resources;
	

	
	public ConsoleController() { 
	
	};
	
	public TextArea getConsole() {
		
		return console;
	}
	
	public void setText( String text ) {

		console.setText( text );
	}
	
	@FXML
	private void initialize() {
		
		System.out.println("ConsoleController Initialized");
		
		Platform.runLater(() -> {
			
		});
	}
	
	/*
	 * Inserts text into the console and auto scrolls to the bottom.
	 */
	public void insertText( String s ) {
		
		console.setText( console.getText() + s );
		console.positionCaret(s.length());
		console.appendText("");
		console.setScrollTop( Double.MAX_VALUE );

	}
	
	
}
