package userInterface;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import gameObjects.Thing;
import gameObjects.ThingList;

public class Controller {
	
	@FXML private URL location;
	@FXML private ResourceBundle resources;
	
	// GUI areas
	@FXML private BorderPane mapHolder;
	@FXML private BorderPane consoleHolder;
	@FXML private TextArea guiMap;
	@FXML private BorderPane mainHolder;
	@FXML private BorderPane keyboardHolder;
	@FXML private ListView< Thing > listInventory;
	
	// Controllers
	@FXML private TextMapController tmc;
	@FXML private TextInputController tip;
	@FXML private ConsoleController cc;
	@FXML private KeyboardController kc;
	
	
	public Controller() { }
	
	public void setMapChars( String map, ArrayList< ArrayList< TileChar > > tileChars  ) {

		Platform.runLater(() -> {
			
			tmc.setMapChars( map, tileChars );
			
		});
	}
	
	public void setRoomDescription( String description ) {

		Platform.runLater(() -> {
			
			tmc.setRoomDescription( description );
		});
	}
	
	public void setTileMap() {

		Platform.runLater(() -> {
			
			try {
				tmc.setTileMap();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	public void setInventoryList( ThingList testList  ) {

		Platform.runLater(() -> {
			
			listInventory.getItems().addAll( testList );
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

			mapHolder.setCenter( tmc.getMapPane() );
			
		});
	}
	
	public void initializeConsoleHolder() {
		
		Platform.runLater(() -> {
			
			consoleHolder.setCenter( cc.getConsole() );
			consoleHolder.setBottom( tip.getTextInput() );
			tip.getTextInput().requestFocus();
			
		});
	}
	
	public void initializeKeyboardHolder() {
		
		Platform.runLater(() -> {
			
			keyboardHolder.setBottom( kc.getKeyboard() );
			
		});
	}
	
//	public InlineCssTextArea getGuiMap() {
//		
//		return tmc.getMap();	
//	}
	
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
