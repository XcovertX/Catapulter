package main.java.world;

import java.util.ArrayList;

import main.java.gameObjects.Thing;
import main.java.gameObjects.ThingList;
import main.java.light.Light;
import main.java.light.RadiatingLight;

public class UpdateLight extends UpdateWorld {

	@Override
	public void run(Thing aThing) {
		
		if( aThing.isGameRoom() ) {
			
			GameRoom gameRoom = ( GameRoom ) aThing;

			ThingList lightSourceObjects = gameRoom.getAllRoomLightSourceObjects();
			
			for( int i = 0; i < lightSourceObjects.size(); i++ ) {
				
				Thing t = lightSourceObjects.get( i );
				
				ArrayList< Light > lightSources = t.getLightSources();
				
				for( int j = 0; j < lightSources.size(); j++ ) {
					
					RadiatingLight rLight = (RadiatingLight) lightSources.get( j );
					
					if( rLight.lightTimeCheck() ) {
						
						rLight.cycleIntensity();
					}
				}
			}	
		}
	}
}
