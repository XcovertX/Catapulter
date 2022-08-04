package main.java.gameObjects;

import java.awt.Color;
import java.util.ArrayList;

import main.java.UserInterface.Image;
import main.java.UserInterface.TSX;
import main.java.light.Light;
import main.java.light.RadiatingLight;

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
				Image[] images = new Image[ 1 ];
				images[ 0 ] = tsx.buildThingImage( 0, 0, 32, 32  );
				this.setThingImages( images );
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        ArrayList< Light > lightSources = new ArrayList< Light >();
        RadiatingLight rLight = new RadiatingLight( 10, ( float ) 0.3, ( float ) 0.7, new Color( 255, 255, 255 ), "flicker" );
        lightSources.add( rLight );
        this.setLightSources( lightSources );
	}
}