package UserInterface;

import java.net.URL;
import java.util.ResourceBundle;

import game.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Circle;

public class Controller {
//	
//	@FXML
//	private TextMapController tmc;
	
	@FXML
	private TextArea guiMap;
	
	@FXML
	private URL location;
	
	@FXML
	private ResourceBundle resources;
	
	public Controller() {
	
	}
	
	public TextArea getGuiMap() {
		
		return guiMap;
		
	}
	
	
	public void setMapChars() {

		Platform.runLater(() -> {
			guiMap.setText( Game.currentGame.mapChars );
		});
	}
	
	
	@FXML
	private void initialize() {
		
		System.out.println("Controller Initialized");
		
		Platform.runLater(() -> {
			
		});
	}
	
	
//	public void setMap( String map ) {
//		
//		tmc.setMapChars( map );
//	}
}
