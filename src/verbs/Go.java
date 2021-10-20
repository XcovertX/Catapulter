package verbs;

import java.awt.Color;

import game.Game;
import gameObjects.Actor;
import globals.Direction;
import world.GameMap;
import world.GameRoom;
import world.GameTile;

public class Go extends Action {
	
	public Go() {
		setRequiresNoun( true );
		setIsDirection( true );
	}

	@Override
	public void run() {
	}

	@Override
	public void run( String thing ) {
	    if( thing.equalsIgnoreCase( "n" ) ) {
	        updateOutput( movePlayerTo( Direction.NORTH ) );
	    }

	    if( thing.equalsIgnoreCase( "s" ) ) {
	        updateOutput( movePlayerTo( Direction.SOUTH ) );
	    }

	    if( thing.equalsIgnoreCase( "w" ) ) {
	        updateOutput( movePlayerTo( Direction.WEST ) );
	    }

	    if( thing.equalsIgnoreCase( "e" ) ) {
	        updateOutput( movePlayerTo( Direction.EAST ) );
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
	
	 // move a Person to a Room
    void moveActorTo( Actor p, GameTile aGameTile ) {
        p.setTile( aGameTile );
        aGameTile.setTileChar();
        
    }

    // move an Actor in direction 'dir'
   public int moveTo( Actor anActor, Direction dir ) {
        // return: Constant representing the room number moved to
        // or NOEXIT
        //
        // try to move any Person (typically but not necessarily player)
        // in direction indicated by dir
        GameTile gt = anActor.getTile();
        int exit;

        switch ( dir ) {
            case NORTH:
                exit = gt.getN();
                break;
            case SOUTH:
                exit = gt.getS();
                break;
            case EAST:
                exit = gt.getE();
                break;
            case WEST:
                exit = gt.getW();
                break;
            default:
                exit = Direction.NOEXIT; // noexit - stay in same room
                break;
        }
        if ( exit != Direction.NOEXIT ) {
        	
        	
        	 //TODO Make this a variable
        	if( ( ( GameTile ) Game.currentRoom.getTiles().get( exit ) ).isDoor() ==  true) { //NOTE: you can stop using this if you want the NPA to stay in room.
        		
        		//TODO This doesn't make sense --
        		GameTile door = (GameTile) ( Game.currentRoom.getTiles().get( exit ) );
        		Game.currentWorld =  Game.worldReader.getWorld( door.getExternalMapLocation(), door.getExternalMapName() );
        		Game.currentMap = (GameMap) ( Game.currentWorld.getMaps().get( 0 ) );
        		Game.currentRoom = (GameRoom) ( Game.currentMap.getRooms().get( 0 ) );
        		Game.currentTile = (GameTile) ( Game.currentRoom.getTiles().get( door.getExternalTile() ) );
        		
        		Game.currentMap.setWorld( Game.currentWorld );
        		Game.currentRoom.setMap( Game.currentMap );
        		Game.currentTile.setRoom( Game.currentRoom );
        		
        		//TODO Decide to instantiate whole world or individual maps
//        		currentTile = door.getExternalTile();
//        		currentRoom = currentTile.getRoom();
//        		currentMap = currentRoom.getMap();
        		
        		moveActorTo( anActor, Game.currentTile );
        		Game.currentTile.setTileChar(); //sets previous tile char
        		
        	} else {
        		
        		GameTile previousTile = Game.currentTile;
        		Game.currentTile = (GameTile) Game.currentRoom.getTiles().get( exit );
        		moveActorTo( anActor, Game.currentTile );
        		previousTile.setTileChar(); //sets previous tile char
        	}
        }
        return exit;
    }

    public int movePlayerTo( Direction dir ) {
        // return: Constant representing the room number moved to
        // or NOEXIT (see moveTo())
        //        
        return moveTo( Game.currentGame.getPlayer(), dir );
    }

    public void updateOutput( int roomNumber ) {
        // if roomNumber = NOEXIT, display a special message, otherwise
        // display text (e.g. name and description of room)
        String s;
        String currentExits = null;

        GameTile gt = Game.currentGame.getPlayer().getTile(); // current room player is in
        
        if ( roomNumber == Direction.NOEXIT ) {
        	
            s = "No Exit!";
            currentExits = gt.getTileExits().toString();     
            Game.currentGame.getUI().println( s );
            
        } else {
        
    		Game.currentGame.getUI().printColor( "Time: " + Game.calendar.getTime(), Color.ORANGE );
    		Game.currentGame.getUI().printColor( "Wind Direction: " + Game.calendar.getWeather().getCurrentWindDirection() + " ::: ", Color.ORANGE );
    		Game.currentGame.getUI().printlnColor( "Wind Speed: " + Game.calendar.getWeather().getCurrentWindIntensity(), Color.ORANGE );
    		Game.currentGame.getUI().print( "You are in " );
    		Game.currentGame.getUI().printColor( gt.getName(), Color.MAGENTA ); // switched to reading room info rather than tile
    		Game.currentGame.getUI().println( "." );							// remove once confirmed it works
    		Game.currentGame.getUI().println( gt.getDescription() );
//    		Game.currentGame.getUI().printColor( gt.getRoom().getName(), Color.MAGENTA );
//    		Game.currentGame.getUI().println( "." );
//    		Game.currentGame.getUI().println( gt.getRoom().getDescription() );
            
            if( !gt.getThings().isEmpty() ) {
            	
            	for( int i = 0; i < gt.getThings().size(); i++ ) {
            		
            		s = "There is a ";
            		Game.currentGame.getUI().printColor( s, Color.white );
            		s = gt.getThings().get( i ).toString();
            		Game.currentGame.getUI().printColor( s, Color.yellow );
            		s = " on the floor."; // TODO make this a variable
            		Game.currentGame.getUI().printlnColor( s, Color.white );
            	}
            }
            
            if( !gt.getNPCs().isEmpty() ) {
            	
            	for( int i = 0; i < gt.getNPCs().size(); i++ ) {
            		
            		Actor actor = ( Actor ) gt.getNPCs().get( i );
            		if( actor.isAlive() ) {
            			Game.currentGame.getUI().printColor( actor.toString(), Color.green );
            			Game.currentGame.getUI().printlnColor( " is here.", Color.white );
            		} else {
            			Game.currentGame.getUI().printColor( "The corpse of ", Color.white );
            			Game.currentGame.getUI().printColor( actor.toString(), Color.RED );
            			Game.currentGame.getUI().printlnColor( " is here.", Color.white );
            		}
            	}
            }
            
            currentExits = "The current exits are " + gt.getTileExits().toString();
            Game.currentGame.getUI().getDisplay().setRoom( Game.currentRoom ); 
        }
        
        Game.currentGame.getUI().printlnColor( currentExits, Color.CYAN );
    }

	public boolean isDirection() {
		return isDirection;
	}

	public void setIsDirection(boolean isDirection) {
		this.isDirection = isDirection;
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
}
