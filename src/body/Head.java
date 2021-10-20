package body;

public class Head extends BodyPartGroup {
	
	private Face face;
	private Hair hair;
	private Ear leftEar;
	private Ear rightEar;
	
	public Head() {
		this.setFace( new Face() );
		this.setHair( new Hair() );
		this.setLeftEar( new Ear() );
		this.setRightEar( new Ear() );
		this.type = "Head";
	}

	public Face getFace() {
		return face;
	}

	public void setFace(Face face) {
		this.face = face;
	}

	public Hair getHair() {
		return hair;
	}

	public void setHair(Hair hair) {
		this.hair = hair;
	}

	public Ear getLeftEar() {
		return leftEar;
	}

	public void setLeftEar(Ear leftEar) {
		this.leftEar = leftEar;
	}

	public Ear getRightEar() {
		return rightEar;
	}

	public void setRightEar(Ear rightEar) {
		this.rightEar = rightEar;
	}

}
