 package world;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import game.Game;
import gameObjects.Actor;
import gameObjects.NonPlayerActor;
import gameObjects.Thing;
import gameObjects.ThingHolder;
import gameObjects.ThingList;
import globals.Direction;

public class GameTile extends ThingHolder {
	
	private transient GameRoom room;
	private ThingList npcs;
	
	private int n, s, w, e, nw, ne, sw, se, u, d, special;
    private int tileNumber;
    private boolean notTile;
    private List< String > currentTileCharPriorityQueue = new ArrayList<>();
    private String defaultTileChar;
    
    // for moving to new maps
    private boolean isDoor = false;
//    private boolean exitMap = false;
//    private boolean exitRoom = false;
//    private String externalMapLocation = "";
    private String externalMapName = "";
    private String externalRoomName = "";
    private int externalTile = 0;

    public GameTile() {
    	super( "", "", new ThingList() );
        this.n = Direction.NOEXIT;
        this.s = Direction.NOEXIT;
        this.w = Direction.NOEXIT;
        this.e = Direction.NOEXIT;
        this.nw = Direction.NOEXIT;
        this.ne = Direction.NOEXIT;
        this.sw = Direction.NOEXIT;
        this.se = Direction.NOEXIT;
        this.u = Direction.NOEXIT;
        this.d = Direction.NOEXIT;
        this.special = Direction.NOEXIT;
        this.npcs = new ThingList();
    	this.defaultTileChar = "   ";
    	this.tileChar = defaultTileChar;
    	this.type = "Tile";
    }
    public GameTile( String aName, String aDescription, GameRoom aRoom, ThingList thingList, 
    		ThingList npcList, int aTileNumber, boolean notTileCheck, String defaultTileChar ) {
    	
        super( aName, aDescription, thingList ); 
        this.type = "Tile";
        this.room = aRoom;
        this.npcs = npcList;
        
        this.n = Direction.NOEXIT;
        this.s = Direction.NOEXIT;
        this.w = Direction.NOEXIT;
        this.e = Direction.NOEXIT;
        this.nw = Direction.NOEXIT;
        this.ne = Direction.NOEXIT;
        this.sw = Direction.NOEXIT;
        this.se = Direction.NOEXIT;
        this.u = Direction.NOEXIT;
        this.d = Direction.NOEXIT;
        this.special = Direction.NOEXIT;
        
        this.tileNumber = aTileNumber;
        
        this.defaultTileChar = defaultTileChar;
        this.tileChar = this.defaultTileChar;
    }

    // --- accessor methods ---
    // n
    public int getN() {
    	
        return n;
    }

    public void setN( int aN ) {
    	
        this.n = aN; 
    }

    // s
    public int getS() {
    	
        return s;
    }

    public void setS( int aS ) {
    	
        this.s = aS;
    }

    // e
    public int getE() {
    	
        return e;
    }

    public void setE( int aE ) {
    	
        this.e = aE;
    }

    // w
    public int getW() {
    	
        return w;
    }

    public void setW( int aW ) {
    	
        this.w = aW;
    }
    
    // nw
    public int getNW() {
    	
        return nw;
    }

    public void setNW( int aNW ) {
    	
        this.nw = aNW; 
    }

    // ne
    public int getNE() {
    	
        return ne;
    }

    public void setNE( int aNE ) {
    	
        this.ne = aNE;
    }

    // sw
    public int getSW() {
    	
        return sw;
    }

    public void setSW( int aSW ) {
    	
        this.sw = aSW;
    }

    // se
    public int getSE() {
    	
        return se;
    }

    public void setSE( int aSE ) {
    	
        this.se = aSE;
    }
    
    // u
    public int getU() {
    	
        return u;
    }

    public void setU( int aU ) {
    	
        this.u = aU;
    }
    
    // d
    public int getD() {
    	
        return d;
    }

    public void setD( int aD ) {
    	
        this.d = aD;
    }
    
    // special room
    public int getSpecial() {
    	
        return special;
    }

    public void setSpecial( int aSpecial ) {
    	
        this.special = aSpecial;
    }
    
    public boolean getNotTile() {
    	
    	return notTile;
    }
    
    public void setNotTile( boolean notTileStatus ) {
    	
    	this.notTile = notTileStatus;
    }
    
