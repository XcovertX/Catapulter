package messages;

import game.Game;
import gameObjects.Thing;

import java.awt.*;

public class TimeMessage extends Message {

    @Override
    public void run() {
        Game.currentGame.getUI().printlnColor( "The current time is: " + Game.calendar.getTime(), Color.ORANGE );
    }

    @Override
    public void run( Thing aThing ) { }

    @Override
    public void run( String string ) { }
}
