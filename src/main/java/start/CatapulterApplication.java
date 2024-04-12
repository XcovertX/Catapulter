package main.java.start;
import main.java.application.*;
import main.java.game.Game;
import java.io.IOException;

public class CatapulterApplication {
	
	public CatapulterApplication() {	

		boolean mapBuilderMode = false;
		boolean newGame = true;
		
		if ( mapBuilderMode != true ) {
		
			Game game = new Game( mapBuilderMode, newGame );
			Thread gameThread = new Thread( new GameLoop( game ) );
			gameThread.setName( "Game_Thread" );
			gameThread.start();
			game.getInputProcessor().updateOutput( 0 ); // change this update once new login process implemented	
		}
	}	
}


