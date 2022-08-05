package verbs;

import java.awt.Color;

import game.Game;
import gameObjects.Container;
import gameObjects.Key;
import gameObjects.Thing;
import gameObjects.ThingHolder;
import globals.CatapulterColor;

public class Unlock extends Action {
	
	public Unlock() {
		this.setRequiresNoun( true );
		this.setRequiresPreposition( true );
	}

	@Override
	public void run() {
		Game.currentGame.getUI().println( "Unlock what?" );
	}

	@Override
	public void run(String thingName ) {
		if( Game.currentTile.getThings().thingExists( thingName ) == false 
				&& Game.currentGame.getPlayer().getThings().thingExists( thingName ) == false ) {
			Game.currentGame.getUI().println( "I don't see a " + thingName + " anywhere here." );
		} else if( Game.currentTile.getThings().thingExists( thingName ) ){
			Thing thing = Game.currentTile.getThings().findThisObject( thingName );
			if( thing.isHoldable() == false ) {
				if( thing.isThingHolder() ) {
					ThingHolder thingHolder = ( ThingHolder ) thing;
					if( thingHolder.isContainer() ) {
						Container container = ( Container ) thing;
						if( container.isLockable() ) {
							if( container.getLock().isLocked() ) {
								if( Game.currentGame.getPlayer().getThings().thingExists( "key" ) ) {
									Key key = ( Key ) Game.currentGame.getPlayer().getThings().findThisObject( "key" );
									if( key.getKeyShape() == container.getLock().getLockShape() ) {
										if( key.getKeyCode() == container.getLock().getLockCode() ) {
											container.getLock().setIsLocked( false );
											Game.currentGame.getUI().printlnColor( "*Click*", CatapulterColor.GREEN60 );
											Game.currentGame.getUI().println( "The " + container + " is now unlocked." );
										} else {
											Game.currentGame.getUI().println( "The key fits into the slot,"
													+ " but it does not work with this lock." );
										}
									} else {
										Game.currentGame.getUI().println( "The key does't fit." );
									}
								} else {
									Game.currentGame.getUI().println( "You dont have the key to open that lock." );
								}
							} else {
								Game.currentGame.getUI().println( "The " + container + " is already unlocked." );
							}
						} else {
							Game.currentGame.getUI().println( "The " + container + " can not be locked or unlocked." );
						}
					} else {
						Game.currentGame.getUI().println( "How would you unlock that?" );
					}
				} else {
					Game.currentGame.getUI().println( "How would you unlock that?" );
				}
			} else {
				Game.currentGame.getUI().println( "In order to unlock the " + thing + ", you must pick it up first." );
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
	public void setRequiresNoun( boolean requiresNoun ) {
		this.requiresNoun = requiresNoun;
		
	}

	@Override
	public boolean canHaveNoun() {
		return this.canHaveNoun;
	}

	@Override
	public void setCanHaveNoun( boolean canHaveNoun ) {
		this.canHaveNoun = canHaveNoun;
		
	}

	@Override
	public boolean isDirection() {
		return this.isDirection;
	}

	@Override
	public void setIsDirection( boolean isDirection ) {
		this.isDirection = isDirection;
		
	}

}
