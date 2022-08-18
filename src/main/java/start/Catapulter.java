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


public class Catapulter {
	public static void main( String[] args ){
	     Application.launch( CatapulterApplication.class, args );
	   }
	public static void altMain( Window window ) {

		Game game = new Game( true );
		new GameLoop( game ).run();
//		gameThread.setName( "Game_Thread" );
//		gameThread.start();
//		game.showIntro();
//		game.getInputProcessor().updateOutput( 0 ); // change this update once new login process implemented
	}

}
