package characteristics;

import java.util.HashMap;
import java.util.Map;

public class ActorAdvancement {
	private Map< Integer, Integer > nextXP = new HashMap<>();
	private Map< Integer, Integer > proficiency = new HashMap<>();
	
	public ActorAdvancement() {
		nextXP.put( 1,       0 );
		nextXP.put( 2,     300 );
		nextXP.put( 3,     900 );
		nextXP.put( 4,    2700 );
		nextXP.put( 5,    6500 );
		nextXP.put( 6,   14000 );
		nextXP.put( 7,   23000 );
		nextXP.put( 8,   34000 );
		nextXP.put( 9,   48000 );
		nextXP.put( 10,  64000 );
		nextXP.put( 11,  85000 );
		nextXP.put( 12, 100000 );
		nextXP.put( 13, 120000 );
		nextXP.put( 14, 140000 );
		nextXP.put( 15, 165000 );
		nextXP.put( 16, 195000 );
		nextXP.put( 17, 225000 );
		nextXP.put( 18, 265000 );
		nextXP.put( 19, 305000 );
		nextXP.put( 20, 355000 );	
		
		proficiency.put( 1,  2 );
		proficiency.put( 2,  2 );
		proficiency.put( 3,  2 );
		proficiency.put( 4,  2 );
		proficiency.put( 5,  3 );
		proficiency.put( 6,  3 );
		proficiency.put( 7,  3 );
		proficiency.put( 8,  3 );
		proficiency.put( 9,  4 );
		proficiency.put( 10, 4 );
		proficiency.put( 11, 4 );
		proficiency.put( 12, 4 );
		proficiency.put( 13, 5 );
		proficiency.put( 14, 5 );
		proficiency.put( 15, 5 );
		proficiency.put( 16, 5 );
		proficiency.put( 17, 6 );
		proficiency.put( 18, 6 );
		proficiency.put( 19, 6 );
		proficiency.put( 20, 6 );

	}
	
	public Integer getNextXP( Integer level ) {
		
		try {
			
			return nextXP.get( level );
			
		} catch( IllegalArgumentException e ) {
			
			return null;
		} 
	}
	
	public Integer getProficiency( Integer level ) {
		
		try {
			
			return proficiency.get( level );
			
		} catch( IllegalArgumentException e ) {
			
			return null;
		} 
	}
}
