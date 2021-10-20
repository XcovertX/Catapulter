package wearableObjects;

public class Ring extends WearableThing {

	public Ring() {
		this.setWearable( true );
		this.setDonned( false );
		this.wearableLocations.add( "Finger" );
		this.wearableLocations.add( "Thumb" );
		this.type = "Ring";
	}
}
