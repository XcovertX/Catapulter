package main.java.UserInterface;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.java.game.Game;

public class TileImageRenderer {
	
	private String tmx;
	
	private TileImage[][] tileImageMap;
	
	
	public void initTileMap() throws Exception {
		
		TMXParser tmx = new TMXParser( Game.currentRoom );
//		GraphicsContext gc = tileMap.getGraphicsContext2D();
		for (int i = 0; i < tmx.layer_count; i++) {
			for (int j = 0; j < tmx.map_rows; j++) {
			    for (int k = 0; k < tmx.map_cols; k++) {
			    	if(tmx.tiles[i][j][k] != null) {
			    		if( i == 0 ) {
			    			
//			    			tileImageMap[j][k] = new TileImage( tmx.layer_count );
			    			
			    		} 
			    		
			    		boolean isAnimated = true;
			    		BufferedImage[] bImage = { tmx.tiles[i][j][k] };
			    		int activeFrame = 0;
			    			
//			    		tileImageMap[j][k].getImageLayers()[i] = new TileImageLayer( isAnimated, bImage, activeFrame );
			    		
//			    		Image image = SwingFXUtils.toFXImage(tmx.tiles[i][j][k], null);
//			    		gc.drawImage(image, (double) k * tmx.size, (double) j * tmx.size);
			    	}
			    }
			}
		}
	}

	public String getTMX() {
		
		return tmx;
	}

	public void setTMX(String tmx) {
		
		this.tmx = tmx;
	}

}
