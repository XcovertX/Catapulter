package main.java.UserInterface;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import main.java.game.Game;

public class GraphicalUserInterface extends Scene {
	
	private TextArea map;

	public GraphicalUserInterface( Parent root ) {
		
		super( root );
		
	}
	
	public TextArea getMap() {
		
		return map;
		
	}
	
	public void setMap( TextArea aMap ) {
		
		this.map = aMap;
		
	}
}
