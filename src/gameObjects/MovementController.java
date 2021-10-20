package gameObjects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import game.Game;

import java.util.Random;
import gameObjects.Actor;
import globals.Direction;
import world.GameWorld;
import world.GameMap;
import world.GameRoom;
import world.GameTile;

public class MovementController {
	
	private NonPlayerActor npa;
	private Timer timer;
	private GameWorld currentWorld;
	private GameMap currentMap;
	private GameRoom currentRoom;
	private GameTile currentTile;
	
	private String recentlyVisited;
	
    // controller state
    private boolean movementRandom;
    private boolean movementCustom;
    private int speed;
    private int freq;
    private int delay;
    private int counter;
    
    List< String > commands = new ArrayList<>(Arrays.asList(
            "take", "drop", "look",
            "n", "s", "w", "e" ) );
    List< String > objects = new ArrayList<>( Arrays.asList() );
	
	public MovementController( NonPlayerActor npc ) {
		
		this.npa = npc;
		this.currentTile = npa.getCurrentGameTile();
		this.currentRoom = this.currentTile.getRoom();
		this.currentMap = currentRoom.getMap();
		this.currentWorld = currentMap.getWorld();
		
		this.recentlyVisited = null;
		this.setSpeed( 10 );
		this.setMovementRandom( npa.movementType() );
		this.setMovementCustom( false );
		this.setFreq( 5000 );					//why?
		this.setDelay( npa.movementDelay() );
		this.currentTile.setTileChar();
		
	}
	
	
	//TODO add other directions
	public void randomRoomNumber( ArrayList< String > currentExits ) {
		
		Random rand = new Random();
		int randomNumber = rand.nextInt( currentExits.size() );
		String randomDirection = currentExits.get( randomNumber );
		
		System.out.println( currentExits );
		if( currentExits.size() == 1 ) {
			randomDirection = currentExits.get(0);
			recentlyVisited = null;
		}
		if( randomDirection == "n" && recentlyVisited != "n" ) {
			
			this.recentlyVisited = "s";
			updateOutput(movePlayerTo(Direction.NORTH));
			
		} else if( randomDirection == "s" && recentlyVisited != "s" ) {
			
			this.recentlyVisited = "n";
			updateOutput(movePlayerTo(Direction.SOUTH));
			
		} else if( randomDirection == "e" && recentlyVisited != "e" ) {
			
			this.recentlyVisited = "w";
			updateOutput(movePlayerTo(Direction.EAST));
			
		} else if( randomDirection == "w" && recentlyVisited != "w" ) {
			
			this.recentlyVisited = "e";
			updateOutput(movePlayerTo(Direction.WEST));
			
		} else {
			
			randomRoomNumber( currentExits );
		}
	}
	
	// move a Person (typically the player) to a Room
    void moveActorTo( Actor p, GameTile aGameTile ) {
    	
        p.setTile( aGameTile );
        setCounter( 0 );
        
    }

    // move an Actor in direction 'dir'
    int moveTo( Actor anActor, Direction dir ) {
    	
        GameTile gt = currentTile;
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
            default:
                exit = Direction.NOEXIT; // noexit - stay in same room
                break;
        }
        if ( exit != Direction.NOEXIT ) {
        	
        	if( ( ( ( GameTile ) getCurrentRoom().getTiles().get( exit )).isDoor() ) ) { //NOTE: you can stop using this if you want the NPA to stay in room.
        		
        		moveActorTo( anActor, currentTile );
        		
        	} else {
        		
        		moveActorTo( anActor, ( GameTile ) currentRoom.getTiles().get( exit ) );
        	}
        }
        return exit;
    }

    public int movePlayerTo(Direction dir) {
        // return: Constant representing the room number moved to
        // or NOEXIT (see moveTo())
        //        
        return moveTo(this.npa, dir);
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

    	GameTile previousTile = currentTile;
    	previousTile.getNPCs().remove( npa ); 
    	previousTile.setTileChar();
        currentTile = npa.getTile(); 
        currentTile.addNPC( npa );
        currentTile.setTileChar();
        
        if( currentRoom.equals( Game.currentRoom ) ) {
        	Game.currentGame.roomChange();
        	if( Game.currentGame.getPlayer().getTile().equals(npa.getTile() ) ) {
        		Game.currentGame.getUI().printColor( npa.getName(), Color.green );
        		Game.currentGame.getUI().print( " has entered from the " );
        		Game.currentGame.getUI().printlnColor( cameFrom().toString(), Color.CYAN);
        		Cat temp = ( Cat ) npa;
        		
        		temp.sayMeow();
        	} else if( Game.currentGame.getPlayer().getTile().equals( previousTile ) ) {
        		Game.currentGame.getUI().printColor( npa.getName(), Color.green );
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
	
	public void setNPA( NonPlayerActor npa ) {
		this.npa = npa;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
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
}
