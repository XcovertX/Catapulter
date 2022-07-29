package main.java.light;

import java.awt.Color;

public class AmbientLight extends Light {
	
	public AmbientLight() {
		this.setBrightness( MAX_BRIGHTNESS );
		this.setDistance( INFINITE );
		this.setRGB( WHITE );	
	}

	public AmbientLight( int distance, int brightness, Color rgb ) {
		
		this.setBrightness( brightness );
		this.setDistance( distance );
		this.setRGB( rgb );
	}
}
