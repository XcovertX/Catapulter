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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;

public class Controller {
	
	@FXML private URL location;
	@FXML private ResourceBundle resources;
	
	// GUI areas
	@FXML private BorderPane mapHolder;
	@FXML private BorderPane consoleHolder;
	@FXML private TextArea guiMap;
	@FXML private BorderPane mainHolder;
	
	// Controllers
	@FXML private TextMapController tmc;
	@FXML private TextInputController tip;
	@FXML private ConsoleController cc;
	@FXML private KeyboardController kc;
	
	
	public Controller() { }
	
	public void setMapChars( String map ) {

		Platform.runLater(() -> {
			
			tmc.setMapChars( map );
		});
	}
	
	// sub-controller initializers
	@FXML
	private void initialize() {
		
		System.out.println("Controller Initialized");
		
		Platform.runLater(() -> { });
	}
	
	public void initializeMapHolder() {
		
		Platform.runLater(() -> {
			
			mapHolder.setCenter( tmc.getMap() );
			
		});
	}
	
	public void initializeConsoleHolder() {
		
		Platform.runLater(() -> {
			
			consoleHolder.setCenter( cc.getConsole() );
			consoleHolder.setBottom( tip.getTextInput() );
		
			
		});
	}
	
	public void initializeKeyboardHolder() {
		
		Platform.runLater(() -> {
			
			mainHolder.setBottom( kc.getKeyboard() );
			
		});
	}
	
	public TextArea getGuiMap() {
		
		return tmc.getMap();	
	}
	
	public TextArea getGuiConsole() {
		
		return cc.getConsole();	
	}
	
	public TextField getGuiTextInput() {
		
		return tip.getTextInput();	
	}
	
	public void setTextMapController( TextMapController tmc ) {
		
		this.tmc = tmc;
	}
	
	public void setTextInputController( TextInputController tip ) {
		
		this.tip = tip;
	}
	
	public void setConsoleController( ConsoleController cc ) {
		
		this.cc = cc;
	}
	
	public void setKeyboardController( KeyboardController kc ) {
		
		this.kc = kc;
	}
	
	public void insertText( String s ) {
		
		cc.insertText( s );
	}
}
