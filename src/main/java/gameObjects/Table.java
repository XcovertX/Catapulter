package gameObjects;

import userInterface.Image;
import userInterface.TSX;
import userInterface.TileChar;

public class Table extends Furniture {
	
	public Table() {
		this.name = "table";
		this.description = "";
		this.setHoldsItemsOnTop( true );
		this.setTileChar( new TileChar( " t " ) );
		
		this.setTSXPath( "files/testcity_tileset.tsx" );
		this.setImageXPosition( 4 );
		this.setImageYPosition( 3 );
        
		this.type = "Table";
	}
}
