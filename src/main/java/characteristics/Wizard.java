package main.java.characteristics;

import main.java.calculator.D6;

public class Wizard extends ActorClass {

	public Wizard() {
		this.setHitDie( new D6() ); 
	}
}
