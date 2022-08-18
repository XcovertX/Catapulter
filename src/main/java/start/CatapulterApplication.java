package start;
import jade.Window;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import application.*;
import game.Game;

import java.io.IOException;


public class CatapulterApplication extends Application {
	
//	public static Stage window;

//    public static void main( String[] args ) throws IOException {
//
//    	launch(args);
//    	
//    }

	@Override
	public void start( Stage primaryStage ) throws Exception {

		Game game = new Game( true );
		Thread gameThread = new Thread( new GameLoop( game ) );
		gameThread.setName( "Game_Thread" );
		gameThread.start();
		game.showIntro();
		game.getInputProcessor().updateOutput( 0 ); // change this update once new login process implemented
	}
}
