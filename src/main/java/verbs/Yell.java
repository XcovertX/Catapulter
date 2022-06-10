package main.java.verbs;

import java.awt.Color;

import main.java.game.Game;

public class Yell extends Action {
	
	public Yell() {
		this.setRequiresNoun( true );
		this.setTalkCommand( true );
		this.setActionDescriptors( new String[]{"fervently", "expressively,", "loudly"} ); 
	}

	@Override
	public void run() {
		Game.currentGame.getUI().print( "You begin to yell... but then decide you have nothing to report." );
	}

	@Override
	public void run( String thing ) {
		String verbDescriptor = getRandomActionDescriptor();
		Game.currentGame.getUI().print( "You " + verbDescriptor + " yell: \"" );
		Game.currentGame.getUI().printColor( thing.toUpperCase(), Color.LIGHT_GRAY );
		Game.currentGame.getUI().println( "!\"" );	
	}
	
	@Override
	public void run( String thingName, String preposition ) {
		String verbDescriptor = getRandomActionDescriptor();
		Game.currentGame.getUI().print( "You yell " + verbDescriptor + " " );
		Game.currentGame.getUI().print( preposition + " " );
		Game.currentGame.getUI().println( thingName + "!");	
	}
	
	@Override
	public void run( String thingNameOne, String preposition, String thingNameTwo ) {

		
	}
}
