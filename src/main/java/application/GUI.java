package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class GUI extends Application {
	
	Stage window;

	Scene scene;
	
	@Override
	public void start( Stage primaryStage ) {
		
		window = primaryStage;
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			scene = new Scene(root);
			
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
			
			String tabPane_css = this.getClass().getResource("tabPane.css").toExternalForm();
			scene.getStylesheets().add(tabPane_css);
			
			window.setScene(scene);
			window.setResizable(true);
			window.setFullScreen(true);
			window.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
