package gameObjects;

import world.GameTile;

public abstract class NonPlayerActor extends Actor {
	
	protected transient MovementController mc;
	
	protected boolean movementType;
	private int movementFreq;
	private int movementDelay;
	

	public NonPlayerActor(String aName, String aDescription, GameTile aGameTile, ThingList tList, String npaSymbol ) {
		super(aName, aDescription, aGameTile, tList, npaSymbol );
		this.setNPC( true );
		this.type = "NonPlayerActor";
	}

	public NonPlayerActor() {
		this.setNPC( true );
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
	
	

}
