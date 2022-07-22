package main.java.world;

import main.java.UserInterface.TileImage;
import main.java.UserInterface.TileImageFrame;
import main.java.UserInterface.TileImageLayer;
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
				TileImage tileImage = gameTile.getTileImage();
				TileImageLayer[] tileImageLayers = tileImage.getImageLayers();
				
				for( int j = 0; j < tileImageLayers.length; j++ ) {
					
					TileImageLayer tileImageLayer = tileImageLayers[ j ];
					
					if( tileImageLayer != null ) {
						if( tileImageLayer.isAnimated() ) {
							
							if( tileImageLayer.frameSwapTimeCheck() ) {
							
								tileImageLayer.cycleActiveImage();
							} 
						}
					}
				}
			}
		}
	}
}
