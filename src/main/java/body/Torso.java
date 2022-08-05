package body;

public class Torso extends BodyPartGroup {
	
	private Chest chest;
	private Back back;
	private Abdominal abs;
	
	public Torso() {
		this.setChest( new Chest() );
		this.setBack( new Back() );
		this.setAbs( new Abdominal() );
		this.type = "Torso";
	}

	public Chest getChest() {
		return chest;
	}

	public void setChest(Chest chest) {
		this.chest = chest;
	}

	public Back getBack() {
		return back;
	}

	public void setBack(Back back) {
		this.back = back;
	}

	public Abdominal getAbs() {
		return abs;
	}

	public void setAbs(Abdominal abs) {
		this.abs = abs;
	}

}
