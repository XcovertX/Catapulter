package main.java.weapons;

public abstract class Explosive extends Weapon {
	
	private int blastRadius;
	private int timer;
	
	public Explosive() {
		this.type = "Explosive";
	}

	public int getBlastRadius() {
		return blastRadius;
	}

	public void setBlastRadius(int blastRadius) {
		this.blastRadius = blastRadius;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

}
