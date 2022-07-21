package main.java.UserInterface;

public class TileImageMap {
	
	private TileImage[][] tileImageMap;

	public TileImage[][] getTileImageMap() {
		
		return tileImageMap;
	}

	public void setTileImageMap( TileImage[][] tileImageMap ) {
		
		this.tileImageMap = tileImageMap;
	}
	
	public TileImage getTileImage( int row, int col ) {
		
		return tileImageMap[row][col];
	}
	
	public void setTileImage( int row, int col, TileImage ti ) {
		
		tileImageMap[row][col] = ti;
	}
	
	public void buildLayout( int row, int col ) {
		
		tileImageMap = new TileImage[row][col];
	}

}
