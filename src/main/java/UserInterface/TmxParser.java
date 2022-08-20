package userInterface;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import gameObjects.Thing;
import jade.Transform;
import org.joml.Vector2f;
import world.GameRoom;

public class TMXParser {

	private GameRoom gameRoom;
	private Thing aThing;

	private TMX tmx;

	private TSX[] TSXs;
	public int size;
	public int layer_count, map_cols, map_rows, img_cols, img_rows, frames;
	public ArrayList< ObjectImage > objs = new ArrayList< ObjectImage >();
	public BufferedImage image;
	public BufferedImage[] images;
	public BufferedImage tiles[][][];
	
	private ImageMap tileImageMap;

	/*
	 * TMX Parser pulls all of the needed information from a tmx file and
	 * constructs TileImage objects to use for the player's map
	 */
	public TMXParser( GameRoom gRoom ) {
		
		gameRoom = gRoom;
		tmx = new TMX( gRoom.getTMX() );
		TSXs = constructAllTSX();
		images = getImages();
		size = tmx.getTilewidth();
		
		layer_count = tmx.getLayerList().getLength();
		map_cols = ( int ) ( tmx.getMapwidth() );
		map_rows = ( int ) ( tmx.getMapheight() );
		tiles = new BufferedImage[ layer_count ][ map_rows ][ map_cols ];
		
		for( int i = 0; i < tmx.getTiles().length; i++ ) { 						// for each layer
		
			int accumulator = 0;
			
			for( int j = 0; j < tmx.getTiles()[ 0 ].length; j++ ) { 			// for each row

				for( int k = 0; k < tmx.getTiles()[ 0 ][ 0 ].length; k++ ) {	// for each column

					int tmxTileNum = tmx.getTiles()[ i ][ j ][ k ];				// number designating sub-image id
					int tileSetNum = getTileSetNum( tmxTileNum );   			// number designate what tile set to use
					if( tileSetNum >= 0 ) {
						int firstgID = Integer.parseInt(
								tmx.getTilesets().item( tileSetNum ).getAttributes().item( 0 ).getNodeValue() );
						int position = tmxTileNum - firstgID;
						if( position >= 0 ) {

							BufferedImage tileSetImage = null;

							try {

								tileSetImage = ImageIO.read( new File( tmx.getImage_paths()[ tileSetNum ] ) );

							} catch ( IOException e ) {

								e.printStackTrace();
								assert false: "ERROR: Could not parse file '" + tmx.getImage_paths()[ tileSetNum ] + "'";
							}

							TSX tsx = TSXs[ tileSetNum ];
							int xPosition = position % tsx.getColumnCount();
							int yPosition = Math.floorDiv( position, tsx.getColumnCount() );

							// building initial tile image
							if( i == 0 ) {

								Image tileImage = new Image();
								tileImage.setImageWidth( size );
								tileImage.setImageHeight( size );
								tileImage.setActiveTilesetPosition( position );
								Vector2f posVec     = new Vector2f( k*size,j*size );
								Vector2f scaleVec   = new Vector2f( size, size );
								tileImage.transform = new Transform( posVec, scaleVec );
								tileImage.setzIndex( 0 );
								tileImage.setImageResourcePath( tmx.getImage_paths()[ tileSetNum ] );
								gRoom.getTile( accumulator ).setBaseTileImage( tileImage );
							}
							
							Image tileImage = gRoom.getTile( accumulator ).getBaseTileImage();
							
							// building tile layer i with frames
							
				    		boolean isAnimated = tsx.isAnimated();

				    		int frameCount;
				    		if( isAnimated ) {
				    			
				    			frameCount = tsx.getFrames().getLength(); 
				    			
				    		} else {
				    			
				    			frameCount = 1;
				    		}

							tileImage.setImageFrameCount( frameCount );
							tileImage.setFrameDuration( new float[ frameCount ] );

				    		for( int l = 0; l < frameCount; l++ ) {

				    			if( isAnimated ) {
									tileImage.getFrameDuration()[ l ] = (float ) Integer.parseInt(
											tsx.getFrames().item( l ).getAttributes().getNamedItem( "duration" ).getNodeValue() );
				    			} else {

									tileImage.getFrameDuration()[ l ] = -1.0f;
								}
				    		}
						}
					}
					accumulator += 1;
				}
			}
		}
		objs = tmx.getObjs();
	}

	public BufferedImage[] getImages() {

		BufferedImage[] imgs = new BufferedImage[ tmx.getImage_paths().length ];
		for(int i = 0; i < imgs.length; i++) {

			try {

				imgs[ i ] = ImageIO.read( new File( tmx.getImage_paths()[ i ] ) );

			} catch ( IOException e ) {

				e.printStackTrace();
				assert false: "ERROR: Could not parse file '" + tmx.getImage_paths()[ i ] + "'";
			}
		}
		return imgs;
	}
	
	public int getTileSetNum( int tmxTileNum ) {
		
		for( int i = 0; i < tmx.getTSX_paths().length; i++ ) {
			
			int firstgID_1 = Integer.parseInt(
					tmx.getTilesets().item( i ).getAttributes().item( 0 ).getNodeValue() );
			
			if( tmxTileNum >= firstgID_1 ) {
				
				if( i + 1 < tmx.getTSX_paths().length  ) {
					
					int firstgID_2 = Integer.parseInt(
							tmx.getTilesets().item( i+1 ).getAttributes().item( 0 ).getNodeValue() );
					
					if( tmxTileNum < firstgID_2 ) {
						
						return i;
					}
				
				} else {
					
					return i;
				}
			}
		}
		return -1;
	}
	
	public TSX[] constructAllTSX() {
		
		String[] tsxPaths = tmx.getTSX_paths();
		TSX[] tsxs = new TSX[ tsxPaths.length ];
		
		for( int i = 0; i < tsxPaths.length; i++ ) {
			
			tsxs[ i ] = new TSX( tsxPaths[ i ] );
		}
		return tsxs;
	}

	public ImageMap getTileImageMap() { return tileImageMap; }

	public void setTileImageMap( ImageMap tileImageMap ) { this.tileImageMap = tileImageMap; }

	public GameRoom getGameRoom() {
		return gameRoom;
	}

	public void setGameRoom(GameRoom gameRoom) {
		this.gameRoom = gameRoom;
	}

	public Thing getAThing() {
		return aThing;
	}

	public void setAThing(Thing aThing) {
		this.aThing = aThing;
	}

	public TMX getTmx() { return tmx; }

	public void setTmx( TMX tmx ) { this.tmx = tmx; }

	public TSX[] getTSXs() { return TSXs; }

	public void setTSXs( TSX[] TSXs ) { this.TSXs = TSXs; }
}
