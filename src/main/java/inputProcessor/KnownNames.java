package inputProcessor;

import java.util.ArrayList;
import java.util.List;

public class KnownNames {
	
private List< String > names = new ArrayList<>();
	
	public KnownNames() {

		names.add( "Ring of Might" );

	}
	
	public boolean check( String s ) {
		return names.contains( s );
	}
}
