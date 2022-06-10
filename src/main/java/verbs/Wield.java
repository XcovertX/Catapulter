package main.java.verbs;

import main.java.game.Game;
import main.java.gameObjects.Actor;
import main.java.gameObjects.Thing;

public class Wield extends Action {
	
	public Wield() {
		setRequiresNoun( true );
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run( String thingName ) {
		
		Actor player = Game.currentGame.getPlayer();
		
		if( player.getThings().thingExists( thingName ) ) {
			Thing thing = player.getThings().findThisObject( thingName );
			
			if( thing.isWieldable() ) {
				
				
				
			} else {
				
				Game.currentGame.getUI().println( "You can not wield the " + thing );
			}
		} else {
			
			Game.currentGame.getUI().println( "You are not carrying a " + thingName );
		}
		
	}

	@Override
	public void run(String thingName, String preposition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(String thingNameOne, String preposition, String thingNameTwo) {
		// TODO Auto-generated method stub
		
	}

}
