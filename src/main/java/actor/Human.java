package actor;

import body.HumanBody;
import gameObjects.ThingList;
import world.GameTile;

public class Human extends Actor{
	
	private HumanBody body;
	private String gender;

	public Human( String aName, String aDescription, GameTile aGameTile, ThingList tList, String aActorSymbol) {
		super(aName, aDescription, aGameTile, tList, aActorSymbol );
		this.setBody( new HumanBody() );
		this.setGender( "" );
		this.type = "Human";
	}

	public HumanBody getBody() {
		return body;
	}

	public void setBody( HumanBody body ) {
		this.body = body;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


}
