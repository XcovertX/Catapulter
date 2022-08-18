package world;

import userInterface.Image;
import userInterface.ImageLayer;
import gameObjects.Thing;
import gameObjects.ThingList;

public class UpdateRoomTileAnimations extends UpdateWorld {

	@Override
	public void run( Thing aThing ) {

		if( aThing.isGameRoom() ) {
			
			GameRoom gameRoom = ( GameRoom ) aThing;
			ThingList tiles = gameRoom.getTiles();
			
			for( int i = 0; i < tiles.size(); i++ ) {
				
				GameTile gameTile = ( GameTile ) tiles.get( i );
				Image tileImage = gameTile.getBaseTileImage();

				if( tileImage != null ){

					ImageLayer[] tileImageLayers = tileImage.getImageLayers();

					for( int j = 0; j < tileImageLayers.length; j++ ) {

						ImageLayer tileImageLayer = tileImageLayers[ j ];

						if( tileImageLayer != null ) {

							if( tileImageLayer.isAnimated() ) {

								if (tileImageLayer.frameSwapTimeCheck()) {

									tileImageLayer.cycleActiveImage();
								}
							}
						}
					}
				}
				
				Image thingImage = gameTile.getCurrentThingImage();
				
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
