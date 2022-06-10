package main.java.verbs;

import main.java.game.Game;
import main.java.gameObjects.Actor;
import main.java.gameObjects.Food;
import main.java.gameObjects.Thing;

public class Eat extends Action {
	
	public Eat() {
		setRequiresNoun( true );
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(String thing) {
		
		Actor player = Game.currentGame.getPlayer();
		
		if( player.getThings().thingExists( thing ) ) {
			Thing aThing = player.getThings().findThisObject( thing );
			if( aThing.isEatable() ) {
				Food food = ( Food ) aThing;
				if( food.hasPortions() ) {
					player.setHunger( player.getHunger() + food.getSustenance() );
					if( player.getHunger() > 100 ) {
						player.setHunger( 100 );
					}
					food.incrementPortionsConsumed();
					if( food.getPortionsConsumed() >= food.getPortions() ) {
						int index = player.getThings().findIndexOf( thing );
						player.getThings().remove( index );
						Game.currentGame.getUI().println( "You eat the remaining portion of the " + thing + "." );
					} else {
						Game.currentGame.getUI().println( "You eat a portion of the " + thing + "." );
					}
					
				} else {
					player.setHunger( player.getHunger() + food.getSustenance() );
					if( player.getHunger() > 100 ) {
						player.setHunger( 100 );
					}
					int index = player.getThings().findIndexOf( thing );
					player.getThings().remove( index );
					Game.currentGame.getUI().println( "You eat the " + thing + "." );
				}
				
			} else {
				Game.currentGame.getUI().println( "You can not eat the " + thing + "." );
			}
		} else {
			Game.currentGame.getUI().println( "You are not carrying a " + thing );
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
