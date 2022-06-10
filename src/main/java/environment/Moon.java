package main.java.environment;

import java.awt.Color;

public class Moon {
	
	private final int fullMoon = 1;
	private final int waxingGibbous = 2;
	private final int firstQuarter = 3;
	private final int waxingCresent = 4;
	private final int newMoon = 5;
	private final int waningCresent = 6;
	private final int thirdQuarter = 7;
	private final int waningGibbous = 8;
	
	private final int aboveWestHorizon = 1;
	private final int zenith = 2;
	private final int aboveEastHorizon = 3;

	private GameCalendar calendar;
	
	private int currentPhase;
	private int currentPositionMultiple;
	private int currentPositionRatio;
	private int timeMoonVisibleMinutes;
	private int currentPosition;
	private int moonRise;
	private int moonSet;
	
	public Moon( GameCalendar calendar ) {
		
		this.setCalendar( calendar );
		setCurrentPhase();
		setCurrentMoonCycle();
		setCurrentPositionMultiple();
		setCurrentPosition();
	}
	
	public void dailyMoonUpdate() {
		setCurrentPhase();
		adjustMoonRiseSet();
		setCurrentPositionMultiple();
	}

	public GameCalendar getCalendar() {
		return calendar;
	}

	public void setCalendar( GameCalendar calendar ) {
		this.calendar = calendar;
	}
	
	public int getCurrentPhase() {
		return currentPhase;
	}

	public void setCurrentPhase() {
		if( calendar.getDay() == 1) {
			this.currentPhase = fullMoon;
		} else if( calendar.getDay() >= 2 && calendar.getDay() <= 7 ) {
			this.currentPhase = waxingGibbous;
		} else if( calendar.getDay() == 8 ) {
			this.currentPhase = firstQuarter;
		} else if( calendar.getDay() >= 9 && calendar.getDay() <= 15 ) {
			this.currentPhase = waxingCresent;
		} else if( calendar.getDay() == 16) {
			this.currentPhase = newMoon;
		} else if( calendar.getDay() >= 17 && calendar.getDay() <= 22 ) {
			this.currentPhase = waningCresent;
		} else if( calendar.getDay() == 23 ) {
			this.currentPhase = thirdQuarter;
		} else if( calendar.getDay() >= 24 && calendar.getDay() <= 30 ) {
			this.currentPhase = waningGibbous;
		}
	}
	
	public void setCurrentMoonCycle() {
		int currentDayOfYear = calendar.currentDayOfYear();
		
		if(currentDayOfYear > 180) {
			
			int firstHalfOfYear = 180;
			int secondHalfOfYear = currentDayOfYear - 180;
			timeMoonVisibleMinutes = 360 + ( 2 * (firstHalfOfYear - 1 ) ) - ( 2 * ( secondHalfOfYear - 1 ) );
			
		} else {
			
			timeMoonVisibleMinutes = 360 + (2 * ( currentDayOfYear - 1 ) );
		}
		setMoonRise( ( 6 * 60 ) - ( timeMoonVisibleMinutes/2 ) ); 
//		System.out.println( moonRise );
		setMoonSet( ( 6 * 60 ) + ( timeMoonVisibleMinutes/2 ) );
//		System.out.println( moonSet );
	}
	
	public void setCurrentPosition() {
		if( calendar.getCurrentMinuteOfDay() <= moonRise || calendar.getCurrentMinuteOfDay() > moonSet ) {
			this.currentPosition = 0;
		} else {
			this.currentPosition = currentPositionMultiple * ( calendar.getCurrentMinuteOfDay() - moonRise ) ;
		}
	}

	public int getCurrentPosition() {
		return currentPosition;
	}
	
	public void adjustCurrentPosition() {
		if( ( calendar.getHour() * 60 ) + calendar.getMinute() >= moonRise && ( calendar.getHour() * 60 ) + calendar.getMinute() <= moonSet ) {
			this.currentPosition = currentPosition + currentPositionMultiple;
			checkForChanges(); 
		}
	}
	
	public void setCurrentPositionMultiple() {
		
		currentPositionMultiple = timeMoonVisibleMinutes * timeMoonVisibleMinutes;
	}

	public int getFullMoon() {
		return fullMoon;
	}

	public int getWaxingGibbous() {
		return waxingGibbous;
	}

	public int getFirstQuarter() {
		return firstQuarter;
	}

	public int getWaxingCresent() {
		return waxingCresent;
	}

	public int getNewMoon() {
		return newMoon;
	}

	public int getWaningCresent() {
		return waningCresent;
	}

	public int getThirdQuarter() {
		return thirdQuarter;
	}

	public int getWaningGibbous() {
		return waningGibbous;
	}

	public int getMoonRise() {
		return moonRise;
	}

	public void setMoonRise(int moonRise) {
		this.moonRise = moonRise;
	}

	public int getMoonSet() {
		return moonSet;
	}

	public void setMoonSet(int moonSet) {
		this.moonSet = moonSet;
	}
	
	public void adjustMoonRiseSet() {
		if( calendar.getMonth() >= calendar.getJanuary() && calendar.getMonth() <= calendar.getJune() ) {
			this.moonRise--;
			this.moonSet++;
		} else if( calendar.getMonth() >= calendar.getJuly() && calendar.getMonth() <= calendar.getDecember() ) {
			this.moonRise++;
			this.moonSet--;
		}
	}

	public int getCurrentPositionMultiple() {
		return currentPositionMultiple;
	}

	public void setCurrentPositionMultiple(int currentPositionMultiple) {
		this.currentPositionMultiple = currentPositionMultiple;
	}
	
	public void checkForChanges() {
		if( calendar.getCurrentMinuteOfDay() == moonRise ) {
			calendar.currentGame.getUI().printlnColor( "The moon rises above the western horizon", Color.ORANGE );
		}
		if( calendar.getCurrentMinuteOfDay() == moonSet ) {
			calendar.currentGame.getUI().printlnColor( "The moon sets below the eastern horizon", Color.ORANGE );
		}
	}
}
