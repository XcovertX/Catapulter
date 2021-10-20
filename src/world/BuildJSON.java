package world;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import res.ResourceLoader;

public class BuildJSON {
	
	public BuildJSON() {
		
	}
	
	public void construct( GameWorld world, String location ) {
		
		String worldName = world.getName();
		String path = location + worldName;
		
		File f = new File( path );
		
		boolean success = f.mkdir();
		
		File fileObj = ResourceLoader.getFilesFolder( path, worldName + ".json" );
		
		GsonBuilder gBuilder = new GsonBuilder();
		gBuilder.enableComplexMapKeySerialization();
		
		Gson json = gBuilder.setPrettyPrinting().create();
		
		String j = json.toJson( world ).toString() ;
		
		try( FileWriter file = new FileWriter( fileObj ) ) {
			file.write( j );
			file.flush();
			
		} catch ( FileNotFoundException e ) {
        	
            System.out.println( "File not found" );
            
        } catch ( IOException e ) {
        	
            System.out.println( "Error initializing stream" );
            
            e.printStackTrace();
        }
	}

}
