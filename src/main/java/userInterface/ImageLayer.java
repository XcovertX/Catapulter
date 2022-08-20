package userInterface;

public class ImageLayer {
	
	private String name;
	private boolean isAnimated;
	private long timeAccumulation; // in milliseconds
	private long lastUpdate;
	private ImageFrame[] frames;
	private int activeFrameIndex;
	
	public ImageLayer() {}
	
	public ImageLayer( boolean isAnimated, ImageFrame[] frames, int activeFrameIndex ) {
		
		this.isAnimated = isAnimated;
		this.frames = frames;
		this.activeFrameIndex = activeFrameIndex;
		this.lastUpdate = System.currentTimeMillis();
		this.timeAccumulation = 0;
	}
	
	public ImageFrame getActiveFrame() { return frames[ activeFrameIndex ]; }
	
	public boolean frameSwapTimeCheck() {
		
		updateTimeAccumulation();

		if( timeAccumulation >= frames[ activeFrameIndex ].getFrameDuration() ) {
			lastUpdate = System.currentTimeMillis();
			timeAccumulation = 0;
			return true;
		}
		return false;
	}
	
	private void updateTimeAccumulation() { timeAccumulation += System.currentTimeMillis() - lastUpdate; }
	
	public void increaseTimeAccumulation( int msCount ) { timeAccumulation += msCount; }
	
	public void setTimeAccumulation( int msCount ) { timeAccumulation = msCount; }
	
	public void cycleActiveImage() {
		
		if( !( activeFrameIndex + 1 >= frames.length ) ) {
			
			activeFrameIndex += 1;
			
		} else {
			
			activeFrameIndex = 0;
		}
	}
	
	public boolean isAnimated() { return isAnimated; }
	
	public void setAnimated( boolean isAnimated ) { this.isAnimated = isAnimated; }
	
	public ImageFrame[] getImageFrames() { return frames; }
	
	public void setImageFrames( ImageFrame[] frames ) { this.frames = frames; }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
