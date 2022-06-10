package main.java.body;

public class HumanBody extends Body{

	private Head head;
	private Neck neck;
	private Torso torso;
	private Waist waist;
	private Leg leftLeg;
	private Leg rightLeg;
	private Arm leftArm;
	private Arm rightArm;
	
	public HumanBody() {
		head = new Head();
		addBodyPartGroup( head );
		torso = new Torso();
		addBodyPartGroup( torso );
		leftLeg = new Leg();
		addBodyPartGroup( leftLeg );
		rightLeg = new Leg();
		addBodyPartGroup( rightLeg );
		leftArm = new Arm();
		addBodyPartGroup( leftArm );
		rightArm = new Arm();
		addBodyPartGroup( rightArm );
		neck = new Neck();
		addBodyPart( neck );
		waist = new Waist();
		addBodyPart( waist  );
		this.type = "HumanBody";
	}
	
	public Head getHead() {
		return this.head;
	}
	
	public void setHead( Head aHead ) {
		this.head = aHead;
	}
	
	public Torso getTorso() {
		return this.torso;
	}
	
	public void setTorso( Torso aTorso ) {
		this.torso = aTorso;
	}
	
	public Leg getLeftLeg() {
		return this.leftLeg;
	}
	
	public void setLeftLeg( Leg aLeg ) {
		this.leftLeg = aLeg;
	}
	
	public Leg getRightLeg() {
		return this.rightLeg;
	}
	
	public void setRightLeg( Leg aLeg ) {
		this.rightLeg = aLeg;
	}
	
	public Arm getLeftArm() {
		return this.leftArm;
	}
	
	public void setLeftArm( Arm aArm ) {
		this.leftArm = aArm;
	}
	
	public Arm getRightArm() {
		return this.rightArm;
	}
	
	public void setRightArm( Arm aArm ) {
		this.rightArm = aArm;
	}

	public Neck getNeck() {
		return this.neck;
	}
	
	public void setNeck( Neck aNeck ) {
		this.neck = aNeck;
	}
	
	public Waist getWaist() {
		return this.waist;
	}
	
	public void setWaist( Waist aWaist ) {
		this.waist = aWaist;
	}
	
}
