package main.java.verbs;

import main.java.game.Game;
import main.java.gameObjects.*;
import main.java.globals.CatapulterColor;
import main.java.world.UpdateThirst;

public class Drink extends Action {
	
	private int drinkAmount = 400;		// 400 drops == 20 milliliters		
	
	public Drink() {
		setRequiresNoun( true );
		setIsDirection( false );
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * The run( string ) method checks the following:
	 * if noun is a thing in player's inventory
	 * if thing is thingholder
	 * if thingholder is a container
	 * if container is is empty
	 * if contents is a liquid
	 * if liquid is drinkable
	 * 
	 * if all those are satisfied, then calls remove()
	 * to increment the contents of the container
	 * 
	 * else, the appropriate message is sent to UI.
	 */
	@Override
	public void run(String thingName ) {
		
		if( Game.currentGame.getPlayer().getThings().thingExists( thingName ) ) {
			
			Thing thing = Game.currentGame.getPlayer().getThings().findThisObject( thingName );
			
			if( thing.isThingHolder() ) {
			
				ThingHolder thingHolder = ( ThingHolder ) thing;
				
				if( thingHolder.isContainer() ) {
				
					Container container = ( Container ) thingHolder;
					
					if( container.isHomogeneousContentContainer() ) {
						
						HomogeneousContentContainer hcc = ( HomogeneousContentContainer ) container;

						if( hcc.isOpen() ) {
							
							if( !hcc.isEmpty() ) {
					
								if( hcc.canContainLiquid() ) {
									
									if( hcc.containsLiquid() ) {
									
										Thing contents = container.getThings().getFirst();
										
										if( contents.isDrinkable() ) {
											
											remove( hcc ); //processes the drinking of the container contents
											
										} else {
											
											Game.currentGame.getUI().println( "You can not drink the " + contents + "." );
										}
									} else {
										
										Game.currentGame.getUI().println( "The contents are not liquid." );
									}
								} else {
									
									Game.currentGame.getUI().println( "The " + hcc + " can not hold a liquid to drink." );
								}
								
							} else {
								
								Game.currentGame.getUI().println( "The " + container + " is currently empty." );
							}
							
						} else {
							
							container.open();
							Game.currentGame.getUI().print( "You " + container.getOpenAction() + " the " + container.getAccessMechanism() + " of the " + container + " and " );
							
							if( !hcc.isEmpty() ) {
						
								if( hcc.canContainLiquid() ) {
								
									if( hcc.containsLiquid() ) {
										
										Thing contents = container.getThings().getFirst();
										
										if( contents.isDrinkable() ) {
											
											
											Game.currentGame.getUI().println( "lift it to your mouth." );
											remove( hcc ); //processes the drinking of the container contents
											container.close();
											Game.currentGame.getUI().print( "You " + container.getCloseAction() + " the " + container.getAccessMechanism() + 
													" of the " + container + " and put it away." );
											
										} else {
											
											Game.currentGame.getUI().println( "You can not drink the " + contents + "." );
										}
									} else {
										
										Game.currentGame.getUI().println( "determine the contents are not liquid." );
										container.close();
										Game.currentGame.getUI().println( "You close the " + container + " and put it away." );
									}
									
								} else {
									
									Game.currentGame.getUI().println( "The " + hcc + " can not hold a liquid to drink." );
								}
							} else {
								
								Game.currentGame.getUI().println( "determine that the " + container + " is empty." );
								container.close();
								Game.currentGame.getUI().println( "You close the " + container + " and put it away." );
							}
						}
						
					} else {

						Game.currentGame.getUI().println( "What do you want to drink out of the " + container + "?" );
					}
				} else {
					Game.currentGame.getUI().println( "The " + thingHolder + " does not carry anything drinkable." );
				}
			} else {
				Game.currentGame.getUI().println( "You can not drink a " + thing + "." );
			}
		} else {
			Game.currentGame.getUI().println( "You are not carrying a " + thingName );
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
	
	private void remove( HomogeneousContentContainer container ) {
		
		int quantity = Math.round( container.getThings().get( 0 ).getSize() / drinkAmount ); // how many drink amounts left

		System.out.println( "quantity: " + quantity );
		int quantityConsumed = 0;
		Liquid liquid = ( Liquid ) container.getThings().getFirst();
		
		if( Game.currentGame.getPlayer().getThirst() < 20 ) { //not thirsty
			
			if( quantity < ( 2 ) ) {
				for(int i = 0; i < quantity; i++ ) {
					
					container.decreaseContents( drinkAmount );
					quantityConsumed++;
				}
				Game.currentGame.getUI().printlnColor( "You drink the remaining contents of the " + container + ".", CatapulterColor.GREEN40 );
			
			} else {
				
				for(int i = 0; i < 2; i++ ) {
					
					container.decreaseContents( drinkAmount );
					quantityConsumed++;
				}
				Game.currentGame.getUI().printlnColor( "You take a modest sip from the " + container + ".", CatapulterColor.GREEN40 );
			}
		
		} else if( Game.currentGame.getPlayer().getThirst() < 70 ) { //pretty thirsty
			
			if( quantity < ( 4 ) ) {
				
				for(int i = 0; i < quantity; i++ ) {
					
					container.decreaseContents( drinkAmount );
					quantityConsumed++;
				}
				Game.currentGame.getUI().printlnColor( "With a large gulp, you down the "
						+ "remaining contents of the " + container + ".", CatapulterColor.GREEN40 );
			} else {
				
				for(int i = 0; i < 4; i++ ) {
					
					container.decreaseContents( drinkAmount );
					quantityConsumed++;
				}
				Game.currentGame.getUI().printlnColor( "You take a large gulp from the " + container + ".", CatapulterColor.GREEN40 );
			}
		
		} else if( Game.currentGame.getPlayer().getThirst() == 100 ) { //so thirst hitpoints are being lost
			
			if( quantity < 6 ) {
				
				for(int i = 0; i < quantity; i++ ) {
					
					container.decreaseContents( drinkAmount );
					quantityConsumed++;
				}
				Game.currentGame.getUI().printlnColor( "With several large gulps, you down the "
						+ "remaining contents of the " + container + ".", CatapulterColor.GREEN40 );
			} else {
				
				for(int i = 0; i < 6; i++ ) {
					
					container.decreaseContents( drinkAmount );
					quantityConsumed++;
				}
				Game.currentGame.getUI().printlnColor( "You take several large gulps from the " + container + ".", CatapulterColor.GREEN40 );
			}
			
		} else {
			
			for(int i = 0; i < quantity; i++ ) {
				
				container.decreaseContents( drinkAmount );
				quantityConsumed++;
			}
			Game.currentGame.getUI().printlnColor( "With several large gulps, you down the "
					+ "remaining contents of the " + container + ".", CatapulterColor.GREEN40 );
		}
		
		int thirstModifier = liquid.getThirstModifier();
		int decreaseBy = thirstModifier * quantityConsumed;
		new UpdateThirst().decreaseThirst( Game.currentGame.getPlayer(), liquid, decreaseBy );
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

	public int getDrinkAmount() {
		return drinkAmount;
	}

	public void setDrinkAmount(int drinkAmount) {
		this.drinkAmount = drinkAmount;
	}
}
