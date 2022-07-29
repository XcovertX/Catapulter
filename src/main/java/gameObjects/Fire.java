package main.java.gameObjects;

import java.util.ArrayList;

import main.java.UserInterface.Image;
import main.java.UserInterface.TSX;

public class Fire extends Thing {

	public Fire() {
		
		this.setLightSource(true);
		this.setName( "fire" );
		this.type = "Fire";
		this.setTSXPath( "files/torch_tileset.tsx" );
		this.setImageXPosition( 0 );
		this.setImageYPosition( 0 );
		
        if( this.getTSXPath() != null ) {

        	try {
				TSX tsx = new TSX( this.getTSXPath() );
				this.setThingImages( tsx.buildThingImages( 20 ) );
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        ArrayList< Light > lightSources = new ArrayList< Light >();
        RadiatingLight rLight = new RadiatingLight();
        lightSources.add( rLight );
        this.setLightSources( lightSources );
	}
}
