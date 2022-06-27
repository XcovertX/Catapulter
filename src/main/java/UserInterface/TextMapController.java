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
	
	public void setMapChars( String mapChars ) {
		
		Text tempT = new Text( mapChars );
		tempT.setFont( new Font("Consolas", 38) );
		StackPane tempSP = new StackPane( tempT );
		tempSP.layout();

		double roomlength = tempT.getLayoutBounds().getWidth();
		double roomWidth = tempT.getLayoutBounds().getHeight();
		
		System.out.println( "Room length: " + roomlength + 60);
		System.out.println( "Room width: " + roomWidth + 60);
		

		map.deleteText(0, map.getLength());
		map.appendText( mapChars );
		map.setPrefWidth( roomlength );
		map.setPrefWidth( roomWidth );
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
