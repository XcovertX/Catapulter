package main.java.UserInterface;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.java.world.GameTile;

import org.fxmisc.richtext.InlineCssTextArea;

public class TextMapController {
	
	@FXML private BorderPane mapPane;
	
	@FXML private InlineCssTextArea map;
	
	@FXML private InlineCssTextArea roomDescription;
	
	@FXML private URL location;
	
	@FXML private ResourceBundle resources;
	
	public TextMapController() { }
	
	public BorderPane getMapPane() {
		
		return mapPane;
	}
	
	public InlineCssTextArea getMap() {
		
		return map;
	}
	
	public void setMapChars( String mapChars, ArrayList< ArrayList< TileChar > > tileChars ) {
		
		Text tempT = new Text( mapChars );
		tempT.setFont( new Font( "Consolas", 12) );
		StackPane tempSP = new StackPane( tempT );
		tempSP.layout();

		double roomlength = tempT.getLayoutBounds().getHeight();
		double roomWidth = tempT.getLayoutBounds().getWidth();
		
		map.clearStyle( 0, map.getLength() );
		map.deleteText( 0, map.getLength() );
		
		
		for( int i = 0; i < tileChars.size(); i++ ) {
			ArrayList< TileChar > row = tileChars.get(i);
			for( int j = 0; j < row.size(); j++ ) {
				TileChar tc = row.get(j);
				
				map.appendText( tc.getChar() );
				if( tc.getTileCharColor() != null ) {
					
					map.setStyle( map.getLength() - 3, map.getLength(), "-fx-fill: " + tc.getTileCharColor() + ";" );
					
				} 
			}
			map.appendText("\n");
		}

		map.setPrefWidth( roomWidth );
		map.setPrefHeight( roomlength );
		map.setDisable( true );
	}
	
	public void setRoomDescription( String description ) {
		
		roomDescription.deleteText( 0, roomDescription.getLength() );
		roomDescription.appendText( description );
		roomDescription.setDisable( true );
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
