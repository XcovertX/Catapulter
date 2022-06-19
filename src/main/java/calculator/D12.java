package main.java.calculator;

import java.util.Random;

public class D12 extends Dice {
	
	public D12() {
		this.setMin( 1 );
		this.setMax( 12 );
	}

	@Override
	public int roll() {
		Random rand = new Random();
		return rand.nextInt( 12 );
	}

}
