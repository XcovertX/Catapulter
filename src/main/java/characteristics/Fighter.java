package characteristics;

import calculator.D10;

public class Fighter extends ActorClass {

	public Fighter() {
		this.setHitDie( new D10() );
	}
}
