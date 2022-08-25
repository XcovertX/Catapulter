package wearableObjects;

import userInterface.Image;
import userInterface.TSX;
import userInterface.TileChar;

public class Ring extends WearableThing {

	public Ring() {
		this.setWearable( true );
		this.setDonned( false );
		this.wearableLocations.add( "Finger" );
		this.wearableLocations.add( "Thumb" );
		this.type = "Ring";
		
		this.setHoldable( true );
		
		this.setTileChar( new TileChar( " r " ) );
		
		this.setTSXPath( "assets/images/testcity_tileset.tsx" );
		this.setTilesetStartPosition( 23 );
		this.setImageXPosition( 4 );
		this.setImageYPosition( 3 );

	}
}
