package calculator;

import java.util.Random;

public class D10 extends Dice {
	
	public D10() {
		this.setMin( 1 );
		this.setMax( 10 );
	}

	@Override
	public int roll() {
		Random rand = new Random();
		return rand.nextInt( 10 );
	}

}
