package userInterface;

import java.awt.image.BufferedImage;

public class ImageFrame {

	private int imageFrameNumber;

	private long frameDuration;
	
	public ImageFrame() {}

	public long getFrameDuration() {
		
		return frameDuration;
	}

	public void setFrameDuration( int frameDuration ) {
		
		this.frameDuration = frameDuration;
	}

	public int getImageFrameNumber() { return imageFrameNumber; }

	public void setImageFrameNumber( int imageFrameNumber ) { this.imageFrameNumber = imageFrameNumber; }
}
