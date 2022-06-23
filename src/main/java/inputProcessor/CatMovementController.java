package main.java.inputProcessor;

import java.util.ArrayList;

import main.java.actor.Cat;

public class CatMovementController extends MovementController {
	
	public CatMovementController( Cat cat ) {
		
		this.setNPC( cat );
		this.setCurrentTile( cat.getCurrentGameTile() );
		this.setCurrentRoom( this.getCurrentTile().getRoom() );
		this.setCurrentMap( this.getCurrentRoom().getMap() );
		this.setCurrentWorld( this.getCurrentMap().getWorld() );
		
		this.setRecentlyVisited( cat.getRecentlyVisited() );
		this.setWalkSpeed( cat.getWalkSpeed() );
		this.setRunSpeed( cat.getRunSpeed() );
		this.setMovementType( cat.getMovementType() );
		this.setDelay( cat.movementDelay() );
		this.getCurrentTile().setTileChar();
	}

	@Override
	public void customWander( ArrayList< String > currentExits ) {
		
		this.moveToRandomTile(currentExits);
		
		
	}

}
