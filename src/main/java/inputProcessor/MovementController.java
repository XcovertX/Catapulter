package inputProcessor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

import actor.Actor;
import actor.Cat;
import actor.NonPlayerActor;
import game.Game;
import globals.Direction;
import world.GameMap;
import world.GameRoom;
import world.GameTile;
import world.GameWorld;

public abstract class MovementController {
	
	private NonPlayerActor npc;
	private Timer timer;
	private GameWorld currentWorld;
	private GameMap currentMap;
	private GameRoom currentRoom;
	private GameTile currentTile;
	private GameTile previousTile;
	
	private String recentlyVisited;
	private int movementTotal;
	
    // controller state
    private boolean movementRandom;
    private boolean movementCustom;
    private String movementType;
    private int walkSpeed;
    private int runSpeed;
    private int delay;
    private int stopCounter;
    private int counter = 0;
    private String lastDirection;
    private String distanceBounds; // inWorld, inMap, inRoom
    
    // relative heading
    private Direction relativeHeading;
    
    List< String > commands = new ArrayList<>(Arrays.asList(
            "take", "drop", "look",
            "n", "s", "w", "e" ) );
    List< String > objects = new ArrayList<>( Arrays.asList() );
	
	public MovementController() { }
	
	public void inRoomWander( ArrayList< String > currentExits ) {
		moveToRandomTile( currentExits );
	}
	
	public abstract void customWander( ArrayList< String > currentExits );
	public abstract void purposeDriven( ArrayList< String > currentExits );
	
	public String getWeightedRandomDirection( ArrayList< String > currentExits ) {
		
		ArrayList< String > weightedExits = (ArrayList< String >) currentExits.clone();
		if( lastDirection != null ) {
			weightedExits.add( lastDirection );
			weightedExits.add( lastDirection );
			weightedExits.add( lastDirection );
			weightedExits.add( lastDirection );
		}
		System.out.println( "we: " + weightedExits );
		Random rand = new Random();
		int randomNumber = rand.nextInt( weightedExits.size() );
		String randomDirection = weightedExits.get( randomNumber );
		return randomDirection;
		
	}
	
	// relative controls
	public int moveForward() {
		
		int direction = movePlayerTo( this.getRelativeHeading() );
		
		if( direction != -1 ) {
			System.out.println( "moving forward" );
			updateOutput( direction );
		}
		
		return direction;
	}
	
	public int moveForwardLeft() {
		return 0;
	}
	
	public int moveLeft() {
		return 0;
	}
	
	public int moveBackwardLeft() {
		return 0;
	}
	
	public int moveBackward() {
		return 0;
	}
	
	public int moveBackwardRight() {
		return 0;
	}
	
	public int moveRight() {
		return 0;
	}
	
	public int moveForwardRight() {
		return 0;
	}
	
	public void turnLeft() {

		switch ( relativeHeading ) {
		
        case NORTH:
        	relativeHeading = Direction.NORTHWEST;
            break;
        case NORTHWEST:
        	relativeHeading = Direction.WEST;
            break;
        case WEST:
        	relativeHeading = Direction.SOUTHWEST;
            break;
        case SOUTHWEST:
        	relativeHeading = Direction.SOUTH;
            break;
        case SOUTH:
        	relativeHeading = Direction.SOUTHEAST;
            break;
        case SOUTHEAST:
        	relativeHeading = Direction.EAST;
            break;
        case EAST:
        	relativeHeading = Direction.NORTHEAST;
            break;
        case NORTHEAST:
        	relativeHeading = Direction.NORTH;
            break;
		default:
			break;
		}
	}
	
	public void turnRight() {
		
		switch ( relativeHeading ) {
		
        case NORTH:
        	relativeHeading = Direction.NORTHEAST;
            break;
        case NORTHEAST:
        	relativeHeading = Direction.EAST;
            break;
        case EAST:
        	relativeHeading = Direction.SOUTHEAST;
            break;
        case SOUTHEAST:
        	relativeHeading = Direction.SOUTH;
            break;
        case SOUTH:
        	relativeHeading = Direction.SOUTHWEST;
            break;
        case SOUTHWEST:
        	relativeHeading = Direction.WEST;
            break;
        case WEST:
        	relativeHeading = Direction.NORTHWEST;
            break;
        case NORTHWEST:
        	relativeHeading = Direction.NORTH;
            break;
		default:
			break;
		}
	}
	
	public void followWallLeft() {
		
		int tileNum = moveForward();
		while( tileNum <= 0 ) {
			turnLeft();
			tileNum = moveForward();
			System.out.println( "tn: " + tileNum + " " + relativeHeading );
		}
		turnRight();
		turnRight();
		moveForward();
	}
	
