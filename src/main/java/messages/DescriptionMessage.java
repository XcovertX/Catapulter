package messages;

import game.Game;
import gameObjects.Thing;
import world.GameTile;

public class DescriptionMessage extends Message {
    @Override
    public void run() { }

    @Override
    public void run( Thing aThing ) {
        if( aThing.isGameTile() ) {
            GameTile gt = (GameTile) aThing;
            Game.currentGame.getUI().println( gt.getRoom().getDescription() );
        }

    }

    @Override
    public void run(String string) {

    }
}
