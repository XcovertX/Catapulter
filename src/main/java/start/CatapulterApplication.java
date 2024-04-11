package main.java.start;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.application.*;
import main.java.game.Game;

import java.io.IOException;


public class CatapulterApplication extends Application {
	
//	public static Stage window;

//    public static void main( String[] args ) throws IOException {
//
//    	launch(args);
//    	
//    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		
    	boolean t = true;
    	boolean f = false;
    	
        boolean mapBuilderMode = f;
        boolean newGame = true;
        
        if ( mapBuilderMode != true ) {
		
			Game game = new Game( mapBuilderMode, newGame );
	    	Thread gameThread = new Thread( new GameLoop( game ) );
	    	gameThread.setName( "Game_Thread" );
	    	gameThread.start();
	    	game.showIntro();
	    	game.getInputProcessor().updateOutput( 0 ); // change this update once new login process implemented
	    	
		}
	    	
	}

}
