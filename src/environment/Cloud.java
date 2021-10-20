package environment;

import java.util.Random;

import gameObjects.Water;

public class Cloud extends Water {

	private boolean cumulonimbus = false;
	private boolean cumulus = false;
	private boolean stratocumulus = false;
	private boolean stratus = false;
	private boolean nimbostratus =  false;
	
	private String color;
	private int skyCoverPercent;
	
	private int chanceOfRainProduction;
	
	public Random rand = new Random();
	
	public Cloud( int elevation, int month ) {
		determineCloudType( month );
	}
	
	public void determineCloudType( int month ) {
		setAllTypeFalse();
		if( month >= 1 && month <= 4 ) {
			int chance = rand.nextInt( 100 );
			if( chance < 50 ) {
				setCumulonimbus( true );
			} else if( chance >= 50 && chance < 75 ) {
				setCumulus( true );
			} else if( chance >= 75 && chance < 88 ) {
				setStratus( true );
			} else if ( chance >= 88 && chance < 94 ) {
				setStratocumulus( true );
			} else if ( chance >= 94 && chance < 100 ) {
				setNimbostratus( true );
			}
		}
	}

	public boolean isCumulonimbus() {
		return cumulonimbus;
	}

	public void setCumulonimbus(boolean cumulonimbus) {
		this.cumulonimbus = cumulonimbus;
	}

	public boolean isCumulus() {
		return cumulus;
	}

	public void setCumulus(boolean cumulus) {
		this.cumulus = cumulus;
	}

	public boolean isStratocumulus() {
		return stratocumulus;
	}

	public void setStratocumulus(boolean stratocumulus) {
		this.stratocumulus = stratocumulus;
	}

	public boolean isStratus() {
		return stratus;
	}

	public void setStratus(boolean stratus) {
		this.stratus = stratus;
	}

	public boolean isNimbostratus() {
		return nimbostratus;
	}

	public void setNimbostratus(boolean nimbostratus) {
		this.nimbostratus = nimbostratus;
	}
	
	public void setAllTypeFalse() {
		setStratus( false );
		setNimbostratus( false );
		setStratocumulus( false );
		setCumulus( false );
		setCumulonimbus( false );
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
