package main.java.gameObjects;

import java.awt.Color;

public abstract class Light {

	static final int INFINITE = -1;
	static final Color WHITE = new Color( 255, 255, 255 );
	static final int MAX_BRIGHTNESS = 100;
	static final int AVG_DISTANCE = 10;
	
	private int distance;
	private int brightness;
	private Color RGB;
	
	public int getDistance() {
		
		return distance;
	}
	
	public void setDistance( int distance ) {
		
		this.distance = distance;
	}
	
	public int getBrightness() {
		
		return brightness;
	}
	
	public void setBrightness( int brightness ) {
		
		this.brightness = brightness;
	}
	
	public Color getRGB() {
		
		return RGB;
	}
	
	public void setRGB( Color rgb ) {
		
		RGB = rgb;
	}
}
