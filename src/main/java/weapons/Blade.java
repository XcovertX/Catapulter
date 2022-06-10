package main.java.weapons;

import main.java.gameObjects.Weapon;

public abstract class Blade extends Weapon {
	
	private double sharpness;
	
	public Blade() {
		this.type = "Blade";
	}

	@Override
	public void deliverDamage() {
		
	}

	public double getSharpness() {
		return sharpness;
	}

	public void setSharpness( double sharpness ) {
		this.sharpness = sharpness;
	}
	
	public void dull( double amount ) {
		setSharpness( sharpness - amount );
	}
	
	
	


}
