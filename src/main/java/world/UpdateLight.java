package world;

import java.util.ArrayList;

import gameObjects.Thing;
import gameObjects.ThingList;
import light.Light;
import light.RadiatingLight;

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
