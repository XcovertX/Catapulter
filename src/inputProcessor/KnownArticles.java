package inputProcessor;

import java.util.ArrayList;
import java.util.List;

public class KnownArticles {

	private List< String > articles = new ArrayList<>();
	
	public KnownArticles() {
		articles.add( "a" );
		articles.add( "an" );
		articles.add( "the" );
	}
	
	public boolean check( String s ) {
		return articles.contains( s );
	}
}
