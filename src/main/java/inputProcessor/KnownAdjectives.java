package main.java.inputProcessor;

import java.util.ArrayList;
import java.util.List;

public class KnownAdjectives {
	

	private List< String > adjectives = new ArrayList<>();
	
	public KnownAdjectives() {

		adjectives.add( "black" );
		adjectives.add( "blue" );
		adjectives.add( "bright" );
		adjectives.add( "close" );
		adjectives.add( "dark" );
		adjectives.add( "dim" );
		adjectives.add( "far" );
		adjectives.add( "fat" );
		adjectives.add( "green" );
		adjectives.add( "hard" );
		adjectives.add( "long" );
		adjectives.add( "near" );
		adjectives.add( "close" );
		adjectives.add( "red" );
		adjectives.add( "small" );
		adjectives.add( "soft" );
		adjectives.add( "thin" );
		adjectives.add( "white" );
		
	}
	
	public boolean check( String s ) {
		return adjectives.contains( s );
	}
}