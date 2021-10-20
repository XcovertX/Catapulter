package verbs;

import game.Game;

public class List extends Action {
	
	public List() {
		setRequiresNoun( true );
	}

	@Override
	public void run() {
		String contents = Game.currentGame.getPlayer().getThings().describeThings();
		Game.currentGame.getUI().println( "\nYou are currently carrying: \n"+ contents );
		
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

	@Override
	public boolean requiresNoun() {
		return this.requiresNoun;
	}

	@Override
	public void setRequiresNoun(boolean requiresNoun) {
		this.requiresNoun = requiresNoun;
		
	}

	@Override
	public boolean isDirection() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsDirection(boolean isDirection) {
		// TODO Auto-generated method stub
		
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
