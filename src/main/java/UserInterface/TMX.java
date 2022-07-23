package main.java.UserInterface;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.*;

import org.w3c.dom.*;

public class TMX {

	private String tmx_path;
	private String tsx_path = "";
	private String[] tsx_paths;

	private int tilewidth;
	private int tileheight;
	private int mapwidth;
	private int mapheight;
	private int size;
	
	private NodeList layerList;
	private NodeList tileSets;
	private int layerCount;
	
	private int tiles[][][];
	private ArrayList< ObjectImage > objs = new ArrayList< ObjectImage >();
	private String[] tokens;
	private String image_Path;
	private String[] image_paths;

	public TMX( String path ) throws Exception {
		
		tmx_path = path;
		File file = new File( path );
		tsx_path += file.getParent() + "/";
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse( file );
		doc.getDocumentElement().normalize();
		Element map = doc.getDocumentElement();
		setMapwidth(Integer.parseInt(  map.getAttribute( "width" ) ) );
		setMapheight(Integer.parseInt( map.getAttribute( "height" ) ) );
		setTilewidth(Integer.parseInt( map.getAttribute( "tilewidth" ) ) );
		tileheight = Integer.parseInt( map.getAttribute( "tileheight" ) );
		
		size = getMapwidth() * getMapheight();
		
		setLayerList( doc.getElementsByTagName( "layer" ) );
		layerCount = getLayerList().getLength();
		
		setTilesets( map.getElementsByTagName( "tileset" ) );
		setTSX_paths( new String[ getTilesets().getLength() ] );

		for( int i = 0; i < getTilesets().getLength(); i++ ) {
			Node source = getTilesets().item(i).getAttributes().item( 1 );
			getTSX_paths()[ i ] = file.getParent() + "/" + source.getNodeValue().toString();
		}
		
		setTiles( new int[ getLayerList().getLength() ][ getMapheight() ][ getMapwidth() ] );
		for( int i = 0; i < getLayerList().getLength(); i++ ) {
			Node layers = getLayerList().item( i );
			Element layer = ( Element ) layers;
			String list = layer.getElementsByTagName( "data" ).item( 0 ).getTextContent();
			tokens = list.split( "," );
			for( int j = 0; j < getMapheight(); j++ ) {
				for( int k = 0; k < getMapwidth(); k++ ) {
					getTiles()[ i ][ j ][ k ] = Integer.parseInt( tokens[ k + j * getMapwidth() ].trim() );
				}
			}
		}

		NodeList objectList = doc.getElementsByTagName( "objectgroup" );
		for( int i = 0; i < objectList.getLength(); i++ ) {
			Node Node_objectgroup = objectList.item( i );
			Element objectgroup = ( Element ) Node_objectgroup;
			String name = objectgroup.getAttribute( "name" );
			NodeList objects = objectgroup.getElementsByTagName( "object" );
			for( int j = 0; j < objects.getLength(); j++ ) {
				Element obj = ( Element ) objects.item( j );
				int x = Integer.parseInt( obj.getAttribute( "x" ) );
				int y = Integer.parseInt( obj.getAttribute( "y" ) );
				int w = 0, h = 0;
				if ( obj.getAttribute( "width" ) != "" ) {
					w = Integer.parseInt( obj.getAttribute( "width" ) );
				}
				if( obj.getAttribute( "height" ) != "" ) {
					h = Integer.parseInt( obj.getAttribute( "height" ) );
				}
				String objType = obj.getAttribute( "type" );
				String objName = obj.getAttribute( "name" );
				NodeList propertiesNodelist = obj.getElementsByTagName( "properties" );
				ArrayList< Property > properties = new ArrayList< Property >();
				
				if( propertiesNodelist.getLength() > 0 ) {
					for( int k = 0; k < propertiesNodelist.getLength(); k++ ) {
						Node propertyNode = propertiesNodelist.item(k);
						Element propertyElement = (Element) propertyNode;
						NodeList propertyNodeList = propertyElement.getElementsByTagName( "property" );
						for (int l = 0; l < propertyNodeList.getLength(); l++) {
							String propName = "", propType = "", propValue = "";
							Element property = (Element) propertyNodeList.item(l);
							propName  = property.getAttribute( "name" );
							propType  = property.getAttribute( "type" );
							propValue = property.getAttribute( "value" );
							properties.add( new Property( propName, propType, propValue ) );
						}
					}
					getObjs().add( new ObjectImage( x, y, w, h, objName, objType, new String( name ), properties ) );
					
				} else {
					
					getObjs().add( new ObjectImage( x, y, w, h, objName, objType, new String( name ) ) );
				}
			}
		}

		setImage_paths( getTSXPaths() );
	}
	
	public String[] getTSXPaths() throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		String[] imagePaths = new String[ tsx_paths.length ];
		
		for( int i = 0; i < imagePaths.length; i++ ) {
			File file = new File( tsx_paths[ i ] );
			Document doc = dBuilder.parse( file );
			doc.getDocumentElement().normalize();
			Element root = doc.getDocumentElement();
			imagePaths[ i ] = file.getParent() + "/";
			imagePaths[ i ] += root.getElementsByTagName( "image" ).item( 0 ).getAttributes().item( 1 ).getNodeValue();
		}
		return imagePaths;
	}

	public String getImagePath() {
		
		return image_Path;
	}

	public int[][][] getTiles() {
		
		return tiles;
	}

	public void setTiles( int tiles[][][] ) {
		
		this.tiles = tiles;
	}

	public NodeList getTilesets() {
		
		return tileSets;
	}

	public void setTilesets( NodeList tilesets ) {
		
		this.tileSets = tilesets;
	}

	public String[] getImage_paths() {
		
		return image_paths;
	}

	public void setImage_paths( String[] image_paths ) {
		
		this.image_paths = image_paths;
	}

	public NodeList getLayerList() {
		
		return layerList;
	}

	public void setLayerList( NodeList layerList ) {
		
		this.layerList = layerList;
	}

	public int getMapwidth() {
		
		return mapwidth;
	}

	public void setMapwidth( int mapwidth ) {
		
		this.mapwidth = mapwidth;
	}

	public int getMapheight() {
		
		return mapheight;
	}

	public void setMapheight( int mapheight ) {
		
		this.mapheight = mapheight;
	}

	public int getTilewidth() {
		return tilewidth;
	}

	public void setTilewidth( int tilewidth ) {
		
		this.tilewidth = tilewidth;
	}

	public ArrayList< ObjectImage > getObjs() {
		
		return objs;
	}

	public void setObjs( ArrayList< ObjectImage > objs ) {
		
		this.objs = objs;
	}

	public String[] getTSX_paths() {
		
		return tsx_paths;
	}

	public void setTSX_paths( String[] tsx_paths 
			) {
		this.tsx_paths = tsx_paths;
	}
}
