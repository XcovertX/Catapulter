package main.java.world;

import java.awt.Color;

import main.java.game.Game;
import main.java.gameObjects.Actor;
import main.java.gameObjects.Thing;
import main.java.globals.CatapulterColor;

public class UpdatePlayer extends UpdateWorld {

	@Override
	public void run(Thing aThing) {
		// TODO Auto-generated method stub
		
	}
	
	public void run() {
		
		Actor player = Game.currentGame.getPlayer();
		
		if( player.isAlive() ) {
			
		
			if( player.getHitPoints() <= 0 ) {
				
				player.die();
				Game.currentGame.getUI().printColor( "Your wounds became fatal and you ", CatapulterColor.RED40 );
				Game.currentGame.getUI().printColor( "died", CatapulterColor.RED100 );
				Game.currentGame.getUI().printlnColor( ".", CatapulterColor.RED40  );
				
			} else {
			
				new UpdateThirst().run( player );
				new UpdateHunger().run( player );
				
				if( player.isEngagedInCombat() ) {
					
					new UpdateFighting().run( player );
					
					if( player.getThirstCounter() > 5 ) {
						player.incrementThirst( 2 );
					}
					if( player.getHungerCounter() > 5 ) {
						player.incrementHunger( 2 );
					}
				}
			}
			
		} else {
			
		}
	}
}
