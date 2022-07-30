package main.java.UserInterface;

import java.awt.image.BufferedImage;

public class ImageFrame {
	
	private BufferedImage frameImage;
	private ImagePixel[][] framePixels;
	private long frameDuration;
	
	public ImageFrame() {}

	public BufferedImage getFrameImage() {
		
		return frameImage;
	}

	public void setFrameImage( BufferedImage frameImage ) {
		
		this.frameImage = frameImage;
	}

	public long getFrameDuration() {
		
		return frameDuration;
	}

	public void setFrameDuration( int frameDuration ) {
		
		this.frameDuration = frameDuration;
	}

	public ImagePixel[][] getFramePixels() {
		
		return framePixels;
	}

	public void setFramePixels( ImagePixel[][] framePixels ) {
		
		this.framePixels = framePixels;
	}

}
