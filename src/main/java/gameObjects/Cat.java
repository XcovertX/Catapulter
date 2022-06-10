package main.java.gameObjects;

import main.java.game.Game;
import main.java.world.GameTile;

public class Cat extends Animal {

	public Cat(String aName, String aDescription, GameTile aGameTile, ThingList tList, String npaSymbol, int freq) {
		super(aName, aDescription, aGameTile, tList, npaSymbol);
		this.setHoldable(true);
		this.setHitPoints ( 3 );
		this.actorSymbol = " c ";
		this.type = "Cat";
	}
	
	public Cat() {
		
		this.setHoldable(true);
		this.setHitPoints ( 3 );
		this.actorSymbol = " c ";
		this.type = "Cat";
	}

	public void sayMeow() {
		say( "meow" );  
	}
	
	public void sit() {
		
	}
	
	public void scratch() {
		
	}

	@Override
	public void actionList() {
		
		
	}
}

