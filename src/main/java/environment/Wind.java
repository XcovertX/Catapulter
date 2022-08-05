package environment;

import java.util.Random;

import globals.Direction;

public class Wind {
	//current wind direction, intensity and duration
	private Direction direction = Direction.SOUTHWEST;
	private int intensity = 30;
	private int duration = 60;
	private int durationCounter = 0;
	
	//next wind direction, intensity, and duration 
	private Direction nextDirection;;
	private int nextIntensity;
	private int nextDuration;
	private int nextStartTime;
	
	private int transitionLength;
	private int transitionIntervalLength;
	private int transitionTime;
	private Direction[] directionTransitionSteps;
	private int transitionInterval;
	private boolean pendingTransition = false;
	private boolean beginTransition = false;
	private boolean transitionInProcess = false;
	private int transitionStepCounter = 0;
	
	private int[] intensityTransitionSteps;
	
	public Random rand = new Random();
	
	public Wind( int month ) {
	}
	
	public void changeWind( int month ) {
		determineDirection( month );
		determineIntensity( month );
		determineTransitionTime();
		determineTransitionIntervalLength();
		determineTransitionSteps( direction, nextDirection );
		determineIntensityTransitionSteps();
		determineMinimumDuration();
		setPendingTransition( true );
		setBeginTransition( true );
	}

	//ensures the wind direction and intensity remain for MIN 300MIN , MAX 2400MIN
	private void determineMinimumDuration() {
		setNextDuration( rand.nextInt( 2100 ) + 300 );
	}

	public void determineDirection( int month ) {
		int chance = rand.nextInt( 100 );
		if( month >= 1 && month <= 3 ) {
			if( chance < 50) {
				setNextDirection( Direction.SOUTHWEST );
			} else if( chance >= 50 && chance < 75 ) {
				setNextDirection( Direction.WEST );
			} else if( chance >= 75 && chance < 100 ) {
				setNextDirection( Direction.SOUTH );
			}
		} else if( month == 4 ) {
			if( chance < 50) {
				setNextDirection( Direction.WEST );
			} else if( chance >= 50 ) {
				setNextDirection( Direction.NORTH );
			}
		}else if( month >= 5 && month <= 8 ) {
			if( chance < 50) {
				setNextDirection( Direction.NORTHEAST );
			} else if( chance >= 50 && chance < 75 ) {
				setNextDirection( Direction.EAST );
			} else if( chance >= 75 && chance < 100 ) {
				setNextDirection( Direction.NORTH );
			}
		} else if( month == 9 ) {
			if( chance < 50) {
				setNextDirection( Direction.EAST );
			} else if( chance >= 50 ) {
				setNextDirection( Direction.SOUTH );
			}
		} else if( month >= 10 && month <= 12 ) {
			if( chance < 50) {
				setNextDirection( Direction.SOUTHWEST );
			} else if( chance >= 50 && chance < 75 ) {
				setNextDirection( Direction.WEST );
			} else if( chance >= 75 && chance < 100 ) {
				setNextDirection( Direction.SOUTH );
			}
		}
	}
	
	public void determineIntensity( int month ) {
		int chance = rand.nextInt( 100 );
		if( month >= 1 && month <= 6 ) {
			if( chance < 30) {
				setNextIntensity( rand.nextInt(5) );
			} else if( chance >= 30 &&  chance < 90 ) {
				setNextIntensity( rand.nextInt( 15 ) );
			} else if( chance >= 90 &&  chance < 99 ) {
				setNextIntensity( rand.nextInt( 15 ) );
			} else if( chance == 99 ) {
				setNextIntensity( rand.nextInt( 60 ) );
			}
		} else if( month >= 7 && month <= 12 ) {
			if( chance < 50) {
				setNextIntensity( rand.nextInt(5) );
			} else if( chance >= 50 &&  chance < 75 ) {
				setNextIntensity( rand.nextInt( 25 ) );
			} else if( chance >= 75 &&  chance < 99 ) {
				setNextIntensity( rand.nextInt( 30 ) );
			} else if( chance == 99 ) {
				setNextIntensity( rand.nextInt( 90 ) );
			}
		}
	}
	
