package main.java.verbs;

import java.awt.Color;
import java.util.Random;

import main.java.actor.Actor;
import main.java.actor.NonPlayerActor;
import main.java.game.Game;

public class Attack extends Action {

	public Attack() {
		setRequiresNoun( true );
		setCanHaveNoun( true );
		setIsDirection( false );
	}
	
	public Attack(Actor actorOne, Actor actorTwo ) {
		if( actorOne.equals( Game.currentGame.getPlayer() ) ) {
			if( calculateHitSuccess( actorOne, actorTwo ) ) {
				Game.currentGame.getUI().print( "You " );
				Game.currentGame.getUI().printColor( "STRIKE", Color.RED );
				Game.currentGame.getUI().print( " the " );
				Game.currentGame.getUI().printColor( actorTwo.toString() , Color.GREEN );
				Game.currentGame.getUI().println( " with incredible force!" );
				actorTwo.decrementHitPoints( 1 );
			} else {
				Game.currentGame.getUI().print( "You attempt to strike the " );
				Game.currentGame.getUI().printColor( actorTwo.toString(), Color.GREEN );
				Game.currentGame.getUI().print( " but you " );
				Game.currentGame.getUI().printColor( "MISS", Color.CYAN );
				Game.currentGame.getUI().println( "!" );
			}
		} else {
			if( calculateHitSuccess( actorTwo, actorOne ) ) {
				Game.currentGame.getUI().print( "The " );
				Game.currentGame.getUI().printColor( actorOne.toString(), Color.GREEN );
				Game.currentGame.getUI().print( " delivers a " );
				Game.currentGame.getUI().printColor( "DEVISTATING" , Color.RED );
				Game.currentGame.getUI().println( " blow, almost knocking you out!" );
				actorTwo.decrementHitPoints( 1 );
			} else {
				Game.currentGame.getUI().print( "The " );
				Game.currentGame.getUI().printColor( actorOne.toString(), Color.GREEN );
				Game.currentGame.getUI().print( " attempts to strike you down but " );
				Game.currentGame.getUI().printColor( "MISSES", Color.CYAN );
				Game.currentGame.getUI().println( "!" );
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run( String thingName ) {
		if( Game.currentTile.getNPCs().thingExists( thingName ) ) {
			NonPlayerActor npc = ( NonPlayerActor ) Game.currentTile.getNPCs().findThisObject( thingName );
			if( npc.isAttackable() ) {
				
				Game.currentGame.getUI().print( "You draw your weapon and ");
				Game.currentGame.getUI().printColor( "ATTACK", Color.RED );
				Game.currentGame.getUI().print( " the ");
				Game.currentGame.getUI().printlnColor( npc.toString(), Color.GREEN );
				
				npc.setWalking( false );
				
				new Fight( Game.currentGame.getPlayer(), npc );
				
			} else {
				Game.currentGame.getUI().println( "You can not attack the " + npc );
			}
		}
		
	}
	
	public void run( Actor actor ) {
		
	}
	
	@Override
	public void run(String thingName, String preposition) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void run(String thingNameOne, String preposition, String thingNameTwo) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean calculateHitSuccess( Actor attacker, Actor attacked ) {
		int attack = attacker.getStats().getClassType().getHitDie().roll();
		if( attack >= attacked.getStats().getArmorClass() ) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean requiresNoun() {
		return requiresNoun;
	}

	@Override
	public void setRequiresNoun(boolean requiresNoun) {
		this.requiresNoun = requiresNoun;
		
	}

	@Override
	public boolean canHaveNoun() {
		return canHaveNoun;
	}

	@Override
	public void setCanHaveNoun(boolean canHaveNoun) {
		this.canHaveNoun = canHaveNoun;
		
	}

	@Override
	public boolean isDirection() {
		return isDirection;
	}

	@Override
	public void setIsDirection(boolean isDirection) {
		this.isDirection = isDirection;
		
	}

}
