package game;

import java.awt.Color;
import java.io.BufferedReader;
import java.util.*;     // required for ArrayList

import jade.Window;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import userInterface.GraphicalUserInterface;
import userInterface.ShaderNew;
import userInterface.TMXParser;
import userInterface.UserInterface;
import actor.Actor;
import actor.Human;
import environment.GameCalendar;
import gameObjects.ThingList;
import inputProcessor.InputProcessor;
import light.Light;
import light.RadiatingLight;
import mapBuilder.RoomBuilder;
import utility.SignReader;
import world.GameMap;
import world.GameRoom;
import world.GameTile;
import world.GameWorld;
import world.UpdateLight;
import world.UpdatePlayer;
import world.UpdateRoomTileAnimations;

public class Game {
    
	public static GameWorld currentWorld;
	public static GameMap currentMap;
	public static GameRoom currentRoom;
	public static GameTile currentTile;
    public static BufferedReader in;
    public static Game currentGame;
    public static GameCalendar calendar;
    
    public Stage window;
    public Parent root;
    public GraphicalUserInterface gui;

    // scene window
    private Window gameWindow;

	public static WorldReader worldReader;
    private String input;
    private String output;
    private UserInterface userInterface;
    private InputProcessor inputProcessor;
    private boolean roomChange = false;
    private Actor player;  // the player - provides 'first person perspective'
    
    public Game( boolean newGame ) {
    	
    	 if( newGame == true ) {
    		
    		// TODO build new game / load game selector

    		Game.currentGame = this;
    		
    		worldReader = new WorldReader();
    		
    		currentWorld =  worldReader.getWorld( "files/worlds/testtileworld", "testtileworld.json" );
    		currentMap   = ( GameMap )  ( currentWorld.getMaps().get( 0 ) );
    		currentRoom  = ( GameRoom ) ( currentMap.getRooms().get( 0 ) );
    		currentTile  = ( GameTile ) ( currentRoom.getTiles().get( 40 ) );
    		
    		gameWindow = Window.get();
            gameWindow.run();

    		
    		player = new Human( "player", "This is a player", currentTile, new ThingList(), " @ " );
    		
    		
//    		//test shader
//    		player.setLightSources( new ArrayList< Light >() );
//    		player.getLightSources().add( new RadiatingLight( 5, ( float ) 0.2, ( float ) 0.5, new Color( 22, 150, 150 ), "flicker" ) );
//    		player.setLightSource( true );
//
//    		ShaderNew s = new ShaderNew( "files/assets/shaders/default.shader" );
    		
//    		Ring ring = new Ring();
//    		ring.setName( "Ring of Might and Madness" );
//    		ring.setAltNames( new String[ 1 ] );
//    		ring.getAltNames()[ 0 ] = "ring";
//    		currentTile.getThings().add( ring );
    		
//    		Fire fire = new Fire();
//    		currentRoom.getTile( 20 ).addThing( fire );
//    		
//    		Revolver r = new Revolver();
//    		r.setName( "revolver" );
//    		player.getInventory().add( r );
    		
//    		Table table = new Table();
//    		table.setHeight( 3.0 );
//    		Skull skull = new Skull();
//    		skull.setHeight( 2.0 );
//    		
//    		table.addThing( skull );
//    		skull.setOnTopOf( true );
//    		currentRoom.getTile( 30 ).addThing( table );
    		
//    		currentWorld.setLocations();
//    		
//    		System.out.println( "Skull height: " + currentRoom.calculateTotalHeight( skull ) );
//    		System.out.println( "Table height: " + currentRoom.calculateTotalHeight( table ) );
    		
//    		currentRoom.setAllRoomLightSourceObjects();
//
//    		userInterface = new UserInterface( player );
//    		userInterface.getDisplay().setRoom( currentRoom );
//
//    		try {
//				new TMXParser( currentRoom );
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	        calendar = new GameCalendar( currentGame );

	        setInputProcessor( new InputProcessor() );
	        
    	} else {
    		
    	}
    }

    // access methods
    // map
    public GameMap getMap() { return currentMap; }

    public void setMap( GameMap aMap ) { currentMap = aMap; }

    // player
    public Actor getPlayer() { return player; }

    public void setPlayer( Actor aPlayer ) { player = aPlayer; }
    
    // test method
    public void changeScene() {
    	Pane p = new Pane();
    	Scene s = new Scene(p, 200, 200);
    	window.setScene(s);
    }
    
//    // test method
//    public void initializeGUI() {
//
//    	gui = new GraphicalUserInterface();
//    	gui.setGUI();
//    	
//    }

    public void showIntro(){
    	
        String s;
        SignReader welcome = new SignReader( "files/graphics", "welcome.txt" );
        s = welcome.getText();
        userInterface.println( s );
    }

    public Window getGameWindow() { return gameWindow; }

    public void setGameWindow(Window gameWindow) { this.gameWindow = gameWindow; }
    
    public void roomChange() {
    	this.roomChange = true;
    }
    
    public void render() { }
    
    public UserInterface getUI() {
    	return this.userInterface;
    }

	public InputProcessor getInputProcessor() {
		return inputProcessor;
	}

	public void setInputProcessor(InputProcessor inputProcessor) {
		this.inputProcessor = inputProcessor;
	}
	
	public void updateWorld() {
		new UpdatePlayer().run();
		new UpdateLight().run( Game.currentRoom );
		new UpdateRoomTileAnimations().run( Game.currentRoom );
		currentWorld.allLists( "environment" );
//		currentWorld.allLists( "weather" );
		currentWorld.allLists( "actors" );
	}
	
	
}