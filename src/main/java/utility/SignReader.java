package main.java.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import main.java.resources.res.ResourceLoader;

public class SignReader {
	
	private String text = "";
	
	public SignReader(String fName, String dName) {
		
		try {
			
			File fileObj = ResourceLoader.getFilesFolder(fName, dName);
			Scanner myReader = new Scanner(fileObj);
			while (myReader.hasNextLine()) {
				text += "\n" + myReader.nextLine();
			}
			
			myReader.close();
			
		} catch (FileNotFoundException e) {
			
			System.out.println("File not found");
			e.printStackTrace();
			
		}
	}
	
	public String getText() {
		return text;
		
	}
}
