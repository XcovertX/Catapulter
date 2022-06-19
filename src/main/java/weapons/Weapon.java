package main.java.weapons;

import main.java.calculator.Dice;
import main.java.gameObjects.Thing;

public abstract class Weapon extends Thing {
	
	private int level;
	private Dice damageDie;
	private Dice versatileDamageDie;
	
	private String weaponType;
	private String damageType;
	private int damagePotential;
	private int damageRange;
	private double weaponHealth;
	private String material;
	private int reach;
	
	private boolean isRangeWeapon;
	private boolean isCloseCombatWeapon;
	private boolean isVersatile;
	
	public abstract void deliverDamage();

	public int getDamageRange() {
		return damageRange;
	}

	public void setDamageRange(int damageRange) {
		this.damageRange = damageRange;
	}

	public double getWeaponHealth() {
		return weaponHealth;
	}

	public void setWeaponHealth(double weaponHealth) {
		this.weaponHealth = weaponHealth;
	}

	public int getDamagePotential() {
		return damagePotential;
	}

	public void setDamagePotential(int damagePotential) {
		this.damagePotential = damagePotential;
	}

	public boolean isRangeWeapon() {
		return isRangeWeapon;
	}

	public void setRangeWeapon(boolean isRangeWeapon) {
		this.isRangeWeapon = isRangeWeapon;
	}

	public boolean isCloseCombatWeapon() {
		return isCloseCombatWeapon;
	}

	public void setCloseCombatWeapon(boolean isCloseCombatWeapon) {
		this.isCloseCombatWeapon = isCloseCombatWeapon;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Dice getDamageDie() {
		return damageDie;
	}

	public void setDamageDie(Dice damageDie) {
		this.damageDie = damageDie;
	}

	public String getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}

	public String getDamageType() {
		return damageType;
	}

	public void setDamageType(String damageType) {
		this.damageType = damageType;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public int getReach() {
		return reach;
	}

	public void setReach(int reach) {
		this.reach = reach;
	}

	public Dice getVersatileDamageDie() {
		return versatileDamageDie;
	}

	public void setVersatileDamageDie(Dice versatileDamageDie) {
		this.versatileDamageDie = versatileDamageDie;
	}

	public boolean isVersatile() {
		return isVersatile;
	}

	public void setVersatile(boolean isVersatile) {
		this.isVersatile = isVersatile;
	}
	
	
}
