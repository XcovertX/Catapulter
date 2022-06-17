package main.java.characteristics;

public class ActorPersonality {
	
	// alignment: evil -10 to 10 good
	private int alignment;
	// order: chaotic -10 to 10 lawful
	private int order;
	
	public int getAlignment() {
		
		return alignment;
	}
	public void setAlignment( int alignment ) {
		
		this.alignment = alignment;
	}
	public int getOrder() {
		
		return order;
	}
	public void setOrder( int order ) {
		
		this.order = order;
	}

}