	public void moveToRandomTile( ArrayList< String > currentExits ) {
		
//		Random rand = new Random();
//		int randomNumber = rand.nextInt( currentExits.size() );
//		String randomDirection = currentExits.get( randomNumber );
		String randomDirection;
		if( currentExits.size() == 1 ) {
			randomDirection = currentExits.get(0);
			recentlyVisited = null;
		}
		
		randomDirection = getWeightedRandomDirection( currentExits );
		System.out.println( "dir: " + randomDirection );
		if( randomDirection == "n" && ( recentlyVisited != "n" || recentlyVisited != "nw" || recentlyVisited != "ne" ) ) {
			
			this.lastDirection = randomDirection;
			this.recentlyVisited = "s";
			updateOutput( movePlayerTo( Direction.NORTH ) );
			
		} else if( randomDirection == "s" && ( recentlyVisited != "s" || recentlyVisited != "sw" || recentlyVisited != "se" ) ) {
			
			this.lastDirection = randomDirection;
			this.recentlyVisited = "n";
			updateOutput( movePlayerTo( Direction.SOUTH ) );
			
		} else if( randomDirection == "e" && ( recentlyVisited != "e" || recentlyVisited != "ne" || recentlyVisited != "se" ) ) {
			
			this.lastDirection = randomDirection;
			this.recentlyVisited = "w";
			updateOutput( movePlayerTo( Direction.EAST ) );
			
		} else if( randomDirection == "w" && ( recentlyVisited != "w" || recentlyVisited != "nw" || recentlyVisited != "sw" ) ) {
			
			this.lastDirection = randomDirection;
			this.recentlyVisited = "e";
			updateOutput( movePlayerTo( Direction.WEST ) );
			
		} else if( randomDirection == "nw" && ( recentlyVisited != "nw" || recentlyVisited != "n" || recentlyVisited != "w" ) ) {
			
			this.lastDirection = randomDirection;
			this.recentlyVisited = "se";
			updateOutput( movePlayerTo( Direction.NORTHWEST ) );
			
		} else if( randomDirection == "sw" && ( recentlyVisited != "sw" || recentlyVisited != "s" || recentlyVisited != "w" ) ) {
			
			this.lastDirection = randomDirection;
			this.recentlyVisited = "ne";
			updateOutput( movePlayerTo( Direction.SOUTHWEST ) );
			
		} else if( randomDirection == "ne" && ( recentlyVisited != "ne" || recentlyVisited != "n" || recentlyVisited != "e" ) ) {

			System.out.println("going ne");
			this.lastDirection = randomDirection;
			this.recentlyVisited = "sw";
			updateOutput( movePlayerTo( Direction.NORTHEAST ) );
			
		} else if( randomDirection == "se" && ( recentlyVisited != "se" || recentlyVisited != "s" || recentlyVisited != "e" ) ) {
			
			this.lastDirection = randomDirection;
			this.recentlyVisited = "nw";
			updateOutput( movePlayerTo( Direction.SOUTHEAST ) );
			
		} else {
//			this.recentlyVisited = null; //remove this one diagonal movement implemented
			moveToRandomTile( currentExits );
		}
	}
	
	// move an actor to a new tile
    void moveActorTo( Actor npc, GameTile aGameTile ) {
    	
    	previousTile = currentTile;
    	currentTile = aGameTile;
    	previousTile.getNPCs().remove( npc ); 
//    	previousTile.setTileChar();
        currentTile.addNPC( npc );
//        currentTile.setTileChar();
        npc.setTile( aGameTile );
        setCounter( 0 );
        
    }

    // move an Actor in direction 'dir'
    int moveTo( Actor anActor, Direction dir ) {
    	
        GameTile gt = this.currentTile;
        int exit;

        switch (dir) {
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
            case NORTHWEST:
                exit = gt.getNW();
                break;
            case SOUTHWEST:
                exit = gt.getSW();
                break;
            case NORTHEAST:
                exit = gt.getNE();
                break;
            case SOUTHEAST:
                exit = gt.getSE();
                break;
            default:
                exit = Direction.NOEXIT;
                break;
        }
        
        if ( exit != Direction.NOEXIT ) {
        	
        	if( ( ( ( GameTile ) getCurrentRoom().getTiles().get( exit ) ).isDoor() ) ) { 
        		
        		if( !npc.getDistanceBounds().equals( "inRoom" ) ) {
	        		
	        		GameTile door = (GameTile) ( this.currentRoom.getTiles().get( exit ) );
	        		
	        		this.currentMap  = ( GameMap )  ( Game.currentWorld.getMaps().get( Game.currentWorld.getMaps().findIndexOf( door.getExternalMapName() ) ) );
	        		this.currentRoom = ( GameRoom ) ( this.currentMap.getRooms().get( this.currentMap.getRooms().findIndexOf( door.getExternalRoomName() ) ) );
	        		
	        		moveActorTo( anActor, ( GameTile ) ( this.currentRoom.getTiles().get( door.getExternalTile() ) ) );
	        		
        		} else {
        			
        			moveActorTo( anActor, ( GameTile ) this.currentRoom.getTiles().get( exit ) );	
        		}
        		
        	} else {
        		
        		moveActorTo( anActor, ( GameTile ) this.currentRoom.getTiles().get( exit ) );
        	}
        }
        return exit;
    }

