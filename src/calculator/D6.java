package calculator;

import java.util.Random;

public class D6 extends Dice {
	
	public D6() {
		this.setMin( 1 );
		this.setMax( 6 );
	}

	@Override
	public int roll() {
		Random rand = new Random();
		return rand.nextInt( 6 );
	}

}
