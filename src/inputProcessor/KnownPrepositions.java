package inputProcessor;

import java.util.ArrayList;
import java.util.List;

public class KnownPrepositions {

	private List< String > prepositions = new ArrayList<>();
	
	public KnownPrepositions() {
		prepositions.add( "am" );
		prepositions.add( "are" );
		prepositions.add( "at" );
		prepositions.add( "be" );
		prepositions.add( "been" );
		prepositions.add( "can" );
		prepositions.add( "canbe" );
		prepositions.add( "for" );
		prepositions.add( "from" );
		prepositions.add( "has" );
		prepositions.add( "hasbeen" );
		prepositions.add( "have" );
		prepositions.add( "havebeen" );
		prepositions.add( "in" );
		prepositions.add( "inside" );
		prepositions.add( "insideof" );
		prepositions.add( "into" );
		prepositions.add( "is" );
		prepositions.add( "may" );
		prepositions.add( "maybe" );
		prepositions.add( "my" );
		prepositions.add( "must" );
		prepositions.add( "mustbe" );
		prepositions.add( "of" );
		prepositions.add( "on" );
		prepositions.add( "onto" );
		prepositions.add( "ontopof" );
		prepositions.add( "out" );
		prepositions.add( "outof" );
		prepositions.add( "outsideof" );
		prepositions.add( "that" );
		prepositions.add( "this" );
		prepositions.add( "thier" );
		prepositions.add( "to" );
		prepositions.add( "towards" );
		prepositions.add( "under" );
		prepositions.add( "underneath" );
		prepositions.add( "was" );
		prepositions.add( "were" );
		prepositions.add( "with" );
		
	}
	
	public boolean check( String s ) {
		return prepositions.contains( s );
	}
}
