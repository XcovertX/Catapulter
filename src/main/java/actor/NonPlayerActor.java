package main.java.actor;

import main.java.characteristics.Constitution;
import main.java.characteristics.Dexterity;
import main.java.gameObjects.ThingList;
import main.java.inputProcessor.MovementController;
import main.java.world.GameTile;

public abstract class NonPlayerActor extends Actor {
	
	protected transient MovementController mc;
	
	protected boolean movementType;
	private int movementFreq;
	private int movementDelay;
	private int lastMovement;
	private String recentlyVisited;
	

	public NonPlayerActor(String aName, String aDescription, GameTile aGameTile, ThingList tList, String npaSymbol ) {
		super(aName, aDescription, aGameTile, tList, npaSymbol );
		this.setNPC( true );
		this.lastMovement = 0;
		this.recentlyVisited = null;
		this.type = "NonPlayerActor";
	}

	public NonPlayerActor() {
		this.setNPC( true );
		this.lastMovement = 0;
		this.recentlyVisited = null;
		this.type = "NonPlayerActor";
	}

	public MovementController getMC() {
		return mc;
	}

	public void setMC( MovementController mc ) {
		this.mc = mc;
	}
	
	public abstract void actionList();

	public boolean movementType() {
		return movementType;
	}

	public void setMovementType(boolean movementType) {
		this.movementType = movementType;
	}

	public int movementFreq() {
		return movementFreq;
	}

	public void setMovementFreq(int movementFreq) {
		this.movementFreq = movementFreq;
	}

	public int movementDelay() {
		return movementDelay;
	}

	public void setMovementDelay(int movementDelay) {
		this.movementDelay = movementDelay;
	}
	
	public void incrementLastMovement() {
		this.lastMovement += 1;
	}
	
	public int getLastMovement() {
		return this.lastMovement;
	}
	
	public void setLastMovement() {
		this.lastMovement = 0;
	}

	public String getRecentlyVisited() {
		return recentlyVisited;
	}

	public void setRecentlyVisited(String recentlyVisited) {
		this.recentlyVisited = recentlyVisited;
	}

}
