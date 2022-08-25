package verbs;

import java.awt.Color;

import actor.Actor;
import game.Game;
import globals.Direction;
import messages.*;
import world.GameMap;
import world.GameRoom;
import world.GameTile;

public class Go extends Action {
	
	public Go() {
		setRequiresNoun( true );
		setIsDirection( true );
	}

	@Override
	public void run() { }

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
	public void run( String thingName, String preposition ) { }
	
	@Override
	public void run( String thingNameOne, String preposition, String thingNameTwo ) { }

	@Override
	public boolean requiresNoun() {
		return this.requiresNoun;
	}

	@Override
	public void setRequiresNoun( boolean requiresNoun ) {
		this.requiresNoun = requiresNoun;
	}

    void moveActorTo( Actor p, GameTile aGameTile ) {
        p.setTile( aGameTile );
        aGameTile.setCurrentTileChar();
		p.moveSprite( aGameTile );
    }

   public int moveTo( Actor anActor, Direction dir ) {

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
                exit = Direction.NOEXIT;
                break;
        }
        if ( exit != Direction.NOEXIT ) {

        	if( ( ( GameTile ) Game.currentRoom.getTiles().get( exit ) ).isDoor() ) {
        		GameTile door = (GameTile) ( Game.currentRoom.getTiles().get( exit ) );
//        		Game.currentWorld =  worldReader.getWorld( door.getExternalMapLocation(), door.getExternalMapName() );
        		Game.currentTile.setCurrentTileCharToDefaultTileChar();
        		Game.currentMap  = ( GameMap )  ( Game.currentWorld.getMaps().get( Game.currentWorld.getMaps().findIndexOf( door.getExternalMapName() ) ) );
        		Game.currentRoom = ( GameRoom ) ( Game.currentMap.getRooms().get( Game.currentMap.getRooms().findIndexOf( door.getExternalRoomName() ) ) );
        		Game.currentTile = ( GameTile ) ( Game.currentRoom.getTiles().get( door.getExternalTile() ) );
        		
        		Game.currentMap.setWorld( Game.currentWorld );
        		Game.currentRoom.setMap( Game.currentMap );
        		Game.currentTile.setRoom( Game.currentRoom );
        		
        		//TODO Decide to instantiate whole world or individual maps
//        		currentTile = door.getExternalTile();
//        		currentRoom = currentTile.getRoom();
//        		currentMap = currentRoom.getMap();
        		
        		moveActorTo( anActor, Game.currentTile );
        		Game.currentTile.setCurrentTileChar(); //sets previous tile char
        		
        	} else {
        		
        		GameTile previousTile = Game.currentTile;
        		Game.currentTile = (GameTile) Game.currentRoom.getTiles().get( exit );
        		moveActorTo( anActor, Game.currentTile );
        		previousTile.setCurrentTileChar(); //sets previous tile char
        	}
        }
        return exit;
    }

    public int movePlayerTo( Direction direction ) {

        return moveTo( Game.currentGame.getPlayer(), direction );
    }

    public void updateOutput( int roomNumber ) {

        GameTile gt = Game.currentGame.getPlayer().getTile();
        
        if ( roomNumber == Direction.NOEXIT ) {
			new ExitsMessage().run( "No Exit" );
			new ExitsMessage().run( gt );
        } else {
			new LocationMessage().run( gt );
			new DescriptionMessage().run( gt );
			new ExitsMessage().run( gt );
			new ActorsPresentMessage().run( gt );
			new ThingsPresentMessage().run( gt );
            Game.currentGame.getUI().getDisplay().setRoom( Game.currentRoom ); 
        }
//        Game.currentGame.getUI().getTextMapController().setRoomDescription( currentExits );
    }

	public boolean isDirection() {
		return isDirection;
	}

	public void setIsDirection( boolean isDirection ) {
		this.isDirection = isDirection;
	}

	@Override
	public boolean canHaveNoun() { return false; }

	@Override
	public void setCanHaveNoun( boolean requiresNoun ) { }
}
