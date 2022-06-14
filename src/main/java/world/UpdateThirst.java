package main.java.world;

import main.java.game.Game;
import main.java.gameObjects.Actor;
import main.java.gameObjects.Liquid;
import main.java.gameObjects.Thing;
import main.java.globals.CatapulterColor;

public class UpdateThirst extends UpdateWorld {

	@Override
	public void run(Thing aThing) {
		
		if( aThing.isActor() ) {
			
			Actor actor = ( Actor ) aThing;
			
			actor.incrementThirstCounter();
			
			if( actor.getThirstCounter() > 1000 ) {
				actor.incrementThirst( 1 );
				actor.setThirstCounter( 0 );
				Game.currentGame.getUI().printlnColor( ( "Thirst: " + Double.toString( actor.getThirst() ) ), CatapulterColor.RED20 );
			}
			
			if( actor.getThirst() == 50 && actor.getThirstCounter() == 0 ) {
				
				Game.currentGame.getUI().printlnColor( "You are beginning to get thirsty.", CatapulterColor.RED20 );
				
			} else if( actor.getThirst() == 60 && actor.getThirstCounter() == 0 ) {
				
				Game.currentGame.getUI().printlnColor( "Your mouth is dry. You need to find something to drink.", CatapulterColor.RED30 );
				
			} else if( actor.getThirst() == 70 && actor.getThirstCounter() == 0 ) {
				
				Game.currentGame.getUI().printlnColor( "You need to find something to drink soon.", CatapulterColor.RED50 );
				
			} else if( actor.getThirst() == 80 && actor.getThirstCounter() == 0 ) {
				
				Game.currentGame.getUI().printlnColor( "Your lips are chapped and your mouth is dry. You need to find something to drink soon.", CatapulterColor.RED60 );
				
			} else if( actor.getThirst() == 85 && actor.getThirstCounter() == 0 ) {
				
				Game.currentGame.getUI().printlnColor( "You need to find something to drink soon.", CatapulterColor.RED70 );
				
			} else if( actor.getThirst() > 90 && actor.getThirstCounter() == 0 ) {
				
				Game.currentGame.getUI().printlnColor( "You are VERY thirsty and in need of a water.", CatapulterColor.RED80 );
				actor.decrementHitPoints( .5 );
				
			}
		}	
	}
	
	public void decreaseThirst( Actor player, Liquid liquid, int decreaseBy ) {
		
		int thirst = player.getThirst();
		
		if( thirst - decreaseBy < 0 ) {
			
			player.setThirst( 0 );
			player.setThirstCounter( 0 );
			Game.currentGame.getUI().printlnColor( "Your thirst has been completely quenched.", CatapulterColor.GREEN80 );
			
		} else {
			
			player.setThirst( thirst - decreaseBy );
			player.setThirstCounter( 0 );

			if( decreaseBy < -20 ) {
				
				Game.currentGame.getUI().printlnColor( "The " + liquid + " scours the back of your throat.", CatapulterColor.RED80 ); 
			
			} else if( decreaseBy < -10 ) {
				
				Game.currentGame.getUI().printlnColor( "Your mouth slightly burns as you swallow the acidic " + liquid + ".", CatapulterColor.RED40 );
			
			} else if( decreaseBy < 0 ) {
				
				Game.currentGame.getUI().printlnColor( "The " + liquid + " leaves an unpleasant irritation in your mouth.", CatapulterColor.RED20 );
			
			} else if( decreaseBy < 10 ) {
				System.out.println( decreaseBy );
				Game.currentGame.getUI().printlnColor( "The " + liquid + " is soothing and revitalizing.", CatapulterColor.GREEN20 );
			
			} else if( decreaseBy < 20 ) {
				
				Game.currentGame.getUI().printlnColor( "The " + liquid + " adds sustenance and vitality to your mind and body.", CatapulterColor.GREEN40 );
			
			} else if( decreaseBy < 50 ) {
				
				if( liquid.isWater() ) {
					
					Game.currentGame.getUI().printlnColor( "AHHHH..Life giving water. Netar of the gods!", CatapulterColor.GREEN80 );
					Game.currentGame.getUI().printlnColor( "You now feel strength and renewal flowing through you!", CatapulterColor.GREEN80 );
				}
				Game.currentGame.getUI().printlnColor( "Your soul is wholly rejuvinated by the " + liquid +".", CatapulterColor.GREEN80 );
			
			} 
			
			if( player.getThirst() > 50 ) {
				
				Game.currentGame.getUI().printlnColor( "Your thirst has subsided.", CatapulterColor.GREEN80 );
				
			} else if( player.getThirst() > 30 ) {
				
				Game.currentGame.getUI().printlnColor( "You are still thirsty.", CatapulterColor.RED20 );
				
			} else if( player.getThirst() > 30 ) {
				
				Game.currentGame.getUI().printlnColor( "You are still pretty thirsty.", CatapulterColor.RED40 );
				
			} else if( player.getThirst() > 30 ) {
				
				Game.currentGame.getUI().printlnColor( "You are still VERY thirsty.", CatapulterColor.RED80 );
			}	
		}
	}
}
