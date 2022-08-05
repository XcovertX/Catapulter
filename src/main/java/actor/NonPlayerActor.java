package actor;

import characteristics.Constitution;
import characteristics.Dexterity;
import gameObjects.ThingList;
import inputProcessor.MovementController;
import world.GameTile;

public abstract class NonPlayerActor extends Actor {
	
	protected transient MovementController mc;
	
	private int movementFreq;
	private int movementDelay;
	private int lastMovement;
	private String recentlyVisited;
	
	private int walkSpeed;
	private int runSpeed;
	
	private String controllerType;
	

	public NonPlayerActor(String aName, String aDescription, GameTile aGameTile, ThingList tList, String npaSymbol ) {
		super(aName, aDescription, aGameTile, tList, npaSymbol );
		this.setNPC( true );
		this.lastMovement = 0;
		this.movementType = "inRoomWonder";
		this.recentlyVisited = null;
		this.type = "NonPlayerActor";
	}

	public NonPlayerActor() {
		this.setNPC( true );
		this.lastMovement = 0;
		this.movementType = "inRoomWonder";
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

	public int getWalkSpeed() {
		return walkSpeed;
	}

	public void setWalkSpeed(int walkSpeed) {
		this.walkSpeed = walkSpeed;
	}

	public int getRunSpeed() {
		return runSpeed;
	}

	public void setRunSpeed(int runSpeed) {
		this.runSpeed = runSpeed;
	}

	public String getControllerType() {
		return controllerType;
	}

	public void setControllerType(String controllerType) {
		this.controllerType = controllerType;
	}

}
