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
        
		this.type = "Skull";
	}

}
