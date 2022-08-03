package main.java.UserInterface;

public class ImagePixel {
	
	private int x;
	private int y;
	private int rgb;
	private int roughness;
	
	public ImagePixel() {
		
//		this.rgb = 0x00;
	}

	public int getRgb() {
		
		return rgb;
	}

	public void setRgb( int rgb ) {
		
		this.rgb = rgb;
	}

	public int getRoughness() {
		
		return roughness;
	}

	public void setRoughness( int roughness ) {
		
		this.roughness = roughness;
	}

	public int getX() {
		
		return x;
	}

	public void setX( int x ) {
		
		this.x = x;
	}

	public int getY() {
		
		return y;
	}

	public void setY( int y ) {
		
		this.y = y;
	}

}
