package world;

import java.awt.Color;
import java.util.ArrayList;

import jade.Camera;
import jade.RoomScene;
import org.joml.Vector2f;
import userInterface.ImageMap;
import actor.NonPlayerActor;
import game.Game;
import gameObjects.Thing;
import gameObjects.ThingHolder;
import gameObjects.ThingList;
import inputProcessor.MovementControllerList;
import light.AmbientLight;
import light.Light;


public class GameRoom extends ThingHolder {
	
	private transient GameMap map;
	private int roomLength;
	private int roomWidth;
	private boolean inside;
	private boolean outside;
	
	private ThingList lightSourceObjects;
	private RoomScene scene;
	private Camera camera;

	private String roomTileSetPath;
	public static final int ROOM_TILE_WIDTH = 32;
	public static final int ROOM_TILE_HEIGHT = 32;
	
	public GameRoom() {
		super( "", "", new ThingList() );
		this.setTMX( "assets/images/testRoom.tmx" );
		this.isGameRoom = true;
		this.setLightSources( new ArrayList< Light >() );
		AmbientLight ambLight = new AmbientLight();
		ambLight.setBrightness( ( float ) 0.05);
		ambLight.setRGB( new Color( 255, 242, 114 ) );
		this.getLightSources().add( ambLight );
		this.scene = new RoomScene( this );
		this.camera = new Camera( new Vector2f( 0, 0) );
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
		this.camera = new Camera( new Vector2f( -250, 0 ) );
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

	public void setOutside( boolean outside ) {
		
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
		
		if( theta == Math.PI || theta == -Math.PI  ) {
			
			return "s";
			
		} else if( theta < Math.PI && theta > Math.PI / 2 ) {
			
			return "se";
			
		} else if( theta == Math.PI / 2  ) {
			
			return "e";
			
		} else if( theta < Math.PI / 2 && theta > 0 ) {
			
			return "ne";
			
		} else if( theta == 0  ) {
			
			return "n";
			
		} else if( theta < 0 && theta > -Math.PI / 2  ) {
			
			return "nw";
			
		} else if( theta == -Math.PI / 2 ) {
			
			return "w";
			
		} else if( theta < -Math.PI / 2 && theta > -Math.PI ) {
			
			return "sw";
			
		} else {
			
			return "unknown";
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
		
		if( this == Game.currentRoom ) {
			
			if( Game.currentGame.getPlayer().isLightSource() ) {
			
				allLightSourceObjects.add( Game.currentGame.getPlayer() );
			}
		}
		
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
	
	public double calculateTotalHeight( Thing aThing ) {
		
		Thing t = aThing;
		double totalHeight = 0.0;
		totalHeight += t.getHeight();
		
		while( !t.getHeldBy().isGameTile() ) {
			
			ThingHolder tHolder = ( ThingHolder ) t.getHeldBy();
			totalHeight += tHolder.getHeight();
			t = tHolder;
		}
		
		return totalHeight;
	}

	public RoomScene getScene() { return scene; }

	public void setScene( RoomScene scene ) { this.scene = scene; }

	public Camera getCamera() { return camera; }

	public void setCamera( Camera camera ) { this.camera = camera; }

	public String getRoomTileSetPath() { return roomTileSetPath; }

	public void setRoomTileSetPath( String roomTileSetPath ) {

		this.roomTileSetPath = roomTileSetPath;
	}
}
