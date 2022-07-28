package main.java.gameObjects;

import java.awt.Color;

public class RadiatingLight extends Light {
	
	private String direction;
	private int angle;
	
	public RadiatingLight() {
		
		this.setBrightness( MAX_BRIGHTNESS );
		this.setDistance( AVG_DISTANCE );
		this.setRGB( WHITE );
	}

	public RadiatingLight( int distance, int brightness, Color rgb ) {
		
		this.setBrightness( brightness );
		this.setDistance( distance );
		this.setRGB( rgb );
	}
	
	public String getDirection() {
		
		return direction;
	}

	public void setDirection( String direction ) {
		
		this.direction = direction;
	}

	public int getAngle() {
		
		return angle;
	}

	public void setAngle( int angle ) {
		
		this.angle = angle;
	}
}