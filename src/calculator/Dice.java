package calculator;

public abstract class Dice {
	
	private int min;
	private int max;
	
	public abstract int roll();

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
	

}
