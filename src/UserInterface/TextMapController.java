package UserInterface;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class TextMapController {
	
	@FXML private TextArea map;
	
	@FXML private URL location;
	
	@FXML private ResourceBundle resources;
	
	public TextMapController() { }
	
	public TextArea getMap() {
		
		return map;
	}
	
	public void setMapChars( String mapChars ) {

		map.setText( mapChars );
	}
	
	@FXML
	private void initialize() {
		
		System.out.println("TextMapController Initialized");
		
		Platform.runLater(() -> {
			
		});
	}

}
