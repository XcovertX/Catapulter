package messages;

import game.Game;
import gameObjects.Thing;
import world.GameTile;

import java.awt.*;

public class LocationMessage extends Message {

    @Override
    public void run() { }

    @Override
    public void run( Thing aThing ) {

        if( aThing.isGameTile() ){
            GameTile gt = ( GameTile ) aThing;
            Game.currentGame.getUI().printColor( "Time: " + Game.calendar.getTime(), Color.ORANGE );
            Game.currentGame.getUI().printColor( "Wind Direction: " + Game.calendar.getWeather().getCurrentWindDirection() + " ::: ", Color.ORANGE );
            Game.currentGame.getUI().printlnColor( "Wind Speed: " + Game.calendar.getWeather().getCurrentWindIntensity(), Color.ORANGE );
            Game.currentGame.getUI().print( "You are in " );
            Game.currentGame.getUI().printColor( gt.getRoom().getName(), Color.MAGENTA ); // switched to reading room info rather than tile
            Game.currentGame.getUI().println( "." );							// remove once confirmed it works
        }
    }

    @Override
    public void run( String string ) {

    }
}
