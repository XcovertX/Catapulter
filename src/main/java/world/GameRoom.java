package main.java.world;

import java.util.ArrayList;
import java.util.Vector;

import main.java.UserInterface.ImageMap;
import main.java.actor.NonPlayerActor;
import main.java.game.Game;
import main.java.gameObjects.AmbientLight;
import main.java.gameObjects.Light;
import main.java.gameObjects.Thing;
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
	
	private ThingList lightSourceObjects;
	
	public GameRoom() {
		super( "", "", new ThingList() );
		this.setTMX("files/testCity.tmx");
		this.isGameRoom = true;
		this.setLightSources( new ArrayList< Light >() );
		AmbientLight ambLight = new AmbientLight();
		ambLight.setBrightness( ( float ) 0.05 );
		this.getLightSources().add( ambLight );
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
		
		return ( AmbientLight ) this.getLightSources().get( 0 );
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
		
		return Math.abs( distance );
	}
	
	public String calculateRelativeDirection( int tileNumberA, int tileNumberB ) {
		
		// vector 1
		int yA = calculateRow( tileNumberA );
		int xA = calculateColumn( tileNumberA );
		int yB = yA + 1;
		int xB = xA;
		
		// new point to make vector 2
		int yC = calculateRow( tileNumberB );
		int xC = calculateColumn( tileNumberB );
		
		int uX = xB - xA;
		int uY = yB - yA;
		int vX = yA - yB;
		int vY = xB - xA;
		int wX = xC - xA;
		int wY = yC - yA;

		double theta = Math.atan2( wX * vX + wY * vY, wX * uX + wY * uY );
		
		if( theta > ( 5 * Math.PI ) / 6 || theta < -( 5 * Math.PI ) / 6  ) {
			
			return "s";
			
		} else if( theta >= ( 2 * Math.PI ) / 3  ) {
			
			return "sw";
			
		} else if( theta >= Math.PI / 3  ) {
			
			return "w";
			
		} else if( theta >= Math.PI / 6  ) {
			
			return "nw";
			
		} else if( theta >= -Math.PI / 6  ) {
			
			return "n";
			
		} else if( theta >= -Math.PI / 3  ) {
			
			return "ne";
			
		} else if( theta >= -( 2 * Math.PI ) / 3  ) {
			
			return "e";
			
		} else if( theta >= -( 5 * Math.PI ) / 6  ) {
			
			return "se";
			
		} else {
			
			return "s";
		}
	}
	
	public GameTile getTile( int tileNumber ) {
		
		return ( GameTile ) this.getTiles().get( tileNumber );
	}

	public ThingList getAllRoomLightSourceObjects() {
		return lightSourceObjects;
	}

	public void setAllRoomLightSourceObjects() {
		
		ThingList allLightSourceObjects = new ThingList();
		
		for( int i = 0; i < this.getTiles().size(); i++ ) {
			
			GameTile tile = ( GameTile ) this.getTiles().get( i );
			
			if( tile.getThings().size() > 0 ) {
				
				ThingList tList = tile.getThings();
				
				for( int j = 0; j < tList.size(); j++ ) {
					
					Thing thing = tList.get( j );
					if( thing.isLightSource() ) {
						thing.setCurrentGameTile(tile);
						allLightSourceObjects.add( thing );
					}
				}
			}
			
			if( tile.getNPCs().size() > 0 ) {
				ThingList tList = tile.getNPCs();
				for( int j = 0; j < tList.size(); j++ ) {
					Thing thing = tList.get( j );
					if( thing.isLightSource() ) {
						allLightSourceObjects.add( thing );
					}
				}
			}
		}
		
		this.lightSourceObjects = allLightSourceObjects;
	}
}
