package main.java.body;

public class Face extends BodyPartGroup {

	private Eye leftEye;
	private Eye rightEye;
	private Nose nose;
	private Mouth mouth;
	private Hair beard;
	
	public Face() {
		this.setLeftEye( new Eye() );
		this.setRightEye( new Eye() );
		this.setNose( new Nose() );
		this.setMouth( new Mouth() );
		this.setBeard( new Hair() );
		this.type = "Face";
	}

	public Eye getLeftEye() {
		return leftEye;
	}

	public void setLeftEye(Eye leftEye) {
		this.leftEye = leftEye;
	}

	public Eye getRightEye() {
		return rightEye;
	}

	public void setRightEye(Eye rightEye) {
		this.rightEye = rightEye;
	}

	public Nose getNose() {
		return nose;
	}

	public void setNose(Nose nose) {
		this.nose = nose;
	}

	public Hair getBeard() {
		return beard;
	}

	public void setBeard(Hair beard) {
		this.beard = beard;
	}

	public Mouth getMouth() {
		return mouth;
	}

	public void setMouth(Mouth mouth) {
		this.mouth = mouth;
	}
}
