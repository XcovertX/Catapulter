package UserInterface;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import game.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
		
		System.out.println("TextInputController Initialized");
		
		Platform.runLater(() -> {
			
		});
	}
	
    @FXML
    void userIn( ActionEvent event ) {
    	
    	String userInText = textInput.getText();
    	
    	Game.currentGame.getUI().setText( userInText );
    	
		Game.currentGame.getUI().setCommands( textInput.getText().split( " " ) );
		
		try {
			
			Game.in = new BufferedReader( new InputStreamReader( convertToInputStream( userInText ) ) );
		
		} catch (IOException e1) {
			
			e1.printStackTrace();
		} 
		
		Game.currentGame.getUI().logic();
    }
    
    // converts type String to InputStream
	public InputStream convertToInputStream( String text ) throws IOException {

	    return new ByteArrayInputStream( text.getBytes() );
	}
	
	
}
