package inputProcessor;

import java.util.ArrayList;
import java.util.List;

public class KnownNouns {
	
	private List< String > things = new ArrayList<>();
	
	public KnownNouns() {

		things.add( "apple" );
		things.add( "bottle" );
		things.add( "boat" );
		things.add( "bone" );
		things.add( "button" );
		things.add( "cat" );
		things.add( "chair" );
		things.add( "chest" );
		things.add( "coin" );
		things.add( "console" );
		things.add( "data" );
		things.add( "desk" );
		things.add( "disk" );
		things.add( "e" );
		things.add( "exaust" );
		things.add( "fan" );
		things.add( "fence" );
		things.add( "fire" );
		things.add( "floor" );
		things.add( "fruit" );
		things.add( "gas" );
		things.add( "key" );
		things.add( "keyboard" );
		things.add( "knife" );
		things.add( "lamp" );
		things.add( "lever" );
		things.add( "light" );
		things.add( "lock" );
		things.add( "n" );
		things.add( "paper" );
		things.add( "phone" );
		things.add( "pher" );
		things.add( "phero" );
		things.add( "pheromones" );
		things.add( "rat" );
		things.add( "ring" );
		things.add( "rock" );
		things.add( "room" );
		things.add( "s" );
		things.add( "screen" );
		things.add( "self" );
		things.add( "Skull" );
		things.add( "sword" );
		things.add( "table" );
		things.add( "tent" );
		things.add( "tel" );
		things.add( "telsec" );
		things.add( "telsec pheromones" );
		things.add( "vent" );
		things.add( "vice" );
		things.add( "w" );
		things.add( "wall" );
		things.add( "water" );
		things.add( "wind" );
		things.add( "window" );
		things.add( "wire" );
		
	}
	
	public boolean check( String s ) {
		return things.contains( s );
	}
	
}
