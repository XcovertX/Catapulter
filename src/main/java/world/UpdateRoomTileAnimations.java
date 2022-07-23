package main.java.world;

import main.java.UserInterface.Image;
import main.java.UserInterface.ImageFrame;
import main.java.UserInterface.ImageLayer;
import main.java.game.Game;
import main.java.gameObjects.Thing;
import main.java.gameObjects.ThingHolder;
import main.java.gameObjects.ThingList;

public class UpdateRoomTileAnimations extends UpdateWorld {

	@Override
	public void run( Thing aThing ) {

		if( aThing.isGameRoom() ) {
			
			GameRoom gameRoom = ( GameRoom ) aThing;
			ThingList tiles = gameRoom.getTiles();
			
			for( int i = 0; i < tiles.size(); i++ ) {
				
				GameTile gameTile = ( GameTile ) tiles.get( i );
				Image tileImage = gameTile.getBaseTileImage();
				ImageLayer[] tileImageLayers = tileImage.getImageLayers();
				
				for( int j = 0; j < tileImageLayers.length; j++ ) {
					
					ImageLayer tileImageLayer = tileImageLayers[ j ];
					
					if( tileImageLayer != null ) {
						
						if( tileImageLayer.isAnimated() ) {
							
							if( tileImageLayer.frameSwapTimeCheck() ) {
							
								tileImageLayer.cycleActiveImage();
							} 
						}
					}
				}
				Image thingImage = gameTile.getCurrentTileImage();
				
				if( thingImage != null ) {
					
					ImageLayer thingImageLayer = thingImage.getImageLayer( 0 );
					
					if( thingImageLayer != null ) {
						
						if( thingImageLayer.isAnimated() ) {
							
							if( thingImageLayer.frameSwapTimeCheck() ) {
							
								thingImageLayer.cycleActiveImage();
							} 
						}
					}
				}
			}
		}
	}
}
