package main.java.gameObjects;

public abstract class Weapon extends Thing {
	
	private int damagePotential;
	private int damageRange;
	private double weaponHealth;
	
	private boolean isRangeWeapon;
	private boolean isCloseCombatWeapon;
	
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
	

}
