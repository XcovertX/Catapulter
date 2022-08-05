package gameObjects;

public class Key extends Thing {
	
	private int keyShape;
	private int keyCode;

	public Key() {
		this.name = "key";
		this.setKeyShape( 0001 );
		this.setKeyCode( 0001 );
		this.type = "Key";
	}

	public int getKeyShape() {
		return keyShape;
	}

	public void setKeyShape( int keyShape ) {
		this.keyShape = keyShape;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode( int keyCode ) {
		this.keyCode = keyCode;
	}
}
