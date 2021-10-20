package verbs;

import game.Game;
import gameObjects.Human;
import gameObjects.Thing;
import wearableObjects.WearableThing;

public class Wear extends Action {
	
	public Wear() {
		this.setRequiresNoun( true );
		this.setCanHaveNoun( true );
	}

	@Override
	public void run() {
		Game.currentGame.getUI().println( "Wear what?" );
		
	}

	@Override
	public void run(String thingName) {
		if( Game.currentGame.getPlayer().getThings().thingExists( thingName ) ) {
			Thing thing = Game.currentGame.getPlayer().getThings().findThisObject( thingName );
			if( thing.isWearable() ) {
				WearableThing wearableThing = ( WearableThing ) thing;
				if( wearableThing.isDonned() == false ) {
					if( Game.currentGame.getPlayer().type == "Human" ) {
						Human human = (Human) Game.currentGame.getPlayer();
						human.getBody().getRightArm().getHand().getIndexFinger().wearItemOn( wearableThing );
						wearableThing.setDonned( true );
						Game.currentGame.getUI().println( "You place the " + wearableThing + " on your finger," );
					}
				} else {
					Game.currentGame.getUI().println( "You are currently wearing the " + thing );
				}
			} else {
				Game.currentGame.getUI().println( "You can not wear the " + thing ); 
			}
		} else if( Game.currentTile.getThings().thingExists( thingName ) ) {
			Game.currentGame.getUI().println( "You'll have to pick the " + thingName 
					+ " up first before you can attemt to wear it." );
		} else {
			Game.currentGame.getUI().println( "You're not carrying a " + thingName + " to put on." );
		}
		
	}

	@Override
	public void run(String thingName, String preposition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(String thingNameOne, String preposition, String thingNameTwo) {
		// TODO Auto-generated method stub
		
	}

}
