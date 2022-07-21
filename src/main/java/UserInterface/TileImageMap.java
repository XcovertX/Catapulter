package main.java.UserInterface;

public class TileImageMap {
	
	private TileImage[][] tileImageMap;
	private TileImage[][] tileImageMapVerticallyFlipped;
	private TileImage[] tileImageArray;
	
	public void flipMapVertically() {
		
		tileImageMapVerticallyFlipped = new TileImage[ tileImageMap.length ][ tileImageMap[ 0 ].length ];
		tileImageArray = new TileImage[ tileImageMap.length * tileImageMap[0].length ];
		
		for( int i = 0; i < tileImageMap.length; i++ ) {
			
			for( int j = 0; j < tileImageMap[ 0 ].length; j++ ) {
				
				tileImageMapVerticallyFlipped[ tileImageMap[ 0 ].length - i - 1][ j ] = tileImageMap[ i ][ j ];
			}
		}
	}
	
	public void transformToArray() {
		
		tileImageArray = new TileImage[ tileImageMap.length * tileImageMap[0].length ];
		int accumulator = 0;
		for( int i = 0; i < tileImageMap.length; i++ ) {
			
			for( int j = 0; j < tileImageMap[ 0 ].length; j++ ) {
				
				tileImageArray[ accumulator ] = tileImageMap[ i ][ j ];
				accumulator += 1;
			}
		}
	}


	public TileImage[][] getTileImageMap() {
		
		return tileImageMap;
	}
	
	public TileImage[][] getVerticallyFlippedTileImageMap() {
		
		return tileImageMapVerticallyFlipped;
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
	
	public TileImage getVerticallyFlippedTileImage( int row, int col ) {
		
		return tileImageMapVerticallyFlipped[ row ][ col ];
	}
	
	public void setVerticallyFlippedTileImage( int row, int col, TileImage ti ) {
		
		tileImageMapVerticallyFlipped[ row ][ col ] = ti;
	}
	
	public void buildLayout( int row, int col ) {
		
		tileImageMap = new TileImage[ row ][ col ];
	}

	public TileImage[] getTileImageArray() {
		
		return tileImageArray;
	}

	public void setTileImageArray( TileImage[] tileImageMaplist ) {
		
		this.tileImageArray = tileImageMaplist;
	}
}
