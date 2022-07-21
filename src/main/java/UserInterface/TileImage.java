package main.java.UserInterface;

public class TileImage {
	
	private TileImageLayer[] imageLayers; // 0 = base layer
	
	public TileImage( int layerCount ) {
		
		this.imageLayers = new TileImageLayer[ layerCount ];
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

}
