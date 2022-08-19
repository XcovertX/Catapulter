package gameObjects;

import java.awt.Color;
import java.util.ArrayList;

import components.SpriteRenderer;
import components.SpriteSheet;
import jade.Transform;
import userInterface.Image;
import userInterface.TSX;
import light.Light;
import light.RadiatingLight;
import util.AssetPool;

public class Fire extends Thing {

	public Fire() {
		
		this.setLightSource(true);
		this.setName( "fire" );
		this.type = "Fire";
		this.setTSXPath( "assets/images/torch_tileset.tsx" );

		this.setImageXPosition( 0 );
		this.setImageYPosition( 0 );
		
        if( this.getTSXPath() != null ) {

			TSX tsx = new TSX( this.getTSXPath() );

			Image[] images = new Image[ 1 ];
			images[ 0 ] = tsx.buildThingImage( 0, 0, 32, 32  );
			this.setThingImages( images );
        }
        
        ArrayList< Light > lightSources = new ArrayList< Light >();
        RadiatingLight rLight = new RadiatingLight( 10, ( float ) 0.3, ( float ) 0.7, new Color( 255, 255, 255 ), "flicker" );
        lightSources.add( rLight );
        this.setLightSources( lightSources );
	}
}
