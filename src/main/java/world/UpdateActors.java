package main.java.world;

import main.java.gameObjects.Actor;
import main.java.gameObjects.NonPlayerActor;
import main.java.gameObjects.Thing;

public class UpdateActors extends UpdateWorld {

	@Override
	public void run(Thing aThing) {
		if( aThing.isActor() ) {
			Actor actor = ( Actor ) aThing;
			if( actor.isAlive() ) {
				
				if( actor.getHitPoints() <= 0 ) {
					actor.die();
				}
				
				if( actor.isWalking() ) {
					
					new UpdateWalking().run( actor );
					
//					if( actor.getThirstCounter() > 5 ) {
//						actor.decrementThirst( 2 );
//					}
//					if( actor.getHungerCounter() > 5 ) {
//						actor.decrementHunger( 2 );
//					}
				}
				
				if( actor.isEngagedInCombat() ) {
					
					new UpdateFighting().run( actor );
					
//					if( actor.getThirstCounter() > 5 ) {
//						actor.decrementThirst( 2 );
//					}
//					if( actor.getHungerCounter() > 5 ) {
//						actor.decrementHunger( 2 );
//					}
				}
				
			} else {
				new UpdateDecomposing().run( actor );
			}
		}
	}
}
