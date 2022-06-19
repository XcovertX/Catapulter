package main.java.calculator;

import java.util.Random;

public class D8 extends Dice {
	
	public D8() {
		this.setMin( 1 );
		this.setMax( 8 );
	}

	@Override
	public int roll() {
		Random rand = new Random();
		return rand.nextInt( 8 );
	}

}
