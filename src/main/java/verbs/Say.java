package verbs;

import java.awt.Color;

import game.Game;

public class Say extends Action {

	public Say() {
		this.setRequiresNoun( false );
		this.setTalkCommand( true );
		this.setIsDirection( false );
	}
	
	@Override
	public void run() {
		Game.currentGame.getUI().print( "Say what?" );
	}

	@Override
	public void run( String thing ) {
		Game.currentGame.getUI().print( "You say: \"" );
		Game.currentGame.getUI().printColor( thing, Color.LIGHT_GRAY );
		Game.currentGame.getUI().println( ".\"" );	
	}

	@Override
	public void run( String thingName, String message ) {
		Game.currentGame.getUI().print( thingName + " says: \"" );
		Game.currentGame.getUI().printColor( message, Color.LIGHT_GRAY );
		Game.currentGame.getUI().println( ".\"" );
	}
	
	@Override
	public void run( String thingNameOne, String preposition, String thingNameTwo ) { }
	
	@Override
	public boolean requiresNoun() { return requiresNoun; }

	@Override
	public void setRequiresNoun( boolean requiresNoun ) {
		this.requiresNoun = requiresNoun;
	}

	public boolean isDirection() { return isDirection; }

	public void setIsDirection( boolean isDirection ) {
		this.isDirection = isDirection;
	}

	@Override
	public boolean canHaveNoun() { return requiresNoun; }

	@Override
	public void setCanHaveNoun( boolean requiresNoun ) { }
}
