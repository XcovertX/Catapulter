package main.java.light;

import java.util.HashMap;
import java.util.Map;

public class LightBehaviors {
	
	private Map< String, int[] > behaviors = new HashMap<>();
	
	public LightBehaviors() {
		
		int[] constant = { 5 };
		int[] flicker = { 5, 5, 3, 4, 3, 3, 2, 2, 5, 5, 3, 4, 6, 7, 9, 9, 8, 6, 5, 5, 3, 4, 3, 3, 2, 5, 8, 9, 
				  		  9, 8, 8, 8, 7, 7, 8, 8, 8, 5, 3, 4, 6, 6, 6, 6, 5, 5, 5, 5, 6, 8, 9, 7, 5, 6, 5, 6,
				  	      5, 6, 7, 6, 5, 4, 4, 2, 2, 3, 3, 4, 6, 7, 8, 9, 8, 6, 5, 5, 3, 4, 3, 3, 2, 5, 8, 9, 
						  9, 8, 8, 8, 7, 7, 8, 8, 8, 5, 3, 2, 3, 4, 5, 6, 7, 3, 5, 4, 6, 8, 9, 7, 4, 3, 5, 6 };
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
