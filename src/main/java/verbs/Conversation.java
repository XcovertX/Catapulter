package main.java.verbs;
import java.awt.Color;

import main.java.game.Game;
import main.java.gameObjects.Actor;

public class Conversation {
	
	private Actor talkerOne;
	private Actor talkerTwo;
	private int counterOne;
	private int counterTwo;
	
	public Conversation( Actor actorOne, Actor actorTwo ) {
		this.setTalkerOne( actorOne );
		this.setTalkerTwo( actorTwo );
		this.setCounterOne( 0 );
		this.setCounterTwo( 0 );
		this.talkerOne.setEngagedInConversation( true );
		this.talkerTwo.setEngagedInConversation( true );
		this.talkerOne.setConversation( this);
		this.talkerTwo.setConversation( this );
	}

	public Actor getTalkerOne() {
		return talkerOne;
	}

	public void setTalkerOne(Actor talkerOne) {
		this.talkerOne = talkerOne;
	}

	public Actor getTalkerTwo() {
		return talkerTwo;
	}

	public void setTalkerTwo(Actor talkerTwo) {
		this.talkerTwo = talkerTwo;
	}

	public int getCounterOne() {
		return counterOne;
	}

	public void setCounterOne(int counterOne) {
		this.counterOne = counterOne;
	}

	public int getCounterTwo() {
		return counterTwo;
	}

	public void setCounterTwo(int counterTwo) {
		this.counterTwo = counterTwo;
	}
}
		
