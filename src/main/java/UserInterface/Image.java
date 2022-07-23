package main.java.UserInterface;

public class Image {
	
	private int tileImageNumber;
	private ImageLayer[] imageLayers; // 0 = base layer
	
	public Image() {}
	
	public Image( int layerCount, int tileImageNum ) {
		
		this.imageLayers = new ImageLayer[ layerCount ];
		this.tileImageNumber = tileImageNum;
	}
	
	public ImageLayer[] getImageLayers() {
		
		return imageLayers;
	}
	
	public void setImageLayers( ImageLayer[] imageLayers ) {
		
		this.imageLayers = imageLayers;
	}
	
	public ImageLayer getImageLayer( int index ) {
		
		return imageLayers[ index ];
	}
	public void setImageLayer( int index, ImageLayer imageLayer ) {
		
		this.imageLayers[ index ] = imageLayer;
	}

	public int getTileImageNumber() {
		
		return tileImageNumber;
	}

	public void setTileImageNumber( int tileImageNumber ) {
		
		this.tileImageNumber = tileImageNumber;
	}

}
