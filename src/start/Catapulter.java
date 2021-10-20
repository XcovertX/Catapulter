package start;
import game.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import application.*;

import java.io.IOException;


public class Catapulter extends Application {
	
//	public static Stage window;

    public static void main( String[] args ) throws IOException {

    	launch(args);
    	
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
    	boolean t = true;
    	boolean f = false;
    	
        boolean mapBuilderMode = f;
        boolean newGame = true;
        
        if ( mapBuilderMode != true ) {
		
			Game game = new Game( mapBuilderMode, newGame );
	  	
	    	new Thread( new GameLoop( game ) ).start();
	    	game.showIntro();
	    	game.getInputProcessor().updateOutput( 0 ); // change this update once new login process implemented
	    	
		}
	    	
	}

}
