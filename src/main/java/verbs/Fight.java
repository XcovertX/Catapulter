package main.java.verbs;

import java.awt.Color;

import main.java.game.Game;
import main.java.gameObjects.Actor;

public class Fight {
	
	private Actor opponentOne;
	private Actor opponentTwo;
	private int counterOne;
	private int counterTwo;
	
	public Fight( Actor actorOne, Actor actorTwo ) {
		this.setOpponentOne( actorOne );
		this.setOpponentTwo( actorTwo );
		this.setCounterOne( actorOne.getStats().getDexterity() );
		this.setCounterTwo( actorTwo.getStats().getDexterity() );
		this.opponentOne.setEngagedInCombat( true );
		this.opponentTwo.setEngagedInCombat( true );
		this.opponentOne.setFight( this );
		this.opponentTwo.setFight( this );
		
		combatCycle();
	}
	
	public void combatCycle() {
		
		
		if( counterOne/( opponentOne.getStats().getDexterity() ) > 1 ) {
			new Attack( opponentOne, opponentTwo );
			setCounterOne( 0 );
		}
		
		if( counterTwo/( opponentTwo.getStats().getDexterity() ) > 1 ) {
			new Attack( opponentTwo, opponentOne );
			setCounterTwo( 0 );
		}
		
		if(opponentOne.getHitPoints() <= 0 ) {
			opponentOne.die();
			if( opponentOne.equals( Game.currentGame.getPlayer() ) ) {
				Game.currentGame.getUI().print( "You have ");
				Game.currentGame.getUI().printColor( "died", Color.RED);
				Game.currentGame.getUI().println( ".");
			} else {
				Game.currentGame.getUI().print( "The ");
				Game.currentGame.getUI().printColor( opponentOne.toString(), Color.GREEN);
				Game.currentGame.getUI().print( " has ");
				Game.currentGame.getUI().printColor( "died", Color.RED );
				Game.currentGame.getUI().println( "." );
			}
		}
		
		if(opponentTwo.getHitPoints() <= 0 ) {
			opponentTwo.die();
			if( opponentTwo.equals( Game.currentGame.getPlayer() ) ) {
				Game.currentGame.getUI().print( "You have ");
				Game.currentGame.getUI().printColor( "died", Color.RED);
				Game.currentGame.getUI().println( ".");
			} else {
				Game.currentGame.getUI().print( "The ");
				Game.currentGame.getUI().printColor( opponentTwo.toString(), Color.GREEN);
				Game.currentGame.getUI().print( " has ");
				Game.currentGame.getUI().printColor( "died", Color.RED );
				Game.currentGame.getUI().println( "." );
			}
		}
		
		if( !opponentOne.isAlive() || !opponentTwo.isAlive() ) {
		
			endFight();
		}
	}

	public Actor getOpponentOne() {
		return opponentOne;
	}

	public void setOpponentOne( Actor actorOne ) {
		this.opponentOne = actorOne;
	}

	public Actor getOpponentTwo() {
		return opponentTwo;
	}

	public void setOpponentTwo( Actor actorTwo ) {
		this.opponentTwo = actorTwo;
	}

	public int getCounterOne() {
		return counterOne;
	}
	
	public int getCounterTwo() {
		return counterTwo;
	}

	public void setCounterOne( int counter ) {
		this.counterOne = counter;
	}
	
	public void setCounterTwo( int counter ) {
		this.counterTwo = counter;
	}
	
	public void incrementCounters() {
		this.counterOne++;
		this.counterTwo++;
	}
	
	public void endFight() {
		this.opponentOne.setEngagedInCombat( false );
		this.opponentTwo.setEngagedInCombat( false );
		this.opponentOne.setFight( null );
		this.opponentTwo.setFight( null );
	}

}
