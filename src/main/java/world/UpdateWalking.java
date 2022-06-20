package main.java.world;

import main.java.actor.Actor;
import main.java.actor.NonPlayerActor;
import main.java.gameObjects.Thing;
import main.java.inputProcessor.MovementController;

public class UpdateWalking extends UpdateWorld {
	
	private MovementController mc;

	@Override
	public void run(Thing aThing) {
		
		if( aThing.isActor() ) {
			Actor actor = ( Actor ) aThing;
			if( actor.isNPC() ) {
				NonPlayerActor npc = ( NonPlayerActor ) actor;
			
				
				if( npc.getMC() == null ) {
					npc.setMC( new MovementController( npc ) );
				}
				
				mc = npc.getMC();
				mc.incrementCounter();
			
				if( mc.getCounter() > npc.getStats().getDexterity() ) {
					if( npc.isWandering() ) {	
						
						mc.randomRoomNumber( mc.getCurrentTile().getTileExits() );
					
					} else {
						
						//TODO build customer walking directions/instructions
					}
				}
			} else {
				
			}
		}
	}
}
