package gameObjects;

public class Food extends Thing {
	
	private boolean hasPortions;
	private int portions;
	private int portionsConsumed;
	private int sustenance;
	
	public Food() {
		this.setEatable( true );
		this.type = "food";
	}

	public int getPortions() {
		return portions;
	}

	public void setPortions(int portions) {
		this.portions = portions;
	}

	public int getSustenance() {
		return sustenance;
	}

	public void setSustenance(int sustenance) {
		this.sustenance = sustenance;
	}

	public int getPortionsConsumed() {
		return portionsConsumed;
	}

	public void setPortionsConsumed(int portionsConsumed) {
		this.portionsConsumed = portionsConsumed;
	}

	public boolean hasPortions() {
		return hasPortions;
	}

	public void setHasPortions(boolean hasPortions) {
		this.hasPortions = hasPortions;
	}
	
	public void incrementPortionsConsumed() {
		this.portionsConsumed++;
	}

}
