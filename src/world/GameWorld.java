package world;

import gameObjects.Thing;
import gameObjects.ThingHolder;
import gameObjects.ThingList;

public class GameWorld extends ThingHolder {
	
	public GameWorld() {
		super( "", "", new ThingList() );
		this.type = "World";
	}

	public GameWorld( String aName, String aDescription ) {
		 
		super( aName, aDescription, new ThingList() );
		this.type = "World";
	}
	
	public ThingList getMaps() {

		return things;
	}
	
	public void setMaps( ThingList maps ) {

		this.things = maps;
	}
	
	public void addMap( GameMap aMap ) {

		this.things.add( aMap );
	}
	
	public void removeMap( int mapLocation ) {

		this.things.remove( mapLocation );
	}
}
