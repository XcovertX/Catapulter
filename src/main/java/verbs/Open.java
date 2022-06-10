package main.java.verbs;

import main.java.game.Game;
import main.java.gameObjects.Container;
import main.java.gameObjects.Thing;
import main.java.gameObjects.ThingHolder;

public class Open extends Action {
	
	public Open() {
		setRequiresNoun( true );
		setCanHaveNoun( true );
	}

	@Override
	public void run() {
		Game.currentGame.getUI().println( "Open what?");
		
	}

	@Override
	public void run( String thingName ) {

		if( Game.currentTile.getThings().thingExists( thingName ) == false 
				&& Game.currentGame.getPlayer().getThings().thingExists( thingName ) == false ) {
			Game.currentGame.getUI().println( "I don't see a " + thingName + " to open." );
		} else if( Game.currentTile.getThings().thingExists( thingName ) ) {
			Thing thing = Game.currentTile.getThings().findThisObject( thingName );
			if( thing.isHoldable() == false ) {
				if( thing.isThingHolder() ) {
					ThingHolder thingHolder = ( ThingHolder ) thing;
					if( thingHolder.isContainer() ) {
						Container container = ( Container ) thing;
						if( container.isOpenable() ) {
							if( container.isOpen() == false ) {
								if( container.isLockable() ) {
									if( container.getLock().isLocked() ) {
										Game.currentGame.getUI().println( "The " + container + " is currently locked. You will need a key to open it." );
									} else {
										container.open();
										Game.currentGame.getUI().print( "You " + container.getOpenAction() + " the " 
												+ container.getAccessMechanism() + " of the " + container + "." );
									}
								} else {
									Game.currentGame.getUI().println( "The " + container + " can not be locked." );
								}
							} else {
								Game.currentGame.getUI().println( "The " + container + " is already open." );
							}
						} else {
							Game.currentGame.getUI().println( "The " + container + " can not be opened." );
						}
					} else {
						Game.currentGame.getUI().println( "How exactly would you open a " + thing + "?" );
					}
				
				} else {
					Game.currentGame.getUI().println( "How exactly would you open a " + thing + "?" );
				}
			} else {
				Game.currentGame.getUI().println( "You have to pick up the " + thing + " to determine if"
						+ " you can open it.");
			}
				
		} else if( Game.currentGame.getPlayer().getThings().thingExists( thingName ) == true ){
			Thing thing = Game.currentGame.getPlayer().getThings().findThisObject( thingName );
			if( thing.isThingHolder() ) {
				ThingHolder thingHolder = ( ThingHolder ) thing;
				if( thingHolder.isContainer() ) {
					Container container = ( Container ) thing;
					if( container.isOpenable() ) {
						if( container.isOpen() == false ) {
							if( container.isLockable() ) {
								if( container.getLock().isLocked() ) {
									Game.currentGame.getUI().println( "The " + container + " is currently locked. You will need a key to open it." );
									return;
								}
							}
							container.open();
							Game.currentGame.getUI().print( "You " + container.getOpenAction() + " the " 
									+ container.getAccessMechanism() + " of the " + container + "." );
						
						} else {
							Game.currentGame.getUI().println( "The " + container + " is already open." );
						}
					} else {
						Game.currentGame.getUI().println( "The " + container + " can not be opened." );
					}
				} else {
					Game.currentGame.getUI().println( "How exactly would you open a " + thing + "?" );
				}
			}
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
		return this.requiresNoun;
	}

	@Override
	public void setRequiresNoun(boolean requiresNoun) {
		this.requiresNoun = true;
		
	}

	@Override
	public boolean canHaveNoun() {
		return this.canHaveNoun;
	}

	@Override
	public void setCanHaveNoun(boolean requiresNoun) {
		this.canHaveNoun = true;
		
	}

	@Override
	public boolean isDirection() {
		return this.isDirection;
	}

	@Override
	public void setIsDirection(boolean isDirection) {
		this.isDirection = isDirection;
		
	}
	
	

}
