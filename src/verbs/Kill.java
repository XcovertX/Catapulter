package verbs;

public class Kill extends Action {
	
	
	public Kill() {
		setRequiresNoun( true );
		setCanHaveNoun( true );
		setIsDirection( false );
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(String thing) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void run(String thingName, String preposition) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void run( String thingNameOne, String preposition, String thingNameTwo ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean requiresNoun() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRequiresNoun(boolean requiresNoun) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canHaveNoun() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCanHaveNoun(boolean requiresNoun) {
		// TODO Auto-generated method stub
		
	}

}
