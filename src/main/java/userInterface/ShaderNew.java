package userInterface;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ShaderNew {
	
	private int shaderID;
	private String vertexSource;
	private String fragmentSource;
	private String filePath;
	
	public ShaderNew( String filePath ) {
		
		this.filePath = filePath;
		
		try {
			
			String source = new String( Files.readAllBytes( Paths.get( filePath ) ) );
			String[] splitString = source.split( "(#type)( )+([a-zA-Z]+)" );
			
			// find first pattern after first "#type" 
			int index = source.indexOf( "#type" ) + 6;
			int endOfLine = source.indexOf( "\r\n", index );
			String firstPattern = source.substring( index, endOfLine ).trim();
			
			// find second pattern after second "#type"
			index = source.indexOf( "#type", endOfLine ) + 6;
			endOfLine = source.indexOf( "\r\n", index );
			String secondPattern = source.substring( index, endOfLine ).trim();
			
			if( firstPattern.equals( "vertex" ) ) {
				
				vertexSource = splitString[ 1 ];
				
			} else if( firstPattern.equals( "fragment" ) ) {
				
				fragmentSource = splitString[ 1 ];
				
			} else {
				
				throw new IOException( "Unexpected token '" + firstPattern + "'" );
			}
			
			if( secondPattern.equals( "vertex" ) ) {
				
				vertexSource = splitString[ 2 ];
				
			} else if( secondPattern.equals( "fragment" ) ) {
				
				fragmentSource = splitString[ 2 ];
				
			} else {
				
				throw new IOException( "Unexpected token '" + secondPattern + "'" );
			}
			
			System.out.println(vertexSource);
			System.out.println(fragmentSource);
			
		} catch( IOException e) {
			
			e.printStackTrace();
			assert false : "Error: Could not open file for shader: '" + filePath + "'";
		}
		
	}
	
	public void compile() {
		
	}
	
	public void use() {
		
	}
	
	public void detach() {
		
	}

	public int getShaderID() {
		return shaderID;
	}

	public void setShaderID(int shaderID) {
		this.shaderID = shaderID;
	}

	public String getVertexSource() {
		return vertexSource;
	}

	public void setVertexSource(String vertexSource) {
		this.vertexSource = vertexSource;
	}

	public String getFragmentSource() {
		return fragmentSource;
	}

	public void setFragmentSource(String fragmentSource) {
		this.fragmentSource = fragmentSource;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
