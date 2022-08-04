package main.java.light;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public abstract class Light {

	static final int INFINITE = -1;
	static final Color WHITE = new Color( 255, 255, 255 );
	static final float MAX_BRIGHTNESS = ( float ) 1.0;
	static final int AVG_DISTANCE = 10;

	private int    distance;
	private float  height;
	private float  brightness;
	private float  maxBrightness;
	private Color  RGB;
	private String behaviorType;
	
	private int    timeAccumulation;
	private int    duration;
	private long   lastUpdate;
	private int[]  intensityFluctuation;
	private int    intentistyIndex;
	
	public int getDistance() {
		
		return distance;
	}
	
	public void setDistance( int distance ) {
		
		this.distance = distance;
	}
	
	public float getBrightness() {
		
		return brightness;
	}
	
	public void setBrightness( float brightness ) {
		
		this.brightness = brightness;
	}
	
	public Color getRGB() {
		
		return RGB;
	}
	
	public void setRGB( Color rgb ) {
		
		RGB = rgb;
	}

	public String getBehaviorType() {
		
		return behaviorType;
	}

	public void setBehaviorType( String behaviorType ) {
		
		this.behaviorType = behaviorType;
	}

	public float getHeight() {
		
		return height;
	}

	public void setHeight( float height ) {
		
		this.height = height;
	}
	
	public int getTimeAccumulation() {
		
		return timeAccumulation;
	}
	
	public void setTimeAccumulation( int accumulation ) {
		
		this.timeAccumulation = accumulation;
	}
	
	public int getDuration() {
		
		return duration;
	}
	
	public void setDuration(int duration) {
		
		this.duration = duration;
	}
	
	public int[] getIntensityFluctuation() {
		
		return intensityFluctuation;
	}
	
	public void setIntensityFluctuation() {
		
		this.intensityFluctuation = new LightBehaviors().getLightBehavior( this.behaviorType );
	}
	
	public long getLastUpdate() {
		
		return lastUpdate;
	}
	
	public void setLastUpdate( long lastUpdate ) {
		
		this.lastUpdate = lastUpdate;
	}
	
	public boolean lightTimeCheck() {
		
		updateTimeAccumulation();

		if( timeAccumulation >= duration ) {
			lastUpdate = System.currentTimeMillis();
			timeAccumulation = 0;
			return true;
		}
		return false;
	}
	
	private void updateTimeAccumulation() {
		
		timeAccumulation += System.currentTimeMillis() - lastUpdate;
	}
	
	public void cycleIntensity() {
		
		if( !( intentistyIndex + 1 >= intensityFluctuation.length ) ) {
			
			intentistyIndex += 1;
			
		} else {
			
			intentistyIndex = 0;
		}
		
		updateLightFields();
	}	
	
	private void updateLightFields() {
		
		this.distance = intensityFluctuation[ intentistyIndex ];
	}

	public float getMaxBrightness() {
		
		return maxBrightness;
	}

	public void setMaxBrightness( float maxBrightness ) {
		
		this.maxBrightness = maxBrightness;
	}
}
