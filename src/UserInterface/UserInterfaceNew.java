package UserInterface;

import world.GameWorld;
import world.GameMap;
import world.GameRoom;
import world.GameTile;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JTextField;

import game.Game;
import gameObjects.Actor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UserInterfaceNew implements ActionListener, KeyListener {
	
	public BufferedReader in;
	private GameWorld currentWorld;
	private GameMap currentMap;
	private GameRoom currentRoom;
	private GameTile currentTile;
	private DisplayNew display;
	private ConsoleLogicNew consLog;
	
	// console input textfield pointer
	private JTextField inputTextField;
	
	// input text from console text field
	private String text;
	private String[] commands;  
	
	private TextMapController guiMapController;
	
	public UserInterfaceNew( Actor player ) {
		
		this.currentTile = player.getTile();
		this.currentRoom = currentTile.getRoom();
		this.currentMap = currentRoom.getMap();
		this.currentWorld = currentMap.getWorld();
		
		
		
		this.display = new DisplayNew();
		
		initializeGUI(); //javafx gui
		
		
		consLog = new ConsoleLogicNew( display );

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
	
	//  implementation of Key Listener to return key press events 
	inputTextField.addKeyListener(new KeyListener() {
		
		@Override
		public void keyPressed(KeyEvent e) {
			consLog.keyPressedPerform(e);	
		}
		@Override
		public void keyReleased(KeyEvent e) {}
		@Override
		public void keyTyped(KeyEvent e) {}	
		
		});
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

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	public DisplayNew getDisplay() {
		
		return this.display;
	}
	
	public void initializeGUI() {
		
		try {
			
			Game.currentGame.window = new Stage();
//			System.out.println( getClass().getClassLoader().getResource( "Main.fxml" ).getPath() );
			FXMLLoader fxmll =  new FXMLLoader( getClass().getClassLoader().getResource( "Main.fxml" ) );
			Game.currentGame.root = fxmll.load();
			Game.currentGame.gui = new GraphicalUserInterface( Game.currentGame.root );
			Game.currentGame.window.setScene( Game.currentGame.gui );
			
			System.out.println( getClass().getClassLoader().getResource("application.css").getPath() );
			String css = this.getClass().getClassLoader().getResource("application.css").toExternalForm();
			Game.currentGame.gui.getStylesheets().add(css);
			
			String tabPane_css = this.getClass().getClassLoader().getResource("tabPane.css").toExternalForm();
			Game.currentGame.gui.getStylesheets().add(tabPane_css);
			
			String keyboard_css = this.getClass().getClassLoader().getResource("keyboard.css").toExternalForm();
			Game.currentGame.gui.getStylesheets().add(keyboard_css);
			
			guiMapController = ( TextMapController ) fxmll.getController();
			
			Game.currentGame.window.setResizable( true );
			Game.currentGame.window.setFullScreen( true );
			Game.currentGame.window.show();
		
		} catch ( IOException e2 ) {
			
			e2.printStackTrace();
		}
		

	}

	public TextMapController getGuiMapController() {
		return guiMapController;
	}

	public void setGuiMapController(TextMapController guiMapController) {
		this.guiMapController = guiMapController;
	}
}