package body;

public class Hand extends BodyPartGroup {
	
	private Thumb thumb;
	private Finger pointerFinger;
	private Finger indexFinger;
	private Finger ringFinger;
	private Finger pinky;
	
	
	public Hand() {
		this.setThumb( new Thumb() );
		this.setPointerFinger( new Finger() );
		this.setIndexFinger( new Finger() );
		this.setRingFinger( new Finger() );
		this.setPinky( new Finger() );
		this.type = "Hand";
	}

	public Finger getPointerFinger() {
		return pointerFinger;
	}

	public void setPointerFinger(Finger pointerFinger) {
		this.pointerFinger = pointerFinger;
	}

	public Finger getIndexFinger() {
		return indexFinger;
	}

	public void setIndexFinger(Finger indexFinger) {
		this.indexFinger = indexFinger;
	}

	public Finger getRingFinger() {
		return ringFinger;
	}

	public void setRingFinger(Finger ringFinger) {
		this.ringFinger = ringFinger;
	}

	public Finger getPinky() {
		return pinky;
	}

	public void setPinky(Finger pinky) {
		this.pinky = pinky;
	}

	public Thumb getThumb() {
		return thumb;
	}

	public void setThumb(Thumb thumb) {
		this.thumb = thumb;
	}

}
