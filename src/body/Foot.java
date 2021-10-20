package body;

public class Foot extends BodyPartGroup {
	
	private Toe bigToe;
	private Toe toeTwo;
	private Toe toeThree;
	private Toe toeFour;
	private Toe toeFive;
	private Heel heel;

	public Foot() {
		this.setBigToe( new Toe() );
		this.setToeTwo( new Toe() );
		this.setToeThree( new Toe() );
		this.setToeFour( new Toe() );
		this.setToeFive( new Toe() );
		this.setHeel( new Heel() );
		this.type = "Foot";
	}

	public Toe getBigToe() {
		return bigToe;
	}

	public void setBigToe(Toe bigToe) {
		this.bigToe = bigToe;
	}

	public Toe getToeTwo() {
		return toeTwo;
	}

	public void setToeTwo(Toe toeTwo) {
		this.toeTwo = toeTwo;
	}

	public Toe getToeThree() {
		return toeThree;
	}

	public void setToeThree(Toe toeThree) {
		this.toeThree = toeThree;
	}

	public Toe getToeFour() {
		return toeFour;
	}

	public void setToeFour(Toe toeFour) {
		this.toeFour = toeFour;
	}

	public Toe getToeFive() {
		return toeFive;
	}

	public void setToeFive(Toe toeFive) {
		this.toeFive = toeFive;
	}

	public Heel getHeel() {
		return heel;
	}

	public void setHeel(Heel heel) {
		this.heel = heel;
	}
}
