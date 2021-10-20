package world;

import gameObjects.MovementController;
import gameObjects.NonPlayerActor;
import gameObjects.ThingHolder;
import gameObjects.ThingList;


public class GameRoom extends ThingHolder {
	
	private transient GameMap map;
	private int roomLength;
	private int roomWidth;
	private boolean inside;
	private boolean outside;
	
	public GameRoom() {
		super( "", "", new ThingList() );
		this.type = "Room";
	}

	public GameRoom( String aRoomName, String aRoomDescription, GameMap aGameMap, 
			ThingList tiles, int aRoomLength, int aRoomWidth ) {
		
		super( aRoomName, aRoomDescription, tiles );
		this.type = "Room";
		this.map = aGameMap;
		this.roomLength = aRoomLength;
		this.roomWidth = aRoomWidth;
	}
	
	public void initializeNPCMovement() {

		for( int i = 0; i < this.things.size(); i++) {
			GameTile tile = ( GameTile ) this.things.get( i );
			for( int j = 0; j < tile.getNPCs().size(); j++ ) {
				if( tile.getNPCs().get( j ).isNPC() ) {
					NonPlayerActor npc = ( NonPlayerActor ) tile.getNPCs().get( j );
					npc.setMC( new MovementController( npc ) );
					System.out.println( npc );
				}
			}
		}
	}

	public int getRoomLength() {
		
		return roomLength;
	}

	public void setRoomLength( int roomLength ) {
		
		this.roomLength = roomLength;
	}

	public int getRoomWidth() {
		
		return roomWidth;
	}

	public void setRoomWidth( int roomWidth ) {
		
		this.roomWidth = roomWidth;
	}
	
	public int getRoomSize() {
		
		return roomWidth * roomLength;
	}

	public GameMap getMap() {
		
		return map;
	}

	public void setMap( GameMap map ) {
		
		this.map = map;
	}
	
	public ThingList getTiles() {
		
		return things;
	}
	
	public void setTiles( ThingList tiles ) {
		
		this.things = tiles;
	}
	
	public void addTile( GameTile aTile ) {
		
		this.things.add( aTile );
	}
	
	public void removeTile( int tileLocation ) {
		
		this.things.remove( tileLocation );
	}

	public boolean isInside() {
		return inside;
	}

	public void setInside(boolean inside) {
		this.inside = inside;
	}

	public boolean isOutside() {
		return outside;
	}

	public void setOutside(boolean outside) {
		this.outside = outside;
	}
}
