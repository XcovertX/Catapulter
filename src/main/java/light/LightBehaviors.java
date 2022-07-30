package main.java.light;

import java.util.HashMap;
import java.util.Map;

public class LightBehaviors {
	
	private Map< String, int[] > behaviors = new HashMap<>();
	
	public LightBehaviors() {
		
		int[] constant = { 5 };
		int[] flicker = { 1, 2, 3, 3, 3, 3, 2, 1, 2, 2, 1, 1, 3, 2, 1, 1, 3, 3 };
		int[] glitch = { 2, 2, 2, 0, 2, 2, 0, 2, 0, 2, 2, 2, 2, 2, 0, 2, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 0, 0, 2, 0, 2 };
		
		behaviors.put( "constant", constant );	
		behaviors.put( "flicker", flicker );	
		behaviors.put( "glitch", glitch );	
	}
	
	public int[] getLightBehavior( String type ) {
		
		try {
			
			return behaviors.get( type );
			
		} catch( IllegalArgumentException e ) {
			
			return null;
		} 
	}
	
	public boolean check( String s ) {
		
		return behaviors.containsKey( s );
	}
	
}