    public int movePlayerTo( Direction dir ) {
      
        return moveTo( this.npc, dir );
    }

    private void goN() {
        updateOutput(movePlayerTo(Direction.NORTH));
    }

    private void goS() {
        updateOutput(movePlayerTo(Direction.SOUTH));
    }

    private void goW() {
        updateOutput(movePlayerTo(Direction.WEST));
    }

    private void goE() {
        updateOutput(movePlayerTo(Direction.EAST));
    }

    public void updateOutput( int tileNumber ) { 
 
        if( this.currentRoom.equals( Game.currentRoom ) ||
        	this.previousTile.getRoom().equals( Game.currentRoom )	) {
        	if( Game.currentGame.getPlayer().getTile().equals( npc.getTile() ) ) {
        		Game.currentGame.getUI().printColor( npc.getName(), Color.green );
        		Game.currentGame.getUI().print( " has entered from the " );
        		Game.currentGame.getUI().printlnColor( cameFrom().toString(), Color.CYAN);
        		Cat temp = ( Cat ) npc;
        		temp.sayMeow();
        		
        	} else if( Game.currentGame.getPlayer().getTile().equals( previousTile ) ) {
        		Game.currentGame.getUI().printColor( npc.getName(), Color.green );
        		Game.currentGame.getUI().print( " has exited to the " );
        		Game.currentGame.getUI().printlnColor( wentTo().toString(), Color.CYAN);
        	}
        }
        Game.currentGame.getUI().getDisplay().setRoom( Game.currentRoom ); 
    }

    public String ProcessVerb( List< String > wordlist ) {
        String verb;
        String msg = "";
        verb = wordlist.get(0);
        if ( !commands.contains( verb ) ) {
            msg = verb + " is not a known verb! ";
        } else {
            switch ( verb ) {
                case "n":
                    goN();
                    break;
                case "s":
                    goS();
                    break;
                case "w":
                    goW();
                    break;
                case "e":
                    goE();
                    break;
                default:
                    msg = verb + " (not yet implemented)";
                    break;
            }
        }
        return msg;
    }

    public String ProcessVerbNoun( List< String > wordlist ) {
        String verb;
        String noun;
        String msg = "";
        verb = wordlist.get( 0 );
        noun = wordlist.get( 1 );
        if ( !commands.contains( verb ) ) {
            msg = verb + " is not a known verb! ";
        }
        if (!objects.contains(noun)) {
            msg += ( noun + " is not a known noun!" );
        }
        msg += " (not yet implemented)";
        return msg;
    }

    public String ParseCommand( List< String > wordlist ) {
        String msg;
        if ( wordlist.size() == 1 ) {
            msg = ProcessVerb( wordlist );
        } else if ( wordlist.size() == 2 ) {
            msg = ProcessVerbNoun( wordlist );
        } else {
            msg = "Only 2 word commands allowed!";
        }
        return msg;
    }

    public List<String> WordList(String input) {
        String delims = " \t,.:;?!\"'";
        List<String> strlist = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer( input, delims );
        String t;

        while (tokenizer.hasMoreTokens()) {
            t = tokenizer.nextToken();
            strlist.add( t );
        }
        return strlist;
    }
    
    public String runCommand( String inputstr ) {
        List<String> wordlist;
        String s = "ok";
        String lowstr = inputstr.trim().toLowerCase();        
        if ( !lowstr.equals( "q" ) ) {
            if ( lowstr.equals( "" ) ) {
                s = "You must enter a command";
            } else {
                wordlist = WordList( lowstr );                
                s = ParseCommand( wordlist );
            }
        }
        return s;
    }
    
