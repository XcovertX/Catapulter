package verbs;

import java.awt.Color;

import game.Game;
import gameObjects.Actor;

public class Strike extends Action {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(String thingName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(String thingName, String preposition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(String thingNameOne, String preposition, String thingNameTwo) {
		// TODO Auto-generated method stub
		
	}
	
	public void run( Actor actor ) {
		
		actor.decrementHitPoints( 1 );
		Game.currentGame.getUI().printlnColor( "You land a devistating blow to the " + actor + "'s upper torso!!", Color.RED );
	}

}
