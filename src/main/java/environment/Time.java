package main.java.environment;

import java.util.Timer;

public class Time {
	
	private static Timer timer;
	
	public Time() {
		
		this.setTimer( new Timer() );
	}

	public static Timer getTimer() {
		
		return timer;
	}

	public void setTimer( Timer timer ) {
		
		Time.timer = timer;
	}

}
