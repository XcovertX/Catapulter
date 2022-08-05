package verbs;

import game.Game;

public class Get extends Action {
	
	protected String[] onPrepositions = new String[] { "on", "onto", "ontopof" };
	protected String[] underPrepositions = new String[] { "under", "underneath" };
	protected String[] inPrepositions = new String[] { "in", "into", "inside", "insideof" };
	protected String[] outPrepositions = new String[] { "out", "outof", "outsideof",  };
	
	public Get() {
		this.setRequiresNoun( true );
		this.setCanHavePreposition( true );
	}

	@Override
	public void run() {
		Game.currentGame.getUI().println( "Get what?" );
	}

	// if noun follows immediately, call take() method on the the noun
	@Override
	public void run( String thingName ) { 
		new Take().run( thingName );
	}

	@Override
	public void run( String thingName, String preposition ) {
		// move player
		
	}
	
	@Override
	public void run( String thingNameOne, String preposition, String thingNameTwo ) {
		if( preposition.equals( "from" ) ) {
			new Take().run( thingNameOne, preposition, thingNameTwo );
		} else {
			for( int i = 0; i < onPrepositions.length; i++ ) {
				if( onPrepositions[ i ].equals( preposition ) ) {
					
					
					break;
				}
			}
			for( int i = 0; i < underPrepositions.length; i++ ) {
				if( underPrepositions[ i ].equals( preposition ) ) {
					
					
					break;
				}
			}
			for( int i = 0; i < inPrepositions.length; i++ ) {
				if( inPrepositions[ i ].equals( preposition ) ) {
					
					// move player
					break;
				}
			}
			for( int i = 0; i < outPrepositions.length; i++ ) {
				if( outPrepositions[ i ].equals( preposition ) ) {
					
					// move player
					break;
				}
			}
		}
	}
}
