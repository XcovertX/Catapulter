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
	public void run(String thing) {
		Game.currentGame.getUI().print( "You say: \"" );
		Game.currentGame.getUI().printColor( thing, Color.LIGHT_GRAY );
		Game.currentGame.getUI().println( ".\"" );	
	}

	@Override
	public void run(String thingName, String preposition) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void run( String thingNameOne, String preposition, String thingNameTwo ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean requiresNoun() {
		return requiresNoun;
	}

	@Override
	public void setRequiresNoun(boolean requiresNoun) {
		this.requiresNoun = requiresNoun;
	}

	public boolean isDirection() {
		return isDirection;
	}

	public void setIsDirection(boolean isDirection) {
		this.isDirection = isDirection;
	}

	@Override
	public boolean canHaveNoun() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCanHaveNoun(boolean requiresNoun) {
		// TODO Auto-generated method stub
		
	}

}
