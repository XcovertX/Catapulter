package main.java.gameObjects;

import main.java.game.Game;

public class Chest extends Container {
	public Chest(){
		super( "", "", new ThingList(), 5, 10, new String[10] );
		this.type = "Chest";
	}
	
	// item thing holder constructor
	public Chest( String aName, String aDescription, ThingList thingList, 
			int size, int maxCanContain, String[] typesCanContain ) {
		
		super( aName, aDescription, thingList, size, maxCanContain, typesCanContain );
		this.openable = true;
		this.isOpen = false;
		this.setLock( new Lock() );
		this.isLocked = false;
		this.lockable = true;
		this.type = "Chest";
	
	}
	
	@Override
	public void addThing( Thing aThing ) {
		if( aThing.getMatterState().equals( "solid" ) ) {
			this.things.add( aThing );
		}
	}
}
