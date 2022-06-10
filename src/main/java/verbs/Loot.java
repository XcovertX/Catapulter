package main.java.verbs;

import java.awt.Color;

import main.java.game.Game;
import main.java.gameObjects.Actor;
import main.java.gameObjects.Thing;

public class Loot extends Action{
	

	public Loot() {
		setRequiresNoun( true );
		setCanHaveNoun( true );
		setIsDirection( false );
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run( String thingName ) {
		
		if( Game.currentTile.getNPCs().thingExists( thingName ) ) {
			 Actor actor = ( Actor ) Game.currentTile.getNPCs().findThisObject( thingName );

			 if( !actor.isAlive() ) {
				 Game.currentGame.getUI().print( "You examine the inventory of " );
				 Game.currentGame.getUI().printlnColor( actor.toString(), Color.RED );
				 if( !actor.getThings().isEmpty() ) {
					 for( int i = 0; i < actor.getThings().size(); i++ ) {
						 Thing aThing = actor.getThings().remove( i );
						 Game.currentGame.getPlayer().getThings().add( aThing );
						 Game.currentGame.getUI().print( "You retrieve a " );
						 Game.currentGame.getUI().printColor( aThing.toString(), Color.YELLOW );
						 Game.currentGame.getUI().println( " from the corpse." );
					 }
					 Game.currentGame.getUI().println( "After retrieving everything of interest, you "
					 		+ "return the corpse back to initial state." );
				 } else {
					 Game.currentGame.getUI().println( "You find nothing of interest on the corpse." );
				 }
			 } else {
				 Game.currentGame.getUI().println( "I prefer to only loot corpses..." );
			 }
			
		} else {
			Game.currentGame.getUI().println( "I don't see a " + thingName + " anywhere here." );
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
