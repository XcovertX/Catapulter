package main.java.UserInterface;

public class TileImage {
	
	private int tileImageNumber;
	private TileImageLayer[] imageLayers; // 0 = base layer
	
	public TileImage( int layerCount, int tileImageNum ) {
		
		this.imageLayers = new TileImageLayer[ layerCount ];
		this.tileImageNumber = tileImageNum;
	}
	
	public TileImageLayer[] getImageLayers() {
		
		return imageLayers;
	}
	public void setImageLayers( TileImageLayer[] imageLayers ) {
		
		this.imageLayers = imageLayers;
	}
	
	public TileImageLayer getImageLayer( int index ) {
		
		return imageLayers[ index ];
	}
	public void setImageLayer( int index, TileImageLayer imageLayer ) {
		
		this.imageLayers[ index ] = imageLayer;
	}

	public int getTileImageNumber() {
		
		return tileImageNumber;
	}

	public void setTileImageNumber( int tileImageNumber ) {
		
		this.tileImageNumber = tileImageNumber;
	}

}
