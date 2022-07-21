package main.java.UserInterface;

import java.awt.image.BufferedImage;

public class TileImageLayer {
	
	private boolean isAnimated;
	private int timeAccumulation; // in milliseconds
	private TileImageFrame[] frames;
	private int activeFrameIndex;
	
	public TileImageLayer( boolean isAnimated, TileImageFrame[] frames, int activeImageIndex ) {
		
		this.isAnimated = isAnimated;
		this.frames = frames;
		this.activeFrameIndex = activeImageIndex;
	}
	
	public TileImageFrame getActiveFrame() {
		
		return frames[ activeFrameIndex ];
	}
	
	public boolean frameSwapTimeCheck() {
		
		if( timeAccumulation >= frames[ activeFrameIndex ].getFrameDuration() ) {
			
			return true;
		}
		return false;
	}
	
	public void increaseTimeAccumulation( int msCount ) {
		
		timeAccumulation += msCount;
	}
	
	public void setTimeAccumulation( int msCount ) {
		
		timeAccumulation = msCount;
	}
	
	public void cycleActiveImage() {
		
		if( !( activeFrameIndex + 1 <= frames.length ) ) {
			
			activeFrameIndex += 1;
			
		} else {
			
			activeFrameIndex = 0;
		}
	}
	
	public boolean isAnimated() {
		
		return isAnimated;
	}
	
	public void setAnimated( boolean isAnimated ) {
		
		this.isAnimated = isAnimated;
	}
	
	public TileImageFrame[] getFrames() {
		
		return frames;
	}
	
	public void setAnimatedImage( TileImageFrame[] frames ) {
		
		this.frames = frames;
	}
}
