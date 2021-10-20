package environment;

import java.awt.Color;

public class Sun {
	
	private GameCalendar calendar;
	
	private final int aboveWestHorizon = 1;
	private final int zenith = 2;
	private final int aboveEastHorizon = 3;
	
	private int currentPhase;
	private int currentPositionMultiple;
	private int currentPositionRatio;
	private int timeSunVisibleMinutes;
	private int currentPosition;
	private int sunRise;
	private int sunSet;
	
	public Sun( GameCalendar calendar ) {
		
		this.setCalendar( calendar );
		setCurrentSunCycle();
		setCurrentPositionMultiple();
		setCurrentPosition();
	}
	
	public void dailySunUpdate() {
		adjustSunRiseSet();
		setCurrentPositionMultiple();
	}

	public GameCalendar getCalendar() {
		return calendar;
	}

	public void setCalendar( GameCalendar calendar ) {
		this.calendar = calendar;
	}
	
	public void setCurrentSunCycle() {
		int currentDayOfYear = calendar.currentDayOfYear();
		
		if(currentDayOfYear > 180) {
			
			int firstHalfOfYear = 180;
			int secondHalfOfYear = currentDayOfYear - 180;
			timeSunVisibleMinutes = 360 + ( 2 * (firstHalfOfYear - 1 ) ) - ( 2 * ( secondHalfOfYear - 1 ) );
			
		} else {
			
			timeSunVisibleMinutes = 360 + (2 * ( currentDayOfYear - 1 ) );
		}
		setSunRise( ( 10 * 60 ) - ( timeSunVisibleMinutes/2 ) ); 
//		System.out.println( sunRise );
		setSunSet( ( 10 * 60 ) + ( timeSunVisibleMinutes/2 ) );
//		System.out.println( sunSet );
	}
	
	public void setCurrentPosition() {
		if( calendar.getCurrentMinuteOfDay() <= sunRise || calendar.getCurrentMinuteOfDay() > sunSet ) {
			this.currentPosition = 0;
		} else {
			this.currentPosition = currentPositionMultiple * ( calendar.getCurrentMinuteOfDay() - sunRise ) ;
		}
	}

	public int getCurrentPosition() {
		return currentPosition;
	}
	
	public void adjustCurrentPosition() {
		if( calendar.getCurrentMinuteOfDay() >= sunRise - 60 && calendar.getCurrentMinuteOfDay() <= sunSet + 60 ) {
			this.currentPosition = currentPosition + currentPositionMultiple;
			checkForChanges(); 
		}
	}
	
	public void setCurrentPositionMultiple() {
		
		currentPositionMultiple = timeSunVisibleMinutes * timeSunVisibleMinutes;
	}

	public int getSunRise() {
		return sunRise;
	}

	public void setSunRise(int moonRise) {
		this.sunRise = moonRise;
	}

	public int getMoonSet() {
		return sunSet;
	}

	public void setSunSet(int sunSet) {
		this.sunSet = sunSet;
	}
	
	public void adjustSunRiseSet() {
		if( calendar.getMonth() >= calendar.getJanuary() && calendar.getMonth() <= calendar.getJune() ) {
			this.sunRise--;
			this.sunSet++;
		} else if( calendar.getMonth() >= calendar.getJuly() && calendar.getMonth() <= calendar.getDecember() ) {
			this.sunRise++;
			this.sunSet--;
		}
	}

	public int getCurrentPositionMultiple() {
		return currentPositionMultiple;
	}

	public void setCurrentPositionMultiple(int currentPositionMultiple) {
		this.currentPositionMultiple = currentPositionMultiple;
	}
	
	public void checkForChanges() {
		if( calendar.getCurrentMinuteOfDay() == sunRise - 30 ) {
			calendar.currentGame.getUI().printlnColor( "The black of night begins to fade and gives way to the morning dawn", Color.ORANGE );
		}
		if( calendar.getCurrentMinuteOfDay() == sunRise ) {
			calendar.currentGame.getUI().printlnColor( "The sun rises above the western horizon", Color.ORANGE );
		}
		if( calendar.getCurrentMinuteOfDay() == sunSet ) {
			calendar.currentGame.getUI().printlnColor( "The sun sets below the eastern horizon", Color.ORANGE );
		}
		if( calendar.getCurrentMinuteOfDay() == sunSet + 30 ) {
			calendar.currentGame.getUI().printlnColor( "The last bit of remaining daylight yeilds to the darkness of night", Color.ORANGE );
		}
	}
}
