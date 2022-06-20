package main.java.weapons;

import main.java.damage.Slash;

public class Sword extends Blade {
	
	public Sword() {
		this.type = "Sword";
		this.setDamage( new Slash() );
		
	}
}
