package environment;

import gameObjects.ThingHolder;

public class Region extends ThingHolder {

	private int elevation;
	private int terrainType;
	private int terrainGrade; //scale 1 to 10
	
	private final int plateau = 1;
	private final int plane = 2;
	private final int hill = 3;
	private final int valley = 4;
	private final int canyon = 5;
	private final int mountain = 10;
	
	
	public Region() {
		setElevation( 15000 );
		setTerrainType( plateau );
		
	}

	public int getElevation() {
		return elevation;
	}

	public void setElevation(int elevation) {
		this.elevation = elevation;
	}

	public int getTerrainType() {
		return terrainType;
	}

	public void setTerrainType(int terrainType) {
		this.terrainType = terrainType;
	}

	public int getTerrainGrade() {
		return terrainGrade;
	}

	public void setTerrainGrade(int terrainGrade) {
		this.terrainGrade = terrainGrade;
	}
}
