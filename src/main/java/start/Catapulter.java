package main.java.start;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.application.*;
import main.java.game.Game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Catapulter {

	public static void main(String[] args){
		SpringApplication.run(CatapulterApplication.class,args);
	   }
}
