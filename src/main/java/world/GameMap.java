package main.java.world;

import main.java.gameObjects.RoomList;
import main.java.gameObjects.ThingHolder;
import main.java.gameObjects.ThingList;


public class GameMap extends ThingHolder {
	
	private transient GameWorld world;
	
	public RoomList roomList;
	public String mapCatagory;
	
	public GameMap() {
		super( "", "", new ThingList() );
		this.type = "Map";
	}

	public GameMap( String aMapName, String aMapDescription ) {
		super(aMapName, aMapDescription, new ThingList());
		this.type = "Map";
	}
	
	public GameWorld getWorld() {
		
		return world;
	}
	
	public void setWorld( GameWorld aGameWorld ) {
		
		this.world = aGameWorld;
	}
	
	public ThingList getRooms() { 
		
		return things;
	}
	
	public void setRooms( ThingList rooms ) { 

		this.things = rooms;
	}
	
	public void addRoom( GameRoom aRoom ) {

		this.things.add( aRoom );
	}
	
	public void removeRoom( int roomLocation ) {

		this.things.remove( roomLocation );
	}
	
	public void setCatagory( String cat ) {
		this.mapCatagory = cat;
	}
	
	public String getCatagory() {
		return mapCatagory;
	}
	
}