	//determines wind transition path and executes it smoothly
	public void determineTransitionSteps( Direction start, Direction end ) {
		Direction[] allDirections = { Direction.NORTH, Direction.NORTHEAST, Direction.EAST, Direction.SOUTHEAST, 
				Direction.SOUTH, Direction.SOUTHWEST, Direction.WEST, Direction.NORTHWEST };
		for( int i = 0; i < allDirections.length; i++ ) {
			if( start == allDirections[ i ] ) {
				for( int j = 0; j < allDirections.length; j++ ) {
					if( end == allDirections[ j ] ) {
						Direction[] directionSteps;
						int numberOfSteps;
						if( i == j ) {
							numberOfSteps = 0;
							directionSteps = new Direction[ numberOfSteps + 1 ];
							directionSteps[ numberOfSteps ] = end;
							setDirectionTransitionSteps(  directionSteps );
							//call smooth transition of intensity
						} else if ( i < j) {
							numberOfSteps = j - i;
							directionSteps = new Direction[ numberOfSteps + 1 ];
							int h = j;
							for( int k = numberOfSteps; k >= 0; k-- ) {
								directionSteps[ k ] = allDirections[ h ];
								h--;
							}
							setDirectionTransitionSteps( directionSteps );
						} else if ( i > j ) {
							numberOfSteps = i - j;
							directionSteps = new Direction[ numberOfSteps + 1 ];
							int h = i;
							for( int k = numberOfSteps; k >= 0; k-- ) {
								directionSteps[ k ] = allDirections[ h ];
								h--;
							}
							setDirectionTransitionSteps( directionSteps );
						}
					}
				}
			}
		}
	}
	
	public void determineIntensityTransitionSteps() {
		
		int[] intensityTransitionSteps = new int[ getDirectionTransitionSteps().length ];
		if( intensity == nextIntensity ) {
			for( int i = 0; i < intensityTransitionSteps.length ; i++ ) {
				intensityTransitionSteps[ i ] = nextIntensity;
			}
			setIntensityTransitionSteps( intensityTransitionSteps );
		} else if( intensity < nextIntensity ) {
				int transitionInterval = Math.round( ( nextIntensity - intensity ) / intensityTransitionSteps.length );
				for( int i = 0; i < intensityTransitionSteps.length ; i++ ) {
					intensityTransitionSteps[ i ] = intensity + transitionInterval;
				}
				setIntensityTransitionSteps( intensityTransitionSteps );
		} else if( intensity > nextIntensity ) {
			int transitionInterval = Math.round( ( intensity - nextIntensity ) / intensityTransitionSteps.length );
			for( int i = 0; i < intensityTransitionSteps.length ; i++ ) {
					intensityTransitionSteps[ i ] = intensity - transitionInterval;
				}
				setIntensityTransitionSteps( intensityTransitionSteps );
		}
	}
	
	//ensures wind direction transition time is in between is in between 180MIN and 600MIN
	public void determineTransitionTime() {
		setTransitionTime( rand.nextInt( 420 ) + 180 );
		setDuration( this.duration + this.transitionTime );
	}
	
	//ensures transition interval length is in between 10MIN to 20MIN
	public void determineTransitionIntervalLength() {
		setTransitionIntervalLength( rand.nextInt( 10 ) + 10 );
	}
	
	public void beginTransition() {
		setDuration( this.duration + this.transitionIntervalLength );
		setTransitionInProcess( true );
		setBeginTransition( false );
		setDirection( getDirectionTransitionSteps()[ transitionStepCounter ] );
		setIntensity( getIntensityTransitionSteps()[ transitionStepCounter ] );
	}
	
