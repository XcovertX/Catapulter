package gameObjects;

public class Closet extends Container {
	
	public Closet() {
		
		this.type = "Closet";
	}
	
	@Override
	public void addThing( Thing aThing ) {
		if( aThing.getMatterState().equals( "solid" ) ) {
			this.things.add( aThing );
		}
	}

}
