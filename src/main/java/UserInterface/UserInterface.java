package main.java.UserInterface;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.JTextField;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.actor.Actor;
import main.java.game.Game;
import main.java.gameObjects.ThingList;
import main.java.world.GameMap;
import main.java.world.GameRoom;
import main.java.world.GameTile;
import main.java.world.GameWorld;

public class UserInterface implements ActionListener {
	
	public BufferedReader in;
	private GameWorld currentWorld;
	private GameMap currentMap;
	private GameRoom currentRoom;
	private GameTile currentTile;
	private Display display;
	private ConsoleLogic consLog;
	
	// console input textfield pointer
	private JTextField inputTextField;
	
	// input text from console text field
	private String text;
	private String jfxText;
	private String[] commands;  
	
	private TextMapController guiMapController;
	private Controller guiController;
	private ConsoleController guiConsoleController;
	private TextInputController guiTextInputController;
	private KeyboardController guiKeyboardController;
	private BorderPane mapPane;	
	private BorderPane textInputPane;
	private BorderPane consolePane;
	private BorderPane keyboardPane;
	
	public UserInterface( Actor player ) {
		
		this.currentTile = player.getTile();
		this.currentRoom = currentTile.getRoom();
		this.currentMap = currentRoom.getMap();
		this.currentWorld = currentMap.getWorld();
		
		this.display = new Display();

		initializeGUI(); //javafx gui
		
		consLog = new ConsoleLogic( display, guiController );

		inputTextField = display.getInputField();
		inputTextField.addActionListener( new ActionListener() {
		
		@Override
		public void actionPerformed( ActionEvent e ) {
			
			text = inputTextField.getText();
			
			commands = inputTextField.getText().split( " " );
			
			try {
				
				Game.in = new BufferedReader( new InputStreamReader( convertToInputStream( text ) ) );
			
			} catch (IOException e1) {
				
				e1.printStackTrace();
			} 

			logic();
		}
	});
	
	}
	
	void render(Graphics2D g, TmxParser tmx){
		for (int j = 0; j < tmx.map_rows; j++) {
			for (int i = 0; i < tmx.map_cols; i++) {
				g.drawImage(tmx.tiles[j][i], null, i * tmx.size, j * tmx.size);
		    }
		}
	}
	
	// global logic
	public void logic() {
		
		if(text.length() > 0) {

			consLog.consoleFeatures(text);
			
			consLog.doCommands(commands, text); 
		}
	}

	public InputStream convertToInputStream( String text ) throws IOException {

	    return new ByteArrayInputStream( text.getBytes() );
	}
	
	public boolean trace() {
		
		return consLog.getTrace();
	}
	
	public void println(String s){
		
		consLog.println(s, trace() );
	}
	
	public void print(String s){
		
		consLog.print(s, trace() );
	}
	
	public void printlnColor(String s, Color c) {
		
		consLog.println(s, trace(), c);
	}
	
	public void printColor(String s, Color c) {
		
		consLog.print(s, trace(), c);
	}
	
	public Display getDisplay() {
		
		return this.display;
	}
	
