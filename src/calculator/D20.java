package calculator;

import java.util.Random;

public class D20 extends Dice {

	@Override
	public int roll() {
		Random rand = new Random();
		return rand.nextInt( 20 );
	}

}
