package game;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;     // required for ArrayList

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import UserInterface.GraphicalUserInterface;
import UserInterface.UserInterface;
import UserInterface.UserInterfaceNew;
import application.GUI;
import environment.GameCalendar;
import gameObjects.Actor;
import gameObjects.Cat;
import gameObjects.Human;
import gameObjects.NonPlayerActor;
import gameObjects.Thing;
import gameObjects.ThingHolder;
import gameObjects.ThingList;
import globals.Direction;
import inputProcessor.InputProcessor;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mapBuilder.RoomBuilder;
import utility.SignReader;
import world.GameMap;
import world.GameRoom;
import world.GameTile;
import world.GameWorld;
import world.UpdatePlayer;

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
    private UserInterfaceNew userInterface;
    private InputProcessor inputProcessor;
    private boolean roomChange = false;
    private Actor player;  // the player - provides 'first person perspective'
    
    private boolean initializing; // temp to test concept
    
    public Game( boolean mapBuilderMode, boolean newGame ) {
    	
    	if( mapBuilderMode == true ) {
    		
    		new RoomBuilder();
    		
    	} else if( newGame == true ) {
    		
    		// TODO build new game / load game selector
    		
    		this.initializing = true;
    		
    		Game.currentGame = this;
    		
    		worldReader = new WorldReader();
    		
    		currentWorld =  worldReader.getWorld( "files/worlds/catapulter", "catapulter.json" );
    		currentMap = (GameMap) ( currentWorld.getMaps().get( 0 ) );
    		currentRoom = (GameRoom) ( currentMap.getRooms().get( 0 ) );
    		currentTile = (GameTile) ( currentRoom.getTiles().get( 12 ) );
    		
    		currentWorld.setLocations();
    		
    		player = new Human( "player", "This is a player", currentTile, new ThingList(), " @ " );
    		
//    		initializeGUI();
    		
    		userInterface = new UserInterfaceNew( player );
    		System.out.println("check");
    		userInterface.getDisplay().setRoom( currentRoom );
 
	        calendar = new GameCalendar( currentGame );

	        setInputProcessor( new InputProcessor() );
	        
	        this.initializing = false;
	        
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
    
    public UserInterfaceNew getUI() {
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
		currentWorld.allLists( "environment" );
//		currentWorld.allLists( "weather" );
		currentWorld.allLists( "actors" );
	}

	public boolean isInitializing() {
		return initializing;
	}
	
	
}