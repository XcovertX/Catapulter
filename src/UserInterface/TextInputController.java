package UserInterface;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import game.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TextInputController {
	
	@FXML private TextField textInput;
	
	@FXML private URL location;
	
	@FXML private ResourceBundle resources;
	
	// command memory -- will remember 100 commands
	ArrayList<String> recentUsed = new ArrayList<String>();
	int recentUsedID = 0;
	int recentUsedMaximum = 100; 
	
	public TextInputController() { };
	
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
    	
    	// This ensures no non-null, empty input streams are generated
    	if( userInText.equals( "" ) ) {
    		userInText = " ";
    	}
    	
    	Game.currentGame.getUI().setText( userInText );
    	
		Game.currentGame.getUI().setCommands( textInput.getText().split( " " ) );
		
		try {
		
			Game.in = new BufferedReader( new InputStreamReader( convertToInputStream( userInText ) ) );
			logCommand( userInText );
		
		} catch ( IOException e1 ) {
			
			e1.printStackTrace();
		} 
		
		Game.currentGame.getUI().logic();
    }
    
    // converts type String to InputStream
	public InputStream convertToInputStream( String text ) throws IOException {

	    return new ByteArrayInputStream( text.getBytes() );
	}
	
    public void logCommand( String text ) {

		recentUsed.add( text );
		recentUsedID = 0;
		textInput.selectAll();
			
    }
    
    
    public void keyPressedPerform( KeyEvent e ) {
    	
    	System.out.println( e.getCode() );
    	if( e.getCode() == KeyCode.UP) {
			if( recentUsedID < ( recentUsedMaximum - 1 ) && recentUsedID < ( recentUsed.size() - 1 ) ) {
				
				recentUsedID++;
			}
		
			textInput.setText( recentUsed.get( recentUsed.size() - 1 - recentUsedID ) );
		
		} else if( e.getCode() == KeyCode.DOWN ) {
			if( recentUsedID > 0 ) {
				
				recentUsedID--;
			}
			textInput.setText( recentUsed.get( recentUsed.size() - 1 - recentUsedID ) );
		}
    }
    
	
	
}
