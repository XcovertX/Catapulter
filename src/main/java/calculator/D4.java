package calculator;

import java.util.Random;

public class D4 extends Dice {
	
	public D4() {
		this.setMin( 1 );
		this.setMax( 4 );
	}

	@Override
	public int roll() {
		Random rand = new Random();
		return rand.nextInt( 4 );
	}

}
