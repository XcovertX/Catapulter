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
import main.java.UserInterface.UserInterface;
import main.java.UserInterface.UserInterfaceNew;
import main.java.actor.Actor;
import main.java.actor.Cat;
import main.java.actor.Human;
import main.java.actor.NonPlayerActor;
import main.java.application.GUI;
import main.java.environment.GameCalendar;
import main.java.gameObjects.Thing;
import main.java.gameObjects.ThingHolder;
import main.java.gameObjects.ThingList;
import main.java.globals.Direction;
import main.java.inputProcessor.InputProcessor;
import main.java.mapBuilder.RoomBuilder;
import main.java.utility.SignReader;
import main.java.world.GameMap;
import main.java.world.GameRoom;
import main.java.world.GameTile;
import main.java.world.GameWorld;
import main.java.world.UpdatePlayer;

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
    
    public Game( boolean mapBuilderMode, boolean newGame ) {
    	
    	if( mapBuilderMode == true ) {
    		
    		new RoomBuilder();
    		
    	} else if( newGame == true ) {
    		
    		// TODO build new game / load game selector
    		
    		
    		
    		Game.currentGame = this;
    		
    		worldReader = new WorldReader();
    		
    		currentWorld =  worldReader.getWorld( "files/worlds/catapulter", "TestWorld.json" );
    		currentMap = (GameMap) ( currentWorld.getMaps().get( 0 ) );
    		currentRoom = (GameRoom) ( currentMap.getRooms().get( 0 ) );
    		currentTile = (GameTile) ( currentRoom.getTiles().get( 12 ) );
    		
    		currentWorld.setLocations();
    		
    		player = new Human( "player", "This is a player", currentTile, new ThingList(), " @ " );
    		
    		userInterface = new UserInterfaceNew( player );
    		userInterface.getDisplay().setRoom( currentRoom );
 
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
	
	
}