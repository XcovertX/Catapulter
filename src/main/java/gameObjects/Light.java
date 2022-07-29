package main.java.gameObjects;

import java.awt.Color;

public abstract class Light {

	static final int INFINITE = -1;
	static final Color WHITE = new Color( 255, 255, 255 );
	static final int MAX_BRIGHTNESS = 100;
	static final int AVG_DISTANCE = 10;
	
	private int tileNumber;
	
	private int distance;
	private float brightness;
	private Color RGB;
	
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

	public int getTileNumber() {
		
		return tileNumber;
	}

	public void setTileNumber( int tileNumber ) {
		
		this.tileNumber = tileNumber;
	}
}
