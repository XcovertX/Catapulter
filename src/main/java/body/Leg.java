package main.java.body;

public class Leg extends BodyPartGroup {

	private Foot leftFoot;
	private Foot rightFoot;
	
	public Leg() {
		this.setLeftFoot( new Foot() );
		this.setRightFoot( new Foot() );
		this.type = "Leg";
	}

	public Foot getLeftFoot() {
		return leftFoot;
	}

	public void setLeftFoot(Foot leftFoot) {
		this.leftFoot = leftFoot;
	}

	public Foot getRightFoot() {
		return rightFoot;
	}

	public void setRightFoot(Foot rightFoot) {
		this.rightFoot = rightFoot;
	}
}
