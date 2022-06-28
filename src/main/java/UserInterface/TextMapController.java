package main.java.UserInterface;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.fxmisc.richtext.InlineCssTextArea;

public class TextMapController {
	
	@FXML private BorderPane mapPane;
	
	@FXML private InlineCssTextArea map;
	
	@FXML private URL location;
	
	@FXML private ResourceBundle resources;
	
	public TextMapController() { }
	
	public BorderPane getMapPane() {
		
		return mapPane;
	}
	
	public InlineCssTextArea getMap() {
		
		return map;
	}
	
	public void setMapChars( String mapChars, int width, int height ) {
		
		Text tempT = new Text( mapChars );
		tempT.setFont( new Font("Consolas", 18) );
		StackPane tempSP = new StackPane( tempT );
		tempSP.layout();

		double roomlength = tempT.getLayoutBounds().getHeight();
		double roomWidth = tempT.getLayoutBounds().getWidth();
		
		System.out.println( "Room length: " + roomlength );
		System.out.println( "Room width: " + roomWidth );
		

		map.deleteText(0, map.getLength());
		map.appendText( mapChars );
		map.setPrefWidth( roomWidth );
		map.setPrefHeight( roomlength );
		map.setDisable(true);
	}
	
	@FXML
	private void initialize() {
		
		System.out.println( "TextMapController Initialized" );
		
		Platform.runLater(() -> {
			
			map.setPrefWidth( 350 );
			map.setPrefHeight( 350 );
			
		});
	}

}
