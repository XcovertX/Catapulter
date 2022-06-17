package calculator;

import java.util.Random;

public class D12 extends Dice {

	@Override
	public int roll() {
		Random rand = new Random();
		return rand.nextInt( 12 );
	}

}
