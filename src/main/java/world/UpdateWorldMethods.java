package main.java.world;

import java.util.HashMap;
import java.util.Map;


public class UpdateWorldMethods {

	private Map< String, UpdateWorld > updates = new HashMap<>();
	
	public UpdateWorldMethods() {
		updates.put( "decompose", new UpdateDecomposing() );
		updates.put( "walk", new UpdateWalking() );
		updates.put( "environment", new UpdateEnvironment() );
		updates.put( "actors", new UpdateActors() );
		updates.put( "hunger", new UpdateHunger() );
		updates.put( "thirst", new UpdateThirst() );
		
	}
	
	public UpdateWorld getUpdateCommand( String updateName ) {
		
		try {
			
			return updates.get( updateName );
			
		} catch( IllegalArgumentException e ) {
			
			return null;
		} 
	}
	
	public boolean check( String s ) {
		return updates.containsKey( s );
	}
}
