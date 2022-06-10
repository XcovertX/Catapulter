package main.java.world;

import main.java.gameObjects.Actor;
import main.java.gameObjects.Thing;
import main.java.verbs.Fight;

public class UpdateFighting extends UpdateWorld {

	@Override
	public void run(Thing aThing) {
		
		if( aThing.isActor() ) {
			Actor actor = ( Actor ) aThing;
			if( actor.isEngagedInCombat() ) {
				Fight fight = actor.getFight();
				fight.incrementCounters();
				fight.combatCycle();
			}
		}
		
	}

}
