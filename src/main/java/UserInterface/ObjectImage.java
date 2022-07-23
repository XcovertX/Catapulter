package main.java.UserInterface;

import java.util.ArrayList;

public class ObjectImage {

	public int x, y, w, h;
	public String objGroup, objName, objType;
	public ArrayList<Property> properties;

	public ObjectImage(int x, int y, int w, int h, String objName, String objType, String objGroup, ArrayList<Property> properties) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.objName = objName;
		this.objGroup = objGroup;
		this.objType = objType;
		this.properties = properties;
	}
	
	public ObjectImage(int x, int y, int w, int h, String objName, String objType, String objGroup) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.objName = objName;
		this.objGroup = objGroup;
		this.objType = objType;
	}

}
