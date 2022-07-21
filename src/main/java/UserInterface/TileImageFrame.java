package main.java.UserInterface;

import java.awt.image.BufferedImage;

public class TileImageFrame {
	
	private BufferedImage frameImage;
	private int frameDuration;

	public BufferedImage getFrameImage() {
		
		return frameImage;
	}

	public void setFrameImage( BufferedImage frameImage ) {
		
		this.frameImage = frameImage;
	}

	public int getFrameDuration() {
		
		return frameDuration;
	}

	public void setFrameDuration( int frameDuration ) {
		
		this.frameDuration = frameDuration;
	}

}
