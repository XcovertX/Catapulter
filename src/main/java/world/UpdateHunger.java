package main.java.world;

import main.java.game.Game;
import main.java.gameObjects.Actor;
import main.java.gameObjects.Thing;
import main.java.globals.CatapulterColor;

public class UpdateHunger extends UpdateWorld {

	@Override
	public void run( Thing aThing ) {
		
		if( aThing.isActor() ) {
			
			Actor actor = ( Actor ) aThing;
			
			actor.incrementHungerCounter();
			
			if( actor.getHungerCounter() > 60 ) {
				actor.incrementHunger( 1 );
				Game.currentGame.getUI().printlnColor( Double.toString( actor.getHunger() ), CatapulterColor.RED20 );
				actor.setHungerCounter( 0 );
			}
			
			if( actor.getHunger() == 50 && actor.getThirstCounter() > 59 ) {
				
				Game.currentGame.getUI().printlnColor( "You are beginning to get hungry.", CatapulterColor.RED20 );
				
			} else if( actor.getHunger() == 40 && actor.getThirstCounter() > 59 ) {
				
				Game.currentGame.getUI().printlnColor( "Your stomach growls with hunger.", CatapulterColor.RED30 );
				
			} else if( actor.getHunger() == 30 && actor.getThirstCounter() > 59 ) {
				
				Game.currentGame.getUI().printlnColor( "Your stomach growls with hunger.", CatapulterColor.RED50 );
				
			} else if( actor.getHunger() == 20 && actor.getThirstCounter() > 59 ) {
				
				Game.currentGame.getUI().printlnColor( "Your stomach growls with hunger.", CatapulterColor.RED70 );
				
			} else if( actor.getHunger() == 15 && actor.getThirstCounter() > 59 ) {
				
				Game.currentGame.getUI().printlnColor( "Your stomach growls with hunger.", CatapulterColor.RED90 );
				
			} else if( actor.getHunger() < 10 && actor.getThirstCounter() > 59 ) {
				
				Game.currentGame.getUI().printlnColor( "You are VERY hungry and in need of a meal.", CatapulterColor.RED100 );
				actor.decrementHitPoints( .5 );
			}
		}
	}
}
