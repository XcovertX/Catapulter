package wearableObjects;

import java.util.LinkedList;

import gameObjects.Thing;

public class WearableThing extends Thing {
	
	protected boolean isDonned;
	protected LinkedList< String > wearableLocations;
	
	public WearableThing() {
		this.setWearable( false );
		this.setDonned( false );
		this.setWearableLocations( new LinkedList< String >() );
		this.type = "WearableThing";
	}

	public boolean isDonned() {
		return isDonned;
	}

	public void setDonned( boolean isDonned ) {
		this.isDonned = isDonned;
	}

	public LinkedList< String > getWearableLocations() {
		return this.wearableLocations;
	}

	public void setWearableLocations( LinkedList< String > locations ) {
		this.wearableLocations = locations;
	}
	
}
