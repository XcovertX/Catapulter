package main.java.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;

import main.java.resources.res.ResourceLoader;

public class MapWriter {

    public MapWriter( Gson aMap, String fileLocation ) {
    	
    	File folderLocation = ResourceLoader.getFilesFolder( fileLocation, aMap + ".json" );
    	
        try {
        	
            FileOutputStream f = new FileOutputStream( folderLocation );
            ObjectOutputStream o = new ObjectOutputStream( f );

            // Write objects to file
            o.writeObject( aMap );

            o.close();
            f.close();

        } catch ( FileNotFoundException e ) {
        	
            System.out.println( "File not found" );
            
        } catch ( IOException e ) {
        	
            System.out.println( "Error initializing stream" );
            
            e.printStackTrace();

		}
    }
}