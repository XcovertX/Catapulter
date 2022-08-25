package verbs;

import java.util.Random;

public abstract class Action {
	
	protected boolean requiresNoun = false;
	protected boolean requiresPreposition = false;
	protected boolean canHaveNoun = false;
	protected boolean canHavePreposition = false;
	protected boolean isDirection = false;
	protected boolean isTalkCommand = false;
	
	public String[] actionDescriptors;
	
	public abstract void run();

	public abstract void run( String thingName );
	
	public abstract void run( String thingName, String preposition );
	
	public abstract void run( String thingNameOne, String preposition, String thingNameTwo );
	
	public String getRandomActionDescriptor() {
		Random rand = new Random();
		int randomIndex = rand.nextInt( actionDescriptors.length );
		String descriptor = actionDescriptors[ randomIndex ];
		return descriptor;
	}
	
	public boolean requiresNoun() {
		return this.requiresNoun;
	}
	
	public void setRequiresNoun( boolean requiresNoun ) {
		this.requiresNoun = requiresNoun;
	}
	
	public boolean canHaveNoun() {
		return this.canHaveNoun;
	}
	
	public void setCanHaveNoun( boolean canHaveNoun ) {
		this.canHaveNoun = canHaveNoun;
	}
	
	public boolean isDirection() {
		return this.isDirection;
	}
	
	public void setIsDirection( boolean isDirection ) {
		this.isDirection = isDirection;
	}

	public boolean requiresPreposition() {
		return requiresPreposition;
	}

	public void setRequiresPreposition( boolean requiresPreposition ) {
		this.requiresPreposition = requiresPreposition;
	}

	public boolean canHavePreposition() {
		return canHavePreposition;
	}

	public void setCanHavePreposition( boolean canHavePreposition ) {
		this.canHavePreposition = canHavePreposition;
	}

	public boolean isTalkCommand() {
		return isTalkCommand;
	}

	public void setTalkCommand( boolean isTalkCommand ) {
		this.isTalkCommand = isTalkCommand;
	}

	public String[] getActionDescriptors() {
		return actionDescriptors;
	}

	public void setActionDescriptors(String[] actionDescriptors) {
		this.actionDescriptors = actionDescriptors;

	}
}
