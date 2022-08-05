package verbs;

public class Ask extends Action {
	
	public Ask() {
		this.setTalkCommand( true );
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
	public void run(String thingNameOne, String preposition, String thingNameTwo) {
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

	@Override
	public boolean isDirection() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsDirection(boolean isDirection) {
		// TODO Auto-generated method stub
		
	}
}
