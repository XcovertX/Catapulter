package gameObjects;

public class Apple extends Fruit {
	
	public Apple() {
		this.setHasPortions( false );
		this.setPortions( 1 );
		this.setSustenance( 20 );
		this.type = "Apple";
	}

}
