package main.java.UserInterface;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.java.world.GameRoom;

class TMXParser {

	private GameRoom gameRoom;
	private TMX tmx;
	private TSX[] TSXs;
	public int size;
	public int layer_count, map_cols, map_rows, img_cols, img_rows, frames;
	public ArrayList<Rect> objs = new ArrayList<Rect>();
	public BufferedImage image;
	public BufferedImage[] images;
	public BufferedImage tiles[][][];
	
	private TileImageMap tileImageMap;

	/*
	 * TMX Parser pulls all of the needed information from a tmx file and
	 * constructs TileImage objects to use for the player's map
	 */
	public TMXParser( GameRoom gRoom ) throws Exception {
		
		gameRoom = gRoom;
		tmx = new TMX( gRoom.getTMX() );
		TSXs = constructAllTSX();
		images = getImages();
		size = tmx.getTilewidth();
		
		layer_count = tmx.getLayerList().getLength();
		map_cols = ( int ) ( tmx.getMapwidth() );
		map_rows = ( int ) ( tmx.getMapheight() );
		tiles = new BufferedImage[ layer_count ][ map_rows ][ map_cols ];
		
		tileImageMap = new TileImageMap();
		tileImageMap.buildLayout( map_rows, map_cols );
		
		for( int i = 0; i < tmx.getTiles().length; i++ ) { 						// for each layer
			
			int accumulator = 0;
			
			for( int j = 0; j < tmx.getTiles()[ 0 ].length; j++ ) { 			// for each row 

				for( int k = 0; k < tmx.getTiles()[ 0 ][ 0 ].length; k++ ) {	// for each column

					int tmxTileNum = tmx.getTiles()[ i ][ j ][ k ];				// number designating sub-image id		
					int tileSetNum = getTileSetNum( tmxTileNum );   			// number designate what tile set to use
					if( tileSetNum >= 0 ) {
						int firstgID = Integer.valueOf( tmx.getTilesets().item( tileSetNum ).getAttributes().item( 0 ).getNodeValue() );
						int position = tmxTileNum - firstgID;
						if( position >= 0 ) {
							
							BufferedImage tileSetImage = ImageIO.read( new File( tmx.getImage_paths()[ tileSetNum ] ) );
							
							TSX tsx = TSXs[ tileSetNum ];
							
							// building initial tile image
							if( i == 0 ) {
								TileImage tileImage = new TileImage( layer_count, accumulator );
								tileImageMap.setTileImage( j, k, tileImage );
							}
							TileImage tileImage = tileImageMap.getTileImage( j, k );
							
							// building tile layer i with frames
							
				    		boolean isAnimated = tsx.isAnimated();
				    		int activeFrame = 0;
				    		int frameCount;
				    		if( isAnimated ) {
				    			
				    			frameCount = tsx.getFrames().getLength(); 
				    			
				    		} else {
				    			
				    			frameCount = 1;
				    		}
				    		
				    		TileImageFrame[] frames = new TileImageFrame[ frameCount ];
				    		
				    		for( int l = 0; l < frames.length; l++ ) {
				    			
				    			frames[ l ] = new TileImageFrame();
				    			frames[ l ].setFrameImage( tileSetImage.getSubimage( ( position + l ) * size, 0, size, size ) );
				    		}

							TileImageLayer imageLayer = new TileImageLayer( isAnimated, frames, activeFrame );
							tileImage.setImageLayer( i, imageLayer );
						}
					}
				}
				accumulator += 1;
			}
		}
		
		tileImageMap.flipMapVertically();
		tileImageMap.transformToArray();
		gRoom.setTileImages( tileImageMap );

		objs = tmx.getObjs();
	}
	
	public BufferedImage[] getImages() throws IOException {
		BufferedImage[] imgs = new BufferedImage[ tmx.getImage_paths().length ];
		for(int i = 0; i < imgs.length; i++) {
			imgs[i] = ImageIO.read(new File(tmx.getImage_paths()[i]));
		}
		return imgs;
	}
	
	public int getTileSetNum( int tmxTileNum ) throws IOException {
		
		for( int i = 0; i < tmx.getTSX_paths().length; i++ ) {
			
			int firstgID_1 = Integer.valueOf(tmx.getTilesets().item(i).getAttributes().item(0).getNodeValue());
			
			if( tmxTileNum >= firstgID_1 ) {
				
				if( i + 1 < tmx.getTSX_paths().length  ) {
					
					int firstgID_2 = Integer.valueOf(tmx.getTilesets().item(i+1).getAttributes().item(0).getNodeValue());
					
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
	
	public TSX[] constructAllTSX() throws Exception {
		
		String[] tsxPaths = tmx.getTSX_paths();
		TSX[] tsxs = new TSX[ tsxPaths.length ];
		
		for( int i = 0; i < tsxPaths.length; i++ ) {
			
			tsxs[ i ] = new TSX( tsxPaths[ i ] );
		}
		return tsxs;
	}

	public TileImageMap getTileImageMap() {
		
		return tileImageMap;
	}

	public void setTileImageMap( TileImageMap tileImageMap ) {
		
		this.tileImageMap = tileImageMap;
	}

	public GameRoom getGameRoom() {
		return gameRoom;
	}

	public void setGameRoom(GameRoom gameRoom) {
		this.gameRoom = gameRoom;
	}
	
//	public TileImageFrame[] constructAllFrames() {
//		
//	}
	
//	public BufferedImage[] getTSXFrames() {
//		
//	}

}
