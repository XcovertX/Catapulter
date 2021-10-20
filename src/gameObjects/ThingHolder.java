package gameObjects;

import verbs.Action;

public class ThingHolder extends Thing {
	
	protected ThingList things;
	private boolean holdsItemsOnTop;
	private boolean holdsItemsUnderneath;
	private boolean holdsItemsWithin;
    private boolean isContainer;
	private boolean isFurniture;
	
	public ThingHolder(){
		super( "", "" );
		this.things = new ThingList();
		this.setThingHolder( true );
		this.type = "ThingHolder";
	}
	
	// item thing holder constructor
	public ThingHolder( String aName, String aDescription, ThingList thingList ) {
		
		super( aName, aDescription );
		this.things = thingList;
		this.setThingHolder( true );
		this.type = "ThingHolder";
	
	}
	
	public ThingList getThings() {
		
		return things;
	}
	
	public void setThings( ThingList things ) {
		
		this.things = things;
	}
	
	public void addThing( Thing aThing ) {
		
		this.things.add( aThing );
		
	}
	
	public void removeThing( int thingLocation ) {
		
		Thing aThing = this.things.get( thingLocation );
		this.things.remove( thingLocation );
		aThing.setOnTopOf( false );
		aThing.setUnderneath( false );
		aThing.setContainedWithin( false );
	}

	public boolean isEmpty() {
		
		if( things.isEmpty() ) {
			return true;
		}
		return false;
	}

	public boolean isThingHolder() {
		return isThingHolder;
	}

	public void setThingHolder(boolean isThingHolder) {
		this.isThingHolder = isThingHolder;
	}

	public boolean holdsItemsOnTop() {
		return holdsItemsOnTop;
	}

	public void setHoldsItemsOnTop(boolean holdsItemsOnTop) {
		this.holdsItemsOnTop = holdsItemsOnTop;
	}

	public boolean holdsItemsUnderneath() {
		return holdsItemsUnderneath;
	}

	public void setHoldsItemsUnderneath(boolean holdsItemsUnderneath) {
		this.holdsItemsUnderneath = holdsItemsUnderneath;
	}

	public boolean isFurniture() {
		return isFurniture;
	}

	public void setFurniture(boolean isFurniture) {
		this.isFurniture = isFurniture;
	}
	
	public boolean isContainer() {
		return isContainer;
	}

	public void setIsContainer( boolean isContainer ) {
		this.isContainer = isContainer;
	}

	public boolean holdsItemsWithin() {
		return holdsItemsWithin;
	}

	public void setHoldsItemsWithin(boolean holdsItemsWithin) {
		this.holdsItemsWithin = holdsItemsWithin;
	}
}
