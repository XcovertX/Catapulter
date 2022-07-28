package main.java.world;

import main.java.UserInterface.ImageMap;
import main.java.actor.NonPlayerActor;
import main.java.game.Game;
import main.java.gameObjects.AmbientLight;
import main.java.gameObjects.Light;
import main.java.gameObjects.ThingHolder;
import main.java.gameObjects.ThingList;
import main.java.inputProcessor.MovementController;
import main.java.inputProcessor.MovementControllerList;


public class GameRoom extends ThingHolder {
	
	private transient GameMap map;
	private int roomLength;
	private int roomWidth;
	private boolean inside;
	private boolean outside;
	
	public GameRoom() {
		super( "", "", new ThingList() );
		this.setTMX("files/testCity.tmx");
		this.isGameRoom = true;
		this.setLightSources( new Light[ 1 ] );
		this.getLightSources()[ 0 ] = new AmbientLight();
		this.type = "Room";
	}

	public GameRoom( String aRoomName, String aRoomDescription, GameMap aGameMap, 
			ThingList tiles, int aRoomLength, int aRoomWidth ) {
		
		super( aRoomName, aRoomDescription, tiles );
		this.type = "Room";
		this.map = aGameMap;
		this.roomLength = aRoomLength;
		this.roomWidth = aRoomWidth;
		this.isGameRoom = true;
	}
	
	public void initializeNPCMovement() {
		
		MovementControllerList mcl = new MovementControllerList();

		for( int i = 0; i < this.things.size(); i++) {
			GameTile tile = ( GameTile ) this.things.get( i );
			for( int j = 0; j < tile.getNPCs().size(); j++ ) {
				if( tile.getNPCs().get( j ).isNPC() ) {
					NonPlayerActor npc = ( NonPlayerActor ) tile.getNPCs().get( j );
					npc.setMC( mcl.getController( npc ) );
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
	
	public void setTileImages( ImageMap tileImageMap ) {
		
		ThingList tiles = getTiles();
		for( int i = 0; i < tiles.size(); i++ ) {
			GameTile tile = ( GameTile ) tiles.get( i );
			
			tile.setBaseTileImage( tileImageMap.getTileImageArray()[ i ] );
			tileImageMap.getTileImageArray()[ i ].setTileImageNumber( tile.getTileNumber() );
		}
	}
	
	public AmbientLight getAmbientLight() {
		
		return ( AmbientLight ) this.getLightSources()[ 0 ];
	}
	
	public int calculateRow( int tileNumber ) {
		
		return (int) Math.floor( tileNumber / Game.currentRoom.getRoomWidth() );
	}
	
	public int calculateColumn( int tileNumber ) {
		
		return tileNumber % getRoomWidth();
	}
	
	public double calculateDistance( int tileNumberA, int tileNumberB ) {

		int rowOfA = calculateRow( tileNumberA );
		int colOfA = calculateColumn( tileNumberA );
		int rowOfB = calculateRow( tileNumberB );
		int colOfB = calculateColumn( tileNumberB );
		
		int base = colOfA - colOfB;
		int height = rowOfA -rowOfB;
		int baseSq = base * base;
		int heightSq = height * height;
		
		int distSq = baseSq + heightSq;
		
		double distance = Math.sqrt( distSq );
		
		return distance;
	}
}
