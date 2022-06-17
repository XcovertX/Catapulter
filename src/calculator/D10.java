package calculator;

import java.util.Random;

public class D10 extends Dice {

	@Override
	public int roll() {
		Random rand = new Random();
		return rand.nextInt( 10 );
	}

}
