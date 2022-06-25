package main.java.world;

import main.java.actor.Actor;
import main.java.actor.NonPlayerActor;
import main.java.gameObjects.Thing;
import main.java.inputProcessor.MovementController;
import main.java.inputProcessor.MovementControllerList;

public class UpdateWalking extends UpdateWorld {
	
	private MovementController mc;

	@Override
	public void run(Thing aThing) {
		
		if( aThing.isActor() ) {
			Actor actor = ( Actor ) aThing;
			if( actor.isNPC() ) {
				NonPlayerActor npc = ( NonPlayerActor ) actor;
				
//				MovementControllerList mcl = new MovementControllerList( npc );
				if( npc.getMC() == null ) {
					MovementControllerList mcl = new MovementControllerList( npc );
					npc.setMC( mcl.getController( npc ) );

					System.out.println(npc.getControllerType());
				}
				
				mc = npc.getMC();
				mc.incrementCounter();
			
				if( mc.getCounter() > npc.getStats().getDexterity() ) {

					if( mc.getMovementType().equals( "inRoomWander" ) ) {	

						mc.moveToRandomTile( mc.getCurrentTile().getTileExits() );
					
					} else if( mc.getMovementType().equals( "inMapWander" ) ) {	
						
						mc.inRoomWander( mc.getCurrentTile().getTileExits() );
					
					} else if( mc.getMovementType().equals( "inWorldWander" ) ) {	
						
						mc.moveToRandomTile( mc.getCurrentTile().getTileExits() );
					
					} else if( mc.getMovementType().equals( "customWander" ) ) {	
						
						mc.customWander( mc.getCurrentTile().getTileExits() );
					
					} else if( mc.getMovementType().equals( "purposeDriven" ) ) {	
						
						mc.purposeDriven( mc.getCurrentTile().getTileExits() );
					
					} else {
						
						//TODO build customer walking directions/instructions
					}
				}
			} else {
				
			}
		}
	}
}
