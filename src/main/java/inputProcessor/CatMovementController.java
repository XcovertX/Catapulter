package main.java.inputProcessor;

import java.util.ArrayList;

import main.java.actor.Cat;
import main.java.actor.NonPlayerActor;
import main.java.calculator.D20;

public class CatMovementController extends MovementController {
	
	private int sitCounter;
	private int sleepCounter;
	private int maxSitTime;
	
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
		this.setSitCounter( 0 );
		this.setMaxSitTime( 50 );
		this.getCurrentTile().setTileChar();
	}

	@Override
	public void customWander( ArrayList< String > currentExits ) {
		
		if( this.getNPC().isSitting() ) {
			this.sitCounter++;
			this.setCounter( 0 );
			if( new D20().roll() > 15 ) {
				( ( Cat ) this.getNPC() ).sayMeow();
			}
			if( this.sitCounter > this.maxSitTime ) {
				( ( Cat ) this.getNPC() ).walk();
			}
			return;
		}
		
		this.moveToRandomTile(currentExits);
		this.incrementMovementTotalCounter();
		
		if( this.getMovementTotal() > 10 ) { // random magic number for cat to move before it rolls a chance to lay down. change when done testing
			D20 d20 = new D20();
			if( d20.roll() > 10 ) {
				( ( Cat ) this.getNPC() ).sit();
				this.setMovementTotal( 0 );
			}
		}
	}

	public int getSitCounter() {
		return sitCounter;
	}

	public void setSitCounter(int sitCounter) {
		this.sitCounter = sitCounter;
	}

	public int getSleepCounter() {
		return sleepCounter;
	}

	public int getMaxSitTime() {
		return maxSitTime;
	}

	public void setMaxSitTime(int maxSitTime) {
		this.maxSitTime = maxSitTime;
	}
}
