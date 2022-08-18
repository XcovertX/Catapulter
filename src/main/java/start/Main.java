package start;

import game.Game;
import jade.Window;
import javafx.stage.Stage;

public class Main {
    public static void main( String [] args ) throws Exception {

        run();
    }

    public static void run() throws Exception {

        Game game = new Game( true );
        new GameLoop( game ).run();
//        Thread gameThread = new Thread( new GameLoop( game ) );
//        gameThread.setName( "Game_Thread" );
//        gameThread.start();
//        game.showIntro();
//        game.getInputProcessor().updateOutput( 0 ); // change this update once new login process implemented
    }
}

