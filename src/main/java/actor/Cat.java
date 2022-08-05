package actor;
import userInterface.TileChar;
import game.Game;
import gameObjects.ThingList;
import world.GameTile;

public class Cat extends Animal {

	public Cat(String aName, String aDescription, GameTile aGameTile, ThingList tList, String npcSymbol, int freq) {
		super(aName, aDescription, aGameTile, tList, npcSymbol);
		this.setHoldable(true);
		this.setHitPoints ( 3 );
		this.setControllerType( "cat" );
		this.setMovementType( "inRoomWander" );
		this.type = "Cat";
	}
	
	public Cat() {
		
		this.setHoldable(true);
		this.setHitPoints ( 3 );
		this.getStats().setDexterity( 10 );
		this.getStats().setArmorClass( 2 );
		this.setControllerType( "cat" );
		this.setMovementType( "inRoomWander" );
		this.setDistanceBounds( "inMap" );
		this.setTileChar( new TileChar( " c " ) );
		this.type = "Cat";
	}

	public void sayMeow() {
		if( this.currentTile.getRoom().equals( Game.currentRoom ) ) {
			say( "meow" );  
		}
	}
	
	public void sit() {
		this.setIsSitting( true );
		this.setDescription( "A cat sits here lazily, looking about the room with a seemingly"
				+ " care-free expression on its face.");
	}
	
	public void walk() {
		this.setIsSitting( false );
		this.setDescription( "A cat is here, slowly walking about the room." );
	}
	
	public void scratch() {
		
	}

	@Override
	public void actionList() {
		
		
	}
}

