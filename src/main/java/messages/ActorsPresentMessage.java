package messages;

import actor.Actor;
import game.Game;
import gameObjects.Thing;
import world.GameTile;

import java.awt.*;

public class ActorsPresentMessage extends Message {
    @Override
    public void run() {}

    @Override
    public void run( Thing aThing ) {

        if( aThing.isActor() ) {
            Actor actor = ( Actor ) aThing;
            if( actor.isAlive() ) {
                Game.currentGame.getUI().printColor( actor.toString(), Color.green );
                Game.currentGame.getUI().printlnColor( " is here.", Color.white );
            } else {
                Game.currentGame.getUI().printColor( "The corpse of ", Color.white );
                Game.currentGame.getUI().printColor( actor.toString(), Color.RED );
                Game.currentGame.getUI().printlnColor( " is here.", Color.white );
            }
        } else if( aThing.isGameTile() ) {
            GameTile gt = (GameTile) aThing;
            if( !gt.getNPCs().isEmpty() ) {
                for( int i = 0; i < gt.getNPCs().size(); i++ ) {
                    Actor actor = ( Actor ) gt.getNPCs().get( i );
                    new ActorsPresentMessage().run( actor );
                }
            }
        }
    }

    @Override
    public void run( String string ) { }
}
