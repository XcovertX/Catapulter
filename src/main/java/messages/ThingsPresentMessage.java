package messages;

import game.Game;
import gameObjects.Thing;
import world.GameTile;

import java.awt.*;

public class ThingsPresentMessage extends Message {
    @Override
    public void run() { }

    @Override
    public void run( Thing aThing ) {
        if( aThing.isGameTile() ) {
            GameTile gt = (GameTile) aThing;
            String s = "";
            if ( !gt.getThings().isEmpty() ) {
                for ( int i = 0; i < gt.getThings().size(); i++ ) {

                    Thing thing = gt.getThings().get(i);

                    s = "There is a ";
                    Game.currentGame.getUI().printColor( s, Color.white );

                    s = thing.toString();
                    Game.currentGame.getUI().printColor( s, Color.yellow );

                    if( thing.isContainedWithin() ) {
                        s = " in the ";
                    } else if( thing.isUnderneath() ) {
                        s = " under the ";
                    } else {
                        s = " on the ";
                    }
                    Game.currentGame.getUI().printColor(s, Color.white);

                    s = thing.getHeldBy().toString();
                    Game.currentGame.getUI().printColor( s, Color.yellow );

                    s = ".";
                    Game.currentGame.getUI().printlnColor( s, Color.white );
                }
            }
        }
    }

    @Override
    public void run(String string) {

    }
}
