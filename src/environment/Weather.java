package environment;

import java.util.Random;

import gameObjects.Thing;
import globals.Direction;

public class Weather extends Thing {

	private GameCalendar calendar;
	private Region region;
	
	private int temperature;
	private int humidity;
	
	private Cloud clouds;
	private Rain rain;
	private Wind wind;
	

	public Random rand = new Random();
	
	public Weather( GameCalendar calendar, Region aRegion ) {
		this.setCalendar( calendar );
		this.setRegion( aRegion );
		formulate();
	}
	
	public void formulate() {
		setClouds( new Cloud( region.getElevation(), calendar.getMonth() ) );
		setWind( new Wind( calendar.getMonth() ) );
		setRain( new Rain() );
	}
	
	public void dailyWeatherUpdate() {
	}
	
	public void adjustCurrentWeather() {
		
		// Wind Adjustments
		wind.incrementDurationCounter();
		if( wind.getDurationCounter() >= wind.getDuration() && wind.isPendingTransition() == false ) {
			wind.changeWind( calendar.getMonth() );
		} else if( wind.getDurationCounter() >= wind.getDuration() && wind.isBeginTransition() == true ) {
			wind.beginTransition();
		} else if( wind.getDurationCounter() >= wind.getDuration() && wind.isTransitionInProcess() == true) {
			wind.transition();
		}
		
		
	}
	
	public Direction getCurrentWindDirection() {
		return getWind().getDirection();
	}
	
	public int getCurrentWindIntensity() {
		return getWind().getIntensity();
	}
	
	public GameCalendar getCalendar() {
		return calendar;
	}

	public void setCalendar( GameCalendar calendar ) {
		this.calendar = calendar;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public Cloud getClouds() {
		return clouds;
	}

	public void setClouds(Cloud clouds) {
		this.clouds = clouds;
	}

	public Rain getRain() {
		return rain;
	}

	public void setRain(Rain rain) {
		this.rain = rain;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}
	
	
}
