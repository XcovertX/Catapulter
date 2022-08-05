package gameObjects;

import userInterface.Image;
import userInterface.TSX;
import userInterface.TileChar;

public class Skull extends Thing {
	
	public Skull() {
		
		this.setName( "skull" );
		this.setHoldable( true );
		this.setTileChar( new TileChar( " s " ) );
		
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
        
		this.type = "Skull";
	}

}
