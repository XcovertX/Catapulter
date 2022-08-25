package messages;

import game.Game;
import gameObjects.Thing;
import world.GameTile;

import java.awt.*;

public class ExitsMessage extends Message {
    @Override
    public void run() { }

    @Override
    public void run( Thing aThing ) {
        if( aThing.isGameTile() ) {
            GameTile gt = ( GameTile ) aThing;
            String currentExits = "The current exits are " + gt.getTileExits().toString();
            Game.currentGame.getUI().printlnColor( currentExits, Color.CYAN );
        }
    }

    @Override
    public void run( String string ) {
        if( string.equals( "No Exit" ) ) {
            Game.currentGame.getUI().println( "There is no exit that way." );
        };
    }
}
