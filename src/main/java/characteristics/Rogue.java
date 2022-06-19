package main.java.characteristics;

import main.java.calculator.D8;

public class Rogue extends ActorClass {

	public Rogue() {
		this.setHitDie( new D8() );
	}
}