	public void initializeGUI() {
		
		Game.currentGame.window = new Stage();

		FXMLLoader mapLoader =  new FXMLLoader( getClass().getClassLoader().getResource( "Map.fxml" ) );
		try {
			mapPane = mapLoader.load();
		} catch (IOException e) {
			System.out.println( "Can not find folder" );
			e.printStackTrace();
		}
		
		FXMLLoader textInputLoader =  new FXMLLoader( getClass().getClassLoader().getResource( "TextInput.fxml" ) );
		try {
			textInputPane = textInputLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FXMLLoader consoleLoader =  new FXMLLoader( getClass().getClassLoader().getResource( "Console.fxml" ) );
		try {
			consolePane = consoleLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FXMLLoader keyboardLoader =  new FXMLLoader( getClass().getClassLoader().getResource( "SmallKeyboard.fxml" ) );
		try {
			keyboardPane = keyboardLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FXMLLoader fxmll =  new FXMLLoader( getClass().getClassLoader().getResource( "Main.fxml" ) );
		try {
			Game.currentGame.root = fxmll.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// set main controller reference
		setGuiController( ( Controller ) fxmll.getController() );
		
		// set nested controllers
		setTextMapController( mapLoader.getController() );
		setTextInputController( textInputLoader.getController() );
		setConsoleController( consoleLoader.getController() );
		setKeyboardController( keyboardLoader.getController() );
		
		Game.currentGame.gui = new GraphicalUserInterface( Game.currentGame.root );
		
		Game.currentGame.window.setScene( Game.currentGame.gui );
		
		Game.currentGame.gui.addEventFilter( KeyEvent.KEY_PRESSED, new EventHandler< KeyEvent >() {

			@Override
			public void handle( KeyEvent event ) {
				
				guiTextInputController.keyPressedPerform( event );
				guiKeyboardController.keyPressedPerform( event );
				
			}
			
		});
		
		Game.currentGame.gui.addEventFilter( KeyEvent.KEY_RELEASED, new EventHandler< KeyEvent >() {

			@Override
			public void handle( KeyEvent event ) {
			
				guiKeyboardController.keyReleasedPerform( event );
				
			}
			
		});
		
		String css = this.getClass().getClassLoader().getResource("application.css").toExternalForm();
		Game.currentGame.gui.getStylesheets().add(css);
		
		String tabPane_css = this.getClass().getClassLoader().getResource("tabPane.css").toExternalForm();
		Game.currentGame.gui.getStylesheets().add(tabPane_css);
		
		String keyboard_css = this.getClass().getClassLoader().getResource("keyboard.css").toExternalForm();
		Game.currentGame.gui.getStylesheets().add(keyboard_css);
		
		guiController.initializeMapHolder();
		guiController.initializeConsoleHolder();
		guiController.initializeKeyboardHolder();
		
		//test
		TmxParser tmx;
		BufferedImage bImage;
		Graphics2D g;
		File file = new File("files/map.png");
		try {
			tmx = new TmxParser("files/test_tile_room.tmx");
			bImage = tmx.tiles[0][0];
			ImageIO.write(bImage, "png", file);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found");
			e2.printStackTrace();
		}
		
		
		
		// test
		ThingList playerInventory = Game.currentGame.getPlayer().getInventory();
		guiController.setInventoryList( playerInventory );
		
		// test room description
		String roomDescription = Game.currentGame.getPlayer().getTile().getRoom().getDescription();
		guiController.setRoomDescription( roomDescription );
		
		Game.currentGame.window.setResizable( true );
		Game.currentGame.window.setFullScreen( true );
		Game.currentGame.window.show();

	}

	public TextMapController getGuiMapController() {
		return guiMapController;
	}

	public void setGuiMapController(TextMapController guiMapController) {
		this.guiMapController = guiMapController;
	}

	public Controller getGuiController() {
		return guiController;
	}

	public void setGuiController(Controller guiController) {
		this.guiController = guiController;
	}

	public String getText() {
		return text;
	}

	public void setText( String jfxText ) {
		this.text = jfxText;
	}
	
	public String[] getCommands() {
		return commands;
	}
	
	public void setCommands( String[] inputCommands ) {
		commands = inputCommands;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public ConsoleController getConsoleController() {
		return guiConsoleController;
	}

	public void setConsoleController( ConsoleController consoleController ) {
		this.guiConsoleController = consoleController;
		guiController.setConsoleController( guiConsoleController );
	}

	public TextInputController getTextInputController() {
		return guiTextInputController;
	}

	public void setTextInputController(TextInputController textInputController) {
		this.guiTextInputController = textInputController;
		guiController.setTextInputController( guiTextInputController );
	}
	
	public TextMapController getTextMapController() {
		return guiMapController;
	}

	public void setTextMapController( TextMapController textMapController ) {
		this.guiMapController = textMapController;
		guiController.setTextMapController( guiMapController );
	}

	public KeyboardController getKeyboardController() {
		return guiKeyboardController;
	}

	public void setKeyboardController(KeyboardController keyboardController) {
		this.guiKeyboardController = keyboardController;
		guiController.setKeyboardController( guiKeyboardController );
	}
}