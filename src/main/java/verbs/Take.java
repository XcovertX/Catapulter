package verbs;

import game.Game;
import gameObjects.Container;
import gameObjects.Thing;
import gameObjects.ThingHolder;

public class Take extends Action {

	public Take() {
		this.setRequiresNoun( true );
		this.setActionDescriptors( new String[]{"quickly", "covertly"} ); //depending and what mode you are in																				// (sneak, attack, ect) these will change
	}
	
	@Override
	public void run() {
		Game.currentGame.getUI().println( "Take what?" );
	}

	@Override
	public void run( String thingName ) {
		if( Game.currentTile.getThings().thingExists( thingName ) == false ) {
			Game.currentGame.getUI().println( "You don't see a " + thingName + " here to take." );
		} else {
			int index = Game.currentTile.getThings().findIndexOf( thingName );
			Thing thing = Game.currentTile.getThings().get( index );
			if( thing.isHoldable() ) {
				Game.currentGame.getUI().println( "You " + getRandomActionDescriptor() 
					+ " pick the " + thing + getPreposition( "take", thing ) 
					+ "the " + thing.getLocationInRoom() + "." );
				Game.currentGame.getPlayer().addThing( thing );
				Game.currentTile.getThings().remove( index );
				thing.setLocationInRoom( "inventory" );
			} else {
				Game.currentGame.getUI().println( "You can not pick up the " + thing + "." );
			}
		}
	}

	@Override
	public void run( String thingName, String preposition ) {
		Game.currentGame.getUI().println( "Take what " + preposition + " the "+ thingName + "?" );
	}
	
	@Override
	public void run( String thingNameOne, String preposition, String thingNameTwo ) {
		
		if( Game.currentTile.getThings().thingExists( thingNameTwo ) ) {
			int indexTwo = Game.currentTile.getThings().findIndexOf( thingNameTwo );
			Thing thingTwo = Game.currentTile.getThings().get( indexTwo );
			if( thingTwo.isThingHolder() ) {
				ThingHolder thingHolder = ( ThingHolder ) thingTwo;
				if( thingHolder.isContainer() ) {
					Container container = ( Container ) thingTwo;
					if( container.isLockable() ) {
						if( container.getLock().isLocked() ) {
							Game.currentGame.getUI().println( "The " + container + " is currently locked." );
						} else {
					 
							if( container.isOpen() ) {
								if( container.getThings().thingExists( thingNameOne ) ) {
									int indexOne = container.getThings().findIndexOf( thingNameOne );
									Thing thingOne = container.getThings().get( indexOne );
									Game.currentGame.getUI().println( "You " + getRandomActionDescriptor() + " retrieve the " 
											+ thingOne + getPreposition( "take", thingOne ) + "the " + container + "." );
									container.removeThing( indexOne );
									Game.currentGame.getPlayer().addThing( thingOne );
									thingOne.setLocationInRoom( "inventory" );
								} else {
									Game.currentGame.getUI().println( "I don't see a " + thingNameOne + " in the " + container + "." );
								}
							} else {
								Game.currentGame.getUI().print( "The " + container + " is closed. You will have to open it before"
										+ " you can take any thing from it." );
							}
						}
					}
				} else {
					
					if( thingHolder.getThings().thingExists( thingNameOne ) ) {
						int indexOne = thingHolder.getThings().findIndexOf( thingNameOne );
						Thing thingOne = thingHolder.getThings().get( indexOne );
						Game.currentGame.getUI().println( "You " + getRandomActionDescriptor() + " retrieve the " 
								+ thingOne + getPreposition( "take", thingOne ) + "the " + thingHolder + "." );
						thingHolder.removeThing( indexOne );
						Game.currentGame.getPlayer().addThing( thingOne );
						thingOne.setLocationInRoom( "inventory" );
					} else {
						Game.currentGame.getUI().println( "I don't see a " + thingNameOne + " in the " + thingHolder + "." );
					}
				}
			} else {
				if( thingTwo.isThingHolder() ) {
					ThingHolder thingHolder = ( ThingHolder ) thingTwo;
					if( thingHolder.getThings().thingExists( thingNameOne ) ) {
						int indexOne = thingHolder.getThings().findIndexOf( thingNameOne );
						Thing thingOne = thingHolder.getThings().get( indexOne );
						thingHolder.removeThing( indexOne );
						Game.currentGame.getPlayer().addThing( thingOne );
						Game.currentGame.getUI().println( "You pick up the " + thingOne + " off the " + thingHolder );
						thingOne.setLocationInRoom( "inventory" );
						
					} else {
						Game.currentGame.getUI().println( "I don't see a " + thingNameOne + " on the " + thingHolder + "." );
					}
				} else {
					Game.currentGame.getUI().println( "Nothing can be retrieved from the " + thingTwo + "." );
				}	
			}
		
		} else if( Game.currentGame.getPlayer().getThings().thingExists( thingNameTwo ) ) {
		
			int indexTwo = Game.currentGame.getPlayer().getThings().findIndexOf( thingNameTwo );
			Thing thingTwo = Game.currentGame.getPlayer() .getThings().get( indexTwo );
			if( thingTwo.isThingHolder() ) {
				ThingHolder thingHolder = ( ThingHolder ) thingTwo;
				if( thingHolder.isContainer() ) {
					Container container = ( Container ) thingTwo;
					if( container.isLockable() ) {
						if( container.getLock().isLocked() ) {
							Game.currentGame.getUI().println( "The " + container + " is currently locked." );
						}
					}
					if( container.isOpen() ) {
						if( container.getThings().thingExists( thingNameOne ) ) {
							int indexOne = container.getThings().findIndexOf( thingNameOne );
							Thing thingOne = container.getThings().get( indexOne );
							Game.currentGame.getUI().println( "You retrieve the " + thingOne + getPreposition( "take", thingOne ) +"the " + container + "." );
							container.removeThing( indexOne );
							Game.currentGame.getPlayer().addThing( thingOne );
							thingOne.setLocationInRoom( "inventory" );
						} else {
							Game.currentGame.getUI().println( "I don't see a " + thingNameOne + " in the " + container + "." );
						}
					} else if( container.isOpen() == false ) {
						Game.currentGame.getUI().print( "The " + container + " is closed. You will have to open it before"
								+ " you can take any thing from it." );
					}
				}
			}
		} else {
			Game.currentGame.getUI().println( "I dont see a " + thingNameOne + " anywhere around here." );
		}
	}
	
	// once build put, separate this into two method and remove anAction
	public String getPreposition( String anAction, Thing aThing ) {
		if( aThing.isOnTopOf() ) {
			return " up off ";
		} else if( aThing.isUnderneath() ) {
			return " up from underneath ";
		} else if( aThing.isContainedWithin() ) {
			return " out of ";
		}
		return "";
	}
}
