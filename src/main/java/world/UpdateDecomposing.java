package main.java.world;

import main.java.game.Game;
import main.java.gameObjects.Actor;
import main.java.gameObjects.Bone;
import main.java.gameObjects.Skull;
import main.java.gameObjects.Thing;

public class UpdateDecomposing extends UpdateWorld {

	@Override
	public void run( Thing aThing ) {

		if( aThing.isActor() ) {
			Actor actor = ( Actor ) aThing;
			if( !actor.isAlive() ) {
				
				long deathDate = actor.getDeathDate();
				
				int timeSinceDeath = getTimeSinceDeath( deathDate );
				
				if( timeSinceDeath < 1200 ) {
					
					actor.setDescription( "Here lies the corpse of " + actor +". The body currently shows no signs of decay" );
					
				} else if( timeSinceDeath < 2400 ) {
					
					actor.setDescription( "Here lies the corpse of " + actor +". The body is beginning to show no signs of decay" );
					
				} else if( timeSinceDeath < 3600 ) {
					
					actor.setDescription( "Here lies the corpse of " + actor +". The body is covered in maggots"
							+ "and the flesh is beginning to fall off the bones" );
					
				} else if( timeSinceDeath < 4800 ) {
					
					actor.setDescription( "Here lies the corpse of " + actor +". The body is starting to look deflated"
							+ " and bone is now visible in a few locations" );
					
				} else if( timeSinceDeath < 6000 ) {
					
					actor.setDescription( "Here lies the corpse of " + actor +". The body is mostly deflated"
							+ " and the flesh is almost completely gone." );
					
				} else if( timeSinceDeath < 7200 ) {
					
					actor.setDescription( "Here lies the corpse of " + actor +". The body has been reduced to nothing"
							+ " than skeletal remains" );
					
				} else if( timeSinceDeath < 8400 ) {
					
					actor.setDescription( "Here lies the corpse of " + actor +". The few remaining bones have yellowed"
							+ " and withered with time." );
					
				} else {
					
					actor.dropAll();
					actor.getCurrentGameTile().getThings().add( new Bone() );
					actor.getCurrentGameTile().getThings().add( new Bone() );
					actor.getCurrentGameTile().getThings().add( new Bone() );
					actor.getCurrentGameTile().getThings().add( new Skull() );
					
					actor.getCurrentGameTile().getNPCs().remove( actor );
					
				}
				
			}
		}
		
	}
	
	private int getTimeSinceDeath( long deathDate ) {
		
		long currentDate = Game.calendar.getTick();
		
		int timeSinceDeath = (int) (currentDate - deathDate);

		return timeSinceDeath;
	}

}
