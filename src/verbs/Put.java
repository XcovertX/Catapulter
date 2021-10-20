package verbs;

import game.Game;
import gameObjects.Thing;
import wearableObjects.WearableThing;

public class Put extends Action {
	
	public Put() {
		this.setRequiresNoun( true );
		this.setCanHaveNoun( true );
		this.setRequiresPreposition( true );
	}

	@Override
	public void run() {
		Game.currentGame.getUI().println( "Put what?" );
		
	}

	@Override
	public void run( String thingName ) {
		Game.currentGame.getUI().println( "Where should I put the " + thingName + "?");
	}

	
	@Override
	public void run(String thingName, String preposition) {
		if( preposition.equals( "on" ) ) {
			if( Game.currentGame.getPlayer().getThings().thingExists( thingName ) ) {
				Thing thing = Game.currentGame.getPlayer().getThings().findThisObject( thingName );
				if( thing.isWearable() ) {
					WearableThing wearableThing = ( WearableThing ) thing;
					if( wearableThing.isDonned() == false ) {
						new Wear().run( thingName );
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
		
	}
	
	@Override
	public void run( String thingNameOne, String preposition, String thingNameTwo ) {
		// TODO Auto-generated method stub
		
	}
	
	public String getPreposition( Thing aThing ) {
		if( aThing.isOnTopOf() ) {
			return "on";
		} else if( aThing.isUnderneath() ) {
			return "under";
		} else if( aThing.isContainedWithin() ) {
			return "in to";
		}
		return "";
	}

}
