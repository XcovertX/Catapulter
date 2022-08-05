package userInterface;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;

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
