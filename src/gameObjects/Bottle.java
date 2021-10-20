package gameObjects;

public class Bottle extends HomogeneousContentContainer {
	
	public Bottle(){
		this.type = "Bottle";
	}
	
	// item thing holder constructor
	public Bottle( String aName, String aDescription, ThingList thingList, 
			int size, int maxCanContain, String[] typesCanContain ) {
		this.type = "Bottle";
	
	}
	
	@Override
	public void addThing( Thing aThing ) {
		if( aThing.getMatterState().equals( "liquid" ) ) {
			this.things.add( aThing );
		}
	}
	
	

}
