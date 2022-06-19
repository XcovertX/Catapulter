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
			
			if( actor.getHungerCounter() > 1000 ) {
				actor.incrementHunger( 1 );
				Game.currentGame.getUI().printlnColor( ( "Hunger: " + Double.toString( actor.getHunger() ) ), CatapulterColor.RED20 );
				actor.setHungerCounter( 0 );
			}
			
			if( actor.getHunger() == 50 && actor.getHungerCounter() == 0 ) {
				
				Game.currentGame.getUI().printlnColor( "You are beginning to get hungry.", CatapulterColor.RED20 );
				
			} else if( actor.getHunger() == 60 && actor.getHungerCounter() == 0 ) {
				
				Game.currentGame.getUI().printlnColor( "Your stomach growls with hunger.", CatapulterColor.RED30 );
				
			} else if( actor.getHunger() == 70 && actor.getHungerCounter() == 0 ) {
				
				Game.currentGame.getUI().printlnColor( "Your stomach growls with hunger.", CatapulterColor.RED50 );
				
			} else if( actor.getHunger() == 80 && actor.getHungerCounter() == 0  ) {
				
				Game.currentGame.getUI().printlnColor( "Your stomach growls with hunger.", CatapulterColor.RED70 );
				
			} else if( actor.getHunger() == 85 && actor.getHungerCounter() == 0 ) {
				
				Game.currentGame.getUI().printlnColor( "Your stomach growls with hunger and you are feeling a bit faint.", CatapulterColor.RED90 );
				
			} else if( actor.getHunger() > 90 && actor.getHungerCounter() == 0 ) {
				
				Game.currentGame.getUI().printlnColor( "You are VERY hungry and in need of a meal.", CatapulterColor.RED100 );
				actor.decrementHitPoints( 1 );
			}
		}
	}
}
