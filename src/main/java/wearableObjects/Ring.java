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
		
		this.setTSXPath( "files/testcity_tileset.tsx" );
		this.setImageXPosition( 4 );
		this.setImageYPosition( 3 );
		
        if( this.getTSXPath() != null ) {

        	try {
				TSX tsx = new TSX( this.getTSXPath() );
				this.setThingImages( new Image[ 1 ] );
				this.getThingImages()[ 0 ] = tsx.buildThingImage( this.getImageXPosition(), this.getImageYPosition(), 32, 32 );
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
        }
	}
}
