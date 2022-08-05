package weapons;

import actor.Actor;

public abstract class Gun extends FiredWeapon {
	
	private int ammunitionRemaining;
	private Ammunition ammo;
	
	public Gun() {
		this.setWieldable( true );
		this.type = "Gun";
	}

	@Override
	public void deliverDamage() {
		// TODO Auto-generated method stub
		
	}
	
	public void shoot( Actor actor ) {
		
	}

	public int getAmmunitionRemaining() {
		return ammunitionRemaining;
	}

	public void setAmmunitionRemaining(int ammunitionRemaining) {
		this.ammunitionRemaining = ammunitionRemaining;
	}

	public Ammunition getAmmo() {
		return ammo;
	}

	public void setAmmo(Ammunition ammo) {
		this.ammo = ammo;
	}

}
