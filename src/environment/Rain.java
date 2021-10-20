package environment;

import gameObjects.Water;

public class Rain extends Water {
	
	private final int mist = 1;
	private final int light = 2;
	private final int moderate = 3;
	private final int heavy = 4;
	
	private final int vertical = 1;
	private final int slightAngle = 2;
	private final int moderateAngle = 3;
	private final int sideways = 4;

	public Rain() {
		
	}

	public int getMist() {
		return mist;
	}

	public int getLight() {
		return light;
	}

	public int getModerate() {
		return moderate;
	}

	public int getHeavy() {
		return heavy;
	}

	public int getVertical() {
		return vertical;
	}

	public int getSlightAngle() {
		return slightAngle;
	}

	public int getModerateAngle() {
		return moderateAngle;
	}

	public int getSideways() {
		return sideways;
	}
	
	
}
