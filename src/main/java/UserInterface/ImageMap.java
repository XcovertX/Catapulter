package main.java.UserInterface;

public class ImageMap {
	
	private Image[][] tileImageMap;
	private Image[][] tileImageMapVerticallyFlipped;
	private Image[] tileImageArray;
	
	public void flipMapVertically() {
		
		tileImageMapVerticallyFlipped = new Image[ tileImageMap.length ][ tileImageMap[ 0 ].length ];
		tileImageArray = new Image[ tileImageMap.length * tileImageMap[0].length ];
		
		for( int i = 0; i < tileImageMap.length; i++ ) {
			
			for( int j = 0; j < tileImageMap[ 0 ].length; j++ ) {
				
				tileImageMapVerticallyFlipped[ tileImageMap[ 0 ].length - i - 1][ j ] = tileImageMap[ i ][ j ];
			}
		}
	}
	
	public void transformToArray() {
		
		tileImageArray = new Image[ tileImageMap.length * tileImageMap[0].length ];
		int accumulator = 0;
		for( int i = 0; i < tileImageMap.length; i++ ) {
			
			for( int j = 0; j < tileImageMap[ 0 ].length; j++ ) {
				
				tileImageArray[ accumulator ] = tileImageMapVerticallyFlipped[ i ][ j ];
				
				accumulator += 1;
			}
		}
	}


	public Image[][] getTileImageMap() {
		
		return tileImageMap;
	}
	
	public Image[][] getVerticallyFlippedTileImageMap() {
		
		return tileImageMapVerticallyFlipped;
	}

	public void setTileImageMap( Image[][] tileImageMap ) {
		
		this.tileImageMap = tileImageMap;
	}
	
	public Image getTileImage( int row, int col ) {
		
		return tileImageMap[row][col];
	}
	
	public void setTileImage( int row, int col, Image ti ) {
		
		tileImageMap[row][col] = ti;
	}
	
	public Image getVerticallyFlippedTileImage( int row, int col ) {
		
		return tileImageMapVerticallyFlipped[ row ][ col ];
	}
	
	public void setVerticallyFlippedTileImage( int row, int col, Image ti ) {
		
		tileImageMapVerticallyFlipped[ row ][ col ] = ti;
	}
	
	public void buildLayout( int row, int col ) {
		
		tileImageMap = new Image[ row ][ col ];
	}

	public Image[] getTileImageArray() {
		
		return tileImageArray;
	}

	public void setTileImageArray( Image[] tileImageMaplist ) {
		
		this.tileImageArray = tileImageMaplist;
	}
}
