package main.java.UserInterface;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class TSX {
	
	private String tsxPath;
	
	private NodeList tiles;
	private NodeList images;
	
	private String imageSourcePath;
	
	private int tileCount;
	private int tileWidth;
	private int tileHeight;
	private int columnCount;
	
	private NodeList animations;
	private NodeList frames;
	
	private File file;
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private Document doc;
	private Element tileset;
	
	private BufferedImage tileSetImage;
	
	private int xPosition;
	private int yPosition;
	
	public static final int TILE_LAYER_COUNT = 5;
	public static final int THING_LAYER_COUNT = 3;
	
	public TSX( String path ) throws Exception {
		
		this.tsxPath = path;
		
		file = new File( path );
//		tsxPath += file.getParent() + "/";
		dbFactory = DocumentBuilderFactory.newInstance();
		dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse( file );
		doc.getDocumentElement().normalize();
		tileset = doc.getDocumentElement();
		tiles = tileset.getElementsByTagName( "tile" );
		images = tileset.getElementsByTagName( "image" );
		animations = tileset.getElementsByTagName( "animation" );
		frames = tileset.getElementsByTagName( "frame" );
		String parent = file.getParent() + "/";
		imageSourcePath = parent + images.item( 0 ).getAttributes().getNamedItem( "source" ).getNodeValue().toString();
		
		tileCount   = Integer.valueOf( tileset.getAttribute( "tilecount"   ) );
		tileWidth   = Integer.valueOf( tileset.getAttribute( "tilewidth"   ) );
		tileHeight  = Integer.valueOf( tileset.getAttribute( "tileheight"  ) );
		columnCount = Integer.valueOf( tileset.getAttribute( "columns" ) );
		
		tileSetImage = ImageIO.read( new File( imageSourcePath ) );
		
		xPosition = 0;
		yPosition = 0;
	}
	
	public Image[] buildThingImages( int gameTileNumber ) {
		
		Image[] thingImages = new Image[ tiles.getLength() ];						// number of different image sections in image

		for( int i = 0; i < thingImages.length; i++ ) {								// for each image section
			
			Image thingImage = new Image();
			ImageLayer thingImageLayer = new ImageLayer();
			ImageLayer[] thingImageLayers = new ImageLayer[ 1 ];
			thingImage.setImageLayers( thingImageLayers );
			
			int frameCount;
    		if( frames.getLength() > 0 ) {
    			
    			thingImageLayer.setAnimated( true );
    			frameCount = frames.getLength();
    			
    		} else {
    			
    			thingImageLayer.setAnimated( false );
    			frameCount = 1;
    		}
    		
			ImageFrame[] thingImageFrames = new ImageFrame[ frameCount ];
			
			for( int j = 0; j < thingImageFrames.length; j++ ) {
				
				thingImageFrames[ j ] = new ImageFrame();
				
				thingImageFrames[ j ].setFrameImage( tileSetImage.getSubimage( ( xPosition + j ) * tileWidth, ( yPosition + i ) * tileHeight, tileWidth, tileHeight ) );
				
				if( thingImageLayer.isAnimated() ) {
					
					thingImageFrames[ j ].setFrameDuration( Integer.valueOf( getFrames().item( j ).getAttributes().getNamedItem( "duration" ).getNodeValue() ) );
				
				} else {
					
					thingImageFrames[ j ].setFrameDuration( -1 );
				}
			}
			thingImageLayer.setImageFrames( thingImageFrames );
			thingImage.setImageLayer( 0, thingImageLayer );
			thingImage.setTileImageNumber( gameTileNumber );
			thingImages[ i ] = thingImage;
		}
		return thingImages;
	}
	
	public Image buildThingImage( int x, int y, int xSize, int ySize ) {
		
		Image thingImage = new Image();						// number of different image sections in image
		thingImage.setImageWidth( xSize );
		thingImage.setImageHeight( ySize );
		ImageLayer thingImageLayer = new ImageLayer();
		ImageLayer[] thingImageLayers = new ImageLayer[ 1 ];

		thingImage.setImageLayers( thingImageLayers );
		thingImageLayer.setName( "north" );					//change. set to north for testing

		int frameCount;
    	if( frames.getLength() > 0 ) {
    			
			thingImageLayer.setAnimated( true );
			frameCount = frames.getLength();
			
		} else {

			thingImageLayer.setAnimated( false );
			frameCount = 1;
		}
		
		ImageFrame[] thingImageFrames = new ImageFrame[ frameCount ];
		
		if( thingImageFrames.length > 1 ) {
			thingImageLayer.setAnimated( true );
		}
		for( int j = 0; j < thingImageFrames.length; j++ ) {
			
			thingImageFrames[ j ] = new ImageFrame();
			thingImageFrames[ j ].setFrameImage( tileSetImage.getSubimage( ( x + j ) * xSize, y * ySize, xSize, ySize ) );
			thingImageFrames[ j ].setFramePixels( getPixels( thingImageFrames[ j ].getFrameImage(), xSize, ySize ) );
			if( thingImageLayer.isAnimated() ) {
				
				thingImageFrames[ j ].setFrameDuration( Integer.valueOf( getFrames().item( j ).getAttributes().getNamedItem( "duration" ).getNodeValue() ) );
			
			} else {
				
				thingImageFrames[ j ].setFrameDuration( -1 );
			}
		}
		thingImageLayer.setImageFrames( thingImageFrames );
		thingImage.setImageLayer( 0, thingImageLayer );
		thingImage.setTileImageNumber( -1 );
		
		return thingImage;
	}
	
	public ImagePixel[][] getPixels( BufferedImage img, int xSize, int ySize ) {
		
		ImagePixel[][] pixels = new ImagePixel[ ySize ][ xSize ];
		for( int i = 0; i < xSize; i++ ) {
			for( int j = 0; j < ySize; j++ ) {
				
				ImagePixel pixel = new ImagePixel();
				pixel.setX( i );
				pixel.setY( j );
				int rbg = img.getRGB( i, j );
				pixel.setRgb( rbg );
				if( j == 0 || j == ySize - 1 || i == 0 || i == xSize - 1) {
					pixel.setRoughness( 1 );
				} else {
					pixel.setRoughness(4);
				}
				pixels[ j ][ i ] = pixel;	
			}
		}
		return pixels;
	}
	
	public boolean isAnimated() {
		
		if( frames.getLength() > 1 ) {
			
			return true;
		}
		return false;
	}
	
	public String getTsxPath() {
		return tsxPath;
	}

	public void setTsxPath(String tsxPath) {
		this.tsxPath = tsxPath;
	}

	public NodeList getTiles() {
		return tiles;
	}

	public void setTiles(NodeList tiles) {
		this.tiles = tiles;
	}

	public NodeList getImages() {
		return images;
	}

	public void setImages(NodeList images) {
		this.images = images;
	}

	public String getImageSourcePath() {
		return imageSourcePath;
	}

	public void setImageSourcePath(String imageSourcePath) {
		this.imageSourcePath = imageSourcePath;
	}

	public int getTileCount() {
		return tileCount;
	}

	public void setTileCount(int tileCount) {
		this.tileCount = tileCount;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	public NodeList getAnimations() {
		return animations;
	}

	public void setAnimations(NodeList animations) {
		this.animations = animations;
	}

	public NodeList getFrames() {
		return frames;
	}

	public void setFrames(NodeList frames) {
		this.frames = frames;
	}

	public String getTSXPath() {
		
		return tsxPath;
	}

	public void setTSXPath(String tsxPath) {
		
		this.tsxPath = tsxPath;
	}
}
