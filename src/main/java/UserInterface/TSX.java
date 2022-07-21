package main.java.UserInterface;

import java.io.File;
import java.io.IOException;

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

	public TSX( String path ) throws Exception {
		
		this.tsxPath = path;
		
		File file = new File( path );
//		tsxPath += file.getParent() + "/";
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse( file );
		doc.getDocumentElement().normalize();
		Element tileset = doc.getDocumentElement();
		
		tiles = tileset.getElementsByTagName( "tile" );
		images = tileset.getElementsByTagName( "image" );
		animations = tileset.getElementsByTagName( "animation" );
		frames = tileset.getElementsByTagName( "frame" );
		imageSourcePath = images.item( 0 ).getAttributes().getNamedItem( "source" ).getNodeValue().toString();
		tileCount   = Integer.valueOf( tileset.getAttribute( "tilecount"   ) );
		tileWidth   = Integer.valueOf( tileset.getAttribute( "tilewidth"   ) );
		tileHeight  = Integer.valueOf( tileset.getAttribute( "tileheight"  ) );
		columnCount = Integer.valueOf( tileset.getAttribute( "columns" ) );
			
	}

	public String getTSXPath() {
		
		return tsxPath;
	}

	public void setTSXPath(String tsxPath) {
		
		this.tsxPath = tsxPath;
	}
	
	public boolean isAnimated() {
		
		if( frames.getLength() > 1 ) {
			
			return true;
		}
		return false;
	}

}