    public ArrayList< String > getTileExits() {
    	
    	ArrayList< String > tileExits = new ArrayList< String >();
    	
    	if ( this.n != -1 ) {
    		
    		tileExits.add( "n" );
    	}
    	
    	if ( this.s != -1 ) {
    		
    		tileExits.add( "s" );
    	}
    	
    	if ( this.e != -1 ) {
    		
    		tileExits.add( "e" );
    	}
    	
    	if ( this.w != -1 ) {
    		
    		tileExits.add( "w" );
    	}
    	
    	if ( this.ne != -1 ) {
    		
    		tileExits.add( "ne" );
    	}
    	
    	if ( this.nw != -1 ) {
    		
    		tileExits.add( "nw" );
    	}
    	
    	if ( this.se != -1 ) {
    		
    		tileExits.add( "se" );
    	}
    	
    	if ( this.sw != -1 ) {
    		
    		tileExits.add( "sw" );
    	}

    	if ( this.u != -1 ) {
    		
    		tileExits.add( "u" );
    	}
    	
    	if ( this.d != -1 ) {
    		
    		tileExits.add( "d" );
    	}
    	
    	return tileExits;

    }
    
    public void setTileCharToDefaultTileChar() {
    	this.tileChar = defaultTileChar;
    }
    
    public void setTileChar() {
    	
    	if( Game.currentGame.getPlayer().getTile().equals( this ) ) {
    		this.tileChar = Game.currentGame.getPlayer().getActorSymbol();
    	} else if( npcs.isEmpty() == false ) {
    		Actor mostRecentActor = ( Actor ) this.npcs.getLast();
    		this.tileChar = mostRecentActor.getActorSymbol();
    	} else if( npcs.isEmpty() == true ) {
    		this.tileChar = defaultTileChar;
    	}
    }
    
    public String getTileChar() {
    	return this.tileChar;
    }
    
    public int getTileNumber() {
    	
    	return tileNumber;
    }

//	public String getExternalMapLocation() {
//		
//		return externalMapLocation;
//	}
//
//	public void setExternalMap( String externalMapLocation ) {
//		
//		this.externalMapLocation = externalMapLocation;
//	}

	public String getExternalMapName() {
		
		return externalMapName;
	}

	public void setExternalMapName( String externalMapName ) {
		
		this.externalMapName = externalMapName;
	}

	public int getExternalTile() {
		
		return externalTile;
	}
	
	public void setExternalTile( int externalTile ) {
		
		this.externalTile = externalTile;
	}

	public boolean isDoor() {
		
		return isDoor;
	}

	public void setIsDoor( boolean isDoor ) {
		
		this.isDoor = isDoor;
	}
	
	public ThingList getNPCs() {
		
		return 	npcs;
	}
	
	public void setNPCs( ThingList npcs ) {
		
		this.npcs = npcs;
	}
	
	public void addNPC( Thing npc ) {
		
		this.npcs.add( npc );
	}
	
	public void removeNPC( int npcLocation ) {
		
		this.npcs.remove( npcLocation );
	}
	
	public Actor getNPC( int index ) {
			
		return (Actor) npcs.get(index);
			
	}

	public GameRoom getRoom() {
		
		return room;
	}

	public void setRoom(GameRoom room) {
		
		this.room = room;
	}
	public List< String > getCharPriorityQueue() {
		return currentTileCharPriorityQueue;
	}
	public void setcTileChar( List< String > charPriorityQueue ) {
		this.currentTileCharPriorityQueue = charPriorityQueue;
	}
	
	public void setDefaultTileChar( String tileChar ) {
		this.defaultTileChar = tileChar;
	}
	
	public String getDefaultTileChar() {
		return this.defaultTileChar;
	}
	
	public void setTileNumber( int tileNumber ) {
		this.tileNumber = tileNumber;
	}
//	public boolean isExitMap() {
//		return exitMap;
//	}
//	public void setExitMap(boolean exitMap) {
//		this.exitMap = exitMap;
//	}
//	public boolean isExitRoom() {
//		return exitRoom;
//	}
//	public void setExitRoom(boolean exitRoom) {
//		this.exitRoom = exitRoom;
//	}
	public String getExternalRoomName() {
		return externalRoomName;
	}
	public void setExternalRoomName(String externalRoomName) {
		this.externalRoomName = externalRoomName;
	}
}
