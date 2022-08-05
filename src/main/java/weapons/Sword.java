package weapons;

import damage.Slash;

public class Sword extends Blade {
	
	public Sword() {
		this.type = "Sword";
		this.setDamage( new Slash() );
		
	}
}
