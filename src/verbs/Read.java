package verbs;

import game.Game;
import gameObjects.Thing;

public class Read extends Action   {
	
	public Read() {
		setRequiresNoun( true );
		setIsDirection( false );
	}

	@Override
	public void run() {
		Game.currentGame.getUI().println( "As much as I love reading thoughts, I am not sure what you would like me to read?" );
		
	}

	@Override
	public void run( String thingName ) {
		if( Game.currentTile.getThings().thingExists( thingName ) == false 
				&& Game.currentGame.getPlayer().getThings().thingExists( thingName ) == false ) {
			Game.currentGame.getUI().println( "I don't see a " + thingName + " anywhere here." );
		} else if( Game.currentTile.getThings().thingExists( thingName ) == true ) {
			Thing thing = Game.currentTile.getThings().findThisObject( thingName );
			if( thing.isReadable() ) {
				if( thing.hasLargeText() == true ) {
					Game.currentGame.getUI().println( "On the " + thing.getReadableLargeTextLocation() + " of the "
							+ thing + ", the words: '" + thing.getReadableLargeText() + "' are written." );
					if( thing.hasSmallText() == true ) {
						Game.currentGame.getUI().println( "There is also some small print on the " 
								+ thing.getReadableSmallTextLocation() + " of the " + thing + ","
										+ "\nhowever, you may have to pick it up to get a closer look." );
					}
				} else if( thing.hasSmallText() == true ) {
					Game.currentGame.getUI().println( "There are some small words are written on the " 
							+ thing.getReadableSmallTextLocation() + " of the " + thing + ","
							+ "\nhowever, you may have to pick it up to get a closer look." );
				}
			} else {
				Game.currentGame.getUI().println( "The " + thing + " does not appear to have anything readble on it." );
			}
		} else if( Game.currentGame.getPlayer().getThings().thingExists( thingName ) == true ){
			Thing thing = Game.currentGame.getPlayer().getThings().findThisObject( thingName );
			if( thing.isReadable() ) {
				if( thing.hasLargeText() == true ) {
					Game.currentGame.getUI().println( "On the " + thing.getReadableLargeTextLocation() + " of the "
							+ thing + ", the words: \n'" + thing.getReadableLargeText() + "' are written.\n");
					if( thing.hasSmallText() == true ) {
						Game.currentGame.getUI().println( "There is also some small print on the " 
								+ thing.getReadableSmallTextLocation() + " of the " + thing + " which read:\n"
										+ "'" + thing.getReadableSmallText() + ".'\n" );
					}
				} else if( thing.hasSmallText() == true ) {
					Game.currentGame.getUI().println( "There are some small words are written on the " 
							+ thing.getReadableSmallTextLocation() + " of the " + thing + " which read:\n"
							+ "'" + thing.getReadableSmallText() + ".'\n" );
				}
			} else {
				Game.currentGame.getUI().println( "The " + thing + " does not appear to have anything readable on it." );
			}
		}
	}
	
	@Override
	public void run(String thingName, String preposition) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void run( String thingNameOne, String preposition, String thingNameTwo ) {
		// TODO Auto-generated method stub
		
	}
}
