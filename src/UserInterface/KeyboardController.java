package UserInterface;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import game.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class KeyboardController {
	
	@FXML private Pane keyboard;
	
	@FXML private URL location;
	
	@FXML private ResourceBundle resources;
	
	@FXML private Button Button1;
	@FXML private Button Button2;
	@FXML private Button Button3;
	@FXML private Button Button4;
	@FXML private Button Button5;
	@FXML private Button Button6;
	@FXML private Button Button7;
	@FXML private Button Button8;
	@FXML private Button Button9;
	@FXML private Button Button0;
	
	@FXML private Button ButtonA;
	@FXML private Button ButtonB;
	@FXML private Button ButtonC;
	@FXML private Button ButtonD;
	@FXML private Button ButtonE;
	@FXML private Button ButtonF;
	@FXML private Button ButtonG;
	@FXML private Button ButtonH;
	@FXML private Button ButtonI;
	@FXML private Button ButtonJ;
	@FXML private Button ButtonK;
	@FXML private Button ButtonL;
	@FXML private Button ButtonM;
	@FXML private Button ButtonN;
	@FXML private Button ButtonO;
	@FXML private Button ButtonP;
	@FXML private Button ButtonQ;
	@FXML private Button ButtonR;
	@FXML private Button ButtonS;
	@FXML private Button ButtonT;
	@FXML private Button ButtonU;
	@FXML private Button ButtonV;
	@FXML private Button ButtonW;
	@FXML private Button ButtonX;
	@FXML private Button ButtonY;
	@FXML private Button ButtonZ;
	
	@FXML private Button ButtonESC;
	@FXML private Button ButtonTAB;
	@FXML private Button ButtonCAPS;
	@FXML private Button ButtonSHIFT;
	@FXML private Button ButtonSPACE;
	@FXML private Button ButtonLEFTSQRBRACKET;
	@FXML private Button ButtonRIGHTSQRBRACKET;
	@FXML private Button ButtonBACKSLASH;
	@FXML private Button ButtonDASH;
	@FXML private Button ButtonEQUALS;
	@FXML private Button ButtonENTER;
	@FXML private Button ButtonBACKSPACE;
	@FXML private Button ButtonCTRL;
	@FXML private Button ButtonALT;
	@FXML private Button ButtonLEFT;
	@FXML private Button ButtonRIGHT;
	@FXML private Button ButtonUP;
	@FXML private Button ButtonDOWN;
	@FXML private Button ButtonCOMMA;
	@FXML private Button ButtonMINUS;
	@FXML private Button ButtonPERIOD;
	@FXML private Button ButtonSLASH;
	@FXML private Button ButtonSEMI;
	
	
	@FXML private HashMap buttons;
	
	private Button[] keys;
	
	public KeyboardController() { };

	@FXML
	private void initialize() {
		
		System.out.println("KeyboardController Initialized");
		
		Platform.runLater(() -> {
			
			keys = new Button[] { 
					
					ButtonENTER, ButtonBACKSPACE, ButtonTAB, new Button(), new Button(), 
					ButtonSHIFT, ButtonCTRL, ButtonALT, new Button(), ButtonCAPS, ButtonESC,
					ButtonSPACE, new Button(), new Button(), new Button(), new Button(),
					ButtonLEFT, ButtonUP, ButtonRIGHT, ButtonDOWN, ButtonCOMMA, ButtonMINUS,
					ButtonPERIOD, ButtonSLASH, 
					
					Button0, Button1, Button2, Button3, Button4, 
					Button5, Button6, Button7, Button8, Button9,
					
					ButtonSEMI, ButtonEQUALS,
					
					ButtonA, ButtonB, ButtonC, ButtonD, ButtonE,
					ButtonF, ButtonG, ButtonH, ButtonI, ButtonJ,
					ButtonK, ButtonL, ButtonM, ButtonN, ButtonO,
					ButtonP, ButtonQ, ButtonR, ButtonS, ButtonT,
					ButtonU, ButtonV, ButtonW, ButtonX, ButtonY,
					ButtonZ, 
					
					ButtonLEFTSQRBRACKET, ButtonBACKSLASH, ButtonRIGHTSQRBRACKET
					
			};
	
		});
	}
	
	public Pane getKeyboard() {
		return keyboard;
	}
	
    public void keyPressedPerform( KeyEvent e ) {

    	KeyCode[] allKeys = KeyCode.values();
    	
    	for( int i = 0; i < allKeys.length; i++ ) {
    		
    		if( e.getCode() == allKeys[ i ] ) {
    			
    			keys[ i ].setStyle("-fx-background-color: #15fc03; -fx-text-fill: #001501;");
    			return;
    		}
    	}
    }
    
    public void keyReleasedPerform( KeyEvent e ) {
    	
    	KeyCode[] allKeys = KeyCode.values();
    	
    	for( int i = 0; i < allKeys.length; i++ ) {
    		
    		if( e.getCode() == allKeys[ i ] ) {
    			keys[ i ].setStyle("-fx-background-color: #001501; -fx-text-fill: #15fc03;");
    			return;
    		}
    	}
    }
    
	
	
}
