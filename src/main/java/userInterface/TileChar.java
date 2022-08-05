package userInterface;

public class TileChar {
	
    private String tileChar;
    private String tileCharColor;
    
	public TileChar() {
		
		this.setChar( " ? " );
	}
	
	public TileChar( String tileChar ) {
		
		this.setChar( tileChar );
	}
	
	public TileChar( String tileChar, String tileCharColor ) {
		
		this.setChar( tileChar );
		this.setCharColor( tileCharColor );
	}
    
	public String getChar() {
		return tileChar;
	}
	public void setChar( String tileChar ) {
		this.tileChar = tileChar;
	}
	public String getTileCharColor() {
		return tileCharColor;
	}
	public void setCharColor( String tileCharColor ) {
		this.tileCharColor = tileCharColor;
	}
}
