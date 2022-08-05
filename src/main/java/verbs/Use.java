package verbs;

import game.Game;

public class Use extends Action {

	@Override
	public void run() {
		Game.currentGame.getUI().println( "Use what?");	
	}

	@Override
	public void run(String thing) {
		// TODO Auto-generated method stub
		
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
