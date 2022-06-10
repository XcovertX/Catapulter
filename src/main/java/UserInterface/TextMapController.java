package main.java.UserInterface;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class TextMapController {
	
	@FXML private BorderPane mapPane;
	
	@FXML private TextArea map;
	
	@FXML private URL location;
	
	@FXML private ResourceBundle resources;
	
	public TextMapController() { }
	
	public BorderPane getMapPane() {
		
		return mapPane;
	}
	
	public TextArea getMap() {
		
		return map;
	}
	
	public void setMapChars( String mapChars ) {
		
		Text tempT = new Text(mapChars);
		tempT.setFont( map.getFont() );
		StackPane tempSP = new StackPane(tempT);
		tempSP.layout();

		double roomlength = tempT.getLayoutBounds().getWidth() + 60;
		double roomWidth = tempT.getLayoutBounds().getHeight() + 60;
		
		System.out.println( "Room length: " + roomlength);
		System.out.println( "Room width: " + roomWidth);
		
		map.setPrefWidth(roomlength);
		map.setPrefHeight(roomWidth);
		map.setText( mapChars );
	}
	
	@FXML
	private void initialize() {
		
		System.out.println("TextMapController Initialized");
		
		Platform.runLater(() -> {
			
			map.setPrefWidth(350);
			map.setPrefHeight(350);
			
		});
	}

}
