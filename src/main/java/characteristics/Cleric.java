package main.java.characteristics;

import main.java.calculator.D8;

public class Cleric extends ActorClass {

	public Cleric() {
		this.setHitDie( new D8() );
	}
}
