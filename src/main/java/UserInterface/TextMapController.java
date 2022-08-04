package main.java.UserInterface;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.java.game.Game;
import main.java.gameObjects.Thing;
import main.java.gameObjects.ThingList;
import main.java.light.AmbientLight;
import main.java.light.Light;
import main.java.light.RadiatingLight;
import main.java.world.GameRoom;
import main.java.world.GameTile;

import org.fxmisc.richtext.InlineCssTextArea;

public class TextMapController {
	
	@FXML private BorderPane mapPane;
	
//	@FXML private InlineCssTextArea map;
	
	@FXML private Canvas tileMap;
	
	@FXML private InlineCssTextArea roomDescription;
	
	@FXML private URL location;
	
	@FXML private ResourceBundle resources;
	
	public TextMapController() { }
	
	public BorderPane getMapPane() {
		
		return mapPane;
	}
	
//	public InlineCssTextArea getMap() {
//		
//		return map;
//	}
	
	public void setMapChars( String mapChars, ArrayList< ArrayList< TileChar > > tileChars ) {
		
//		Text tempT = new Text( mapChars );
//		tempT.setFont( new Font( "Consolas", 12) );
//		StackPane tempSP = new StackPane( tempT );
//		tempSP.layout();
//
//		double roomlength = tempT.getLayoutBounds().getHeight();
//		double roomWidth = tempT.getLayoutBounds().getWidth();
//		
//		map.clearStyle( 0, map.getLength() );
//		map.deleteText( 0, map.getLength() );
//		
//		
//		for( int i = 0; i < tileChars.size(); i++ ) {
//			ArrayList< TileChar > row = tileChars.get(i);
//			for( int j = 0; j < row.size(); j++ ) {
//				TileChar tc = row.get(j);
//				
//				map.appendText( tc.getChar() );
//				if( tc.getTileCharColor() != null ) {
//					
//					map.setStyle( map.getLength() - 3, map.getLength(), "-fx-fill: " + tc.getTileCharColor() + ";" );
//					
//				} 
//			}
//			map.appendText("\n");
//		}
//
//		map.setPrefWidth( roomWidth );
//		map.setPrefHeight( roomlength );
//		map.setDisable( true );
	}
	
	public void render() {
		
		Platform.runLater(() -> {
			
			this.setRoomDescription( Game.calendar.getClock() );
			
			GraphicsContext gc = tileMap.getGraphicsContext2D();
			gc.clearRect( 0, 0, tileMap.getWidth(), tileMap.getHeight() );
			GameRoom gameRoom = Game.currentRoom;
			Shader shader = new Shader();
			int accumulator = 0;
			int tileWidth = 8; // figure out where to put this
			
			for (int i = gameRoom.getRoomLength() - 1; i >= 0; i--) {
				
			    for (int j = 0; j < gameRoom.getRoomWidth(); j++) {
			    	
			    	GameTile gameTile = ( GameTile ) Game.currentRoom.getTiles().get( accumulator );
			    	BufferedImage newImage = shader.shadeImage( gameTile );
		    		Image image = SwingFXUtils.toFXImage( newImage, null );
		    		gc.drawImage( image, ( double ) j * tileWidth, ( double ) i * tileWidth );
		    		
			    	accumulator += 1;
			    }
			}
		});
	};
	
	public void setRoomDescription( String description ) {
		
		Platform.runLater(() -> {
		
			roomDescription.deleteText( 0, roomDescription.getLength() );
			roomDescription.appendText( description + "\n\n" );
			roomDescription.appendText( "Current exits are " + Game.currentTile.getTileExits() + "\n\n" );
			roomDescription.appendText( Game.currentRoom.getDescription() );
			roomDescription.setDisable( true );
			
		});
	}
	
	public void setTileMap() throws Exception {
		
//		GraphicsContext gc = tileMap.getGraphicsContext2D();
//		TMXParser tmx = new TMXParser( Game.currentRoom );
//		
//		for (int i = 0; i < tmx.layer_count; i++) {
//			int accumulator = 0;
//			for (int j = 0; j < tmx.map_rows; j++) {
//				
//			    for (int k = 0; k < tmx.map_cols; k++) {
//			    	
//			    	GameTile gameTile = ( GameTile ) Game.currentRoom.getTiles().get( accumulator );
//			    	main.java.UserInterface.Image tileImage = gameTile.getBaseTileImage();
//			    	ImageLayer tileImageLayer = tileImage.getImageLayer( i );
//			    	if( tileImageLayer != null ) {
//			    		ImageFrame tileImageFrame = tileImageLayer.getActiveFrame();
//			    		BufferedImage frameImage = tileImageFrame.getFrameImage();
//			    		Image image = SwingFXUtils.toFXImage( frameImage, null );
//			    		gc.drawImage( image, ( double ) k * tmx.size, ( double ) j * tmx.size );
//			    	}
//			    	accumulator += 1;
//			    }
//			}
//		}
	}
	
	@FXML
	private void initialize() {
		
		System.out.println( "TextMapController Initialized" );
		
		Platform.runLater(() -> {
			
//			map.setPrefWidth( 350 );
//			map.setPrefHeight( 350 );
			try {
				setTileMap();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
	}

//	main.java.UserInterface.Image thingImage = gameTile.getCurrentThingImage();
//	
//	
//	BufferedImage thingImage = shader.shadeImage(gameTile);
//	image = SwingFXUtils.toFXImage( frameImage, null );
//	gc.drawImage( image, ( double ) j * tileWidth, ( double ) i * tileWidth );
	
//	for( int k = 0; k < tileImage.getImageLayers().length; k++) {
//    	ImageLayer tileImageLayer = tileImage.getImageLayer( k );
//    	
//    	if( tileImageLayer != null ) {
//    		
//    		ImageFrame tileImageFrame = tileImageLayer.getActiveFrame();
//    		BufferedImage bImage = tileImageFrame.getFrameImage();
//    		int width =  bImage.getWidth();
//    		int height = bImage.getHeight();
//    		
//    		BufferedImage newImage = new BufferedImage( bImage.getWidth(), 
//    													bImage.getHeight(),
//    													BufferedImage.TYPE_INT_ARGB );
//    		
//    		ImagePixel[][] pixels = tileImageFrame.getFramePixels();
//    		for( int l = 0; l < width; l++ ) {
//    			
//    			for( int m = 0; m < height; m++ ) {
//    				
//    				newImage.setRGB( l, m, pixels[ m ][ l ].getRgb() );
//    				
//    			}
//    		}
//    		
//    		Image image = SwingFXUtils.toFXImage( newImage, null );
//    		gc.drawImage( image, ( double ) j * tileWidth, ( double ) i * tileWidth );
//    	}
//	}
	
//	main.java.UserInterface.Image thingImage = gameTile.getCurrentThingImage();
//	
//	if( thingImage != null ) {
//    	for( int k = 0; k < thingImage.getImageLayers().length; k++) {
//	    	ImageLayer thingImageLayer = thingImage.getImageLayer( k );
//	    	if( thingImageLayer != null ) {
//	    		ImageFrame thingImageFrame = thingImageLayer.getActiveFrame();
//	    		BufferedImage frameImage = thingImageFrame.getFrameImage();
//	    		image = SwingFXUtils.toFXImage( frameImage, null );
//	    		gc.drawImage( image, ( double ) j * tileWidth, ( double ) i * tileWidth );
//	    	}
//    	}
//	}
}
