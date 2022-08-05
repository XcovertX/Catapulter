 package mapBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import gameObjects.MapList;
import gameObjects.RoomList;
import gameObjects.Thing;
import gameObjects.ThingList;
import gameObjects.TileList;
import resources.res.ResourceLoader;
import utility.MapWriter;
import world.GameMap;
import world.GameRoom;
import world.GameTile;
import world.GameWorld;

public class RoomBuilder implements ActionListener {
	
	private RoomBuilderView view;
	private GameWorld world;
	private GameMap map;
	private GameRoom room;
	private GameTile tile;
	
	public RoomBuilder(){
		
    	this.world = new GameWorld( "Catapulter", "This place is great." );
    	
    	this.map = new GameMap( "Perilandria", "dangerous," );
    	
    	this.world.addMap( this.map );
    	
    	this.map.setWorld( this.world );
    	
    	view = new RoomBuilderView( this, "Room Builder", world );
		
		view.startBuilder();
	}
	
	public void buildJSON() {
		
		GsonBuilder gBuilder = new GsonBuilder();
		gBuilder.enableComplexMapKeySerialization();
		
		Gson json = gBuilder.setPrettyPrinting().create();
		
		String j = json.toJson( world ).toString() ;
		
		File fileObj = ResourceLoader.getFilesFolder( "files/maps/testmap", "testMap002.json" );
		//TODO make dynamic file name function with 
		
		try( FileWriter file = new FileWriter( fileObj ) ) {
			file.write( j );
			file.flush();
			
		} catch ( FileNotFoundException e ) {
        	
            System.out.println( "File not found" );
            
        } catch ( IOException e ) {
        	
            System.out.println( "Error initializing stream" );
            
            e.printStackTrace();
        }
	}
	
	public RoomBuilderView getView() {
		
		return view;
	}
	
    public void saveRoom( String aRoomName, String aRoomDescription, GameMap aGameMap,
    		ThingList tiles, int aRoomLength, int aRoomWidth ) {
    	
    	this.room = new GameRoom( aRoomName, aRoomDescription, aGameMap, tiles, aRoomLength, aRoomWidth );
    	this.map.getRooms().clear();
    	this.map.addRoom( this.room ); 
	}
    
    public void addRoom( String aRoomName, String aRoomDescription, GameMap aGameMap,
    		ThingList tiles, int aRoomLength, int aRoomWidth ) {
    	
    	this.room = new GameRoom( aRoomName, aRoomDescription, aGameMap, tiles, aRoomLength, aRoomWidth );
    	this.map.addRoom( this.room );
    }
	
	public int getRoomSize() {
		
		return this.room.getRoomSize();
	}
	
	public GameTile getTile( int tileNumber ) {
		
		return (GameTile) this.room.getTiles().get( tileNumber );
	}
	
	public void setTile( int tileNumber, GameTile aGameTile ) {
		
		this.room.getTiles().add( tileNumber, aGameTile );
	}
	
	public void setViewVisible() {
		
		view.setVisible( true );
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		// TODO Auto-generated method stub
		
	}

	
}
