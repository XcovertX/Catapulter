package main.java.verbs;

import java.awt.Color;

import main.java.actor.Actor;
import main.java.actor.Human;
import main.java.game.Game;
import main.java.gameObjects.Container;
import main.java.gameObjects.HomogeneousContentContainer;
import main.java.gameObjects.Thing;
import main.java.gameObjects.ThingHolder;
import main.java.globals.CatapulterColor;

public class Look extends Action {
	
	public Look() {
		setRequiresNoun( false );
		setCanHaveNoun( true );
		setIsDirection( false );
	}
	
	@Override
    public void run() {
		Game.currentGame.getInputProcessor().updateOutput( Game.currentTile.getTileNumber() );
	}
    
	@Override
	public void run( String thingName ) {
		
		if( Game.currentTile.getNPCs().thingExists( thingName ) ) {
			
			Actor actor = ( Actor ) Game.currentTile.getNPCs().findThisObject( thingName );
			
			if( actor.type.equals( "Human" ) ) {
				
				Human human = (Human) actor;
				Game.currentGame.getUI().println( human.getDescription() );
				Game.currentGame.getUI().println( human + " is wearing a " + human.getBody().getRightArm().getHand().getIndexFinger().getOnItem() );
			}
			Game.currentGame.getUI().println( actor.getDescription() );
		
		} else if( Game.currentTile.getThings().thingExists( thingName ) ) {
			
			Thing thing = Game.currentTile.getThings().findThisObject( thingName );

			Game.currentGame.getUI().println( thing.getDescription() );
			
			if( thing.isReadable() ) {
				
				if( thing.hasLargeText() ) {
				
					Game.currentGame.getUI().println( "On the " + thing.getReadableLargeTextLocation() + " of the "
							+ thing + ", the words: '" + thing.getReadableLargeText() + "' are written." );
					
					if( thing.hasSmallText() ) {
				
						Game.currentGame.getUI().println( "There is also some small print on the " 
								+ thing.getReadableSmallTextLocation() + " of the " + thing + ","
										+ "\nhowever, you may have to pick it up to get a closer look." );
					}
				
				} else if( thing.hasSmallText() ) {
				
					Game.currentGame.getUI().println( "There are some small words are written on the " 
							+ thing.getReadableSmallTextLocation() + " of the " + thing + ","
							+ "\nhowever, you may have to pick it up to get a closer look." );
				}
			}
			if( thing.isThingHolder() ) {
				
				ThingHolder thingHolder = ( ThingHolder ) thing;
				
				if( thingHolder.holdsItemsOnTop() ) {
					
					if( thingHolder.getThings().isEmpty() ==  false ) {
						
						for( int i = 0; i < thingHolder.getThings().size(); i++ ) {
							
							Game.currentGame.getUI().print( "There is a " );
							Game.currentGame.getUI().printColor( thingHolder.getThings().get( 0 ).toString(), CatapulterColor.YELLOW60 );
							Game.currentGame.getUI().println( " on top of the " + thingHolder + "." );
						}
					}
				}
				if( thingHolder.isContainer() ) {
					
					Container container = ( Container ) thingHolder;
				
					if( container.isLockable() ) {
				
						if( container.getLock().isLocked() ) {
					
							Game.currentGame.getUI().println( "The " + container + " is currently locked." );
				
						} else {
				
							Game.currentGame.getUI().print( "The " + container + " is currently" );
						
							if( container.isOpen() ) {
							
								Game.currentGame.getUI().print( " open " );
								
								if( container.isHomogeneousContentContainer() ) {
									
									HomogeneousContentContainer hcc = ( HomogeneousContentContainer ) container;
							
									if( hcc.containsLiquid() ) {
										
										String capacityUsed = hcc.getCapacityUsedEstimation();
										
										if( hcc.isEmpty() ) {
										
											Game.currentGame.getUI().println( "and is " + capacityUsed + "." );
										
										} else {
										
											Thing contents = hcc.getThings().get( 0 );
											Game.currentGame.getUI().println( "and is " + capacityUsed + " of " + contents +"." );
										}
									}
									
									if( hcc.containsSolid() ) {
								
										String capacityUsed = hcc.getCapacityUsedEstimation();
									
										if( hcc.isEmpty() || hcc.isFull() ) {
										
											Game.currentGame.getUI().println( "and is " + capacityUsed + "." );
										
										} else {
									
											Game.currentGame.getUI().println( "and has " + capacityUsed + " various items in it.\n" );
											Game.currentGame.getUI().println( hcc.getThings().describeThings() );
										}
									}
								}
								
							} else if( container.isTranslucent() ) {
					
								Game.currentGame.getUI().print( " closed " );
								
								if( container.isHomogeneousContentContainer() ) {
									
									HomogeneousContentContainer hcc = ( HomogeneousContentContainer ) container;
							
									if( hcc.containsLiquid() ) {
							
										String capacityUsed = hcc.getCapacityUsedEstimation();
							
										if( hcc.isEmpty() ) {
						
											Game.currentGame.getUI().println( "and is " + capacityUsed + "." );
						
										} else {
						
											Thing contents = hcc.getThings().get( 0 );
											Game.currentGame.getUI().println( "and is " + capacityUsed + " of " + contents +  "." );
										}
							
									}
							
									if( hcc.canContainSolid() ) {
								
										String capacityUsed = hcc.getCapacityUsedEstimation();
							
										if( hcc.isEmpty() || hcc.isFull() ) {
							
											Game.currentGame.getUI().println( "and is " + capacityUsed + "." );
							
										} else {
						
											Game.currentGame.getUI().println( "and has " + capacityUsed + " various items in it:\n" );
											Game.currentGame.getUI().println( hcc.getThings().describeThings() );
										}
									}
						
								} else {
						
									Game.currentGame.getUI().print( " closed." );
								}
							}
						}
					} else {
						

						System.out.println("here2");
						if( container.isHomogeneousContentContainer() ) {
							
							HomogeneousContentContainer hcc = ( HomogeneousContentContainer ) container;
					
							if( hcc.canContainLiquid() ) {

								System.out.println("here3");
								String capacityUsed = container.getCapacityUsedEstimation();
								Game.currentGame.getUI().println( "The " + container + " is " + capacityUsed + "." );
							}
							
						}	
					}
				}
			}
		} else if( Game.currentGame.getPlayer().getThings().thingExists( thingName ) ){
			
			Thing thing = Game.currentGame.getPlayer().getThings().findThisObject( thingName );
			Game.currentGame.getUI().println( thing.getDescription() );
			
			if( thing.isReadable() ) {
				
				if( thing.hasLargeText() ) {
				
					Game.currentGame.getUI().println( "On the " + thing.getReadableLargeTextLocation() + " of the "
							+ thing + ", the words: '" + thing.getReadableLargeText() + "' are written.");
					
					if( thing.hasSmallText() ) {
					
						Game.currentGame.getUI().println( "There is also some small print on the " 
								+ thing.getReadableSmallTextLocation() + " of the " + thing + " which read:\n"
										+ "'" + thing.getReadableSmallText() + ".'" );
					}
			
				} else if( thing.hasSmallText() ) {
					
					Game.currentGame.getUI().println( "There are some small words are written on the " 
							+ thing.getReadableSmallTextLocation() + " of the " + thing + " which read:\n"
							+ "'" + thing.getReadableSmallText() + ".'\n" );
				}
			}
			
			if( thing.isThingHolder() ) {
				
				ThingHolder thingHolder = ( ThingHolder ) thing;
			
				if( thingHolder.isContainer() ) {
					
					Container container = ( Container ) thingHolder;
					
					if( container.isLockable() ) {
						
						if( container.getLock().isLocked() ) {
							
							Game.currentGame.getUI().print( "The " + container + " is currently locked." );
							return;
						}
					}
					Game.currentGame.getUI().print( "The " + container + " is currently" );
					
					if( container.isOpen() ) {
						
						Game.currentGame.getUI().print( " open " );
						
						if( container.isHomogeneousContentContainer() ) {
							
							HomogeneousContentContainer hcc = ( HomogeneousContentContainer ) container;
						
							if( hcc.containsLiquid() ) {
								
								String capacityUsed = hcc.getCapacityUsedEstimation();
								
								if( hcc.isEmpty() ) {
									
									Game.currentGame.getUI().println( "and is " + capacityUsed + "." );
								
								} else {
									
									Thing contents = hcc.getThings().get( 0 );
									Game.currentGame.getUI().println( "and is " + capacityUsed + " of " + contents +"." );
								}
							}
							if( hcc.containsSolid() ) {
								
								String capacityUsed = hcc.getCapacityUsedEstimation();
								
								if( hcc.isEmpty() || hcc.isFull() ) {
									
									Game.currentGame.getUI().println( "and is " + capacityUsed + "." );
								
								} else {
								
									Game.currentGame.getUI().println( "and has " + capacityUsed + " various items in it:\n" );
									Game.currentGame.getUI().println( container.getThings().describeThings() );
								
								}
							}
						}
					
					} else if( container.isTranslucent() ) { 
						
						Game.currentGame.getUI().print( " closed " );
						
						if( container.isHomogeneousContentContainer() ) {
							
							HomogeneousContentContainer hcc = ( HomogeneousContentContainer ) container;
						
							if( hcc.containsLiquid() ) {
								
								String capacityUsed = hcc.getCapacityUsedEstimation();
								
								if( hcc.isEmpty() ) {
									
									Game.currentGame.getUI().println( "and is " + capacityUsed + "." );
								
								} else {
									
									Thing contents = hcc.getThings().get( 0 );
									Game.currentGame.getUI().println( "and is " + capacityUsed + " of " + contents +  "." );
								}
							}
							if( hcc.containsSolid() ) {
								
								String capacityUsed = container.getCapacityUsedEstimation();
								
								if( hcc.isEmpty() || hcc.isFull() ) {
								
									Game.currentGame.getUI().println( "and is " + capacityUsed + "." );
								
								} else {
								
									Game.currentGame.getUI().println( "and has " + capacityUsed + " various items in it:\n" );
									Game.currentGame.getUI().println( hcc.getThings().describeThings() );
								}
							}
						}
					} else {
						
						Game.currentGame.getUI().print( " closed." );
					}
				}
//			} else {
//				
//				if( container.canContain( "liquid" ) ) {
//					
//					String capacityUsed = container.getCapacityUsedEstimation();
//					Game.currentGame.getUI().println( "The " + container + " is " + capacityUsed + "." );
//				}	
			}
		} else if( Game.currentTile.getThings().thingExists( thingName ) == false 
				&& Game.currentGame.getPlayer().getThings().thingExists( thingName ) == false ) {
			
			Game.currentGame.getUI().println( "I don't see a " + thingName + " anywhere here." );
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
}