	public void transition() {
		if( directionTransitionSteps[ transitionStepCounter ] == nextDirection ) {
			setDirection( nextDirection );
			setIntensity( nextIntensity );
			setDuration( nextDuration );
			resetDurationCounter();
			resetTransitionStepCounter();
			setTransitionInProcess( false );
			setPendingTransition( false );
		} else {
			transitionStepCounter++;
			setDirection( getDirectionTransitionSteps()[ transitionStepCounter ] );
			setIntensity( getIntensityTransitionSteps()[ transitionStepCounter ] );
			setDuration( this.duration + this.transitionIntervalLength );
		}
	}
	
	public void setDirection( Direction aDirection ) {
		this.direction = aDirection;
	}

	public int getIntensity() {
		return intensity;
	}

	public void setIntensity( int intensity ) {
		this.intensity = intensity;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Direction getNextDirection() {
		return nextDirection;
	}

	public void setNextDirection(Direction nextDirection) {
		this.nextDirection = nextDirection;
	}

	public int getNextIntensity() {
		return nextIntensity;
	}

	public void setNextIntensity(int nextIntensity) {
		this.nextIntensity = nextIntensity;
	}

	public int getNextDuration() {
		return nextDuration;
	}

	public void setNextDuration(int nextDuration) {
		this.nextDuration = nextDuration;
	}

	public int getTransitionLength() {
		return transitionLength;
	}

	public void setTransitionLength(int transitionLength) {
		this.transitionLength = transitionLength;
	}

	public int getTransitionTime() {
		return transitionTime;
	}

	public void setTransitionTime(int transtionTime) {
		this.transitionTime = transtionTime;
	}

	public Direction[] getDirectionTransitionSteps() {
		return directionTransitionSteps;
	}

	public void setDirectionTransitionSteps(Direction[] transitionSteps) {
		this.directionTransitionSteps = transitionSteps;
	}
	
	public void printTransitionSteps() {
		Direction[] steps = getDirectionTransitionSteps();
		int stepsLength = steps.length;
		for( int i = 0; i < stepsLength; i++ ) {
			System.out.println( directionTransitionSteps[ i ] );
		}
	}
	
	public Direction getDirection() {
		return direction;
	}

	public int getNextStartTime() {
		return nextStartTime;
	}

	public void setNextStartTime(int nextStartTime) {
		this.nextStartTime = nextStartTime;
	}

	public int getDurationCounter() {
		return durationCounter;
	}

	public void resetDurationCounter() {
		this.durationCounter = 0;
	}
	
	public void incrementDurationCounter() {
		this.durationCounter++;
	}

	public boolean isPendingTransition() {
		return pendingTransition;
	}

	public void setPendingTransition(boolean pendingTransition) {
		this.pendingTransition = pendingTransition;
	}

	public boolean isTransitionInProcess() {
		return transitionInProcess;
	}

	public void setTransitionInProcess(boolean transitionInProcess) {
		this.transitionInProcess = transitionInProcess;
	}

	public int getTransitionInterval() {
		return transitionInterval;
	}

	public void setTransitionInterval(int transitionInterval) {
		this.transitionInterval = transitionInterval;
	}

	public int getTransitionIntervalLength() {
		return transitionIntervalLength;
	}

	public void setTransitionIntervalLength(int transitionIntervalLength) {
		this.transitionIntervalLength = transitionIntervalLength;
	}

	public int getTransitionStepCounter() {
		return transitionStepCounter;
	}

	public void resetTransitionStepCounter() {
		this.transitionStepCounter = 0;
	}

	public boolean isBeginTransition() {
		return beginTransition;
	}

	public void setBeginTransition(boolean beginTransition) {
		this.beginTransition = beginTransition;
	}

	public int[] getIntensityTransitionSteps() {
		return intensityTransitionSteps;
	}

	public void setIntensityTransitionSteps(int[] intensityTransitionSteps) {
		this.intensityTransitionSteps = intensityTransitionSteps;
	}

}
