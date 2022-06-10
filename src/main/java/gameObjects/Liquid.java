package main.java.gameObjects;

public abstract class Liquid extends Thing  {
	
	/*
	 *  dropCount is the base unit
	 *  
	 */
	private int dropCount;
	
	private int healthModifier;
	private int thirstModifier;
	
	private boolean isWater;
	private boolean isAcid;
	private boolean isPoison;
	private boolean isAlcohol;
	
	public Liquid() {
		this.setDrinkable( true );
		this.type= "Liquid";
	}

	public int getHealthModifier() {
		return healthModifier;
	}

	public void setHealthModifier(int healthModifier) {
		this.healthModifier = healthModifier;
	}

	public int getThirstModifier() {
		return thirstModifier;
	}

	public void setThirstModifier(int thirstModifier) {
		this.thirstModifier = thirstModifier;
	}

	public boolean isWater() {
		return isWater;
	}

	public void setWater(boolean isWater) {
		this.isWater = isWater;
	}

	public boolean isAcid() {
		return isAcid;
	}

	public void setAcid(boolean isAcid) {
		this.isAcid = isAcid;
	}

	public boolean isPoison() {
		return isPoison;
	}

	public void setPoison(boolean isPoison) {
		this.isPoison = isPoison;
	}

	public boolean isAlcohol() {
		return isAlcohol;
	}

	public void setAlcohol(boolean isAlcohol) {
		this.isAlcohol = isAlcohol;
	}

	public int getDropCount() {
		return dropCount;
	}

	public void setDropCount(int dropCount) {
		this.dropCount = dropCount;
	}

	public double getMilliliter() {
		return getDropCount() / 20;
	}

	public double getLiter() {
		return getMilliliter() / 1000;
	}

}
