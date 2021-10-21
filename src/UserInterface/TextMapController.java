package UserInterface;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class TextMapController {
	
	@FXML
	private TextArea guiMap;
	
	@FXML
	private URL location;
	
	@FXML
	private ResourceBundle resources;
	
	public TextMapController() {
	
	}
	

	
	@FXML
	public TextArea getGuiMap() {
		
		return guiMap;
		
	}
	
	
	public void setMapChars( String mapChars ) {
		guiMap.setText( mapChars );
	}

//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//		// TODO Auto-generated method stub
//		
//	}

}
