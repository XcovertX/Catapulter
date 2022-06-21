package main.java.inputProcessor;

import java.util.HashMap;
import java.util.Map;

import main.java.actor.NonPlayerActor;
import main.java.actor.Cat;

public class MovementControllerList {
	
	private Map< String, MovementController > controllers = new HashMap<>();
	
	private NonPlayerActor npc;
	
	public MovementControllerList() {
		controllers.put( "cat", new CatMovementController( (Cat) npc ) ); 

	}
	
	public MovementControllerList( NonPlayerActor npc ) {
		this.npc = npc;
		controllers.put( "cat", new CatMovementController( (Cat) npc ) ); 

	}
	
	public MovementController getController( NonPlayerActor npc ) {
		String controllerType = npc.getControllerType();
		try {
			return controllers.get( controllerType );
		} catch( IllegalArgumentException e ) {
			System.out.println("====##################========================");
			return null;
		} 
	}
	
	public boolean check( String controllerType ) {
		return controllers.containsKey( controllerType );
	}
}
