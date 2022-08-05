package body;

public class Arm extends BodyPartGroup {
	
	private Shoulder shoulder;
	private Wrist wrist;
	private Hand hand;
	
	public Arm() {
		this.setShoulder( new Shoulder() );
		this.setWrist( new Wrist() );
		this.setHand( new Hand() );
		this.type = "Arm";
	}

	public Shoulder getShoulder() {
		return shoulder;
	}

	public void setShoulder(Shoulder shoulder) {
		this.shoulder = shoulder;
	}

	public Wrist getWrist() {
		return wrist;
	}

	public void setWrist(Wrist wrist) {
		this.wrist = wrist;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}
}
