package main.java.game;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;     // required for ArrayList

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.UserInterface.GraphicalUserInterface;
import main.java.UserInterface.TMXParser;
import main.java.UserInterface.TSX;
import main.java.UserInterface.UserInterface;
import main.java.actor.Actor;
import main.java.actor.Cat;
import main.java.actor.Human;
import main.java.actor.NonPlayerActor;
import main.java.application.GUI;
import main.java.environment.GameCalendar;
import main.java.gameObjects.Fire;
import main.java.gameObjects.Light;
import main.java.gameObjects.RadiatingLight;
import main.java.gameObjects.Thing;
import main.java.gameObjects.ThingHolder;
import main.java.gameObjects.ThingList;
import main.java.globals.Direction;
import main.java.inputProcessor.InputProcessor;
import main.java.mapBuilder.RoomBuilder;
import main.java.utility.SignReader;
import main.java.weapons.Revolver;
import main.java.wearableObjects.Ring;
import main.java.world.GameMap;
import main.java.world.GameRoom;
import main.java.world.GameTile;
import main.java.world.GameWorld;
import main.java.world.UpdatePlayer;
import main.java.world.UpdateRoomTileAnimations;

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
    
	public static WorldReader worldReader;
    private String input;
    private String output;
    private UserInterface userInterface;
    private InputProcessor inputProcessor;
    private boolean roomChange = false;
    private Actor player;  // the player - provides 'first person perspective'
    
    public Game( boolean mapBuilderMode, boolean newGame ) {
    	
    	if( mapBuilderMode == true ) {
    		
    		new RoomBuilder();
    		
    	} else if( newGame == true ) {
    		
    		// TODO build new game / load game selector
    		
    		
    		
    		Game.currentGame = this;
    		
    		worldReader = new WorldReader();
    		
    		currentWorld =  worldReader.getWorld( "files/worlds/testtileworld", "testtileworld.json" );
    		currentMap = (GameMap) ( currentWorld.getMaps().get( 0 ) );
    		currentRoom = (GameRoom) ( currentMap.getRooms().get( 0 ) );
    		currentTile = (GameTile) ( currentRoom.getTiles().get( 40 ) );
    		
    		currentWorld.setLocations();
    		
    		player = new Human( "player", "This is a player", currentTile, new ThingList(), " @ " );
    		
    		//test shader
    		player.setLightSources( new ArrayList< Light >() );
    		player.getLightSources().add( new RadiatingLight( 5, 2, null, currentTile.getTileNumber() ) );
    		
    		String dir = currentRoom.calculateRelativeDirection( 30, 44 );
    		System.out.println( "Direction: " + dir );
    		
    		Ring ring = new Ring();
    		ring.setName( "Ring of Might and Madness" );
    		ring.setAltNames( new String[ 1 ] );
    		ring.getAltNames()[ 0 ] = "ring";
    		currentTile.getThings().add( ring );
    		
    		Fire fire = new Fire();
    		fire.getLightSources().get( 0 ).setTileNumber( 20 );
    		currentRoom.getTile( 20 ).getThings().add( fire );
    		
    		Revolver r = new Revolver();
    		r.setName( "revolver" );
    		player.getInventory().add( r );
    		
    		currentRoom.setAllRoomLightSourceObjects();
    		
    		userInterface = new UserInterface( player );
    		userInterface.getDisplay().setRoom( currentRoom );
    		
    		try {
				new TMXParser( currentRoom );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        calendar = new GameCalendar( currentGame );

	        setInputProcessor( new InputProcessor() );
	        
    	} else {
    		
    	}
    }

    // access methods
    // map
    public GameMap getMap() {
    	
        return currentMap;
    } 

    public void setMap( GameMap aMap ) {
    	
    	currentMap = aMap;
    }

    // player
    public Actor getPlayer() {
    	
        return player;
    }

    public void setPlayer( Actor aPlayer ) {
    	
        player = aPlayer;
    }
    
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
    
    public void roomChange() {
    	this.roomChange = true;
    }
    
    public void render() {
    }
    
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
		new UpdateRoomTileAnimations().run( Game.currentRoom );
		currentWorld.allLists( "environment" );
//		currentWorld.allLists( "weather" );
		currentWorld.allLists( "actors" );
	}
	
	
}