    public Direction cameFrom() {
    	if( recentlyVisited.equals( "n" ) ) {
    		return Direction.NORTH;
    	} else if( recentlyVisited.equals( "s" ) ) {
    		return Direction.SOUTH;
    	} else if( recentlyVisited.equals( "e" ) ) {
    		return Direction.EAST;
    	} else if( recentlyVisited.equals( "w" ) ) {
    		return Direction.WEST;
    	} else if( recentlyVisited.equals( "se" ) ) {
    		return Direction.SOUTHEAST;
    	} else if( recentlyVisited.equals( "sw" ) ) {
    		return Direction.SOUTHWEST;
    	} else if( recentlyVisited.equals( "ne" ) ) {
    		return Direction.NORTHEAST;
    	} else if( recentlyVisited.equals( "nw" ) ) {
    		return Direction.NORTHWEST;
    	}
    	return null;
    }
    
    public Direction wentTo() {
    	if( recentlyVisited.equals( "n" ) ) {
    		return Direction.SOUTH;
    	} else if( recentlyVisited.equals( "s" ) ) {
    		return Direction.NORTH;
    	} else if( recentlyVisited.equals( "e" ) ) {
    		return Direction.WEST;
    	} else if( recentlyVisited.equals( "w" ) ) {
    		return Direction.EAST;
    	} else if( recentlyVisited.equals( "nw" ) ) {
    		return Direction.SOUTHEAST;
    	} else if( recentlyVisited.equals( "ne" ) ) {
    		return Direction.SOUTHWEST;
    	} else if( recentlyVisited.equals( "sw" ) ) {
    		return Direction.NORTHEAST;
    	} else if( recentlyVisited.equals( "se" ) ) {
    		return Direction.NORTHWEST;
    	}
    	return null;
    }

	public boolean isMovementRandom() {
		return movementRandom;
	}

	public void setMovementRandom(boolean movementRandom) {
		this.movementRandom = movementRandom;
	}

	public boolean isMovementCustom() {
		return movementCustom;
	}

	public void setMovementCustom(boolean movementCustom) {
		this.movementCustom = movementCustom;
	}

	public GameWorld getCurrentWorld() {
		return currentWorld;
	}

	public void setCurrentWorld(GameWorld currentWorld) {
		this.currentWorld = currentWorld;
	}
	
	public GameMap getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(GameMap currentMap) {
		this.currentMap = currentMap;
	}
	
	public GameRoom getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(GameRoom currentRoom) {
		this.currentRoom = currentRoom;
	}

	public GameTile getCurrentTile() {
		return currentTile;
	}

	public void setCurrentTile(GameTile currentTile) {
		this.currentTile = currentTile;
	}
	
	public Timer getTimer() {
		return this.timer;
	}
	
	public void setTimer() {
		this.timer = new Timer();
	}
	
	public void setNPC( NonPlayerActor npc ) {
		this.npc = npc;
	}
	
	public NonPlayerActor getNPC() {
		return this.npc;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getWalkSpeed() {
		return walkSpeed;
	}

	public void setWalkSpeed(int walkSpeed) {
		this.walkSpeed = walkSpeed;
	}
	
	public int getRunSpeed() {
		return runSpeed;
	}

	public void setRunSpeed(int runSpeed) {
		this.runSpeed = runSpeed;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public void incrementCounter() {
		counter++;
	}
	
	public void setRecentlyVisited( String dir ) {
		this.recentlyVisited = dir;
	}
	
	public String getRecentlyVisited() {
		return this.recentlyVisited;
	}

	public String getMovementType() {
		return movementType;
	}

	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

	public int getStopCounter() {
		return stopCounter;
	}

	public void setStopCounter(int stopCounter) {
		this.stopCounter = stopCounter;
	}

	public int getMovementTotal() {
		return movementTotal;
	}

	public void setMovementTotal(int movementTotal) {
		this.movementTotal = movementTotal;
	}
	
	public void incrementMovementTotalCounter() {
		this.movementTotal++;
	}

	public String getLastDirection() {
		return lastDirection;
	}

	public void setLastDirection(String lastDirection) {
		this.lastDirection = lastDirection;
	}

	public Direction getRelativeHeading() {
		return relativeHeading;
	}

	public void setRelativeHeading(Direction relativeHeading) {
		this.relativeHeading = relativeHeading;
	}
	
	public void setPreviousTile ( GameTile gt ) {
		this.previousTile = gt;
	}
	
	public GameTile getPreviousTile() {
		return this.previousTile;
	}

	public String getDistanceBounds() {
		return distanceBounds;
	}

	public void setDistanceBounds(String distanceBounds) {
		this.distanceBounds = distanceBounds;
	}
}