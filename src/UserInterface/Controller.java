package UserInterface;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.shape.Circle;

public class Controller implements Initializable {
	
	@FXML
	private ProgressBar healthProgress;
	
	@FXML
	private ProgressBar techProgress;
	
	@FXML
	private ProgressBar staminaProgress;
	
	@FXML
	private Button shiftButton;
	
	@FXML
	private Label healthLabel;
	
	@FXML
	private Label techLabel;
	
	@FXML
	private Label staminaLabel;
	
	public double hProgress;
	
	public double tProgress;
	
	public double sProgress;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		
	}
	
	public void increaseProgress() {
		
		hProgress = hProgress - .1;
		healthProgress.setProgress( hProgress );

		if( hProgress < .5 ) {
			healthProgress.setStyle( "-fx-accent: red;" );
		}
	}
	
	public void keyPressed() {
		
		shiftButton.setStyle( "-fx-text-fill: #000c01;" );
		
	}
	
	public void keyReleased( Button b ) {
		
		b.setStyle( "-fx-text-fill: #15fc03;" );
	}
}
