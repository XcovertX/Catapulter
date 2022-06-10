package main.java.body;

public class Hair extends BodyPart {
	
	//growth
	//type of hair straight, curly ect
	
	private String style;
	
	public Hair(){
		this.setStyle( "" );
		this.type = "Hair";
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}
