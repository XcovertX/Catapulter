package weapons;

import gameObjects.Thing;

public abstract class Ammunition extends Thing {
	
	private String ammoType;

	public String getAmmoType() {
		return ammoType;
	}

	public void setAmmoType(String ammoType) {
		this.ammoType = ammoType;
	}

}
