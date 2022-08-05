package body;

import gameObjects.ThingHolder;

public abstract class Body extends ThingHolder {
	
	private String dominantSide;
	
	public Body() {
		this.type = "Body";
	}
	
	public void addBodyPart( BodyPart aBodyPart ) {
		this.addThing( aBodyPart );
	}
	
	public void addBodyPartGroup( BodyPartGroup aBodyPartGroup ) {
		this.addThing( aBodyPartGroup );
	}

	public String getDominantSide() {
		return dominantSide;
	}

	public void setDominantSide(String dominantSide) {
		this.dominantSide = dominantSide;
	}
}
