package main.java.calculator;

import java.util.Random;

public class D20 extends Dice {
	
	public D20() {
		this.setMin( 1 );
		this.setMax( 20 );
	}

	@Override
	public int roll() {
		Random rand = new Random();
		return rand.nextInt( 20 );
	}

}
