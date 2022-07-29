package main.java.light;

import java.awt.Color;

public class RadiatingLight extends Light {
	
	static final String DEFAULT_LIGHT_BEHAVIOR = "constant";
	private String direction;
	private int angle;
	
	public RadiatingLight() {
		
		this.setBrightness( MAX_BRIGHTNESS );
		this.setDistance( AVG_DISTANCE );
		this.setRGB( WHITE );
		this.setBehaviorType( DEFAULT_LIGHT_BEHAVIOR );
		this.setIntensityFluctuation();
	}

	public RadiatingLight( int distance, int brightness, Color rgb, String behaviorType ) {
		
		this.setBrightness( brightness );
		this.setDistance( distance );
		this.setRGB( rgb );
		this.setBehaviorType( behaviorType );
		this.setIntensityFluctuation();
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
