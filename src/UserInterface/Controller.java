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
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;

public class Controller {
	
	@FXML private URL location;
	@FXML private ResourceBundle resources;
	
	// GUI areas
	@FXML private BorderPane mapHolder;
	@FXML private TextArea guiMap;
	
	// Controllers
	@FXML private TextMapController tmc;
	
	public Controller() { }
	
	public TextArea getGuiMap() {
		
		return guiMap;	
	}
	
//	public void setMapChars( String map ) {
//
//		Platform.runLater(() -> {
//			guiMap.setText( map );
//		});
//	}
	
	public void setMapChars( String map ) {

		Platform.runLater(() -> {
			
			tmc.setMapChars( map );
		});
	}
	
	public void initializeMap() {
		
		Platform.runLater(() -> {
			
			mapHolder.setCenter( tmc.getMap() );
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
	
	public void setTextMapController( TextMapController tmc ) {
		
		this.tmc = tmc;
	}
}
