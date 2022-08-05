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
		
        if( this.getTSXPath() != null ) {

        	try {
				TSX tsx = new TSX( this.getTSXPath() );
				this.setThingImages( new Image[ 1 ] );
				this.getThingImages()[ 0 ] = tsx.buildThingImage( this.getImageXPosition(), this.getImageYPosition(), 32, 32 );
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
        }
        
		this.type = "Table";
	}
}
