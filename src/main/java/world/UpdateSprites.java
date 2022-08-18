package world;

import gameObjects.Thing;
import jade.RoomScene;

public class UpdateSprites extends UpdateWorld {

    @Override
    public void run( Thing aThing ) {

        if( aThing.isGameRoom() ) {

            GameRoom gameRoom = ( GameRoom ) aThing;
            RoomScene roomScene = gameRoom.getScene();
            roomScene.update();

        }
    }
}
