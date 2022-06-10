package main.java.element;

import main.java.gameObjects.Thing;

public abstract class Element extends Thing {
	
	private boolean isLiquid;
	private boolean isSolid;
	private boolean isGas;
	
	public Element() {
		this.type = "Element";
	}

	public boolean isLiquid() {
		return isLiquid;
	}

	public void setLiquid(boolean isLiquid) {
		this.isLiquid = isLiquid;
	}

	public boolean isSolid() {
		return isSolid;
	}

	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}

	public boolean isGas() {
		return isGas;
	}

	public void setGas(boolean isGas) {
		this.isGas = isGas;
	}
	
	

}
