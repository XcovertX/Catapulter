package main.java.environment;

import java.util.TimerTask;

import main.java.game.Game;

public class GameCalendar extends Time {
	
	public Game currentGame;

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private long tick;
	
	private Sun sun;
	private Moon moon;
	private Weather weather;
	private Region region;
	
	private final int yearInMonths = 12;
	private final int monthInDays = 30;
	private final int dayInHours = 20;
	private final int hourInMinutes = 60;
	
	private final int january = 1;
	private final int february = 2;
	private final int march = 3;
	private final int april = 4;
	private final int may = 5;
	private final int june = 6;
	private final int july = 7;
	private final int august = 8;
	private final int septemper = 9;
	private final int october = 10;
	private final int november = 11;
	private final int december = 12;
	
	public GameCalendar( Game currentGame ) {
		
		this.currentGame = currentGame;
		
		this.year = 13985;
		this.month = 10;
		this.day = 29;
		this.hour = 0;
		this.minute = 0;
		this.tick = 0;
		
		//temporary 
		this.region = new Region();
		
		this.setSun( new Sun( this ) );
		this.setMoon( new Moon( this ) );
		this.setWeather( new Weather( this, region ) );

		controller( 0, 1000 );
	}
	
	public GameCalendar( int aYear, int aMonth, int aDay, int aHour, int aMinute ) {
		
		this.year = aYear;
		this.month = aMonth;
		this.day = aDay;
		this.hour = aHour;
		this.minute = aMinute;
		this.tick = 0;
	
		controller( 0, 1000 );
	}
	
	private void controller( int delay, int freq ) {
		
		TimerTask instructions = new tasks();
		Time.getTimer().schedule( instructions, delay, freq );
	}
	
	private class tasks extends TimerTask {

		@Override
		public void run() {
			
			incrementMinute();
		}
	}
	
	public void incrementMinute() {
		
		if( this.minute < hourInMinutes - 1 ) {
			this.minute++;
			this.tick++;
			sun.adjustCurrentPosition();
			moon.adjustCurrentPosition();
			weather.adjustCurrentWeather();
//			printWind();
		} else {
			this.minute = 0;
			incrementHour();
			sun.adjustCurrentPosition();
			moon.adjustCurrentPosition();
			weather.adjustCurrentWeather();
//			printWind();
		}
	}
	
	public void incrementHour() {
		
		if( this.hour < dayInHours - 1) {
			this.hour++;
		} else {
			this.hour = 0;
			incrementDay();
		}
	}
	
	public void incrementDay() {
		
		if( this.day < monthInDays ) {
			this.day++;
			sun.dailySunUpdate();
			moon.dailyMoonUpdate();
			weather.dailyWeatherUpdate();
		} else {
			this.day = 1;
			incrementMonth();
			sun.dailySunUpdate();
			moon.dailyMoonUpdate();
			weather.dailyWeatherUpdate();
		}
	}
	
	public void incrementMonth() {
		
		if( this.month < yearInMonths ) {
			this.month++;
		} else {
			this.month = 1;
			incrementYear();
		}
	}
	
	public void incrementYear() {
		
		this.year++;
	}
	
	public void printClock() {
		System.out.println(year + " : " + month + " : " + day + " : " + hour + " : " + minute);
	}
	
	public void printWind() {
		System.out.println("Wind Direction: " + weather.getWind().getDirection() + " :: Wind Intensity: " + weather.getWind().getIntensity() );
	}
	
	public int[] getDate() {
		
		int[] date = { year, month, day, hour, minute };
		return date;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public int getMonth() {
		return this.month;
	}
	
	public int getDay() {
		return this.day;
	}
	
	public int getHour() {
		return this.hour;
	}
	
	public int getMinute() {
		return this.minute;
	}
	
	public String getTime() {
		String currentTime = String.valueOf( getYear() ) + ":";
		currentTime += String.valueOf( getMonth() ) + ":";
		currentTime += String.valueOf( getDay() ) + ":";
		currentTime += String.valueOf( getHour() ) + ":";
		currentTime += String.valueOf( getMinute() ) + " ::: ";
		return currentTime;
	}
	
	public Sun getSun() {
		return sun;
	}

	public void setSun( Sun sun ) {
		this.sun = sun;
	}

	public Moon getMoon() {
		return moon;
	}

	public void setMoon(Moon moon) {
		this.moon = moon;
	}

	public int getJanuary() {
		return january;
	}

	public int getFebruary() {
		return february;
	}

	public int getMarch() {
		return march;
	}

	public int getApril() {
		return april;
	}

	public int getMay() {
		return may;
	}

	public int getJune() {
		return june;
	}

	public int getJuly() {
		return july;
	}

	public int getAugust() {
		return august;
	}

	public int getSeptemper() {
		return septemper;
	}

	public int getOctober() {
		return october;
	}

	public int getNovember() {
		return november;
	}

	public int getDecember() {
		return december;
	}
	
	public int getCurrentMinuteOfDay() {
		return ( getHour() * this.hourInMinutes ) + getMinute();
	}

	public int currentDayOfYear() {
		return getMonth() * this.monthInDays + getDay();
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public long getTick() {
		return tick;
	}

	public void setTick(long tick) {
		this.tick = tick;
	}
}
