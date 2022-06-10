package main.java.gameObjects;

public class Treasure extends Thing {	
	

	public Treasure( String aName, String aDescription, int value ) {
		
		super(aName, aDescription);
		this.type = "Treasure";
		this.value = value;
	}
	
	public Treasure() {
		// TODO Auto-generated constructor stub
	}


}
